package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
// </editor-fold>

/**
 * This class is the data access class of the {@link RefCityBO} entity.
 * @author Mickael Dubois
 */
public interface IRefCityDAO extends IReadOnlyDAO<RefCityBO, Integer>{

    /**
     * Get all {@link RefCityBO} in a given {@link RefCountryBO}.
     * The result is ordered by name.
     * @param pIdRefCountryBO - The given {@link RefCountryBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCityBO}
     */
    List<RefCityBO> getRefCitiesByCountry(final Integer pIdRefCountryBO, final Pagination pPagination);

    /**
     * Get all {@link RefCityBO} by the beginning name.
     * The result is ordered by name.
     * @param pName - The city name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCityBO}
     */
    
    List<RefCityBO> getRefCitiesByName(final String pName, final Pagination pPagination);
    /**
     * Get all {@link RefCityBO} by the beginning zip code.
     * The result is ordered by name.
     * @param pZipCode - The city zip code to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCityBO}
     */
    List<RefCityBO> getRefCitiesByZipCode(final Integer pZipCode, final Pagination pPagination);

    /**
     * Get all {@link RefCityBO} in a given {@link RefRegionBO}.
     * The result is ordered by name.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCityBO}
     */
    List<RefCityBO> getRefCitiesByRegion(final Integer pIdRefRegionBO, final Pagination pPagination);

    /**
     * Get all {@link RefCityBO} in a given {@link RefStateBO}.
     * The result is ordered by name.
     * @param pIdRefStateBO - The given {@link RefStateBO} id
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link {@link RefCityBO}
     */
    List<RefCityBO> getRefCitiesByState(final Integer pIdRefStateBO, final Pagination pPagination);


}
