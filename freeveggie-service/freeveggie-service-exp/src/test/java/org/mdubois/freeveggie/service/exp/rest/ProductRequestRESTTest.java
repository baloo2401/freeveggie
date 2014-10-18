package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;

/**
 *
 * @author Mickael
 */
public class ProductRequestRESTTest {

    private final ProductRequestREST service = new ProductRequestREST();

    @Mocked
    private IProductBean productBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, productBean);
    }

    @Test
    public void testGetProductRequestById() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductRequestById(pContextMsg, pSentId);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductRequestById(pContextUserId, pSentId));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductRequestByIdThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductRequestById(pContextMsg, pSentId);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductRequestById(pContextUserId, pSentId));
    }

    @Test
    public void testGetProductRequestByIdFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pSentId = 101L;

        final ProductRequestMsg expected = new ProductRequestMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                productBean.getProductRequestById(pContextMsg, pSentId);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductRequestById(pContextUserId, pSentId));
    }

    @Test
    public void testAddProductRequest() throws BusinessException {
        final Long pContextUserId = null;
        final ProductRequestMsg pProductRequest = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.send(pContextMsg, pProductRequest);
            }
        };
        service.addProductRequest(pProductRequest, pContextUserId);
    }

    @Test
    public void testAddProductRequestFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final ProductRequestMsg pProductRequest = new ProductRequestMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.send(pContextMsg, pProductRequest);
            }
        };
        service.addProductRequest(pProductRequest, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddProductRequestException() throws BusinessException {
        final Long pContextUserId = 10L;
        final ProductRequestMsg pProductRequest = new ProductRequestMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.send(pContextMsg, pProductRequest);
                result = new BusinessException("");
            }
        };
        service.addProductRequest(pProductRequest, pContextUserId);
    }

    @Test
    public void testAcceptComment() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductRequestId = null;
        final String message = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.accept(pContextMsg, pProductRequestId, message);
            }
        };
        service.acceptRequest(message, pProductRequestId, pContextUserId);
    }

    @Test
    public void testAcceptFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pProductRequestId = 11L;
        final String message = "test";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.accept(pContextMsg, pProductRequestId, message);
            }
        };
        service.acceptRequest(message, pProductRequestId, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAcceptException() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pProductRequestId = 11L;
        final String message = "test";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.accept(pContextMsg, pProductRequestId, message);
                result = new BusinessException("");
            }
        };
        service.acceptRequest(message, pProductRequestId, pContextUserId);
    }

    @Test
    public void testRefuseComment() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductRequestId = null;
        final String message = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.refuse(pContextMsg, pProductRequestId, message);
            }
        };
        service.refuseRequest(message, pProductRequestId, pContextUserId);
    }

    @Test
    public void testRefuseFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pProductRequestId = 11L;
        final String message = "test";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.refuse(pContextMsg, pProductRequestId, message);
            }
        };
        service.refuseRequest(message, pProductRequestId, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testRefuseException() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pProductRequestId = 11L;
        final String message = "test";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.refuse(pContextMsg, pProductRequestId, message);
                result = new BusinessException("");
            }
        };
        service.refuseRequest(message, pProductRequestId, pContextUserId);
    }

    @Test
    public void testGetProductRequestByGarden() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestByGarden(pContextMsg, pGardenId, techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductRequestByGarden(pContextUserId, pGardenId, techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductRequestByGardenThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestByGarden(pContextMsg, pGardenId, techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductRequestByGarden(pContextUserId, pGardenId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestByGardenFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pGardenId = 101L;

        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        final List<ProductRequestMsg> expected = new ArrayList<ProductRequestMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                QueryCriteria<ProductRequestCriteriaColumn> criteria = new QueryCriteria<ProductRequestCriteriaColumn>(ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<ProductRequestOrderColumn> order = new ResultOrder<ProductRequestOrderColumn>(ProductRequestOrderColumn.STATUS, OrderWay.ASC);
                techInfo.setOrder(order);
                productBean.getProductRequestByGarden(pContextMsg, pGardenId, techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductRequestByGarden(pContextUserId, pGardenId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestByProduct() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestByProduct(pContextMsg, pProductId, techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductRequestByProduct(pContextUserId, pProductId, techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductRequestByProductThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestByProduct(pContextMsg, pProductId, techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductRequestByProduct(pContextUserId, pProductId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestByProductFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pProductId = 101L;

        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        final List<ProductRequestMsg> expected = new ArrayList<ProductRequestMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                QueryCriteria<ProductRequestCriteriaColumn> criteria = new QueryCriteria<ProductRequestCriteriaColumn>(ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<ProductRequestOrderColumn> order = new ResultOrder<ProductRequestOrderColumn>(ProductRequestOrderColumn.STATUS, OrderWay.ASC);
                techInfo.setOrder(order);
                productBean.getProductRequestByProduct(pContextMsg, pProductId, techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductRequestByProduct(pContextUserId, pProductId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestSend() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestSend(pContextMsg, pSentId, techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductRequestSend(pContextUserId, pSentId, techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductRequestSendThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestSend(pContextMsg, pSentId, techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductRequestSend(pContextUserId, pSentId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestSendFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pSentId = 101L;

        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        final List<ProductRequestMsg> expected = new ArrayList<ProductRequestMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                QueryCriteria<ProductRequestCriteriaColumn> criteria = new QueryCriteria<ProductRequestCriteriaColumn>(ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<ProductRequestOrderColumn> order = new ResultOrder<ProductRequestOrderColumn>(ProductRequestOrderColumn.STATUS, OrderWay.ASC);
                techInfo.setOrder(order);
                productBean.getProductRequestSend(pContextMsg, pSentId, techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductRequestSend(pContextUserId, pSentId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestReceive() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestReceive(pContextMsg, pGardenId, techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductRequestReceive(pContextUserId, pGardenId, techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductRequestReceiveThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                productBean.getProductRequestReceive(pContextMsg, pGardenId, techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductRequestReceive(pContextUserId, pGardenId, techInfoJSON));
    }

    @Test
    public void testGetProductRequestReceiveFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pGardenId = 101L;

        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        final List<ProductRequestMsg> expected = new ArrayList<ProductRequestMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductRequestCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductRequestCriteriaColumn>>();
                QueryCriteria<ProductRequestCriteriaColumn> criteria = new QueryCriteria<ProductRequestCriteriaColumn>(ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<ProductRequestOrderColumn> order = new ResultOrder<ProductRequestOrderColumn>(ProductRequestOrderColumn.STATUS, OrderWay.ASC);
                techInfo.setOrder(order);
                productBean.getProductRequestReceive(pContextMsg, pGardenId, techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductRequestReceive(pContextUserId, pGardenId, techInfoJSON));
    }
}
