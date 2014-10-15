package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.GardenBeanLocal;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.api.IGardenService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;

// </editor-fold>
/**
 *
 * @author mdubois
 */
public class GardenBeanTest {

    @Mocked
    private IGardenService gardenService;
    @Mocked
    private IRightControlerService rightControlerService;

    // <editor-fold defaultstate="collapsed" desc="Create">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testCreate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final IGardenBean instance = new GardenBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.create(pGardenMsg);
                returns(expResult);
            }
        };

        Long result = instance.create(pContextMsg, pGardenMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testCreateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final IGardenBean instance = new GardenBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.create(pContextMsg, pGardenMsg);
    }

    @Test(expected = BusinessException.class)
    public void testCreatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final IGardenBean instance = new GardenBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.create(pGardenMsg);
                result = new BusinessException("BusinessException");
            }
        };

        Long result = instance.create(pContextMsg, pGardenMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = TechnicalException.class)
    public void testCreatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final IGardenBean instance = new GardenBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.create(pGardenMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        Long result = instance.create(pContextMsg, pGardenMsg);
        assertEquals(expResult, result);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update">
    /**
     * Test of update method, of class GardenBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pGardenId = 234L;
        pGardenMsg.setId(pGardenId);
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.update(pGardenMsg);
            }
        };

        instance.update(pContextMsg, pGardenMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pGardenId = 234L;
        pGardenMsg.setId(pGardenId);
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.update(pContextMsg, pGardenMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pGardenId = 234L;
        pGardenMsg.setId(pGardenId);
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(false);

            }
        };

        instance.update(pContextMsg, pGardenMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pGardenId = 234L;
        pGardenMsg.setId(pGardenId);
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.update(pGardenMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.update(pContextMsg, pGardenMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenMsg pGardenMsg = new GardenMsg();
        final Long pGardenId = 234L;
        pGardenMsg.setId(pGardenId);
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.update(pGardenMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.update(pContextMsg, pGardenMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Blacklist">
    /**
     * Test of blacklist method, of class GardenBean.
     */
    @Test
    public void testBlacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pGardenId);
            }
        };

        instance.blacklist(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testBlacklistAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(false);

            }
        };

        instance.blacklist(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testBlacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.blacklist(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testBlacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.blacklist(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unblacklist">
    /**
     * Test of unblacklist method, of class GardenBean.
     */
    @Test
    public void testUnunblacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pGardenId);
            }
        };

        instance.unblacklist(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnunblacklistAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(false);
            }
        };

        instance.unblacklist(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testUnunblacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unblacklist(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testUnunblacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unblacklist(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove">
    /**
     * Test of remove method, of class GardenBean.
     */
    @Test
    public void testRemove() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.remove(pGardenId);
            }
        };

        instance.remove(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.remove(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(false);
            }
        };

        instance.remove(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testRemovetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.remove(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.remove(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testRemovetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.remove(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.remove(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reactivate">
    /**
     * Test of reactivate method, of class GardenBean.
     */
    @Test
    public void testReactivate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.reactivate(pGardenId);
            }
        };

        instance.reactivate(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void AccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.reactivate(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void AccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(false);
            }
        };

        instance.reactivate(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testReactivatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.reactivate(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.reactivate(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testReactivatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.reactivate(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.reactivate(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testArchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pGardenId);
            }
        };

        instance.archive(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testArchiveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(false);
            }
        };

        instance.archive(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testArchivetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.archive(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testArchivetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.archive(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testUnarchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.unarchive(pGardenId);
            }
        };

        instance.unarchive(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.unarchive(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(false);
            }
        };

        instance.unarchive(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testUnArchiveThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.unarchive(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unarchive(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testUnarchiveThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, pGardenId);
                returns(true);

                gardenService.unarchive(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unarchive(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenById">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenById() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenById(pGardenId);
            }
        };

        instance.getGardenById(pContextMsg, pGardenId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByIdAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenById(pContextMsg, pGardenId);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByIdThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenById(pGardenId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenById(pContextMsg, pGardenId);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByIdThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 2739L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenById(pGardenId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenById(pContextMsg, pGardenId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenByUser">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByUser(pUserId, pTechnicalInformation);
            }
        };

        instance.getGardenByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByUserAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.getGardenByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByUserThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByUser(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByUserThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByUser(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenByUser(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenByCity">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCity(pRefCityId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getGardenByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByCityAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenByState">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenByState() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByState(pRefStateId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getGardenByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByStateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByStateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByState(pRefStateId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByStateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByState(pRefStateId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenByRegion">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getGardenByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByRegionAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getGardenByCountry">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testgetGardenByCountry() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getGardenByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetGardenByCountryAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetGardenByCountryThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetGardenByCountryThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comment">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.comment(pGardenCommentMsg);
            }
        };

        instance.comment(pContextMsg, pGardenCommentMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.comment(pContextMsg, pGardenCommentMsg);
    }

    @Test(expected = BusinessException.class)
    public void testCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.comment(pGardenCommentMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.comment(pContextMsg, pGardenCommentMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.comment(pGardenCommentMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.comment(pContextMsg, pGardenCommentMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove comment">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testRemoveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.removeComment(pCommentId);
            }
        };

        instance.removeComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN);
                returns(false);

            }
        };

        instance.removeComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveCommentAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(false);

                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN);
                returns(false);
            }
        };

        instance.removeComment(pContextMsg, pCommentId);
    }

    @Test(expected = BusinessException.class)
    public void testRemoveCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.removeComment(pCommentId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.removeComment(pContextMsg, pCommentId);
    }

    @Test(expected = TechnicalException.class)
    public void testRemoveCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.removeComment(pCommentId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.removeComment(pContextMsg, pCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reactivate comment">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testReactivateComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.reactivateComment(pCommentId);
            }
        };

        instance.reactivateComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testReactivateCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.reactivateComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testReactivateCommentAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(false);

            }
        };

        instance.reactivateComment(pContextMsg, pCommentId);
    }

    @Test(expected = BusinessException.class)
    public void testReactivateCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.reactivateComment(pCommentId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.reactivateComment(pContextMsg, pCommentId);
    }

    @Test(expected = TechnicalException.class)
    public void testReactivateCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.reactivateComment(pCommentId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.reactivateComment(pContextMsg, pCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive comment">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testArchiveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archiveComment(pCommentId);
            }
        };

        instance.archiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testArchiveCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(false);

            }
        };

        instance.archiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = BusinessException.class)
    public void testArchiveCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);
                gardenService.archiveComment(pCommentId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.archiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = TechnicalException.class)
    public void testArchiveCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);
                gardenService.archiveComment(pCommentId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.archiveComment(pContextMsg, pCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive comment">
    /**
     * Test of unarchive method, of class GardenBean.
     */
    @Test
    public void testUnarchiveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.unarchiveComment(pCommentId);
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveCommentAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(false);
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = BusinessException.class)
    public void testUnarchiveCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.unarchiveComment(pCommentId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }

    @Test(expected = TechnicalException.class)
    public void testUnarchiveCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGardenComment(userId, pCommentId);
                returns(true);

                gardenService.unarchiveComment(pCommentId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetGardenComment">
    /**
     * Test of unarchive method, of class GardenBean.
     */
    @Test
    public void testGetGardenComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenComment(pGardenId, pTechnicalInformation);
            }
        };

        instance.getGardenComment(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetGardenCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenComment(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetGardenCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenComment(pGardenId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenComment(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetGardenCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();

        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenComment(pGardenId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenComment(pContextMsg, pGardenId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetGardenCommentWrite">
    /**
     * Test of unarchive method, of class GardenBean.
     */
    @Test
    public void testGetGardenCommentWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenCommentWrite(pUserId, pTechnicalInformation);
            }
        };

        instance.getGardenCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetGardenCommentWriteAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetGardenCommentThrowBusinessExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenCommentWrite(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetGardenCommentByRegionThrowTechnicalExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();

        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenCommentWrite(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Like">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.like(pGardenLikeMsg);
            }
        };

        instance.like(pContextMsg, pGardenLikeMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testLikeAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.like(pContextMsg, pGardenLikeMsg);
    }

    @Test(expected = BusinessException.class)
    public void testLikeThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.like(pGardenLikeMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.like(pContextMsg, pGardenLikeMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testLikeByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.like(pGardenLikeMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.like(pContextMsg, pGardenLikeMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unlike">
    /**
     * Test of archive method, of class GardenBean.
     */
    @Test
    public void testRemoveLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pLikeId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.unlike(pLikeId);
            }
        };

        instance.unlike(pContextMsg, pLikeId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveLikeAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pLikeId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.unlike(pContextMsg, pLikeId);
    }

    @Test(expected = BusinessException.class)
    public void testRemoveLikeThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pLikeId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.unlike(pLikeId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unlike(pContextMsg, pLikeId);
    }

    @Test(expected = TechnicalException.class)
    public void testRemoveLikeByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pLikeId = 12233L;
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.unlike(pLikeId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unlike(pContextMsg, pLikeId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetGardenLike">
    /**
     * Test of unarchive method, of class GardenBean.
     */
    @Test
    public void testGetGardenLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenLike(pGardenId, pTechnicalInformation);
            }
        };

        instance.getGardenLike(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetGardenLikeAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenLike(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetGardenLikeThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenLike(pGardenId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenLike(pContextMsg, pGardenId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetGardenLikeByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pGardenId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();

        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenLike(pGardenId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenLike(pContextMsg, pGardenId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetGardenLikeWrite">
    /**
     * Test of unarchive method, of class GardenBean.
     */
    @Test
    public void testGetGardenLikeWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getGardenLikeWrite(pUserId, pTechnicalInformation);
            }
        };

        instance.getGardenLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetGardenLikeWriteAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getGardenLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetGardenLikeThrowBusinessExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenLikeWrite(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getGardenLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetGardenLikeByRegionThrowTechnicalExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();

        final IGardenBean instance = new GardenBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getGardenLikeWrite(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getGardenLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>
}
