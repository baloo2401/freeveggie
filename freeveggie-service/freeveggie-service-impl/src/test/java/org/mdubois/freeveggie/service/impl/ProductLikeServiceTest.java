package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
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
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IProductLikeDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.service.matcher.ProductLikeBOMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class ProductLikeServiceTest {

    /**
     * {@link Criteria}
     */
    private static QueryCriteria<ProductLikeCriteriaColumn> criteriaStatusEqualSetted = new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.SETTED);

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    // <editor-fold defaultstate="collapsed" desc="Unlike test">
    /**
     * Test we get an exception when we try to unlike that doesn't exist.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unlikeNotExit() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            {

                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);

                mockProductLikeDAO.get(pLikeId);
                repeats(1);
                returns(null);
            }
        };

        productService.unlike(pLikeId);
    }

    /**
     * Test unlike works
     *
     * @throws BusinessException
     */
    @Test
    public void unlikeStatusSetted() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            {

                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                ProductLikeBO productLikeBO = new ProductLikeBO();
                mockProductLikeDAO.get(pLikeId);
                productLikeBO.setId(pLikeId);
                productLikeBO.setCreationDate(new Date());
                productLikeBO.setProduct(productBO);
                productLikeBO.setWriter(userBO);
                productLikeBO.setStatus(EvaluationStatus.SETTED);
                repeats(1);
                returns(productLikeBO);

                ProductLikeBO newProductLikeBO = new ProductLikeBO();
                newProductLikeBO.setId(pLikeId);
                newProductLikeBO.setCreationDate(new Date());
                newProductLikeBO.setProduct(productBO);
                newProductLikeBO.setWriter(userBO);
                newProductLikeBO.setStatus(EvaluationStatus.REMOVED);

                mockProductLikeDAO.save(with(new ProductLikeBOMatcher(newProductLikeBO)));
            }
        };

        productService.unlike(pLikeId);
    }

    /**
     * Test that we have an exception if we try to unlike a removed one.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unlikeStatusArchived() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                ProductBO productBO = new ProductBO();
                productBO.setId(1L);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                ProductLikeBO productLikeBO = new ProductLikeBO();
                mockProductLikeDAO.get(pLikeId);
                productLikeBO.setId(pLikeId);
                productLikeBO.setCreationDate(new Date());
                productLikeBO.setProduct(productBO);
                productLikeBO.setWriter(userBO);
                productLikeBO.setStatus(EvaluationStatus.ARCHIVED);
                repeats(1);
                returns(productLikeBO);
                mockProductLikeBOConverter.convert(productLikeBO);
            }
        };

        productService.unlike(pLikeId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Like">
    /**
     * Test that that you can like with an unknown user.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeUnknownUser() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pWriterId = 1L;
        final Long pProductId = 1L;


        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pProductLikeMsg.setWriter(writer);
        ProductMsg product = new ProductMsg();
        product.setId(pProductId);
        pProductLikeMsg.setProduct(product);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;

            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);

                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(null);
            }
        };

        productService.like(pProductLikeMsg);
    }

    /**
     * Test that that you can like with an unexisting product.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeUnknownProduct() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pWriterId = 1L;
        final Long pProductId = 1L;


        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(pProductId);
        pProductLikeMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pProductLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductDAO mockProductDAO;

            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);

                PartialUserBO userBO = new PartialUserBO();
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                mockProductDAO.get(pProductId);
                returns(null);
            }
        };

        productService.like(pProductLikeMsg);
    }

    /**
     * Conrole that we can like twice the same product.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeOnExistingLike() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pWriterId = 1L;
        final Long pProductId = 1L;


        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(pProductId);
        pProductLikeMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pProductLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                PartialUserBO owner = new PartialUserBO();
                owner.setId(1234L);

                GardenBO garden = new GardenBO();
                garden.setOwner(owner);

                ProductBO productBO = new ProductBO();
                productBO.setId(pProductId);
                productBO.setGarden(garden);
                mockProductDAO.get(pProductId);
                returns(productBO);

                ProductLikeBO productLikeBO = new ProductLikeBO();
                mockProductLikeDAO.getProductLikeByUserAndProduct(userBO.getId(), productBO.getId());
                productLikeBO.setId(1L);
                productLikeBO.setStatus(EvaluationStatus.ARCHIVED);
                productLikeBO.setWriter(userBO);
                productLikeBO.setProduct(productBO);
                returns(productLikeBO);
                mockProductLikeBOConverter.convert(productLikeBO);
            }
        };

        productService.like(pProductLikeMsg);
    }

    /**
     * Control that we can like it's own product
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeOnOwnProduct() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pWriterId = 1L;
        final Long pProductId = 1L;


        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(pProductId);
        pProductLikeMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pProductLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;
            @Mocked
            private BusinessObjectConverter<ProductLikeBO,ProductLikeMsg> mockProductLikeMsgConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeMsgConverter", mockProductLikeMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                PartialUserBO owner = new PartialUserBO();
                owner.setId(pWriterId);

                GardenBO garden = new GardenBO();
                garden.setOwner(owner);

                ProductBO productBO = new ProductBO();
                productBO.setId(pProductId);
                productBO.setGarden(garden);
                mockProductDAO.get(pProductId);
                returns(productBO);

            }
        };

        productService.like(pProductLikeMsg);
    }

    /**
     * Control that like works
     *
     * @throws BusinessException
     */
    @Test
    public void like() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pWriterId = 1L;
        final Long pProductId = 1L;


        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(pProductId);
        pProductLikeMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pProductLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;
            @Mocked
            private BusinessObjectConverter<ProductLikeBO,ProductLikeMsg> mockProductLikeMsgConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeMsgConverter", mockProductLikeMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                PartialUserBO owner = new PartialUserBO();
                owner.setId(1234L);

                GardenBO garden = new GardenBO();
                garden.setOwner(owner);

                ProductBO productBO = new ProductBO();
                productBO.setId(pProductId);
                productBO.setGarden(garden);
                mockProductDAO.get(pProductId);
                returns(productBO);

                mockProductLikeDAO.getProductLikeByUserAndProduct(userBO.getId(), productBO.getId());
                returns(null);

                ProductLikeBO newProductLikeBO = new ProductLikeBO();
                newProductLikeBO.setStatus(EvaluationStatus.SETTED);
                newProductLikeBO.setWriter(userBO);
                newProductLikeBO.setProduct(productBO);


                //TODO MDU : control cration date

                mockProductLikeMsgConverter.createNew(pProductLikeMsg);
                returns(newProductLikeBO);

                mockProductLikeDAO.save(newProductLikeBO);
            }
        };

        productService.like(pProductLikeMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Product Like test">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getProductLikeUserNotExit() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IProductLikeDAO mockProductLikeDAO;
            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                repeats(1);
                returns(null);

                mockProductLikeBOConverter.convert((List)null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductLike(pProductId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithNullTechnicalInformation() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithEmptyCriteria() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithCriteriaWithStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that don't have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithCriteriaButNotStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {
                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);


                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithPagination() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;
            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;

            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);


                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);


            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWithOrder() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pProductId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<ProductLikeOrderColumn>(ProductLikeOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IProductDAO mockProductDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "productDAO", mockProductDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByProduct(pProductId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLike(pProductId, pTechnicalInformation);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get product like write test">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getProductLikeWriteUserNotExist() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IProductLikeDAO productLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "productLikeDAO", productLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductLikeBO> productLikeBOs = null;
                productLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                repeats(1);
                returns(productLikeBOs);

                mockProductLikeBOConverter.convert(productLikeBOs);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithNullTechnicalInformation() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithEmptyCriteria() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithCriteriaWithStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithCriteriaButNotStatus() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithPagination() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getProductLikeWriteWithOrder() throws BusinessException {
        final ProductService productService = new ProductService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<ProductLikeOrderColumn>(ProductLikeOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IProductLikeDAO mockProductLikeDAO;

            @Mocked
            private Converter<ProductLikeMsg, ProductLikeBO> mockProductLikeBOConverter;
            {

                Deencapsulation.setField(productService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(productService, "productLikeDAO", mockProductLikeDAO);
                Deencapsulation.setField(productService, "productLikeBOConverter", mockProductLikeBOConverter);

                List<ProductLikeBO> productLikeBOs = new ArrayList<ProductLikeBO>();
                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBOs.add(productLikeBO);
                mockProductLikeDAO.getProductLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(productLikeBOs);
                mockProductLikeBOConverter.convert(productLikeBOs);
            }
        };

        productService.getProductLikeWrite(pUserWriterId, pTechnicalInformation);
    }
    // </editor-fold>
}
