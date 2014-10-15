package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRefCityDAO;
import org.mdubois.freeveggie.dao.api.IRefCountryDAO;
import org.mdubois.freeveggie.dao.api.IRefProductDAO;
import org.mdubois.freeveggie.dao.api.IRefRegionDAO;
import org.mdubois.freeveggie.dao.api.IRefStateDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.api.IReferenceService;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
import org.mdubois.freeveggie.service.msg.RefCountryMsg;
import org.mdubois.freeveggie.service.msg.RefProductMsg;
import org.mdubois.freeveggie.service.msg.RefRegionMsg;
import org.mdubois.freeveggie.service.msg.RefStateMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ReferenceServiceTest {

    @Mocked
    private IRefProductDAO refProductDAO;
    @Mocked
    private Converter<RefProductMsg, RefProductBO> refProductBOConverter;
    @Mocked
    private IRefCountryDAO refCountryDAO;
    @Mocked
    private Converter<RefCountryMsg, RefCountryBO> refCountryBOConverter;
    @Mocked
    private IRefStateDAO refStateDAO;
    @Mocked
    private Converter<RefStateMsg, RefStateBO> refStateBOConverter;
    @Mocked
    private IRefCityDAO refCityDAO;
    @Mocked
    private Converter<RefCityMsg, RefCityBO> refCityBOConverter;
    @Mocked
    private IRefRegionDAO refRegionDAO;
    @Mocked
    private Converter<RefRegionMsg, RefRegionBO> refRegionBOConverter;

    @Test
    public void getRefCitiesByCountryNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                refCityDAO.getRefCitiesByCountry(refCountryId, null);
                returns(null);

                refCityBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefCitiesByCountry(refCountryId, null));
    }

    @Test
    public void getRefCitiesByCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByCountry(refCountryId, null);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByCountry(refCountryId, null);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByCountryWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByCountry(refCountryId, pagination);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByCountry(refCountryId, pagination);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByRegionNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refRegionId = 12543;

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                refCityDAO.getRefCitiesByRegion(refRegionId, null);
                returns(null);

                refCityBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefCitiesByRegion(refRegionId, null));
    }

    @Test
    public void getRefCitiesByRegion() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refRegionId = 12543;
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByRegion(refRegionId, null);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByRegion(refRegionId, null);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByRegionWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refRegionId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByRegion(refRegionId, pagination);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByRegion(refRegionId, pagination);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByStateNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                refCityDAO.getRefCitiesByState(refStateId, null);
                returns(null);

                refCityBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefCitiesByState(refStateId, null));
    }

    @Test
    public void getRefCitiesByState() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByState(refStateId, null);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByState(refStateId, null);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByStateWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByState(refStateId, pagination);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByState(refStateId, pagination);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByName() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "ile";
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByName(cityName, null);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByName(cityName, null);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCitiesByNameWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "par";
        final Pagination pagination = new Pagination(1, 1);
        final List<RefCityMsg> refCities = new ArrayList<RefCityMsg>();
        RefCityMsg refCityMsg = new RefCityMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCityBOConverter", refCityBOConverter);
                Deencapsulation.setField(referenceService, refCityDAO);

                final List<RefCityBO> refCitiesBO = new ArrayList<RefCityBO>();
                final RefCityBO refCityBO = new RefCityBO();
                refCitiesBO.add(refCityBO);

                refCityDAO.getRefCitiesByName(cityName, pagination);
                returns(refCitiesBO);

                refCityBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCityMsg> cities = referenceService.getRefCitiesByName(cityName, pagination);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByCountryNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefRegionMsg> RefRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg RefRegionMsg = new RefRegionMsg();
        RefRegions.add(RefRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                refRegionDAO.getRefRegionsByCountry(refCountryId, null);
                returns(null);

                refRegionBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefRegionsByCountry(refCountryId, null));
    }

    @Test
    public void getRefRegionsByCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg RefRegionMsg = new RefRegionMsg();
        refRegions.add(RefRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByCountry(refCountryId, null);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByCountry(refCountryId, null);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(RefRegionMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByCountryWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg refRegionMsg = new RefRegionMsg();
        refRegions.add(refRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByCountry(refCountryId, pagination);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByCountry(refCountryId, pagination);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(refRegionMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByStateNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                refRegionDAO.getRefRegionsByState(refStateId, null);
                returns(null);

                refRegionBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefRegionsByState(refStateId, null));
    }

    @Test
    public void getRefRegionsByState() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg refRegionMsg = new RefRegionMsg();
        refRegions.add(refRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByState(refStateId, null);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByState(refStateId, null);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(refRegionMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByStateWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refStateId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg refRegionMsg = new RefRegionMsg();
        refRegions.add(refRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByState(refStateId, pagination);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByState(refStateId, pagination);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(refRegionMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByName() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "ile";
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg refRegionMsg = new RefRegionMsg();
        refRegions.add(refRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByName(cityName, null);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByName(cityName, null);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(refRegionMsg, cities.get(0));

    }

    @Test
    public void getRefRegionsByNameWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "par";
        final Pagination pagination = new Pagination(1, 1);
        final List<RefRegionMsg> refRegions = new ArrayList<RefRegionMsg>();
        RefRegionMsg refRegionMsg = new RefRegionMsg();
        refRegions.add(refRegionMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refRegionBOConverter", refRegionBOConverter);
                Deencapsulation.setField(referenceService, refRegionDAO);

                final List<RefRegionBO> refRegionsBO = new ArrayList<RefRegionBO>();
                RefRegionBO refRegionBO = new RefRegionBO();
                refRegionsBO.add(refRegionBO);

                refRegionDAO.getRefRegionsByName(cityName, pagination);
                returns(refRegionsBO);

                refRegionBOConverter.convert(refRegionsBO);
                returns(refRegions);
            }
        };
        List<RefRegionMsg> cities = referenceService.getRefRegionsByName(cityName, pagination);
        Assert.assertTrue(refRegions.size() == 1);
        Assert.assertEquals(refRegionMsg, cities.get(0));

    }

    @Test
    public void getRefCountriesByName() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "ile";
        final List<RefCountryMsg> refCities = new ArrayList<RefCountryMsg>();
        RefCountryMsg refCityMsg = new RefCountryMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCountryBOConverter", refCountryBOConverter);
                Deencapsulation.setField(referenceService, refCountryDAO);

                List<RefCountryBO> refCitiesBO = new ArrayList<RefCountryBO>();
                final RefCountryBO refCityBO = new RefCountryBO();
                refCitiesBO.add(refCityBO);

                refCountryDAO.getRefCountriesByName(cityName, null);
                returns(refCitiesBO);

                refCountryBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCountryMsg> cities = referenceService.getRefCountriesByName(cityName, null);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCountriesByNameWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "par";
        final Pagination pagination = new Pagination(1, 1);
        final List<RefCountryMsg> refCities = new ArrayList<RefCountryMsg>();
        RefCountryMsg refCityMsg = new RefCountryMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCountryBOConverter", refCountryBOConverter);
                Deencapsulation.setField(referenceService, refCountryDAO);

                List<RefCountryBO> refCitiesBO = new ArrayList<RefCountryBO>();
                final RefCountryBO refCityBO = new RefCountryBO();
                refCitiesBO.add(refCityBO);

                refCountryDAO.getRefCountriesByName(cityName, pagination);
                returns(refCitiesBO);

                refCountryBOConverter.convert(refCitiesBO);
                returns(refCities);
            }
        };
        List<RefCountryMsg> cities = referenceService.getRefCountriesByName(cityName, pagination);
        Assert.assertTrue(refCities.size() == 1);
        Assert.assertEquals(refCityMsg, cities.get(0));

    }

    @Test
    public void getRefCountryByIsoCodeA2() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String codeA2 = "par";
        final RefCountryMsg refCityMsg = new RefCountryMsg();

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCountryBOConverter", refCountryBOConverter);
                Deencapsulation.setField(referenceService, refCountryDAO);

                final RefCountryBO refCityBO = new RefCountryBO();

                refCountryDAO.getRefCountryByIsoCodeA2(codeA2);
                returns(refCityBO);

                refCountryBOConverter.convert(refCityBO);
                returns(refCityMsg);
            }
        };
        Assert.assertEquals(refCityMsg, referenceService.getRefCountryByIsoCodeA2(codeA2));

    }

    @Test
    public void getRefCountryByIsoCodeA3() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String codeA3 = "par";
        final RefCountryMsg refCityMsg = new RefCountryMsg();

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, "refCountryBOConverter", refCountryBOConverter);
                Deencapsulation.setField(referenceService, refCountryDAO);

                final RefCountryBO refCityBO = new RefCountryBO();

                refCountryDAO.getRefCountryByIsoCodeA3(codeA3);
                returns(refCityBO);

                refCountryBOConverter.convert(refCityBO);
                returns(refCityMsg);
            }
        };
        Assert.assertEquals(refCityMsg, referenceService.getRefCountryByIsoCodeA3(codeA3));

    }

    @Test
    public void getRefStatesByCountryNoCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefStateMsg> refCities = new ArrayList<RefStateMsg>();
        RefStateMsg refCityMsg = new RefStateMsg();
        refCities.add(refCityMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, refStateDAO);
                Deencapsulation.setField(referenceService, "refStateBOConverter", refStateBOConverter);

                refStateDAO.getRefStatesByCountry(refCountryId, null);
                returns(null);

                refStateBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(referenceService.getRefStatesByCountry(refCountryId, null));
    }

    @Test
    public void getRefStatesByCountry() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final List<RefStateMsg> refStates = new ArrayList<RefStateMsg>();
        RefStateMsg refStateMsg = new RefStateMsg();
        refStates.add(refStateMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, refStateDAO);
                Deencapsulation.setField(referenceService, "refStateBOConverter", refStateBOConverter);

                final List<RefStateBO> refStatesBO = new ArrayList<RefStateBO>();
                RefStateBO refStateBO = new RefStateBO();
                refStatesBO.add(refStateBO);

                refStateDAO.getRefStatesByCountry(refCountryId, null);
                returns(refStatesBO);

                refStateBOConverter.convert(refStatesBO);
                returns(refStates);
            }
        };
        List<RefStateMsg> cities = referenceService.getRefStatesByCountry(refCountryId, null);
        Assert.assertTrue(cities.size() == 1);
        Assert.assertEquals(refStateMsg, cities.get(0));

    }

    @Test
    public void getRefStatesByCountryWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final Integer refCountryId = 12543;
        final Pagination pagination = new Pagination(1, 1);
        final List<RefStateMsg> refStates = new ArrayList<RefStateMsg>();
        RefStateMsg refStateMsg = new RefStateMsg();
        refStates.add(refStateMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, refStateDAO);
                Deencapsulation.setField(referenceService, "refStateBOConverter", refStateBOConverter);

                final List<RefStateBO> refStatesBO = new ArrayList<RefStateBO>();
                RefStateBO refStateBO = new RefStateBO();
                refStatesBO.add(refStateBO);

                refStateDAO.getRefStatesByCountry(refCountryId, pagination);
                returns(refStatesBO);

                refStateBOConverter.convert(refStatesBO);
                returns(refStates);
            }
        };
        List<RefStateMsg> cities = referenceService.getRefStatesByCountry(refCountryId, pagination);
        Assert.assertTrue(cities.size() == 1);
        Assert.assertEquals(refStateMsg, cities.get(0));

    }

    @Test
    public void getRefStatesByName() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "ile";
        final List<RefStateMsg> refStates = new ArrayList<RefStateMsg>();
        RefStateMsg refStateMsg = new RefStateMsg();
        refStates.add(refStateMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, refStateDAO);
                Deencapsulation.setField(referenceService, "refStateBOConverter", refStateBOConverter);

                final List<RefStateBO> refStatesBO = new ArrayList<RefStateBO>();
                RefStateBO refStateBO = new RefStateBO();
                refStatesBO.add(refStateBO);

                refStateDAO.getRefStatesByName(cityName, null);
                returns(refStatesBO);

                refStateBOConverter.convert(refStatesBO);
                returns(refStates);
            }
        };
        List<RefStateMsg> cities = referenceService.getRefStatesByName(cityName, null);
        Assert.assertTrue(cities.size() == 1);
        Assert.assertEquals(refStateMsg, cities.get(0));

    }

    @Test
    public void getRefStatesByNameWithPagination() throws BusinessException {
        final IReferenceService referenceService = new ReferenceService();
        final String cityName = "par";
        final Pagination pagination = new Pagination(1, 1);
        final List<RefStateMsg> refStates = new ArrayList<RefStateMsg>();
        RefStateMsg refStateMsg = new RefStateMsg();
        refStates.add(refStateMsg);

        new Expectations() {

            {
                Deencapsulation.setField(referenceService, refStateDAO);
                Deencapsulation.setField(referenceService, "refStateBOConverter", refStateBOConverter);

                final List<RefStateBO> refStatesBO = new ArrayList<RefStateBO>();
                RefStateBO refStateBO = new RefStateBO();
                refStatesBO.add(refStateBO);

                refStateDAO.getRefStatesByName(cityName, pagination);
                returns(refStatesBO);

                refStateBOConverter.convert(refStatesBO);
                returns(refStates);
            }
        };
        List<RefStateMsg> cities = referenceService.getRefStatesByName(cityName, pagination);
        Assert.assertTrue(cities.size() == 1);
        Assert.assertEquals(refStateMsg, cities.get(0));

    }

    @Test
    public void getRefProducts() throws BusinessException {
        final IReferenceService service = new ReferenceService();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                refProductDAO.getRefProducts(ProductType.FRUIT.toInt(), null);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProducts(ProductType.FRUIT, null);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }

    @Test
    public void getRefProductsWithTech() throws BusinessException {
        final IReferenceService service = new ReferenceService();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        QueryCriteria<RefProductCriteriaColumn> criteriaStatusEqualSetted = new QueryCriteria<RefProductCriteriaColumn>(RefProductCriteriaColumn.PROTEIN, CriteriaOperation.EQUAL, 12L);

        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> lTechnicalInformation = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
        List<QueryCriteria<RefProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<RefProductCriteriaColumn>>();
        lTechnicalInformation.setCriterias(criterias);
        lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);
        lTechnicalInformation.setOrder(new ResultOrder<RefProductOrderColumn>(RefProductOrderColumn.PROTEIN, OrderWay.DESC));
        lTechnicalInformation.setPagination(new Pagination(1, 1));

        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                refProductDAO.getRefProducts(ProductType.FRUIT.toInt(), lTechnicalInformation);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProducts(ProductType.FRUIT, lTechnicalInformation);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }

    @Test()
    public void getRefProductsByName() throws BusinessException {
        final IReferenceService service = new ReferenceService();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                refProductDAO.getRefProductsByName("test", null);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProductsByName("test", null);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }

    @Test()
    public void getRefProductsByNameWithTechInfo() throws BusinessException {
        final IReferenceService service = new ReferenceService();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> techInfo = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                refProductDAO.getRefProductsByName("test", techInfo);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProductsByName("test", techInfo);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }

    @Test
    public void getRefProductsByReapSeasonValid() throws BusinessException {

        final List<Month> listMonth = new ArrayList<Month>();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        listMonth.add(Month.JANUARY);

        final IReferenceService service = new ReferenceService();
        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                List<Integer> listMonths = new ArrayList<Integer>();
                listMonths.add(Month.JANUARY.toInt());
                refProductDAO.getRefProductsByReapSeason(listMonths, null);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProductsByReapSeason(listMonth, null);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }

    @Test
    public void getRefProductsBySeedSeasonValid() throws BusinessException {

        List<Month> listMonth = new ArrayList<Month>();
        final List<RefProductMsg> productsExp = new ArrayList<RefProductMsg>();
        RefProductMsg productExp = new RefProductMsg();
        productsExp.add(productExp);

        listMonth.add(Month.AUGUST);

        final IReferenceService service = new ReferenceService();
        new Expectations() {

            {
                Deencapsulation.setField(service, refProductDAO);
                Deencapsulation.setField(service, "refProductBOConverter", refProductBOConverter);

                List<RefProductBO> productsBO = new ArrayList<RefProductBO>();
                RefProductBO productBO = new RefProductBO();
                productsBO.add(productBO);

                List<Integer> listMonths = new ArrayList<Integer>();
                listMonths.add(Month.AUGUST.toInt());
                refProductDAO.getRefProductsBySeedSeason(listMonths, null);
                returns(productsBO);

                refProductBOConverter.convert(productsBO);
                returns(productsExp);
            }
        };

        List<RefProductMsg> products = service.getRefProductsBySeedSeason(listMonth, null);
        Assert.assertTrue(products.size() == 1);
        Assert.assertEquals(productExp, products.get(0));
    }
}
