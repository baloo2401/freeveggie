package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
// </editor-fold>

/**
 * The data access class of the {@link AddressBO} entity. It define all the
 * action you can make that involve {@link AddressBO}
 * @author Mickael Dubois
 */
public interface IAddressDAO extends IReadWriteDAO<AddressBO, Long>{
    

    /**
     * Get the home address of the user.
     * @param pIdRefCountryBO - The {@link RefCountryBO} in wish we look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    AddressBO getUserHomeAddressByUserId(final Long pIdUserBO);

    /**
     * Get all {@link AddressBO} save in database from a {@link RefCountryBO}.
     * @param pIdRefCountryBO - The {@link RefCountryBO} in wish we look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByCountry(final Integer pIdRefCountryBO, final Pagination pPagination);

    /**
     * Get all {@link AddressBO} from a city start name in a given {@link RefCountryBO}.
     * @param pIdRefCountryBO - The {@link RefCountryBO} in wish we look for
     * @param pCity - The city name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByCountry(final Integer pIdRefCountryBO, final String pCity, final Pagination pPagination);

    /**
     * Get all {@link AddressBO} from a given zip code in a given {@link RefCountryBO}.
     * @param pIdRefCountryBO - The {@link RefCountryBO} in wish we look for
     * @param pZipCode - The zip code of the city to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByCountry(final Integer pIdRefCountryBO, final int pZipCode, final Pagination pPagination);

    /**
     * Get all {@link AddressBO} of a given {@link RefCountryBO}.
     * @param pIdRefCityBO - The {@link AddressBO} in wish we look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByCity(final Integer pIdRefCityBO, final Pagination pPagination);

    /**
     * Get all {@link AddressBO} of a given {@link RefRegionBO}.
     * @param pIdRefRegionBO - The {@link RefRegionBO} in wish we look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByRegion(final Integer pIdRefRegionBO, final Pagination pPagination);

    /**
     * Get all {@link AddressBO} of a given {@link RefStateBO}.
     * @param pIdRefStateBO - The {@link RefStateBO} in wish we look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A {@link List} of {@link AddressBO}
     */
    List<AddressBO> getAddressByState(final Integer pIdRefStateBO, final Pagination pPagination);

}
