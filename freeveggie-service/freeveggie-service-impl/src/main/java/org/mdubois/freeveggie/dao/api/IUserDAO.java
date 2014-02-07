package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.UserOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link UserBO} entity.
 * @author Mickael Dubois
 */
public interface IUserDAO extends IReadWriteDAO<UserBO, Long>{

    /**
     * Get all {@link UserBO} of living in {@link RefCityBO} or having a garden there and having a given {@link RefProductBO}.
     * @param pIdRefCityBO - The given {@link RefCityBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link UserBO}
     */
    List<UserBO> getUserByCityAndProduct(final Integer pIdRefCityBO, final Integer pIdRefProductBO, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link UserBO} of a {@link RefRegionBO} having a given {@link RefProductBO}.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link UserBO}
     */
    List<UserBO> getUserByRegionAndProduct(final Integer pIdRefRegionBO, final Integer pIdRefProductBO, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link UserBO} of a {@link RefStateBO} having a given {@link RefProductBO}.
     * @param pIdRefStateBO - The given {@link RefStateBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link UserBO}
     */
    List<UserBO> getUserByStateAndProduct(final Integer pIdRefStateBO, final Integer pIdRefProductBO, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link UserBO} of a {@link RefCountryBO} having a given {@link RefProductBO}.
     * @param pIdRefCountryBO - The given {@link RefCountryBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link UserBO}
     */
    List<UserBO> getUserByCountryAndProduct(final Integer pIdRefCountryBO, final Integer pIdRefProductBO, final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation);

    /**
     * Get the user base on the login.
     * @param pLogin - The login to look for
     * @return The UserBO if a user exist, null otherwise
     */
    UserBO getUserByLogin(final String pLogin);

    /**
     * Get the user base on the email.
     * @param pEmail - The email to look for
     * @return The UserBO if a user exist, null otherwise
     */
    UserBO getUserByEmail(final String pEmail);
}
