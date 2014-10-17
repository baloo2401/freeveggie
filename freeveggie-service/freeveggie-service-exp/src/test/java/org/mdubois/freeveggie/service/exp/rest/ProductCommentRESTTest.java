package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;

/**
 *
 * @author Mickael
 */
public class ProductCommentRESTTest {

    private final ProductCommentREST service = new ProductCommentREST();

    @Mocked
    private IProductBean productBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, productBean);
    }

    @Test
    public void testAddProductComment() throws BusinessException {
        final ProductCommentMsg pProductCommentMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.comment(pContextMsg, pProductCommentMsg);
                returns(null);
            }
        };
        service.addProductComment(pProductCommentMsg, pContextUserId);
    }

    @Test
    public void testAddProductCommentWithData() throws BusinessException {
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.comment(pContextMsg, pProductCommentMsg);
                returns(null);
            }
        };
        service.addProductComment(pProductCommentMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddProductCommentThrowException() throws BusinessException {
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.comment(pContextMsg, pProductCommentMsg);
                result = new BusinessException("");
            }
        };
        service.addProductComment(pProductCommentMsg, pContextUserId);
    }

    @Test
    public void testGetProductComment() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                productBean.getProductComment(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductCommentBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductComment(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                QueryCriteria<ProductCommentCriteriaColumn> criteria = new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductComment(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWithOrder() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                QueryCriteria<ProductCommentCriteriaColumn> criteria = new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductCommentOrderColumn> order = new ResultOrder<ProductCommentOrderColumn>(ProductCommentOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductComment(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductCommentThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                productBean.getProductComment(pContextMsg, pProductId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductComment(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWrite() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                productBean.getProductCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductCommentWriteBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWriteWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWriteWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                QueryCriteria<ProductCommentCriteriaColumn> criteria = new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductCommentWriteWithOrder() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCommentCriteriaColumn>>();
                QueryCriteria<ProductCommentCriteriaColumn> criteria = new QueryCriteria<ProductCommentCriteriaColumn>(ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductCommentOrderColumn> order = new ResultOrder<ProductCommentOrderColumn>(ProductCommentOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductCommentWriteThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> technicalInfo = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
                productBean.getProductCommentWrite(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }
}
