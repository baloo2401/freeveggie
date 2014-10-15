package org.mdubois.freeveggie.bean;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.RelationShipBeanLocal;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationShipService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;

/**
 *
 * @author mdubois
 */
public class RelationShipBeanTest {

    @Mocked
    private IRelationShipService relationShipService;
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
        final RelationShipMsg pRelationShipMsg = new RelationShipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationShipMsg.setSender(userMsg);
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.create(pRelationShipMsg);
                returns(expResult);
            }
        };

        Long result = instance.create(pContextMsg, pRelationShipMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(124L);
        pContextMsg.setUser(userContext);
        final RelationShipMsg pRelationShipMsg = new RelationShipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationShipMsg.setSender(userMsg);
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
            }
        };

        instance.create(pContextMsg, pRelationShipMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(124L);
        pContextMsg.setUser(userContext);
        final RelationShipMsg pRelationShipMsg = new RelationShipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationShipMsg.setSender(userMsg);
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.create(pContextMsg, pRelationShipMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final RelationShipMsg pRelationShipMsg = new RelationShipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationShipMsg.setSender(userMsg);
        final IRelationShipBean instance = new RelationShipBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.create(pRelationShipMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.create(pContextMsg, pRelationShipMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final RelationShipMsg pRelationShipMsg = new RelationShipMsg();
        PartialUserMsg userMsg = new PartialUserMsg();
        userMsg.setId(userId);
        pRelationShipMsg.setSender(userMsg);
        final IRelationShipBean instance = new RelationShipBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.create(pRelationShipMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.create(pContextMsg, pRelationShipMsg);
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
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final boolean expResult = true;
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.validate(pRelationShipId, message);
                returns(expResult);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationShipId, message);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testValidateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(false);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testValidateAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        boolean result = instance.validate(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = BusinessException.class)
    public void testValidateThrowBusinessException() throws Exception {

        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.validate(pRelationShipId, message);
                result = new BusinessException("BusinessException");
            }
        };

        instance.validate(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = TechnicalException.class)
    public void testValidateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.validate(pRelationShipId, message);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.validate(pContextMsg, pRelationShipId, message);
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
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final boolean expResult = true;
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.refuse(pRelationShipId, message);
                returns(expResult);
            }
        };

        boolean result = instance.refuse(pContextMsg, pRelationShipId, message);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRefuseAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(false);
            }
        };

        instance.refuse(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRefuseAccessNotGranted2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.refuse(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = BusinessException.class)
    public void testRefuseThrowBusinessException() throws Exception {

        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.refuse(pRelationShipId, message);
                result = new BusinessException("BusinessException");
            }
        };

        instance.refuse(pContextMsg, pRelationShipId, message);
    }

    @Test(expected = TechnicalException.class)
    public void testRefuseThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final String message = "message";

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerRelationship(userId, pRelationShipId);
                returns(true);

                relationShipService.refuse(pRelationShipId, message);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.refuse(pContextMsg, pRelationShipId, message);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetRelationShip">
    /**
     * Test of validate method, of class GardenBean.
     */
    @Test
    public void testGetRelationShip() throws Exception {
        final List<RelationShipMsg> expResult = new ArrayList<RelationShipMsg>();
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.getRelationShip(pRelationShipId, pTechInfo);
                returns(expResult);
            }
        };

        List<RelationShipMsg> result = instance.getRelationShip(pContextMsg, pRelationShipId, pTechInfo);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetRelationShipAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getRelationShip(pContextMsg, pRelationShipId, pTechInfo);
    }

    @Test(expected = BusinessException.class)
    public void testGetRelationShipThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.getRelationShip(pRelationShipId, pTechInfo);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getRelationShip(pContextMsg, pRelationShipId, pTechInfo);
    }

    @Test(expected = TechnicalException.class)
    public void testGetRelationShipThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        UserContext userContext = new UserContext();
        final Long userId = 123L;
        userContext.setId(userId);
        pContextMsg.setUser(userContext);
        final Long pRelationShipId = 85965L;
        final IRelationShipBean instance = new RelationShipBeanLocal();
        final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, relationShipService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                relationShipService.getRelationShip(pRelationShipId, pTechInfo);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getRelationShip(pContextMsg, pRelationShipId, pTechInfo);
    }
    // </editor-fold>
}
