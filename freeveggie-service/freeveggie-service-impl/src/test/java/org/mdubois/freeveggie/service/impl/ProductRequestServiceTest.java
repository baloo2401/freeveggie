package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IProductRequestDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
@RunWith(JMockit.class)
public class ProductRequestServiceTest {

    private static QueryCriteria<ProductRequestCriteriaColumn> criteriaStatusEqualAccepted = new QueryCriteria<ProductRequestCriteriaColumn>(ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL, RequestStatus.ACCEPTED);

    // <editor-fold defaultstate="collapsed" desc="Test send request">
    @Test(expected = BusinessException.class)
    public void sendRequestNoUser() throws BusinessException {
        final ProductService productService = new ProductService();


        final ProductRequestMsg pProductRequestMsg = new ProductRequestMsg();
        ProductMsg product = new ProductMsg();
        final long productId = 1L;
        product.setId(productId);
        pProductRequestMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        final long userId = 13L;
        writer.setId(userId);
        pProductRequestMsg.setRequester(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO userPartialDAO;
            @Mocked
            private IProductDAO productDAO;

            {
                Deencapsulation.setField(productService, userPartialDAO);
                Deencapsulation.setField(productService, productDAO);

                userPartialDAO.get(userId);
                repeats(1);
                returns(null);
            }
        };

        productService.send(pProductRequestMsg);
    }

    @Test(expected = BusinessException.class)
    public void sendRequestNoProduct() throws BusinessException {
        final ProductService productService = new ProductService();


        final ProductRequestMsg pProductRequestMsg = new ProductRequestMsg();
        ProductMsg product = new ProductMsg();
        final long productId = 1L;
        product.setId(productId);
        pProductRequestMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        final long userId = 13L;
        writer.setId(userId);
        pProductRequestMsg.setRequester(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO userPartialDAO;
            @Mocked
            private IProductDAO productDAO;

            {
                Deencapsulation.setField(productService, userPartialDAO);
                Deencapsulation.setField(productService, productDAO);

                PartialUserBO partialUserBO = new PartialUserBO();
                userPartialDAO.get(userId);
                repeats(1);
                returns(partialUserBO);

                productDAO.get(productId);
                returns(null);
            }
        };

        productService.send(pProductRequestMsg);
    }

    @Test
    public void sendRequest() throws BusinessException {
        final ProductService productService = new ProductService();


        final ProductRequestMsg pProductRequestMsg = new ProductRequestMsg();
        ProductMsg product = new ProductMsg();
        final long productId = 1L;
        product.setId(productId);
        pProductRequestMsg.setProduct(product);
        PartialUserMsg writer = new PartialUserMsg();
        final long userId = 13L;
        writer.setId(userId);
        pProductRequestMsg.setRequester(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO userPartialDAO;
            @Mocked
            private IProductDAO productDAO;
            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private INotificationDAO notificationDAO;
            @Mocked
            private BusinessObjectConverter<ProductRequestBO, ProductRequestMsg> productRequestMsgConverter;

            {
                Deencapsulation.setField(productService, userPartialDAO);
                Deencapsulation.setField(productService, productDAO);
                Deencapsulation.setField(productService, notificationDAO);
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestMsgConverter", productRequestMsgConverter);

                PartialUserBO partialUserBO = new PartialUserBO();
                userPartialDAO.get(userId);
                repeats(1);
                returns(partialUserBO);

                ProductBO productBO = new ProductBO();
                productDAO.get(productId);
                returns(productBO);

                ProductRequestBO productRequestBO = new ProductRequestBO();
                productRequestMsgConverter.createNew(pProductRequestMsg);
                returns(productRequestBO);

                productRequestDAO.save(productRequestBO);

                notificationDAO.sendProductRequestNotice(productRequestBO);
            }
        };

        productService.send(pProductRequestMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test accept request">
    @Test
    public void acceptRequest() throws BusinessException {
        final ProductService productService = new ProductService();


        final long productRequestId = 1L;
        final String message = "message";

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private SystemTime systemTime;
            {
                Deencapsulation.setField(productService, productRequestDAO);


                ProductRequestBO productRequestBO = new ProductRequestBO();
                productRequestDAO.get(productRequestId);
                returns(productRequestBO);

                productRequestBO.setAnswer(message);
                SystemTime.asDate();
                returns(new Date());

                productRequestBO.setStatus(RequestStatus.ACCEPTED);

                //TODO : check attribute of object with with utils
                productRequestDAO.update(productRequestBO);
            }
        };

        productService.accept(productRequestId, null);
    }

    @Test(expected = BusinessException.class)
    public void acceptRequestNoRequest() throws BusinessException {
        final ProductService productService = new ProductService();


        final long productRequestId = 1L;
        final String message = "message";

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private SystemTime systemTime;
            {
                Deencapsulation.setField(productService, productRequestDAO);


                ProductRequestBO productRequestBO = new ProductRequestBO();
                productRequestDAO.get(productRequestId);
                returns(null);
            }
        };

        productService.accept(productRequestId, null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test refuse request">
    @Test
    public void refuseRequest() throws BusinessException {
        final ProductService productService = new ProductService();


        final long productRequestId = 1L;
        final String message = "message";

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private SystemTime systemTime;
            {
                Deencapsulation.setField(productService, productRequestDAO);


                ProductRequestBO productRequestBO = new ProductRequestBO();
                productRequestDAO.get(productRequestId);
                returns(productRequestBO);

                productRequestBO.setAnswer(message);
                SystemTime.asDate();
                returns(new Date());

                productRequestBO.setStatus(RequestStatus.REFUSED);

                //TODO : check attribute of object with with utils
                productRequestDAO.update(productRequestBO);
            }
        };

        productService.refuse(productRequestId, null);
    }

    @Test(expected = BusinessException.class)
    public void refuseRequestNoRequest() throws BusinessException {
        final ProductService productService = new ProductService();


        final long productRequestId = 1L;
        final String message = "message";

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private SystemTime systemTime;
            {
                Deencapsulation.setField(productService, productRequestDAO);


                ProductRequestBO productRequestBO = new ProductRequestBO();
                productRequestDAO.get(productRequestId);
                returns(null);
            }
        };

        productService.refuse(productRequestId, null);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test getProductRequestByProduct">
    @Test
    public void testGetProductRequestByProduct() throws BusinessException {
        final ProductService productService = new ProductService();

        final long productId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestByProduct(productId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productRequestBOs);

                productRequestBOConverter.convert(productRequestBOs);
            }
        };

