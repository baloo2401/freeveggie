package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.UserOrderColumn;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 * This class is a service. This class represent all the method that involve {@link UserBO}.
 *
 * @author Mickael Dubois
 */
public interface IUserBean {

    /**
     * Set the role of user.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The user id to update
     * @param pUserRole - The role to set
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean changeRole(final ContextMsg pContextMsg, final Long pUserId, final UserRole pUserRole) throws BusinessException;

    /**
     * Upgrade a user from role : User to Manager Manager to Admin Admin to
     * SuperAdmin
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The user to upgrade
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean upgrade(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;
    
    /**
     * Update user informations
     * 
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserMsg - The user to upgrade
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final ContextMsg pContextMsg, final UserMsg pUserMsg) throws BusinessException;

    /**
     * Downgrade a user from role : Manager to User Admin to Manegr SuperAdmin
     * to Admin
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The user to upgrade
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean downgrade(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Blacklist a user. If the user doesn't exit, an exception will be thrown.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean blacklist(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Unblacklist a user. If the user doesn't exit, an exception will be
     * thrown.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unblacklist(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Validate a user.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean validate(final String pCode) throws BusinessException;

    /**
     * Delete a user. This is not a physical delete. This will just change the
     * status of the user. The user or the system are allow to do it.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean delete(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Undelete a user.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean undelete(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Archive a user. This is not a physical archive. This will just change the
     * status of the user.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archive(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Unarchive a user
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return
     * <code>True</code> if the update succeed,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchive(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;

    /**
     * Get a {@link UserBO} of a given user Id.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The id of the user to update
     * @return The {@link UserBO} find or null.
     * @throws BusinessException In case of any business issue
     */
    UserMsg getUserById(final Long pUserId) throws BusinessException;

    /**
     * Get all {@link UserBO} of a {@link RefCountryBO} having a given {@link RefProductBO}.
     * If the {@link TechnicalInformation} not say the opposite only user which
     * are validate.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCountryId - The given {@link RefCountryBO}
     * @param pRefProductId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A  {@link List} of {@link {@link UserBO}
     * @throws BusinessException In case of any business issue
     */
    List<UserMsg> getUserByCountry(final Integer pRefCountryId, final Integer pRefProductId, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link UserBO} of a {@link RefCountryBO} having a given {@link RefProductBO}.
     * If the {@link TechnicalInformation} not say the opposite only user which
     * are validate.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefStateId - The given {@link RefCountryBO}
     * @param pRefProductId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A  {@link List} of {@link {@link UserBO}
     * @throws BusinessException In case of any business issue
     */
    List<UserMsg> getUserByState(final Integer pRefStateId, final Integer pRefProductId, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link UserBO} of a {@link RefCountryBO} having a given {@link RefProductBO}.
     * If the {@link TechnicalInformation} not say the opposite only user which
     * are validate.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionId - The given {@link RefCountryBO}
     * @param pRefProductId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A  {@link List} of {@link {@link UserBO}
     * @throws BusinessException In case of any business issue
     */
    List<UserMsg> getUserByRegion(final Integer pRefRegionId, final Integer pRefProductId, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link UserBO} of a {@link RefCountryBO} having a given {@link RefProductBO}.
     * If the {@link TechnicalInformation} not say the opposite only user which
     * are validate.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityId - The given {@link RefCountryBO}
     * @param pRefProductId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A  {@link List} of {@link {@link UserBO}
     * @throws BusinessException In case of any business issue
     */
    List<UserMsg> getUserByCity(final Integer pRefCityId, final Integer pRefProductId, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) throws BusinessException;

}
