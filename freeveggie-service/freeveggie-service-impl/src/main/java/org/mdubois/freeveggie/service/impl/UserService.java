package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.dao.api.*;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.CriteriaUtils;
import org.mdubois.freeveggie.order.UserOrderColumn;
import org.mdubois.freeveggie.service.api.IUserService;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

// </editor-fold>
/**
 *
 * @author Mickael Dubois
 */
public class UserService implements IUserService {

    // <editor-fold defaultstate="collapsed" desc="DAO Resource's">
    /**
     * {@link IUserDAO}
     */
    @Inject
    private IUserDAO userDAO;
    /**
     * {@link IUserPartialDAO}
     */
    @Inject
    private IUserPartialDAO userPartialDAO;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Converter Resource's">
    /**
     * {@link Converter<UserMsg, UserBO>}
     */
    @Inject
    private Converter<UserMsg, UserBO> userBOConverter;
    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOConverter;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Contants">
    /**
     * Criteria user status equal validated
     */
    private static final QueryCriteria<UserCriteriaColumn> CRITERIA_USER_STATUS_EQUAL_VALIDATED = new QueryCriteria<UserCriteriaColumn>(
            UserCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
            UserStatus.VALIDED);

    // </editor-fold>
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changeRole(Long pUserId, UserRole pUserRole)
            throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            userBO.setRole(pUserRole);
            userDAO.save(userBO);
            return true;
        } else {
            throw new BusinessException("Try to update an unknown user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean upgrade(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (userBO.getRole().equals(UserRole.USER)) {
                userBO.setRole(UserRole.MANAGER);
            } else if (userBO.getRole().equals(UserRole.ADMIN)) {
                userBO.setRole(UserRole.SUPERADMIN);
            } else {
                throw new BusinessException(
                        "The user have a role that can't be upgraded. Only user and admin can be upgraded");
            }
            userDAO.save(userBO);
            return true;
        } else {
            throw new BusinessException("Try to update an unknown user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean downgrade(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (userBO.getRole().equals(UserRole.MANAGER)) {
                userBO.setRole(UserRole.USER);
            } else if (userBO.getRole().equals(UserRole.SUPERADMIN)) {
                userBO.setRole(UserRole.ADMIN);
            } else {
                throw new BusinessException(
                        "The user have a role that can't be downgrade. Only manager and superadmin can be downgrade");
            }
            userDAO.save(userBO);
            return true;
        } else {
            throw new BusinessException("Try to update an unknown user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blacklist(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.VALIDED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.BLACKLISTED);
                userDAO.update(userBO);
            } else if (!UserStatus.BLACKLISTED.equals(userBO.getStatus())) {
                throw new BusinessException(
                        "Can only blacklist a validated user");
            }
        } else {
            throw new BusinessException("Try to blacklikst an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unblacklist(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.BLACKLISTED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.VALIDED);
                userDAO.update(userBO);
            } else if (!UserStatus.VALIDED.equals(userBO.getStatus())) {
                throw new BusinessException(
                        "Can only unblacklist a blacklisted user");
            }
        } else {
            throw new BusinessException(
                    "Try to unblacklikst an inexisting user");
        }
        return true;
    }

    @Override
    public boolean validate(String pCode) throws BusinessException {String decryptLogin;
        UserBO userBO = userDAO.getUserByUUID(pCode);
        if (userBO == null) {
            throw new BusinessException("Try to validated an inexisting user");
        } else {
            if (UserStatus.NEW.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.VALIDED);
                userDAO.update(userBO);
            } else if (!UserStatus.NEW.equals(userBO.getStatus())) {
                throw new BusinessException(
                        "Only new user can be validated");
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Long pUserId, UserMsg pUserMsg) throws BusinessException {
        UserBO userBO = userDAO.get(pUserMsg.getId());
        if (userBO != null) {
            userBO.setFirstname(pUserMsg.getFirstname());
            userBO.setLastname(pUserMsg.getLastname());
            userBO.setEmail(pUserMsg.getEmail());
            userDAO.update(userBO);

        } else {
            throw new BusinessException("Try to validated an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.VALIDED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.DELETED);
                userDAO.update(userBO);
            } else if (!UserStatus.VALIDED.equals(userBO.getStatus())) {
                throw new BusinessException("Can only delete a validated user");
            }
        } else {
            throw new BusinessException("Try to delete an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean undelete(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.DELETED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.VALIDED);
                userDAO.update(userBO);
            } else if (!UserStatus.DELETED.equals(userBO.getStatus())) {
                throw new BusinessException("Can only undelete a deleted user");
            }
        } else {
            throw new BusinessException("Try to undelete an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.VALIDED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.ARCHIVED);
                userDAO.update(userBO);
            } else if (!UserStatus.ARCHIVED.equals(userBO.getStatus())) {
                throw new BusinessException("Can only archive a validated user");
            }
        } else {
            throw new BusinessException("Try to archive an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchive(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO != null) {
            if (UserStatus.ARCHIVED.equals(userBO.getStatus())) {
                userBO.setStatus(UserStatus.VALIDED);
                userDAO.update(userBO);
            } else if (!UserStatus.VALIDED.equals(userBO.getStatus())) {
                throw new BusinessException("Can only archive a deleted user");
            }
        } else {
            throw new BusinessException("Try to unarchive an inexisting user");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg getUserById(Long pUserId) throws BusinessException {
        UserBO userBO = userDAO.get(pUserId);
        if (userBO == null) {
            throw new BusinessException("Try to get an inexisting user");
        }
        return userBOConverter.convert(userBO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PartialUserMsg getUserPartialById(Long pUserId)
            throws BusinessException {
        PartialUserBO userBO = userPartialDAO.get(pUserId);
        if (userBO == null) {
            throw new BusinessException("Try to get an inexisting user");
        }
        return partialUserBOConverter.convert(userBO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByCity(
            Integer pRefCityId,
            Integer pRefProductId,
            TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userBOConverter.convert(userDAO.getUserByCityAndProduct(
                pRefCityId, pRefProductId,
                getCleanCriteriaUserBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByRegion(
            Integer pRefRegionId,
            Integer pRefProductId,
            TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        List<UserBO> users = userDAO.getUserByRegionAndProduct(pRefRegionId,
                pRefProductId, getCleanCriteriaUserBO(pTechnicalInformation));
        return userBOConverter.convert(users);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByState(
            Integer pRefStateId,
            Integer pRefProductId,
            TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userBOConverter.convert(userDAO.getUserByStateAndProduct(
                pRefStateId, pRefProductId,
                getCleanCriteriaUserBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByCountry(
            Integer pRefCountryId,
            Integer pRefProductId,
            TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userBOConverter.convert(userDAO.getUserByCountryAndProduct(
                pRefCountryId, pRefProductId,
                getCleanCriteriaUserBO(pTechnicalInformation)));
    }

    // <editor-fold defaultstate="collapsed" desc="Private method's">
    private TechnicalInformation<UserCriteriaColumn, UserOrderColumn> getCleanCriteriaUserBO(
            final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) {
        TechnicalInformation<UserCriteriaColumn, UserOrderColumn> toReturn = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();

        if (pTechnicalInformation != null) {
            toReturn.setCriterias(pTechnicalInformation.getCriterias());
            toReturn.setOrder(pTechnicalInformation.getOrder());
            toReturn.setPagination(pTechnicalInformation.getPagination());
            if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
                    UserCriteriaColumn.STATUS)) {
                toReturn.addCriteria(CRITERIA_USER_STATUS_EQUAL_VALIDATED);
            }
        } else {
            toReturn.addCriteria(CRITERIA_USER_STATUS_EQUAL_VALIDATED);
        }
        return toReturn;
    }
    // </editor-fold>
}
