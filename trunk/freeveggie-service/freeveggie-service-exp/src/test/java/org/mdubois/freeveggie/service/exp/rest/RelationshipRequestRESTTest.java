/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IRelationshipBean;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author mickael
 */
public class RelationshipRequestRESTTest {

    private final RelationshipRequestREST service = new RelationshipRequestREST();

    @Mocked
    private IRelationshipBean relationshipBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, relationshipBean);
    }

    @Test
    public void testGetRelationshipRequestReceive() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                relationshipBean.getRelationship(pContextMsg, pUserId, techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getRelationshipRequestReceive(pContextUserId, pUserId, techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetRelationshipRequestReceiveThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                relationshipBean.getRelationship(pContextMsg, pUserId, techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getRelationshipRequestReceive(pContextUserId, pUserId, techInfoJSON));
    }

    @Test
    public void testGetRelationshipRequestReceiveFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pUserId = 101L;

        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"STATUS\"}}";
        final List<RelationshipMsg> expected = new ArrayList<RelationshipMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<RelationshipCriteriaColumn>> criterias = new ArrayList<QueryCriteria<RelationshipCriteriaColumn>>();
                QueryCriteria<RelationshipCriteriaColumn> criteria = new QueryCriteria<RelationshipCriteriaColumn>(RelationshipCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<RelationshipOrderColumn> order = new ResultOrder<RelationshipOrderColumn>(RelationshipOrderColumn.STATUS, OrderWay.ASC);
                techInfo.setOrder(order);
                relationshipBean.getRelationship(pContextMsg, pUserId, techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getRelationshipRequestReceive(pContextUserId, pUserId, techInfoJSON));
    }

    @Test
    public void testAddRelationshipRequest() throws BusinessException {
        final Long pContextUserId = null;
        final RelationshipMsg pRelationshipRequest = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                relationshipBean.create(pContextMsg, pRelationshipRequest);
            }
        };
        service.addRelationshipRequest(pRelationshipRequest, pContextUserId);
    }

    @Test
    public void testAddRelationshipRequestFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final RelationshipMsg pRelationshipRequest = new RelationshipMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                relationshipBean.create(pContextMsg, pRelationshipRequest);
            }
        };
        service.addRelationshipRequest(pRelationshipRequest, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddRelationshipRequestException() throws BusinessException {
        final Long pContextUserId = 10L;
        final RelationshipMsg pRelationshipRequest = new RelationshipMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                relationshipBean.create(pContextMsg, pRelationshipRequest);
                result = new BusinessException("");
            }
        };
        service.addRelationshipRequest(pRelationshipRequest, pContextUserId);
    }
}
