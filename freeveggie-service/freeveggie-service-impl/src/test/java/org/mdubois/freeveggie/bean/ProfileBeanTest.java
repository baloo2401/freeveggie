package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.ProfileBeanLocal;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.api.IProfileService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class ProfileBeanTest {

    @Mocked
    private IProfileService profileService;
    @Mocked
    private IRightControlerService rightControlerService;

    // <editor-fold defaultstate="collapsed" desc="Create">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final ProfileMsg pProfilMsg = new ProfileMsg();
        pProfilMsg.setId(userId);
        final IProfilBean instance = new ProfileBeanLocal();
        final boolean expResult = true;

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.update(userId, pProfilMsg);
                returns(expResult);
            }
        };

        boolean result = instance.update(pContextMsg, pProfilMsg);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final ProfileMsg pProfilMsg = new ProfileMsg();
        pProfilMsg.setId(userId);
        final IProfilBean instance = new ProfileBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.update(pContextMsg, pProfilMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final ProfileMsg pProfilMsg = new ProfileMsg();
        pProfilMsg.setId(userId);
        final IProfilBean instance = new ProfileBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.update(userId, pProfilMsg);
                result = new BusinessException("BusinessException");
            }
        };

        instance.update(pContextMsg, pProfilMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final ProfileMsg pProfilMsg = new ProfileMsg();
        pProfilMsg.setId(userId);
        final IProfilBean instance = new ProfileBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.update(userId, pProfilMsg);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.update(pContextMsg, pProfilMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="GetProfileById">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetProfilById() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IProfilBean instance = new ProfileBeanLocal();
        final ProfileMsg expResult = new ProfileMsg();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.getProfilById(userId);
                returns(expResult);
            }
        };

        ProfileMsg result = instance.getProfilById(pContextMsg, userId);
        assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetProfilByIdAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IProfilBean instance = new ProfileBeanLocal();
        final ProfileMsg expResult = new ProfileMsg();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.getProfilById(pContextMsg, userId);
    }

    @Test(expected = BusinessException.class)
    public void testGetProfilByIdThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IProfilBean instance = new ProfileBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.getProfilById(userId);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getProfilById(pContextMsg, userId);
    }

    @Test(expected = TechnicalException.class)
    public void testGetProfilByIdThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IProfilBean instance = new ProfileBeanLocal();

        new Expectations() {

            {
                Deencapsulation.setField(instance, profileService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                profileService.getProfilById(userId);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getProfilById(pContextMsg, userId);
    }
    // </editor-fold>
}
