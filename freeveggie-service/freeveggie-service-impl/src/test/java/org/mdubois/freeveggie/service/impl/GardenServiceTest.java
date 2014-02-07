package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.impl.GardenDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.api.IGardenService;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.GardenMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class GardenServiceTest {

    /**
     * {@link IGardenService}
     */
    private static IGardenService gardenService = new GardenService();

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    @Test(expected = BusinessException.class)
    public void getGardenByIdExc() throws BusinessException {

        final Long gardenId = 1L;

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockGardenDAO.get(gardenId);
                repeats(1);
                returns(null);
            }
        };

        gardenService.getGardenById(1L);
    }

    @Test
    public void getGardenById() throws BusinessException {

        final Long gardenId = 1L;
        final GardenBO gardenExpected = new GardenBO();
        gardenExpected.setId(gardenId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                mockGardenDAO.get(gardenId);
                returns(gardenExpected);

                mockGardenBOConverter.convert(gardenExpected);
            }
        };

        gardenService.getGardenById(1L);
    }

    @Test
    public void getGardenByUserExc() throws BusinessException {

        final Long userId = 1L;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;
            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByUser(userId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(gardenService.getGardenByUser(userId, null));
    }

    @Test
    public void getGardenByUser() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByUser(userId, with(new TechnicalInformationMatcher(techInfo)));
                List<GardenBO> resList = new ArrayList<GardenBO>();

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);


                GardenBO garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.CREATED);
                resList.add(garden);

                garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.CREATED);
                resList.add(garden);

                returns(resList);
                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByUser(1L, null);

    }

    @Test
    public void getGardenByUserEmptyTechnicalInformation() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByUser(userId, with(new TechnicalInformationMatcher(techInfo)));
                List<GardenBO> resList = new ArrayList<GardenBO>();

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);

                GardenBO garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.CREATED);
                resList.add(garden);

                garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.CREATED);
                resList.add(garden);

                returns(resList);
                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByUser(1L, new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>());

    }

    /**
     * @throws BusinessException
     */
    @Test
    public void getDeletedGardenByUser() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.DELETED);
        techInfo.addCriteria(gardenCriteria);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                mockGardenDAO.getGardenByUser(userId, with(new TechnicalInformationMatcher(techInfo)));
                List<GardenBO> resList = new ArrayList<GardenBO>();

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);

                GardenBO garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.DELETED);
                resList.add(garden);

                garden = new GardenBO();
                garden.setOwner(user);
                garden.setStatus(Status.DELETED);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByUser(1L, techInfo);
    }

    @Test
    public void getGardenByCityBadCity() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByCity(cityId, prodId, null));
    }

    @Test
    public void getGardenByCityBadProduct() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        gardenService.getGardenByCity(cityId, prodId, null);
    }

    @Test()
    public void getGardenByCity() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);



                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByCityAndProduct(prodId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);
                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByCity(cityId, prodId, null);
    }

    @Test()
    public void getGardenByCityEmptyTechnicalInformation() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByCityAndProduct(cityId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);
                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByCity(cityId, prodId, new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>());
    }

    @Test
    public void getGardenByRegionBadRegion() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByRegion(regionId, prodId, null));
    }

    @Test
    public void getGardenByRegionBadProduct() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;

        new Expectations() {

             @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByRegion(regionId, prodId, null));
    }

    @Test()
    public void getGardenByRegion() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByRegion(regionId, prodId, null);
    }

    @Test()
    public void getGardenByRegionEmptyTechnicalInformation() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByRegionAndProduct(regionId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByRegion(regionId, prodId, new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>());
    }

    @Test
    public void getGardenByStateBadState() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        gardenService.getGardenByState(stateId, prodId, null);
    }

    @Test
    public void getGardenByStateBadProduct() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByState(stateId, prodId, null));
    }

    @Test()
    public void getGardenByState() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByState(stateId, prodId, null);
    }

    @Test()
    public void getGardenByStateEmptyTechnicalInformation() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByStateAndProduct(stateId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByState(stateId, prodId, new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>());
    }

    @Test
    public void getGardenByCountryBadCountry() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByCountry(countryId, prodId, null));
    }

    @Test
    public void getGardenByCountryBadProduct() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;

        new Expectations() {

            @Mocked
            private IGardenDAO gardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> gardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", gardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", gardenBOConverter);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                gardenDAO.getGardenByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));
                returns(null);

                gardenBOConverter.convert((List) null);
                returns(null);

            }
        };
        Assert.assertNull(gardenService.getGardenByCountry(countryId, prodId, null));
    }

    @Test()
    public void getGardenByCountry() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByCountry(countryId, prodId, null);

    }

    @Test()
    public void getGardenByCountryEmptyTechnicalInformation() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            @Mocked
            private GardenDAO mockGardenDAO;
            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenBOConverter", mockGardenBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> techInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                QueryCriteria<GardenCriteriaColumn> gardenCriteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(gardenCriteria);

                mockGardenDAO.getGardenByCountryAndProduct(countryId, prodId, with(new TechnicalInformationMatcher(techInfo)));

                List<GardenBO> resList = new ArrayList<GardenBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> gardenProducts = new ArrayList<ProductBO>();
                ProductBO gardenProduct = new ProductBO();
                gardenProduct.setReferenceProduct(refProduct);
                gardenProducts.add(gardenProduct);

                GardenBO garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                garden = new GardenBO();
                garden.setAddress(address);
                garden.setProducts(gardenProducts);
                resList.add(garden);

                returns(resList);

                mockGardenBOConverter.convert(resList);
            }
        };
        gardenService.getGardenByCountry(countryId, prodId, new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>());

    }
}
