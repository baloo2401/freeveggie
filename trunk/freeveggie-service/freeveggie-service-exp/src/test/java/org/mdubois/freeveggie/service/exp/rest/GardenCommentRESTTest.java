package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;

/**
 *
 * @author Mickael
 */
public class GardenCommentRESTTest {

    private final GardenCommentREST service = new GardenCommentREST();

    @Mocked
    private IGardenBean gardenBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, gardenBean);
    }

    @Test
    public void testAddGardenComment() throws BusinessException {
        final GardenCommentMsg pGardenCommentMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.comment(pContextMsg, pGardenCommentMsg);
                returns(null);
            }
        };
        service.addGardenComment(pGardenCommentMsg, pContextUserId);
    }

    @Test
    public void testAddGardenCommentWithData() throws BusinessException {
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.comment(pContextMsg, pGardenCommentMsg);
                returns(null);
            }
        };
        service.addGardenComment(pGardenCommentMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddGardenCommentThrowException() throws BusinessException {
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.comment(pContextMsg, pGardenCommentMsg);
                result = new BusinessException("");
            }
        };
        service.addGardenComment(pGardenCommentMsg, pContextUserId);
    }

    @Test
    public void testGetGardenComment() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                gardenBean.getGardenComment(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenCommentBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenComment(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                QueryCriteria<GardenCommentCriteriaColumn> criteria = new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenComment(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWithOrder() throws BusinessException {
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
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                QueryCriteria<GardenCommentCriteriaColumn> criteria = new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenCommentOrderColumn> order = new ResultOrder<GardenCommentOrderColumn>(GardenCommentOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenComment(pContextMsg, pGardenId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenCommentThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pGardenId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                gardenBean.getGardenComment(pContextMsg, pGardenId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenComment(pContextUserId, pGardenId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWrite() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                gardenBean.getGardenCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenCommentWriteBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWriteWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWriteWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                QueryCriteria<GardenCommentCriteriaColumn> criteria = new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenCommentWriteWithOrder() throws BusinessException {
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
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                QueryCriteria<GardenCommentCriteriaColumn> criteria = new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenCommentOrderColumn> order = new ResultOrder<GardenCommentOrderColumn>(GardenCommentOrderColumn.STATUS, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenCommentWrite(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenCommentWriteThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> technicalInfo = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                gardenBean.getGardenCommentWrite(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenCommentWrite(pContextUserId, pUserId, pTechnicalInfo);
    }
}
