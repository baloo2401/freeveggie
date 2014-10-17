package org.mdubois.freeveggie.service.exp.rest;

import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.ISecurityBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;

/**
 *
 * @author Mickael
 */
public class SecurityRESTTest {

    private final SecurityREST service = new SecurityREST();

    @Mocked
    private ISecurityBean securityBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, securityBean);
    }

    @Test
    public void testControlPassword() throws BusinessException {

        new Expectations() {
            {
                AuthenticationMsg authenticationMsg = new AuthenticationMsg();
                authenticationMsg.setLogin(null);
                authenticationMsg.setPassword(null);
                securityBean.controlPassword(authenticationMsg);
                returns(null);
            }
        };
        Assert.assertNull(service.controlPassword(null, null));
    }

    @Test
    public void testControlPasswordWithValue() throws BusinessException {
        final String login = "login";
        final String password = "login";

        new Expectations() {
            {
                AuthenticationMsg authenticationMsg = new AuthenticationMsg();
                authenticationMsg.setLogin(login);
                authenticationMsg.setPassword(password);
                securityBean.controlPassword(authenticationMsg);
                returns(null);
            }
        };
        Assert.assertNull(service.controlPassword("login", password));
    }

    @Test(expected = BusinessWebException.class)
    public void testControlPasswordTestException() throws BusinessException {

        new Expectations() {
            {
                AuthenticationMsg authenticationMsg = new AuthenticationMsg();
                authenticationMsg.setLogin(null);
                authenticationMsg.setPassword(null);
                securityBean.controlPassword(authenticationMsg);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.controlPassword(null, null));
    }

    @Test
    public void testControlTempPassword() throws BusinessException {

        new Expectations() {
            {
                AuthenticationMsg authenticationMsg = new AuthenticationMsg();
                authenticationMsg.setLogin(null);
                authenticationMsg.setPassword(null);
                securityBean.controlTempPassword(authenticationMsg);
                returns(null);
            }
        };
        Assert.assertNull(service.controlTempPassword(null, null));
    }

    @Test
    public void testControlTempPasswordWithValue() throws BusinessException {
        final String login = "login";
        final String password = "login";

        new Expectations() {
            {
                AuthenticationMsg authenticationMsg = new AuthenticationMsg();
                authenticationMsg.setLogin(login);
                authenticationMsg.setPassword(password);
                securityBean.controlTempPassword(authenticationMsg);
                returns(null);
            }
        };
        Assert.assertNull(service.controlTempPassword("login", password));
    }

    @Test
    public void testChangePassword() throws BusinessException {

        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                ChangePasswordMsg pChangePasswordMsg = null;
                securityBean.changePassword(pContextMsg, pChangePasswordMsg);
                returns(null);
            }
        };
        service.changePassword(null, null);
    }

    @Test
    public void testChangePasswordWithValue() throws BusinessException {

        final ChangePasswordMsg password = new ChangePasswordMsg();
        final String newPass = "new pass";
        password.setNewPassword(newPass);
        final String oldPass = "old pass";
        password.setOldPassword(oldPass);
        final long userId = 10L;
        password.setUserId(userId);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                ChangePasswordMsg pChangePasswordMsg = new ChangePasswordMsg();
                pChangePasswordMsg.setNewPassword(newPass);
                pChangePasswordMsg.setOldPassword(oldPass);
                pChangePasswordMsg.setUserId(userId);
                securityBean.changePassword(pContextMsg, password);
                returns(null);
            }
        };
        service.changePassword(password, userId);
    }

    @Test(expected = BusinessWebException.class)
    public void testChangePasswordTestException() throws BusinessException {

        final ChangePasswordMsg password = new ChangePasswordMsg();
        final String newPass = "new pass";
        password.setNewPassword(newPass);
        final String oldPass = "old pass";
        password.setOldPassword(oldPass);
        final long userId = 10L;
        password.setUserId(userId);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                ChangePasswordMsg pChangePasswordMsg = new ChangePasswordMsg();
                pChangePasswordMsg.setNewPassword(newPass);
                pChangePasswordMsg.setOldPassword(oldPass);
                pChangePasswordMsg.setUserId(userId);
                securityBean.changePassword(pContextMsg, password);
                result = new BusinessException("");
            }
        };
        service.changePassword(password, userId);
    }

    @Test
    public void testIsExistingEmailNotExisting() {
        final String existingEmail = "testExistingEmail";
        final boolean expectedResult = false;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.isExistingEmail(existingEmail);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("false", service.isExistingEmail(existingEmail));
    }

    @Test
    public void testIsExistingEmailExisting() {
        final String existingEmail = "testExistingEmail";
        final boolean expectedResult = true;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.isExistingEmail(existingEmail);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("true", service.isExistingEmail(existingEmail));

    }

    @Test
    public void testGenerateTempPassword() throws BusinessException {
        final String existingEmail = "testExistingEmail";
        final boolean expectedResult = true;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.generateTempPassword(existingEmail);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("true", service.generateTempPassword(existingEmail));
    }

    @Test(expected = BusinessWebException.class)
    public void testGenerateTempPasswordException() throws BusinessException {
        final String existingEmail = "testExistingEmail";
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.generateTempPassword(existingEmail);
                result = new BusinessException("");
            }
        };
        service.generateTempPassword(existingEmail);
    }

    @Test
    public void testIsExistingLoginNotExisting() {
        final String existingLogin = "testExistingLogin";
        final boolean expectedResult = false;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.isExistingLogin(existingLogin);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("false", service.isExistingLogin(existingLogin));
    }

    @Test
    public void testIsExistingLoginExisting() {
        final String existingLogin = "testExistingLogin";
        final boolean expectedResult = true;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.isExistingLogin(existingLogin);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("true", service.isExistingLogin(existingLogin));
    }

    @Test
    public void testHasTempPasswordNo() throws BusinessException {
        final String existingLogin = "testExistingLogin";
        final boolean expectedResult = false;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.hasTempPassword(existingLogin);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("false", service.hasTempPassword(existingLogin));
    }

    @Test
    public void testHasTempPassword() throws BusinessException {
        final String existingLogin = "testExistingLogin";
        final boolean expectedResult = true;
        Deencapsulation.setField(service, securityBean);
        new Expectations() {
            {
                securityBean.hasTempPassword(existingLogin);
                returns(expectedResult);
            }
        };
        Assert.assertEquals("true", service.hasTempPassword(existingLogin));
    }

}
