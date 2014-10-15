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
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.ProductDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.service.api.IProductService;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ProductServiceTest {

    @Mocked
    private ProductDAO mockProductDAO;
    @Mocked
    private Converter<ProductMsg, ProductBO> mockProductBOConverter;

    /**
     * {@link IProductService}
     */
    private static IProductService productService = new ProductService();

//    @After
//    public void tearDown() throws Exception {
//        Mockit.restoreAllOriginalDefinitions();
//    }
    // <editor-fold defaultstate="collapsed" desc="Test method get by id">
    @Test(expected = BusinessException.class)
    public void getProductByIdExc() throws BusinessException {

        final Long productId = 1L;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                mockProductDAO.get(productId);
                times = 1;
                returns(null);
            }
        };

        productService.getProductById(1L);
    }

    @Test
    public void getProductById() throws BusinessException {

        final Long productId = 1L;
        final ProductBO productExpected = new ProductBO();
        productExpected.setId(productId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                mockProductDAO.get(productId);
                returns(productExpected);

                mockProductBOConverter.convert(productExpected);
            }
        };

        productService.getProductById(1L);
    }

// </editor-fold>
    @Test
    public void getProductByUserExc() throws BusinessException {

        final Long userId = 1L;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByUser(userId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        productService.getProductByUser(userId, null);
    }

    @Test
    public void getProductByUser() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByUser(userId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                List<ProductBO> resList = new ArrayList<ProductBO>();

                ProductBO product = new ProductBO();

                GardenBO garden = new GardenBO();
                garden.setOwner(user);

                product.setGarden(garden);
                resList.add(product);

                product = new ProductBO();
                product.setGarden(garden);
                resList.add(product);

                returns(resList);
                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByUser(1L, null);

    }

    @Test
    public void getProductByUserEmptyTechnicalInformation() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByUser(userId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                List<ProductBO> resList = new ArrayList<ProductBO>();

                ProductBO product = new ProductBO();

                GardenBO garden = new GardenBO();
                garden.setOwner(user);

                product.setGarden(garden);
                resList.add(product);

                product = new ProductBO();
                product.setGarden(garden);
                resList.add(product);

                returns(resList);
                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByUser(1L, new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>());

    }

    /**
     * @throws BusinessException
     */
    @Test
    public void getDeletedProductByUser() throws BusinessException {

        final Long userId = 1L;
        PartialUserBO user = new PartialUserBO();
        user.setId(userId);
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.DELETED);
        techInfo.addCriteria(productCriteria);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                PartialUserBO user = new PartialUserBO();
                user.setId(userId);

                mockProductDAO.getProductByUser(userId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                List<ProductBO> resList = new ArrayList<ProductBO>();

                GardenBO garden = new GardenBO();
                garden.setOwner(user);
                ProductBO product = new ProductBO();
                product.setGarden(garden);
                resList.add(product);

                product = new ProductBO();
                product.setGarden(garden);
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByUser(1L, techInfo);
    }

    @Test
    public void getProductByCityBadCity() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCityAndRefProduct(cityId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByCity(cityId, prodId, null));
    }

    @Test
    public void getProductByCityBadProduct() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCityAndRefProduct(cityId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByCity(cityId, prodId, null));
    }

    @Test()
    public void getProductByCity() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCityAndRefProduct(cityId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);
                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByCity(cityId, prodId, null);
    }

    @Test()
    public void getProductByCityEmptyTechnicalInformation() throws BusinessException {

        final Integer cityId = 1;
        final Integer prodId = 1;
        RefCityBO expectedCity = new RefCityBO();
        expectedCity.setId(cityId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefCityBO city = new RefCityBO();
                city.setId(cityId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCityAndRefProduct(cityId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);
                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByCity(cityId, prodId, new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>());
    }

    @Test
    public void getProductByRegionBadRegion() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByRegionAndRefProduct(regionId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByRegion(regionId, prodId, null));
    }

    @Test
    public void getProductByRegionBadProduct() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByRegionAndRefProduct(regionId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByRegion(regionId, prodId, null));
    }

    @Test()
    public void getProductByRegion() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByRegionAndRefProduct(regionId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByRegion(regionId, prodId, null);
    }

    @Test()
    public void getProductByRegionEmptyTechnicalInformation() throws BusinessException {

        final Integer regionId = 1;
        final Integer prodId = 1;
        RefRegionBO expectedRegion = new RefRegionBO();
        expectedRegion.setId(regionId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefRegionBO region = new RefRegionBO();
                region.setId(regionId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByRegionAndRefProduct(regionId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByRegion(regionId, prodId, new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>());
    }

    @Test
    public void getProductByStateBadState() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByStateAndRefProduct(stateId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByState(stateId, prodId, null));
    }

    @Test
    public void getProductByStateBadProduct() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByStateAndRefProduct(stateId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByState(stateId, prodId, null));
    }

    @Test()
    public void getProductByState() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByStateAndRefProduct(stateId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByState(stateId, prodId, null);
    }

    @Test()
    public void getProductByStateEmptyTechnicalInformation() throws BusinessException {

        final Integer stateId = 1;
        final Integer prodId = 1;
        RefStateBO expectedState = new RefStateBO();
        expectedState.setId(stateId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefStateBO state = new RefStateBO();
                state.setId(stateId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByStateAndRefProduct(stateId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByState(stateId, prodId, new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>());
    }

    @Test
    public void getProductByCountryBadCountry() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCountryAndRefProduct(countryId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByCountry(countryId, prodId, null));
    }

    @Test
    public void getProductByCountryBadProduct() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCountryAndRefProduct(countryId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(null);

                mockProductBOConverter.convert((List) null);
                returns(null);
            }
        };
        Assert.assertNull(productService.getProductByCountry(countryId, prodId, null));
    }

    @Test()
    public void getProductByCountry() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCountryAndRefProduct(countryId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByCountry(countryId, prodId, null);

    }

    @Test()
    public void getProductByCountryEmptyTechnicalInformation() throws BusinessException {

        final Integer countryId = 1;
        final Integer prodId = 1;
        RefCountryBO expectedCountry = new RefCountryBO();
        expectedCountry.setId(countryId);
        RefProductBO expectedRefProduct = new RefProductBO();
        expectedRefProduct.setId(prodId);

        new Expectations() {

            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productBOConverter", mockProductBOConverter);

                RefCountryBO country = new RefCountryBO();
                country.setId(countryId);

                RefProductBO refProduct = new RefProductBO();
                refProduct.setId(prodId);

                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> techInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                QueryCriteria<ProductCriteriaColumn> productCriteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL, Status.CREATED);
                techInfo.addCriteria(productCriteria);

                mockProductDAO.getProductByCountryAndRefProduct(countryId, prodId, with(techInfo, new TechnicalInformationMatcher(techInfo)));

                List<ProductBO> resList = new ArrayList<ProductBO>();

                AddressBO address = new AddressBO();
                RefCityBO city = new RefCityBO();
                RefRegionBO region = new RefRegionBO();
                RefStateBO state = new RefStateBO();
                state.setCountry(country);
                region.setState(state);
                city.setRegion(region);
                address.setCity(city);

                List<ProductBO> productProducts = new ArrayList<ProductBO>();
                ProductBO productProduct = new ProductBO();
                productProduct.setReferenceProduct(refProduct);
                productProducts.add(productProduct);

                ProductBO product = new ProductBO();
                resList.add(product);

                product = new ProductBO();
                resList.add(product);

                returns(resList);

                mockProductBOConverter.convert(resList);
            }
        };
        productService.getProductByCountry(countryId, prodId, new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>());

    }
}
