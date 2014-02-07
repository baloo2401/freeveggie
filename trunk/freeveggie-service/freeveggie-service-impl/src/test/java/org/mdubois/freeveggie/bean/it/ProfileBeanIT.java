package org.mdubois.freeveggie.bean.it;

 // <editor-fold defaultstate="collapsed" desc="Imports">
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IProfilBean;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class ProfileBeanIT extends AbstractBeanIntegrationTest {

    private IProfilBean profileBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/ProfileBeanLocal");
        profileBean = (IProfilBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetProfilById() throws Exception {
        profileBean.getProfilById(null, null);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetProfilById1() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(2L);
        profileBean.getProfilById(pContextMsg, null);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetProfilById2() throws Exception {
        Long pProfileId = 1L;
        profileBean.getProfilById(null, pProfileId);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProfilById3() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pProfileId = 1L;
        ProfileMsg result = profileBean.getProfilById(pContextMsg, pProfileId);
        assertEquals(pProfileId, result.getId());
        assertEquals("experience", result.getExperience());
        assertEquals("interest", result.getInterest());
        assertEquals("other", result.getOther());
        assertEquals("participation", result.getParticipation());
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testUpdate() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(2L);
        ProfileMsg pProfileMsg = new ProfileMsg();
        pProfileMsg.setId(2L);
        pProfileMsg.setExperience("experience tmp");
        pProfileMsg.setInterest("interest tmp");
        pProfileMsg.setOther("other tmp");
        pProfileMsg.setParticipation("particpation tmp");
        pProfileMsg.setPersonalDescription("description tmp");
        pProfileMsg.setPhilosophy("philosophy tmp");
        pProfileMsg.setPreferedMeals("meals tmp");
        pProfileMsg.setReason("reason tmp");
        pProfileMsg.setUserPictureFilename("picture tmp");
        profileBean.update(pContextMsg, pProfileMsg);
    }
}
