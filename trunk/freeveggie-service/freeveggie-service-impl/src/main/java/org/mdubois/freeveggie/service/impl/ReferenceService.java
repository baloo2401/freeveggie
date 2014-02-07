package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.dao.api.*;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.api.IReferenceService;
import org.mdubois.freeveggie.service.msg.*;
import org.springframework.util.CollectionUtils;

// </editor-fold>
/**
 *
 * @author Mickael Dubois
 */
public class ReferenceService implements IReferenceService {

    // <editor-fold defaultstate="collapsed" desc="DAO Resource's">
    /**
     * {@link IRefCityDAO}
     */
    @Inject
    private IRefCityDAO refCityDAO;
    /**
     * {@link IRefCountryDAO}
     */
    @Inject
    private IRefCountryDAO refCountryDAO;
    /**
     * {@link IRefProductDAO}
     */
    @Inject
    private IRefProductDAO refProductDAO;
    /**
     * {@link IRefRegionDAO}
     */
    @Inject
    private IRefRegionDAO refRegionDAO;
    /**
     * {@link IRefStateDAO}
     */
    @Inject
    private IRefStateDAO refStateDAO;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Converter">
    /**
     * {@link Converter<RefCityMsg, RefCityBO>}
     */
    @Inject
    private Converter<RefCityMsg, RefCityBO> refCityBOConverter;
    /**
     * {@link Converter<RefCountryMsg, RefCountryBO>}
     */
    @Inject
    private Converter<RefCountryMsg, RefCountryBO> refCountryBOConverter;
    /**
     * {@link Converter<RefStateMsg, RefStateBO>}
     */
    @Inject
    private Converter<RefStateMsg, RefStateBO> refStateBOConverter;
    /**
     * {@link Converter<RefRegionMsg, RefRegionBO>}
     */
    @Inject
    private Converter<RefRegionMsg, RefRegionBO> refRegionBOConverter;
    /**
     * {@link Converter<RefProductMsg, RefProductBO, >}
     */
    @Inject
    private Converter<RefProductMsg, RefProductBO> refProductBOConverter;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get reference method">
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByCountry(final Integer pRefCountryId,
            final Pagination pPagination) throws BusinessException {
        return refCityBOConverter.convert(refCityDAO.getRefCitiesByCountry(
                pRefCountryId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByName(String pName,
            Pagination pPagination) throws BusinessException {
        return refCityBOConverter.convert(refCityDAO.getRefCitiesByName(pName,
                pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByZipCode(Integer pZipCode,
            Pagination pPagination) {
        return refCityBOConverter.convert(refCityDAO.getRefCitiesByZipCode(pZipCode,
                pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByRegion(Integer pRefRegionId,
            Pagination pPagination) throws BusinessException {
        return refCityBOConverter.convert(refCityDAO.getRefCitiesByRegion(
                pRefRegionId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCityMsg> getRefCitiesByState(Integer pRefStateId,
            Pagination pPagination) throws BusinessException {
        return refCityBOConverter.convert(refCityDAO.getRefCitiesByState(
                pRefStateId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefCountryMsg> getRefCountriesByName(String pName,
            Pagination pPagination) throws BusinessException {
        return refCountryBOConverter.convert(refCountryDAO
                .getRefCountriesByName(pName, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefCountryMsg getRefCountryByIsoCodeA2(String pIsoCodeA2)
            throws BusinessException {
        return refCountryBOConverter.convert(refCountryDAO
                .getRefCountryByIsoCodeA2(pIsoCodeA2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefCountryMsg getRefCountryByIsoCodeA3(String pIsoCodeA3)
            throws BusinessException {
        return refCountryBOConverter.convert(refCountryDAO
                .getRefCountryByIsoCodeA3(pIsoCodeA3));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefCountryMsg getRefCountryByIsoCodeNumber(Integer pIsoCodeNumber)
            throws BusinessException {
        return refCountryBOConverter.convert(refCountryDAO
                .getRefCountryByIsoCodeNumber(pIsoCodeNumber));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProducts(
            ProductType pProductType,
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return refProductBOConverter.convert(refProductDAO.getRefProducts(
                pProductType.toInt(), pTechnicalInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProducts(
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        List<QueryCriteria<? extends CriteriaColumn>> criterias = null;
        if(!CollectionUtils.isEmpty(pTechnicalInformation.getCriterias())){
            criterias = new ArrayList<QueryCriteria<? extends CriteriaColumn>>();
            criterias.addAll(pTechnicalInformation.getCriterias());
        }
        return refProductBOConverter.convert(refProductDAO.getAll(
                criterias,
                pTechnicalInformation.getOrder(),
                pTechnicalInformation.getPagination()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsByName(
            String pName,
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return refProductBOConverter.convert(refProductDAO
                .getRefProductsByName(pName, pTechnicalInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsByReapSeason(
            List<Month> pRefMonthIds,
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        List<Integer> refMonths = new ArrayList<Integer>(pRefMonthIds.size());
        for (Month refMonth : pRefMonthIds) {
            refMonths.add(refMonth.getMonthNumber());
        }
        return refProductBOConverter.convert(refProductDAO
                .getRefProductsByReapSeason(refMonths, pTechnicalInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefProductMsg> getRefProductsBySeedSeason(
            List<Month> pRefMonthIds,
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        List<Integer> refMonths = new ArrayList<Integer>(pRefMonthIds.size());
        for (Month refMonth : pRefMonthIds) {
            refMonths.add(refMonth.getMonthNumber());
        }
        return refProductBOConverter.convert(refProductDAO
                .getRefProductsBySeedSeason(refMonths, pTechnicalInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByCountry(Integer pRefCountryId,
            Pagination pPagination) throws BusinessException {
        return refRegionBOConverter.convert(refRegionDAO
                .getRefRegionsByCountry(pRefCountryId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByName(String pName,
            Pagination pPagination) throws BusinessException {
        return refRegionBOConverter.convert(refRegionDAO.getRefRegionsByName(
                pName, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefRegionMsg> getRefRegionsByState(Integer pRefStateId,
            Pagination pPagination) throws BusinessException {
        return refRegionBOConverter.convert(refRegionDAO.getRefRegionsByState(
                pRefStateId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefStateMsg> getRefStatesByCountry(Integer pRefCountryId,
            Pagination pPagination) throws BusinessException {
        return refStateBOConverter.convert(refStateDAO.getRefStatesByCountry(
                pRefCountryId, pPagination));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RefStateMsg> getRefStatesByName(String pName,
            Pagination pPagination) throws BusinessException {
        return refStateBOConverter.convert(refStateDAO.getRefStatesByName(
                pName, pPagination));
    }
    // </editor-fold>
}