        productService.getProductRequestByProduct(productId, techInfo);
    }

    @Test
    public void testGetProductRequestByProductNoProduct() throws BusinessException {
        final ProductService productService = new ProductService();

        final long productId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestByProduct(productId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                productRequestBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductRequestByProduct(productId, techInfo));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test getProductRequestByGarden">
    @Test
    public void testGetProductRequestByGarden() throws BusinessException {
        final ProductService productService = new ProductService();

        final long gardenId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestByGarden(gardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productRequestBOs);

                productRequestBOConverter.convert(productRequestBOs);
            }
        };

        productService.getProductRequestByGarden(gardenId, techInfo);
    }

    @Test
    public void testGetProductRequestByGardenNoPGarden() throws BusinessException {
        final ProductService productService = new ProductService();

        final long gardenId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestByGarden(gardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                productRequestBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductRequestByGarden(gardenId, techInfo));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test getProductRequestReceived">
    @Test
    public void testGetProductRequestReceive() throws BusinessException {
        final ProductService productService = new ProductService();

        final long userPartialId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestReceived(userPartialId,  with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productRequestBOs);

                productRequestBOConverter.convert(productRequestBOs);
            }
        };

        productService.getProductRequestReceive(userPartialId, techInfo);
    }

    @Test
    public void testGetProductRequestReceiveNoUser() throws BusinessException {
        final ProductService productService = new ProductService();

        final long userPartialId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {


            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestReceived(userPartialId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                productRequestBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductRequestReceive(userPartialId, techInfo));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test getProductRequestSend">
    @Test
    public void testGetProductRequestSend() throws BusinessException {
        final ProductService productService = new ProductService();

        final long userPartialId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestSend(userPartialId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(productRequestBOs);

                productRequestBOConverter.convert(productRequestBOs);
            }
        };

        productService.getProductRequestSend(userPartialId, techInfo);
    }

    @Test
    public void testGetProductRequestSendNoUser() throws BusinessException {
        final ProductService productService = new ProductService();

        final long userPartialId = 123L;
        final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        new Expectations() {

            @Mocked
            private IProductRequestDAO productRequestDAO;
            @Mocked
            private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
            {
                Deencapsulation.setField(productService, productRequestDAO);
                Deencapsulation.setField(productService, "productRequestBOConverter", productRequestBOConverter);

                TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> lTechnicalInformation = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualAccepted);

                List<ProductRequestBO>  productRequestBOs = new ArrayList<ProductRequestBO>();
                productRequestDAO.getProductRequestSend(userPartialId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                productRequestBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(productService.getProductRequestSend(userPartialId, techInfo));
    }
    // </editor-fold>

}
