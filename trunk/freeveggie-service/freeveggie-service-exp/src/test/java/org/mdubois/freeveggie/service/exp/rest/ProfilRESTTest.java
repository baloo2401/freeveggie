package org.mdubois.freeveggie.service.exp.rest;

import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IProfilBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.ProfileMsg;

/**
 *
 * @author Mickael
 */
public class ProfilRESTTest {

    private final ProfilREST service = new ProfilREST();

    @Mocked
    private IProfilBean profilBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, profilBean);
    }

    @Test
    public void testGetProfilById() throws BusinessException {

        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                profilBean.getProfilById(pContextMsg, null);
                returns(null);
            }
        };
        Assert.assertNull(service.getProfilById(null, null));
    }

    @Test
    public void testGetProfilByIdWithReturn() throws BusinessException {

        final long userId = 10L;
        final Long profilId = 11L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                profilBean.getProfilById(pContextMsg, profilId);
                returns(null);
            }
        };
        Assert.assertNull(service.getProfilById(profilId, userId));
    }

    @Test
    public void testUpdateProfilById() throws BusinessException {

        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                profilBean.update(pContextMsg, null);
                returns(false);
            }
        };
        service.updateProfil(null, null);
    }

    @Test
    public void testUpdateProfilByIdWithReturn() throws BusinessException {

        final long userId = 10L;
        final ProfileMsg profil = new ProfileMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                profilBean.update(pContextMsg, profil);
                returns(true);
            }
        };
        service.updateProfil(profil, userId);
    }

    @Test(expected = BusinessWebException.class)
    public void testUpdateProfilThrowException() throws BusinessException {

        final long userId = 10L;
        final ProfileMsg profil = new ProfileMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                profilBean.update(pContextMsg, profil);
                result = new BusinessException("");
            }
        };
        service.updateProfil(profil, userId);
    }
}
