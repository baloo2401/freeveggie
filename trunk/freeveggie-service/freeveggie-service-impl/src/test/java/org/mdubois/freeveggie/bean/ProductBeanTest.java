package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.ProductBeanLocal;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.service.api.IProductService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;

// </editor-fold>
/**
 *
 * @author mdubois
 */
public class ProductBeanTest {

    @Mocked
    private IProductService gardenService;
    @Mocked
    private IRightControlerService rightControlerService;
    // <editor-fold defaultstate="collapsed" desc="Create">
    /**
     * Test of create method, of class ProductBean.
     */
    @Test
    public void testCreate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        final Long gardenId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductMsg pProductMsg = new ProductMsg();
        pProductMsg.setGarden(new GardenMsg());
        pProductMsg.getGarden().setId(gardenId);
        final IProductBean instance = new ProductBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, gardenId);
                returns(true);

                gardenService.create(pProductMsg);
                returns(expResult);
            }
        };

        Long result = instance.create(pContextMsg, pProductMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testCreateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductMsg pProductMsg = new ProductMsg();
        final IProductBean instance = new ProductBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.create(pContextMsg, pProductMsg);
    }

    @Test(expected = BusinessException.class)
    public void testCreatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        final Long gardenId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductMsg pProductMsg = new ProductMsg();
        pProductMsg.setGarden(new GardenMsg());
        pProductMsg.getGarden().setId(gardenId);
        final IProductBean instance = new ProductBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, gardenId);
                returns(true);

                gardenService.create(pProductMsg);
                result = new BusinessException("BusinessException");
            }
        };

        Long result = instance.create(pContextMsg, pProductMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = TechnicalException.class)
    public void testCreatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        final Long gardenId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductMsg pProductMsg = new ProductMsg();
        pProductMsg.setGarden(new GardenMsg());
        pProductMsg.getGarden().setId(gardenId);
        final IProductBean instance = new ProductBeanLocal();
        final Long expResult = 1234L;

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerGarden(userId, gardenId);
                returns(true);

                gardenService.create(pProductMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        Long result = instance.create(pContextMsg, pProductMsg);
        assertEquals(expResult, result);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Update">
    /**
     * Test of update method, of class ProductBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pProductId = 234L;
        pProductMsg.setId(pProductId);
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.update(pProductMsg);
            }
        };

        instance.update(pContextMsg, pProductMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pProductId = 234L;
        pProductMsg.setId(pProductId);
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.update(pContextMsg, pProductMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pProductId = 234L;
        pProductMsg.setId(pProductId);
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(false);

            }
        };

        instance.update(pContextMsg, pProductMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pProductId = 234L;
        pProductMsg.setId(pProductId);
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.update(pProductMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.update(pContextMsg, pProductMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final UpdateProductMsg pProductMsg = new UpdateProductMsg();
        final Long pProductId = 234L;
        pProductMsg.setId(pProductId);
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.update(pProductMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.update(pContextMsg, pProductMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Blacklist">
    /**
     * Test of blacklist method, of class ProductBean.
     */
    @Test
    public void testBlacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pProductId);
            }
        };

        instance.blacklist(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testBlacklistAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(false);

            }
        };

        instance.blacklist(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testBlacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.blacklist(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testBlacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.blacklist(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.blacklist(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unblacklist">
    /**
     * Test of unblacklist method, of class ProductBean.
     */
    @Test
    public void testUnunblacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pProductId);
            }
        };

        instance.unblacklist(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnunblacklistAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(false);
            }
        };

        instance.unblacklist(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testUnunblacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unblacklist(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testUnunblacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM, UserRole.SUPERADMIN);
                returns(true);

                gardenService.unblacklist(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unblacklist(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove">
    /**
     * Test of remove method, of class ProductBean.
     */
    @Test
    public void testRemove() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.remove(pProductId);
            }
        };

        instance.remove(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.remove(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testRemoveAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(false);
            }
        };

        instance.remove(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testRemovetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.remove(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.remove(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testRemovetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.remove(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.remove(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reactivate">
    /**
     * Test of reactivate method, of class ProductBean.
     */
    @Test
    public void testReactivate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.reactivate(pProductId);
            }
        };

        instance.reactivate(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void AccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.reactivate(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void AccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(false);
            }
        };

        instance.reactivate(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testReactivatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.reactivate(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.reactivate(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testReactivatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.reactivate(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.reactivate(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testArchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pProductId);
            }
        };

        instance.archive(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testArchiveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(false);
            }
        };

        instance.archive(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testArchivetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.archive(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testArchivetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.SYSTEM);
                returns(true);

                gardenService.archive(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.archive(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testUnarchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.unarchive(pProductId);
            }
        };

        instance.unarchive(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.unarchive(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveAccessNotGrantedExceptionV2() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(false);
            }
        };

        instance.unarchive(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testUnArchiveThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.unarchive(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.unarchive(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testUnarchiveThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProduct(userId, pProductId);
                returns(true);

                gardenService.unarchive(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unarchive(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductById">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductById() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductById(pProductId);
            }
        };

        instance.getProductById(pContextMsg, pProductId);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByIdAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductById(pContextMsg, pProductId);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByIdThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductById(pProductId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductById(pContextMsg, pProductId);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByIdThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 2739L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductById(pProductId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductById(pContextMsg, pProductId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByUser">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByUser(pUserId, pTechnicalInformation);
            }
        };

        instance.getProductByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByUserAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.getProductByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByUserThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByUser(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductByUser(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByUserThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 123990L;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByUser(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductByUser(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByCity">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCity(pRefCityId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getProductByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByCityAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductByCity(pContextMsg, pRefCityId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByState">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductByState() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByState(pRefStateId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getProductByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByStateAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByStateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByState(pRefStateId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByStateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByState(pRefStateId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductByState(pContextMsg, pRefStateId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByRegion">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getProductByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByRegionAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductByRegion(pContextMsg, pRefRegionId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getProductByCountry">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testgetProductByCountry() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
            }
        };

        instance.getProductByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testgetProductByCountryAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testgetProductByCountryThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testgetProductByCountryThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductByCountry(pContextMsg, pRefCountryId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Comment">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.comment(pProductCommentMsg);
            }
        };

        instance.comment(pContextMsg, pProductCommentMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.comment(pContextMsg, pProductCommentMsg);
    }

    @Test(expected = BusinessException.class)
    public void testCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.comment(pProductCommentMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.comment(pContextMsg, pProductCommentMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductCommentMsg pProductCommentMsg = new ProductCommentMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.comment(pProductCommentMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.comment(pContextMsg, pProductCommentMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove comment">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testRemoveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testReactivateComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testArchiveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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
     * Test of unarchive method, of class ProductBean.
     */
    @Test
    public void testUnarchiveComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pCommentId = 12233L;
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
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
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                rightControlerService.isUserOwnerProductComment(userId, pCommentId);
                returns(true);

                gardenService.unarchiveComment(pCommentId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.unarchiveComment(pContextMsg, pCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetProductComment">
    /**
     * Test of unarchive method, of class ProductBean.
     */
    @Test
    public void testGetProductComment() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductComment(pProductId, pTechnicalInformation);
            }
        };

        instance.getProductComment(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetProductCommentAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductComment(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetProductCommentThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductComment(pProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductComment(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetProductCommentByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();

        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductComment(pProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductComment(pContextMsg, pProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetProductCommentWrite">
    /**
     * Test of unarchive method, of class ProductBean.
     */
    @Test
    public void testGetProductCommentWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductCommentWrite(pUserId, pTechnicalInformation);
            }
        };

        instance.getProductCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetProductCommentWriteAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetProductCommentThrowBusinessExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductCommentWrite(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetProductCommentByRegionThrowTechnicalExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();

        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductCommentWrite(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductCommentWrite(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Like">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.like(pProductLikeMsg);
            }
        };

        instance.like(pContextMsg, pProductLikeMsg);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testLikeAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);

            }
        };

        instance.like(pContextMsg, pProductLikeMsg);
    }

    @Test(expected = BusinessException.class)
    public void testLikeThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.like(pProductLikeMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.like(pContextMsg, pProductLikeMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testLikeByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final ProductLikeMsg pProductLikeMsg = new ProductLikeMsg();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.like(pProductLikeMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.like(pContextMsg, pProductLikeMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unlike">
    /**
     * Test of archive method, of class ProductBean.
     */
    @Test
    public void testRemoveLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pLikeId = 12233L;
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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
        final IProductBean instance = new ProductBeanLocal();

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

    // <editor-fold defaultstate="collapsed" desc="GetProductLike">
    /**
     * Test of unarchive method, of class ProductBean.
     */
    @Test
    public void testGetProductLike() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductLike(pProductId, pTechnicalInformation);
            }
        };

        instance.getProductLike(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetProductLikeAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductLike(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetProductLikeThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductLike(pProductId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductLike(pContextMsg, pProductId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetProductLikeByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pProductId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();

        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductLike(pProductId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductLike(pContextMsg, pProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetProductLikeWrite">
    /**
     * Test of unarchive method, of class ProductBean.
     */
    @Test
    public void testGetProductLikeWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                gardenService.getProductLikeWrite(pUserId, pTechnicalInformation);
            }
        };

        instance.getProductLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetProductLikeWriteAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProductLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = BusinessException.class)
    public void testGetProductLikeThrowBusinessExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductLikeWrite(pUserId, pTechnicalInformation);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProductLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }

    @Test(expected = TechnicalException.class)
    public void testGetProductLikeByRegionThrowTechnicalExceptionWrite() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 23L;
        UserContext user = new UserContext();
        user.setId(userId);
        pContextMsg.setUser(user);
        final Long pUserId = 12233L;
        final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();

        final IProductBean instance = new ProductBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, rightControlerService);
                Deencapsulation.setField(instance, gardenService);
                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);
                gardenService.getProductLikeWrite(pUserId, pTechnicalInformation);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProductLikeWrite(pContextMsg, pUserId, pTechnicalInformation);
    }
    // </editor-fold>
}
