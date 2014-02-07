package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bean.local.ReferenceBeanLocal;
import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
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
public class ReferenceBeanTest {

    @Test
    public void getRefCitiesByCountry() throws BusinessException {

        final Integer pRefCountryId = new Integer(1);
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefCityMsg> expResult = new ArrayList<RefCityMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCitiesByCountry(pRefCountryId, pPagination);
                returns(expResult);
            }
        };

        List<RefCityMsg> result = instance.getRefCitiesByCountry(pRefCountryId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCitiesByName() throws BusinessException {

        final String pName = "name";
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefCityMsg> expResult = new ArrayList<RefCityMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCitiesByName(pName, pPagination);
                returns(expResult);
            }
        };

        List<RefCityMsg> result = instance.getRefCitiesByName(pName, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCitiesByRegion() throws BusinessException {

        final Integer pRefRegionId = 1;
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefCityMsg> expResult = new ArrayList<RefCityMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCitiesByRegion(pRefRegionId, pPagination);
                returns(expResult);
            }
        };

        List<RefCityMsg> result = instance.getRefCitiesByRegion(pRefRegionId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCitiesByState() throws BusinessException {

        final Integer pRefStateId = 123;
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefCityMsg> expResult = new ArrayList<RefCityMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCitiesByState(pRefStateId, pPagination);
                returns(expResult);
            }
        };

        List<RefCityMsg> result = instance.getRefCitiesByState(pRefStateId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCountriesByName() throws BusinessException {

        final String pName = "name";
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefCountryMsg> expResult = new ArrayList<RefCountryMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCountriesByName(pName, pPagination);
                returns(expResult);
            }
        };

        List<RefCountryMsg> result = instance.getRefCountriesByName(pName, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCountryByIsoCodeA2() throws BusinessException {

        final String pIsoCodeA2 = "name";
        final IReferenceBean instance = new ReferenceBeanLocal();
        final RefCountryMsg expResult = new RefCountryMsg();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCountryByIsoCodeA2(pIsoCodeA2);
                returns(expResult);
            }
        };
        RefCountryMsg result = instance.getRefCountryByIsoCodeA2(pIsoCodeA2);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefCountryByIsoCodeA3() throws BusinessException {

        final String pIsoCodeA3 = "name";
        final IReferenceBean instance = new ReferenceBeanLocal();
        final RefCountryMsg expResult = new RefCountryMsg();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefCountryByIsoCodeA3(pIsoCodeA3);
                returns(expResult);
            }
        };
        RefCountryMsg result = instance.getRefCountryByIsoCodeA3(pIsoCodeA3);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefProducts() throws BusinessException {

        final ProductType pProductType = ProductType.FRUIT;
        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefProductMsg> expResult = new ArrayList<RefProductMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefProducts(pProductType, pTechnicalInformation);
                returns(expResult);
            }
        };

        List<RefProductMsg> result = instance.getRefProducts(pProductType, pTechnicalInformation);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefProductsByName() throws BusinessException {

        final String pName = "name";
        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefProductMsg> expResult = new ArrayList<RefProductMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefProductsByName(pName, pTechnicalInformation);
                returns(expResult);
            }
        };

        List<RefProductMsg> result = instance.getRefProductsByName(pName, pTechnicalInformation);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefProductsByReapSeason() throws BusinessException {

        final List<Month> pRefMonthIds = new ArrayList<Month>();
        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefProductMsg> expResult = new ArrayList<RefProductMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefProductsByReapSeason(pRefMonthIds, pTechnicalInformation);
                returns(expResult);
            }
        };

        List<RefProductMsg> result = instance.getRefProductsByReapSeason(pRefMonthIds, pTechnicalInformation);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefProductsBySeedSeason() throws BusinessException {

        final List<Month> pRefMonthIds = new ArrayList<Month>();
        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefProductMsg> expResult = new ArrayList<RefProductMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefProductsBySeedSeason(pRefMonthIds, pTechnicalInformation);
                returns(expResult);
            }
        };

        List<RefProductMsg> result = instance.getRefProductsBySeedSeason(pRefMonthIds, pTechnicalInformation);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefRegionsByCountry() throws BusinessException {

        final Integer pRefCountryMsg = 2;
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefRegionMsg> expResult = new ArrayList<RefRegionMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefRegionsByCountry(pRefCountryMsg, pPagination);
                returns(expResult);
            }
        };

        List<RefRegionMsg> result = instance.getRefRegionsByCountry(pRefCountryMsg, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefRegionsByName() throws BusinessException {

        final String pName = "name";
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefRegionMsg> expResult = new ArrayList<RefRegionMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefRegionsByName(pName, pPagination);
                returns(expResult);
            }
        };

        List<RefRegionMsg> result = instance.getRefRegionsByName(pName, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefRegionsByState() throws BusinessException {

        final Integer pRefStateId = 98;
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefRegionMsg> expResult = new ArrayList<RefRegionMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefRegionsByState(pRefStateId, pPagination);
                returns(expResult);
            }
        };

        List<RefRegionMsg> result = instance.getRefRegionsByState(pRefStateId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefStatesByCountry() throws BusinessException {

        final Integer pRefCountryId = 67;
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefStateMsg> expResult = new ArrayList<RefStateMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefStatesByCountry(pRefCountryId, pPagination);
                returns(expResult);
            }
        };

        List<RefStateMsg> result = instance.getRefStatesByCountry(pRefCountryId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test
    public void getRefStatesByName() throws BusinessException {

        final String pName = "name";
        final Pagination pPagination = new Pagination(1, 2);
        final IReferenceBean instance = new ReferenceBeanLocal();
        final List<RefStateMsg> expResult = new ArrayList<RefStateMsg>();

        new Expectations() {

            @Mocked
            private IReferenceService referenceService;

            {
                Deencapsulation.setField(instance, referenceService);

                referenceService.getRefStatesByName(pName, pPagination);
                returns(expResult);
            }
        };

        List<RefStateMsg> result = instance.getRefStatesByName(pName, pPagination);
        Assert.assertEquals(expResult, result);
    }
}
