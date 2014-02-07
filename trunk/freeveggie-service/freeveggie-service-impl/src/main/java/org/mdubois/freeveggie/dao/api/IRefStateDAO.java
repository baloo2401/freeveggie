package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;

/**
 * This class is the data access class of the {@link RefStateBO} entity.
 * @author Mickael Dubois
 */
public interface IRefStateDAO extends IReadOnlyDAO<RefStateBO, Integer>{

    /**
     * Get all {@link RefStateBO} in a given {@link RefCountryBO}.
     * The result is ordered by name.
     * @param pIdRefCountryBO - The give {@link RefCountryBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefStateBO}
     */
    List<RefStateBO> getRefStatesByCountry(final Integer pIdRefCountryBO, final Pagination pPagination);

    /**
     * Get all {@link RefStateBO} starting by a given name.
     * The result is ordered by name.
     * @param pName - The region name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefStateBO}
     */
    List<RefStateBO> getRefStatesByName(final String pName, final Pagination pPagination);

}
