package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;

/**
 *
 * @author Mickael
 */
public class GardenLikeRESTTest {

    private final GardenLikeREST service = new GardenLikeREST();

    @Mocked
    private IGardenBean gardenBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, gardenBean);
    }

    @Test
    public void testAddGardenLike() throws BusinessException {
        final GardenLikeMsg pGardenLikeMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.like(pContextMsg, pGardenLikeMsg);
                returns(null);
            }
        };
        service.addGardenLike(pGardenLikeMsg, pContextUserId);
    }

    @Test
    public void testAddGardenLikeWithData() throws BusinessException {
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.like(pContextMsg, pGardenLikeMsg);
                returns(null);
            }
        };
        service.addGardenLike(pGardenLikeMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddGardenLikeThrowException() throws BusinessException {
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.like(pContextMsg, pGardenLikeMsg);
                result = new BusinessException("");
            }
        };
        service.addGardenLike(pGardenLikeMsg, pContextUserId);
    }

    @Test
    public void testGetGardenLike() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                gardenBean.getGardenLike(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenLikeBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenLike(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                QueryCriteria<GardenLikeCriteriaColumn> criteria = new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenLike(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWithOrder() throws BusinessException {
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
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                QueryCriteria<GardenLikeCriteriaColumn> criteria = new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenLikeOrderColumn> order = new ResultOrder<GardenLikeOrderColumn>(GardenLikeOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenLike(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenLikeThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                gardenBean.getGardenLike(pContextMsg, pGardenId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenLike(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWrite() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                gardenBean.getGardenLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenLikeWriteBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWriteWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWriteWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                QueryCriteria<GardenLikeCriteriaColumn> criteria = new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenLikeWriteWithOrder() throws BusinessException {
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
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                QueryCriteria<GardenLikeCriteriaColumn> criteria = new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenLikeOrderColumn> order = new ResultOrder<GardenLikeOrderColumn>(GardenLikeOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenLikeWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenLikeWriteThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> technicalInfo = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                gardenBean.getGardenLikeWrite(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenLikeWrite(pContextUserId, pUserId, pTechnicalInfo);
    }
}
