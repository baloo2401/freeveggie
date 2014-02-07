package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 * This is a service class. This class list all the method that involve reference informations.
 *
 * @author Mickael Dubois
 */
public interface IReferenceService {

    /**
     * Get all {@link RefCityMsg} in a given {@link RefCountryMsg}.
     * The result is ordered by name.
     * @param pRefCountryId - The given {@link RefCountryMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCityMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefCityMsg> getRefCitiesByCountry(final Integer pRefCountryId, final Pagination pPagination) throws BusinessException;

    /**
     * Get all {@link RefCityMsg} by the beginning name.
     * The result is ordered by name.
     * @param pName - The city name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCityMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefCityMsg> getRefCitiesByName(final String pName, final Pagination pPagination) throws BusinessException;
    
    /**
     * Get all {@link RefCityMsg} by the beginning zip code.
     * The result is ordered by name.
     * @param pZipCode - The city zip code to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCityMsg}
     * @throws BusinessException In case of any business issue
     */
    public List<RefCityMsg> getRefCitiesByZipCode(Integer pZipCode, Pagination pPagination);

    /**
     * Get all {@link RefCityMsg} in a given {@link RefRegionMsg}.
     * The result is ordered by name.
     * @param pRefRegionId - The given {@link RefRegionMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCityMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefCityMsg> getRefCitiesByRegion(final Integer pRefRegionId, final Pagination pPagination) throws BusinessException;

    /**
     * Get all {@link RefCityMsg} in a given {@link RefStateMsg}.
     * The result is ordered by name.
     * @param pRefStateId - The given {@link RefStateMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCityMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefCityMsg> getRefCitiesByState(final Integer pRefStateId, final Pagination pPagination) throws BusinessException;

    /**
     ****  Get all {@link RefCountryMsg} starting with a given name.
     * The result is ordered by name.
     * @param pName - The country name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefCountryMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefCountryMsg> getRefCountriesByName(final String pName, final Pagination pPagination) throws BusinessException;

    /**
     * Get the {@link RefCountryMsg} by the iso code with two letter.
     * @param pIsoCodeA2 - The iso code to look for.
     * @return A {@link RefCountryMsg}
     * @throws BusinessException In case of any business issue
     */
    RefCountryMsg getRefCountryByIsoCodeA2(final String pIsoCodeA2) throws BusinessException;

    /**
     * Get the {@link RefCountryMsg} by the iso code with three letter.
     * @param pIsoCodeA3 - The iso code to look for.
     * @return A {@link RefCountryMsg}
     * @throws BusinessException In case of any business issue
     */
    RefCountryMsg getRefCountryByIsoCodeA3(final String pIsoCodeA3) throws BusinessException;

    /**
     * Get the {@link RefCountryMsg} by the iso code number
     * @param pIsoCodeNumber - The iso code to look for.
     * @return A {@link RefCountryMsg}
     * @throws BusinessException In case of any business issue
     */
    RefCountryMsg getRefCountryByIsoCodeNumber(final Integer pIsoCodeNumber) throws BusinessException;

    /**
     * Get all {@link RefProductMsg} of a given {@link RefProductMsg.ProductType}.
     * @param pProductType - The give {@link RefProductMsg.ProductType}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RefProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefProductMsg> getRefProducts(final ProductType pProductType, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) throws BusinessException;


    /**
     * Get all {@link RefProductMsg} of a given {@link RefProductMsg.ProductType}.
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RefProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefProductMsg> getRefProducts(final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link RefProductMsg} starting of a by a given name.
     * @param pName - The beginning of the product name we are looking for.
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RefProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefProductMsg> getRefProductsByName(final String pName, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link RefProductMsg} that can be reap during a given {@link List} of {@link RefMonthMsg}.
     * @param pRefMonthIds - The {@link List} of {@link RefMonthMsg} that the reference product we are looking for should be reaped.
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RefProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefProductMsg> getRefProductsByReapSeason(final List<Month> pRefMonthIds, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link RefProductMsg} that can be seed during a given {@link List} of {@link RefMonthMsg}.
     * @param pRefMonthIds - The {@link List} of {@link RefMonthMsg} that the reference product we are looking for should be seeded
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RefProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefProductMsg> getRefProductsBySeedSeason(final List<Month> pRefMonthIds, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) throws BusinessException;


    /**
     * Get all {@link RefRegionMsg} for a given {@link RefCountryMsg}.
     * The result is ordered by name.
     * @param pRefCountryMsg - The given {@link RefCountryMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefRegionMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefRegionMsg> getRefRegionsByCountry(final Integer pRefCountryMsg, final Pagination pPagination) throws BusinessException;

    /**
     * Get all {@link RefRegionMsg} starting by the given name.
     * The result is ordered by name.
     * @param pName - The region name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefRegionMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefRegionMsg> getRefRegionsByName(final String pName, final Pagination pPagination) throws BusinessException;

    /**
     * Get all {@link RefRegionMsg} for a given {@link RefStateMsg}.
     * The result is ordered by name.
     * @param pRefStateId - The given {@link RefStateMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefRegionMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefRegionMsg> getRefRegionsByState(final Integer pRefStateId, final Pagination pPagination) throws BusinessException;


    /**
     * Get all {@link RefStateMsg} in a given {@link RefCountryMsg}.
     * The result is ordered by name.
     * @param pRefCountryId - The give {@link RefCountryMsg}
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefStateMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefStateMsg> getRefStatesByCountry(final Integer pRefCountryId, final Pagination pPagination) throws BusinessException;

    /**
     * Get all {@link RefStateMsg} starting by a given name.
     * The result is ordered by name.
     * @param pName - The region name to look for
     * @param pPagination - The {@link Pagination} in wish the result should be return
     * @return A  {@link List} of {@link RefStateMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RefStateMsg> getRefStatesByName(final String pName, final Pagination pPagination) throws BusinessException;

}
