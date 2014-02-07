package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bean.IUserBean;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.UserOrderColumn;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.api.IUserService;
import org.mdubois.freeveggie.service.msg.UserMsg;

// </editor-fold>
/**
 * This class is a service. This class represent all the method that involve
 * {@link UserBO}.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IUserBean.class)
public class UserBeanLocal implements IUserBean {

    /**
     * {@link IUserService}
     */
    @Inject
    private IUserService userService;
    /**
     * {@link IRightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean changeRole(final ContextMsg pContextMsg, final Long pUserId,
            final UserRole pUserRole) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.changeRole(pUserId, pUserRole);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean upgrade(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.upgrade(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final ContextMsg pContextMsg,  final UserMsg pUserMsg)
            throws BusinessException {
        if (pContextMsg.getUser().getId().equals(pUserMsg.getId())) {
            return userService.update(pContextMsg.getUser().getId(), pUserMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean downgrade(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.downgrade(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blacklist(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.blacklist(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unblacklist(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.unblacklist(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final String pCode) throws BusinessException {
        return userService.validate(pCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.delete(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean undelete(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.undelete(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.archive(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchive(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SUPERADMIN, UserRole.SYSTEM)) {
            return userService.unarchive(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg getUserById(final Long pUserId) throws BusinessException {
        return userService.getUserById(pUserId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByCountry(
            final Integer pRefCountryId,
            final Integer pRefProductId,
            final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userService.getUserByCountry(pRefCountryId, pRefProductId,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByState(
            final Integer pRefStateId,
            final Integer pRefProductId,
            final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userService.getUserByState(pRefStateId, pRefProductId,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByRegion(
            final Integer pRefRegionId,
            final Integer pRefProductId,
            final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userService.getUserByRegion(pRefRegionId, pRefProductId,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserMsg> getUserByCity(
            final Integer pRefCityId,
            final Integer pRefProductId,
            final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return userService.getUserByCity(pRefCityId, pRefProductId,
                pTechnicalInformation);
    }
}
