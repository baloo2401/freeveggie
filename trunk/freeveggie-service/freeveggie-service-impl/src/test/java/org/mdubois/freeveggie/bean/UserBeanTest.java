package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bean.local.UserBeanLocal;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.UserOrderColumn;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.api.IUserService;
// </editor-fold>

// </editor-fold>
/**
 *
 * @author mdubois
 */
public class UserBeanTest {

    // <editor-fold defaultstate="collapsed" desc="Blacklist">
    /**
     * Test of blacklist method, of class UserBean.
     */
    @Test
    public void testBlacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.blacklist(pUserId);
            }
        };
        
        instance.blacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testBlacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.blacklist(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.blacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testBlacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.blacklist(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.blacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testBlacklisttThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.blacklist(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unblacklist">
    /**
     * Test of unblacklist method, of class UserBean.
     */
    @Test
    public void testUnunblacklist() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.unblacklist(pUserId);
            }
        };
        
        instance.unblacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testUnunblacklisttThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.unblacklist(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.unblacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testUnunblacklisttThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.unblacklist(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.unblacklist(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testUnunblacklisttThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.unblacklist(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Upgrade">
    /**
     * Test of upgrade method, of class UserBean.
     */
    @Test
    public void testUpgrade() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.upgrade(pUserId);
            }
        };
        
        instance.upgrade(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testUpgradetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.upgrade(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.upgrade(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testUpgradetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.upgrade(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.upgrade(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testUpgradetThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.upgrade(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Downgrade">
    /**
     * Test of downgrade method, of class UserBean.
     */
    @Test
    public void testDowngrade() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.downgrade(pUserId);
            }
        };
        
        instance.downgrade(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testDowngradetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.downgrade(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.downgrade(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testDowngradetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.downgrade(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.downgrade(pContextMsg, pUserId);
    }
    
    
    @Test(expected = AccessNotGrantedException.class)
    public void testDowngradetThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.downgrade(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Validate">
    /**
     * Test of validate method, of class UserBean.
     */
    @Test
    public void testValidate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final String pUserId = "2739L";
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.validate(pUserId);
            }
        };
        
        instance.validate(pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testValidatetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final String pUserId = "2739L";
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.validate(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.validate(pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testValidatetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final String pUserId = "2739L";
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.validate(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.validate(pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Delete">
    /**
     * Test of delete method, of class UserBean.
     */
    @Test
    public void testDelete() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.delete(pUserId);
            }
        };
        
        instance.delete(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testDeletetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.delete(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.delete(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testDeletetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.delete(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.delete(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testDeletetThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.delete(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Undelete">
    /**
     * Test of undelete method, of class UserBean.
     */
    @Test
    public void testUndelete() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.undelete(pUserId);
            }
        };
        
        instance.undelete(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testUndeletetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.undelete(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.undelete(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testUndeletetThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.undelete(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ChangeRole">
    /**
     * Test of changerole method, of class UserBean.
     */
    @Test
    public void testChangeRole() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final UserRole pUserRole = UserRole.ADMIN;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.changeRole(pUserId, pUserRole);
            }
        };
        
        instance.changeRole(pContextMsg, pUserId, pUserRole);
    }
    
    @Test(expected = BusinessException.class)
    public void testChangeRoletThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final UserRole pUserRole = UserRole.ADMIN;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.changeRole(pUserId, pUserRole);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.changeRole(pContextMsg, pUserId, pUserRole);
    }
    
    @Test(expected = TechnicalException.class)
    public void testChangeRoletThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final UserRole pUserRole = UserRole.ADMIN;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.changeRole(pUserId, pUserRole);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.changeRole(pContextMsg, pUserId, pUserRole);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testChangeRoletThrowAccessNotGrantedException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final UserRole pUserRole = UserRole.ADMIN;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.changeRole(pContextMsg, pUserId, pUserRole);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testArchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.archive(pUserId);
            }
        };
        
        instance.archive(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testArchivetThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.archive(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.archive(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testArchivetThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.archive(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.archive(pContextMsg, pUserId);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testArchivetThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.archive(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testUnarchive() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.unarchive(pUserId);
            }
        };
        
        instance.unarchive(pContextMsg, pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testUnArchiveThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.unarchive(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.unarchive(pContextMsg, pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testUnarchiveThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(true);
                
                gardenService.unarchive(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.unarchive(pContextMsg, pUserId);
    }
    @Test(expected = AccessNotGrantedException.class)
    public void testUnarchiveThrowAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                rightControlerService.isUserInRole(anyLong, UserRole.SUPERADMIN, UserRole.SYSTEM);
                returns(false);
            }
        };
        
        instance.unarchive(pContextMsg, pUserId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUserById">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testgetUserById() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserById(pUserId);
            }
        };
        
        instance.getUserById(pUserId);
    }
    
    @Test(expected = BusinessException.class)
    public void testgetUserByIdThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserById(pUserId);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.getUserById(pUserId);
    }
    
    @Test(expected = TechnicalException.class)
    public void testgetUserByIdThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Long pUserId = 2739L;
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserById(pUserId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.getUserById(pUserId);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUserByCity">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testgetUserByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
            }
        };
        
        instance.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = BusinessException.class)
    public void testgetUserByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = TechnicalException.class)
    public void testgetUserByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCityId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.getUserByCity(pRefCityId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUserByState">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testgetUserByState() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
            }
        };
        
        instance.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = BusinessException.class)
    public void testgetUserByStateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = TechnicalException.class)
    public void testgetUserByStateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefStateId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.getUserByState(pRefStateId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUserByRegion">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testgetUserByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
            }
        };
        
        instance.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = BusinessException.class)
    public void testgetUserByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = TechnicalException.class)
    public void testgetUserByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefRegionId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.getUserByRegion(pRefRegionId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUserByCountry">
    /**
     * Test of archive method, of class UserBean.
     */
    @Test
    public void testgetUserByCountry() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
            }
        };
        
        instance.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = BusinessException.class)
    public void testgetUserByCountryThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                throwsException(new BusinessException("BusinessException"));
            }
        };
        
        instance.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
    }
    
    @Test(expected = TechnicalException.class)
    public void testgetUserByCountryThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        final Integer pRefCountryId = 123990;
        final Integer pRefProductId = 998877;
        final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation = new TechnicalInformation<UserCriteriaColumn, UserOrderColumn>();
        final IUserBean instance = new UserBeanLocal();
        
        new Expectations() {
            @Mocked
            private IUserService gardenService;
            @Mocked
            private IRightControlerService rightControlerService;
            
            {
                Deencapsulation.setField(instance, gardenService);
                Deencapsulation.setField(instance, rightControlerService);
                
                gardenService.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };
        
        instance.getUserByCountry(pRefCountryId, pRefProductId, pTechnicalInformation);
    }
    // </editor-fold>
}
