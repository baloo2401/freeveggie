package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
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
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.SearchMsg;

/**
 *
 * @author Mickael
 */
public class GardenRESTTest {

    private final GardenREST service = new GardenREST();

    @Mocked
    private IGardenBean gardenBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, gardenBean);
    }

    @Test
    public void testSearchGarden() throws BusinessException {
        Double latUp = null;
        Double latDown = null;
        Double longUp = null;
        Double longDown = null;
        Integer productId = null;
        final SearchMsg pGardenMsg = new SearchMsg();
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.searchGarden(pContextMsg, pGardenMsg);
                returns(null);
            }
        };
        service.searchGarden(pContextUserId, latUp, latDown, longUp, longDown, productId);
    }

    @Test
    public void testSearchGardenFull() throws BusinessException {
        Double latUp = 1.0;
        Double latDown = 2.0;
        Double longUp = 3.0;
        Double longDown = 4.0;
        Integer productId = 10;
        final SearchMsg pGardenMsg = new SearchMsg();
        pGardenMsg.setLatitudeDown(latDown);
        pGardenMsg.setLatitudeUp(latUp);
        pGardenMsg.setLongitudeDown(longDown);
        pGardenMsg.setLongitudeUp(longUp);
        pGardenMsg.setRefProductId(productId);
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.searchGarden(pContextMsg, pGardenMsg);
                returns(null);
            }
        };
        service.searchGarden(pContextUserId, latUp, latDown, longUp, longDown, productId);
    }

    @Test
    public void testSearchGardenFullWithReturn() throws BusinessException {
        Double latUp = 1.0;
        Double latDown = 2.0;
        Double longUp = 3.0;
        Double longDown = 4.0;
        Integer productId = 10;
        final SearchMsg pGardenMsg = new SearchMsg();
        pGardenMsg.setLatitudeDown(latDown);
        pGardenMsg.setLatitudeUp(latUp);
        pGardenMsg.setLongitudeDown(longDown);
        pGardenMsg.setLongitudeUp(longUp);
        pGardenMsg.setRefProductId(productId);
        final Long pContextUserId = null;
        final List<GardenMsg> gardens = new ArrayList<GardenMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.searchGarden(pContextMsg, pGardenMsg);
                returns(gardens);
            }
        };
        Assert.assertEquals(gardens, service.searchGarden(pContextUserId, latUp, latDown, longUp, longDown, productId));
    }

    @Test(expected = BusinessWebException.class)
    public void testSearchGardenFullThrowException() throws BusinessException {
        Double latUp = 1.0;
        Double latDown = 2.0;
        Double longUp = 3.0;
        Double longDown = 4.0;
        Integer productId = 10;
        final SearchMsg pGardenMsg = new SearchMsg();
        pGardenMsg.setLatitudeDown(latDown);
        pGardenMsg.setLatitudeUp(latUp);
        pGardenMsg.setLongitudeDown(longDown);
        pGardenMsg.setLongitudeUp(longUp);
        pGardenMsg.setRefProductId(productId);
        final Long pContextUserId = null;
        final List<GardenMsg> gardens = new ArrayList<GardenMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.searchGarden(pContextMsg, pGardenMsg);
                result = new BusinessException(anyString);
            }
        };
        Assert.assertEquals(gardens, service.searchGarden(pContextUserId, latUp, latDown, longUp, longDown, productId));
    }

    @Test
    public void testAddGarden() throws BusinessException {
        final GardenMsg pGardenMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.create(pContextMsg, pGardenMsg);
                returns(null);
            }
        };
        service.addGarden(pGardenMsg, pContextUserId);
    }

    @Test
    public void testAddGardenWithData() throws BusinessException {
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pContextUserId = 10L;
        final IdMsg expected = new IdMsg(1235L);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.create(pContextMsg, pGardenMsg);
                returns(1235L);
            }
        };
        Assert.assertEquals(expected, service.addGarden(pGardenMsg, pContextUserId));
    }

    @Test(expected = BusinessWebException.class)
    public void testAddGardenThrowException() throws BusinessException {
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.create(pContextMsg, pGardenMsg);
                result = new BusinessException("");
            }
        };
        service.addGarden(pGardenMsg, pContextUserId);
    }

    @Test
    public void testUpdateGarden() throws BusinessException {
        final GardenMsg pGardenMsg = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.update(pContextMsg, pGardenMsg);
                returns(false);
            }
        };
        service.updateGarden(pGardenMsg, pContextUserId);
    }

    @Test
    public void testUpdateGardenWithData() throws BusinessException {
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pContextUserId = 10L;
        final IdMsg expected = new IdMsg(1235L);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.update(pContextMsg, pGardenMsg);
                returns(true);
            }
        };
        service.updateGarden(pGardenMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testUpdateGardenThrowException() throws BusinessException {
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.update(pContextMsg, pGardenMsg);
                result = new BusinessException("");
            }
        };
        service.updateGarden(pGardenMsg, pContextUserId);
    }

    @Test
    public void testDeleteGarden() throws BusinessException {
        final Long pGardenId = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.remove(pContextMsg, pGardenId);
                returns(false);
            }
        };
        service.deleteGardenById(pContextUserId, pGardenId);
    }

    @Test
    public void testDeleteGardenWithData() throws BusinessException {
        final Long pGardenId = 123L;
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.remove(pContextMsg, pGardenId);
                returns(true);
            }
        };
        service.deleteGardenById(pContextUserId, pGardenId);
    }

    @Test(expected = BusinessWebException.class)
    public void testDeleteGardenThrowException() throws BusinessException {
        final Long pGardenId = 456L;
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.remove(pContextMsg, pGardenId);
                result = new BusinessException("");
            }
        };
        service.deleteGardenById(pContextUserId, pGardenId);
    }

    @Test
    public void testGetGardenByGarden() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenByGardenBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByGardenWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByGardenWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCriteriaColumn>>();
                QueryCriteria<GardenCriteriaColumn> criteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByGardenWithOrder() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"NAME\"}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCriteriaColumn>>();
                QueryCriteria<GardenCriteriaColumn> criteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenOrderColumn> order = new ResultOrder<GardenOrderColumn>(GardenOrderColumn.NAME, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenByGardenThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByUser() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenByUserBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{test}";
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByUserWithPaginationInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByUserWithCriteria() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, \"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}]}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCriteriaColumn>>();
                QueryCriteria<GardenCriteriaColumn> criteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenByUserWithOrder() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"STATUS\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"NAME\"}}";
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                technicalInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<GardenCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCriteriaColumn>>();
                QueryCriteria<GardenCriteriaColumn> criteria = new QueryCriteria<GardenCriteriaColumn>(GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                technicalInfo.setCriterias(criterias);
                ResultOrder<GardenOrderColumn> order = new ResultOrder<GardenOrderColumn>(GardenOrderColumn.NAME, OrderWay.ASC);
                technicalInfo.setOrder(order);
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                returns(null);
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenByUserThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        final String pTechnicalInfo = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> technicalInfo = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
                gardenBean.getGardenByUser(pContextMsg, pUserId, technicalInfo);
                result = new BusinessException("");
            }
        };
        service.getGardenByUser(pContextUserId, pUserId, pTechnicalInfo);
    }

    @Test
    public void testGetGardenById() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getGardenById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getGardenById(pContextUserId, pUserId);
    }

    @Test
    public void testGetGardenByIdBadTechInfo() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getGardenById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getGardenById(pContextUserId, pUserId);
    }

    @Test
    public void testGetGardenByIdFull() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getGardenById(pContextMsg, pUserId);
                returns(null);
            }
        };
        service.getGardenById(pContextUserId, pUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenByIdThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getGardenById(pContextMsg, pUserId);
                result = new BusinessException("");
            }
        };
        service.getGardenById(pContextUserId, pUserId);
    }
}
