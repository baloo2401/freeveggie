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
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.IdMsg;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;

/**
 *
 * @author Mickael
 */
public class ProductRESTTest {

    private final ProductREST service = new ProductREST();

    @Mocked
    private IProductBean productBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, productBean);
    }

    @Test
    public void testAddProduct() throws BusinessException {
        final ProductMsg pProductMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.create(pContextMsg, pProductMsg);
                returns(null);
            }
        };
        service.addProduct(pProductMsg, pContextUserId);
    }

    @Test
    public void testAddProductWithData() throws BusinessException {
        final ProductMsg pProductMsg = new ProductMsg();
        final Long pContextUserId = 10L;
        final IdMsg expected = new IdMsg(1235L);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.create(pContextMsg, pProductMsg);
                returns(1235L);
            }
        };
        Assert.assertEquals(expected, service.addProduct(pProductMsg, pContextUserId));
    }

    @Test(expected = BusinessWebException.class)
    public void testAddProductThrowException() throws BusinessException {
        final ProductMsg pProductMsg = new ProductMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.create(pContextMsg, pProductMsg);
                result = new BusinessException("");
            }
        };
        service.addProduct(pProductMsg, pContextUserId);
    }

    @Test
    public void testUpdateProduct() throws BusinessException {
        final UpdateProductMsg pProductMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.update(pContextMsg, pProductMsg);
                returns(false);
            }
        };
        service.updateProduct(pProductMsg, pContextUserId);
    }

    @Test
    public void testUpdateProductWithData() throws BusinessException {
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pContextUserId = 10L;
        final IdMsg expected = new IdMsg(1235L);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.update(pContextMsg, pProductMsg);
                returns(true);
            }
        };
        service.updateProduct(pProductMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testUpdateProductThrowException() throws BusinessException {
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.update(pContextMsg, pProductMsg);
                result = new BusinessException("");
            }
        };
        service.updateProduct(pProductMsg, pContextUserId);
    }

    @Test
    public void testDeleteProduct() throws BusinessException {
        final Long pProductId = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.remove(pContextMsg, pProductId);
                returns(false);
            }
        };
        service.deleteProductById(pContextUserId, pProductId);
    }

    @Test
    public void testDeleteProductWithData() throws BusinessException {
        final Long pProductId = 123L;
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.remove(pContextMsg, pProductId);
                returns(true);
            }
        };
        service.deleteProductById(pContextUserId, pProductId);
    }

    @Test(expected = BusinessWebException.class)
    public void testDeleteProductThrowException() throws BusinessException {
        final Long pProductId = 456L;
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.remove(pContextMsg, pProductId);
                result = new BusinessException("");
            }
        };
        service.deleteProductById(pContextUserId, pProductId);
    }

    @Test
    public void testGetProductByGarden() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                productBean.getProductByGarden(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductByGardenBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByGardenWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductByGarden(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByGardenWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCriteriaColumn>>();
                QueryCriteria<ProductCriteriaColumn> criteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductByGarden(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByGardenWithOrder() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCriteriaColumn>>();
                QueryCriteria<ProductCriteriaColumn> criteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductOrderColumn> order = new ResultOrder<ProductOrderColumn>(ProductOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductByGarden(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductByGardenThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                productBean.getProductByGarden(pContextMsg, pGardenId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductByGarden(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByUser() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                productBean.getProductByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductByUserBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByUserWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                productBean.getProductByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByUserWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCriteriaColumn>>();
                QueryCriteria<ProductCriteriaColumn> criteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                productBean.getProductByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductByUserWithOrder() throws BusinessException {
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
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<ProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<ProductCriteriaColumn>>();
                QueryCriteria<ProductCriteriaColumn> criteria = new QueryCriteria<ProductCriteriaColumn>(ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<ProductOrderColumn> order = new ResultOrder<ProductOrderColumn>(ProductOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                productBean.getProductByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductByUserThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> technicalInfo = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
                productBean.getProductByUser(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getProductByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetProductById() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getProductById(pContextUserId, pUserId);
    }

    @Test
    public void testGetProductByIdBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getProductById(pContextUserId, pUserId);
    }

    @Test
    public void testGetProductByIdFull() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getProductById(pContextUserId, pUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductByIdThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getProductById(pContextMsg, pUserId);
                result = new BusinessException("");
            }
        };
        service.getProductById(pContextUserId, pUserId);
    }
}
