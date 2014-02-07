package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.bean.IReferenceBean;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.api.IReferenceService;
import org.mdubois.freeveggie.service.msg.*;

// </editor-fold>
/**
 * This is a service class. This class list all the method that involve
 * reference informations.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IReferenceBean.class)
public class ReferenceBeanLocal implements IReferenceBean {

    /**
     * {@link IReferenceService}
     */
    @Inject
    private IReferenceService referenceService;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByCountry(final Integer pRefCountryId,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCitiesByCountry(pRefCountryId,
                pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByName(final String pName,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCitiesByName(pName, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByZipCode(final Integer pZipCode,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCitiesByZipCode(pZipCode, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByRegion(final Integer pRefRegionId,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCitiesByRegion(pRefRegionId, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByState(final Integer pRefStateId,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCitiesByState(pRefStateId, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCountryMsg> getRefCountriesByName(final String pName,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefCountriesByName(pName, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefCountryMsg getRefCountryByIsoCodeA2(final String pIsoCodeA2)
            throws BusinessException {
        return referenceService.getRefCountryByIsoCodeA2(pIsoCodeA2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefCountryMsg getRefCountryByIsoCodeA3(final String pIsoCodeA3)
            throws BusinessException {
        return referenceService.getRefCountryByIsoCodeA3(pIsoCodeA3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProducts(
            final ProductType pProductType,
            final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return referenceService.getRefProducts(pProductType,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProducts(
            final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return referenceService.getRefProducts(pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsByName(
            final String pName,
            final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return referenceService.getRefProductsByName(pName,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsByReapSeason(
            final List<Month> pRefMonthIds,
            final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return referenceService.getRefProductsByReapSeason(pRefMonthIds,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsBySeedSeason(
            final List<Month> pRefMonthIds,
            final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return referenceService.getRefProductsBySeedSeason(pRefMonthIds,
                pTechnicalInformation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByCountry(
            final Integer pRefCountryMsg, final Pagination pPagination)
            throws BusinessException {
        return referenceService.getRefRegionsByCountry(pRefCountryMsg,
                pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByName(final String pName,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefRegionsByName(pName, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByState(final Integer pRefStateId,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefRegionsByState(pRefStateId, pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefStateMsg> getRefStatesByCountry(final Integer pRefCountryId,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefStatesByCountry(pRefCountryId,
                pPagination);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefStateMsg> getRefStatesByName(final String pName,
            final Pagination pPagination) throws BusinessException {
        return referenceService.getRefStatesByName(pName, pPagination);
    }
}
