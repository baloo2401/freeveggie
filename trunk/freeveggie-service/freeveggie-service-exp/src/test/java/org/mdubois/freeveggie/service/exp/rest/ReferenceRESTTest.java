package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IReferenceBean;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.RefProductMsg;

/**
 *
 * @author Mickael
 */
public class ReferenceRESTTest {

    private final ReferenceREST service = new ReferenceREST();

    @Mocked
    private IReferenceBean referenceBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, referenceBean);
    }

    @Test
    public void testGetRefProductByGarden() throws BusinessException {
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> techInfo = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
                referenceBean.getRefProducts(techInfo);
                returns(null);
            }
        };
        Assert.assertNull(service.getRefProduct(techInfoJSON));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetRefProductByGardenThrowException() throws BusinessException {
        final String techInfoJSON = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> techInfo = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
                referenceBean.getRefProducts(techInfo);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getRefProduct(techInfoJSON));
    }

    @Test
    public void testGetRefProductByGardenFull() throws BusinessException {
        final String techInfoJSON = "{\"pagination\": {\"pageNumber\": 1, \"nbPerPage\": 1}, "
                + "\"criterias\":[{\"criteria\":\"TYPE\",\"operation\":\"EQUAL\",\"value\":\"CREATED\"}],"
                + " \"order\":{\"way\":\"ASC\", \"orderColumn\":\"TYPE\"}}";
        final List<RefProductMsg> expected = new ArrayList<RefProductMsg>();
        new Expectations() {
            {
                final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> techInfo = new TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn>();
                techInfo.setPagination(new Pagination(1, 1));
                List<QueryCriteria<RefProductCriteriaColumn>> criterias = new ArrayList<QueryCriteria<RefProductCriteriaColumn>>();
                QueryCriteria<RefProductCriteriaColumn> criteria = new QueryCriteria<RefProductCriteriaColumn>(RefProductCriteriaColumn.TYPE, CriteriaOperation.EQUAL);
                criteria.setValue("CREATED");
                criterias.add(criteria);
                techInfo.setCriterias(criterias);
                ResultOrder<RefProductOrderColumn> order = new ResultOrder<RefProductOrderColumn>(RefProductOrderColumn.TYPE, OrderWay.ASC);
                techInfo.setOrder(order);
                referenceBean.getRefProducts(techInfo);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getRefProduct(techInfoJSON));
    }

}
