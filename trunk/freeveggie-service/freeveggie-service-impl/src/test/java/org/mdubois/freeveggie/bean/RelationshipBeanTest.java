package org.mdubois.freeveggie.bean;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.RelationshipBeanLocal;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationshipService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author mdubois
 */
public class RelationshipBeanTest {

    @Mocked
    private IRelationshipService relationshipService;
    @Mocked
    private IRightControlerService rightControlerService;

    // <editor-fold defaultstate="collapsed" desc="Create">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationshipMsg.setSender(userMsg);
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.create(pRelationshipMsg);
                returns(expResult);
            }
        };

        Long result = instance.create(pContextMsg, pRelationshipMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(124L);
        pContextMsg.setUser(userContext);
        final RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationshipMsg.setSender(userMsg);
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
            }
        };

        instance.create(pContextMsg, pRelationshipMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(124L);
        pContextMsg.setUser(userContext);
        final RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationshipMsg.setSender(userMsg);
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.create(pContextMsg, pRelationshipMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationshipMsg.setSender(userMsg);
        final IRelationshipBean instance = new RelationshipBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.create(pRelationshipMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.create(pContextMsg, pRelationshipMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationshipMsg.setSender(userMsg);
        final IRelationshipBean instance = new RelationshipBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.create(pRelationshipMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.create(pContextMsg, pRelationshipMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Validate">
    /**
     * Test of validate method, of class GardenBean.
     */
    @Test
    public void testValidate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final boolean expResult = true;
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.validate(pRelationshipId, message);
                returns(expResult);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationshipId, message);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testValidateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(false);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testValidateAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = BusinessException.class)
    public void testValidateThrowBusinessException() throws Exception {

        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.validate(pRelationshipId, message);
                result = new BusinessException("BusinessException");
            }
        };

        instance.validate(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = TechnicalException.class)
    public void testValidateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.validate(pRelationshipId, message);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.validate(pContextMsg, pRelationshipId, message);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Refuse">
    /**
     * Test of refuse method, of class GardenBean.
     */
    @Test
    public void testRefuse() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final boolean expResult = true;
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.refuse(pRelationshipId, message);
                returns(expResult);
            }
        };

        boolean result = instance.refuse(pContextMsg, pRelationshipId, message);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRefuseAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(false);
            }
        };

        instance.refuse(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRefuseAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.refuse(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = BusinessException.class)
    public void testRefuseThrowBusinessException() throws Exception {

        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.refuse(pRelationshipId, message);
                result = new BusinessException("BusinessException");
            }
        };

        instance.refuse(pContextMsg, pRelationshipId, message);
    }

    @Test(expected = TechnicalException.class)
    public void testRefuseThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationshipId);
                returns(true);

                relationshipService.refuse(pRelationshipId, message);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.refuse(pContextMsg, pRelationshipId, message);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetRelationship">
    /**
     * Test of validate method, of class GardenBean.
     */
    @Test
    public void testGetRelationship() throws Exception {
        final List<RelationshipMsg> expResult = new ArrayList<RelationshipMsg>();
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.getRelationship(pRelationshipId, pTechInfo);
                returns(expResult);
            }
        };

        List<RelationshipMsg> result = instance.getRelationship(pContextMsg, pRelationshipId, pTechInfo);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetRelationshipAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getRelationship(pContextMsg, pRelationshipId, pTechInfo);
    }

    @Test(expected = BusinessException.class)
    public void testGetRelationshipThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.getRelationship(pRelationshipId, pTechInfo);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getRelationship(pContextMsg, pRelationshipId, pTechInfo);
    }

    @Test(expected = TechnicalException.class)
    public void testGetRelationshipThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationshipId = 85965L;
        final IRelationshipBean instance = new RelationshipBeanLocal();
        final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationshipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationshipService.getRelationship(pRelationshipId, pTechInfo);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getRelationship(pContextMsg, pRelationshipId, pTechInfo);
    }
    // </editor-fold>
}
