package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;

/**
 * This class is the data access class of the {@link RefCountryBO} entity.
 * @author Mickael Dubois
 */
public interface IRefCountryDAO extends IReadOnlyDAO<RefCountryBO, Integer>{

    /**
     ****  Get all {@link RefCountryBO} starting with a given name.
     * The result is ordered by name.
     * @param pName - The country name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCountryBO}
     */
    List<RefCountryBO> getRefCountriesByName(final String pName, final Pagination pPagination);

    /**
     * Get the {@link RefCountryBO} by the iso code with two letter.
     * @param pIsoCodeA2 - The iso code to look for.
     * @return A {@link RefCountryBO}
     */
    RefCountryBO getRefCountryByIsoCodeA2(final String pIsoCodeA2);

    /**
     * Get the {@link RefCountryBO} by the iso code with three letter.
     * @param pIsoCodeA3 - The iso code to look for.
     * @return A {@link RefCountryBO}
     */
    RefCountryBO getRefCountryByIsoCodeA3(final String pIsoCodeA3);

    /**
     * Get the {@link RefCountryBO} by the iso code number
     * @param pIsoCodeNumber - The iso code to look for.
     * @return A {@link RefCountryBO}
     */
    RefCountryBO getRefCountryByIsoCodeNumber(final Integer pIsoCodeNumber);

}
