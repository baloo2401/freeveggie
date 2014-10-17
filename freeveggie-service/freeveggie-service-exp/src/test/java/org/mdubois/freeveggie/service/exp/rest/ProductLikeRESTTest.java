package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;

/**
 *
 * @author Mickael
 */
public class ProductLikeRESTTest {

    private final ProductLikeREST service = new ProductLikeREST();

    @Mocked
    private IProductBean productBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, productBean);
    }

    @Test
    public void testAddProductLike() throws BusinessException {
        final ProductLikeMsg pProductLikeMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.like(pContextMsg, pProductLikeMsg);
                returns(null);
            }
        };
        service.addProductLike(pProductLikeMsg, pContextUserId);
    }

    @Test
    public void testAddProductLikeWithData() throws BusinessException {
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.like(pContextMsg, pProductLikeMsg);
                returns(null);
            }
        };
        service.addProductLike(pProductLikeMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddProductLikeThrowException() throws BusinessException {
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.like(pContextMsg, pProductLikeMsg);
                result = new BusinessException("");
            }
        };
        service.addProductLike(pProductLikeMsg, pContextUserId);
    }

    @Test
    public void testGetProductLike() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                productBean.getProductLike(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductLikeBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductLike(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                QueryCriteria<ProductLikeCriteriaColumn> criteria = new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductLike(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWithOrder() throws BusinessException {
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
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                QueryCriteria<ProductLikeCriteriaColumn> criteria = new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductLikeOrderColumn> order = new ResultOrder<ProductLikeOrderColumn>(ProductLikeOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductLike(pContextMsg, pProductId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductLikeThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pProductId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                productBean.getProductLike(pContextMsg, pProductId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductLike(pContextUserId, pProductId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWrite() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                productBean.getProductLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductLikeWriteBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWriteWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWriteWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                QueryCriteria<ProductLikeCriteriaColumn> criteria = new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductLikeWriteWithOrder() throws BusinessException {
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
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductLikeCriteriaColumn>>();
                QueryCriteria<ProductLikeCriteriaColumn> criteria = new QueryCriteria<ProductLikeCriteriaColumn>(ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductLikeOrderColumn> order = new ResultOrder<ProductLikeOrderColumn>(ProductLikeOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductLikeWriteThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> technicalInfo = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
                productBean.getProductLikeWrite(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }
}
