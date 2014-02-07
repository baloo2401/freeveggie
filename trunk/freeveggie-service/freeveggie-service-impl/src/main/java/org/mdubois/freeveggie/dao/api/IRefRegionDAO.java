package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;

/**
 * This class is the data access class of the {@link RefRegionBO} entity.
 * @author Mickael Dubois
 */
public interface IRefRegionDAO extends IReadOnlyDAO<RefRegionBO, Integer>{

    /**
     * Get all {@link RefRegionBO} for a given {@link RefCountryBO}.
     * The result is ordered by name.
     * @param pIdRefCountryBO - The given {@link RefCountryBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefRegionBO}
     */
    List<RefRegionBO> getRefRegionsByCountry(final Integer pIdRefCountryBO, final Pagination pPagination);

    /**
     * Get all {@link RefRegionBO} starting by the given name.
     * The result is ordered by name.
     * @param pName - The region name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefRegionBO}
     */
    List<RefRegionBO> getRefRegionsByName(final String pName, final Pagination pPagination);

    /**
     * Get all {@link RefRegionBO} for a given {@link RefStateBO}.
     * The result is ordered by name.
     * @param pIdRefStateBO - The given {@link RefStateBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefRegionBO}
     */
    List<RefRegionBO> getRefRegionsByState(final Integer pIdRefStateBO, final Pagination pPagination);

}
