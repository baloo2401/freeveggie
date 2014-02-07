package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IAuthenticationDAO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.security.EncryptionUtils;
import org.mdubois.freeveggie.service.api.ISecurityService;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class SecurityServiceTest {

    @Test(expected = BusinessException.class)
    public void testControlPasswordNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        AuthenticationMsg authenticationMsg = new AuthenticationMsg();
        final String login = "login";
        final String password = "password";

        authenticationMsg.setLogin(login);
        authenticationMsg.setPassword(password);


        new Expectations() {

            @Mocked
            private IAuthenticationDAO authenticationDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOToMsgConverter;

            {
                Deencapsulation.setField(securityService, authenticationDAO);
                Deencapsulation.setField(securityService, userBOToMsgConverter);

                authenticationDAO.controlPassword(login, password);
                returns(null);
            }
        };

        UserMsg userMsg = securityService.controlPassword(authenticationMsg);
    }

    @Test
    public void testControlPassword() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        AuthenticationMsg authenticationMsg = new AuthenticationMsg();
        final String login = "login";
        final String password = "password";

        authenticationMsg.setLogin(login);
        authenticationMsg.setPassword(password);

        final UserMsg userExpected = new UserMsg();

        new Expectations() {

            @Mocked
            private IAuthenticationDAO authenticationDAO;
            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOToMsgConverter;

            {
                Deencapsulation.setField(securityService, authenticationDAO);
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, userBOToMsgConverter);

                UserBO userBO = new UserBO();
                userBO.setStatus(UserStatus.VALIDED);

                authenticationDAO.controlPassword(login, password);
                returns(userBO);
                
                //To save last connexion
                userDAO.save(userBO);

                userBOToMsgConverter.convert(userBO);
                returns(userExpected);
            }
        };

        UserMsg userMsg = securityService.controlPassword(authenticationMsg);
        Assert.assertEquals(userExpected, userMsg);
    }

    @Test
    public void testControlTempPasswordNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        AuthenticationMsg authenticationMsg = new AuthenticationMsg();
        final String login = "login";
        final String password = "password";

        authenticationMsg.setLogin(login);
        authenticationMsg.setPassword(password);


        new Expectations() {

            @Mocked
            private IAuthenticationDAO authenticationDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOToMsgConverter;

            {
                Deencapsulation.setField(securityService, authenticationDAO);
                Deencapsulation.setField(securityService, userBOToMsgConverter);

                authenticationDAO.controlTempPassword(login, password);
                returns(null);
            }
        };

        UserMsg userMsg = securityService.controlTempPassword(authenticationMsg);
        Assert.assertEquals(null, userMsg);
    }

    @Test
    public void testControlTempPassword() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        AuthenticationMsg authenticationMsg = new AuthenticationMsg();
        final String login = "login";
        final String password = "password";

        authenticationMsg.setLogin(login);
        authenticationMsg.setPassword(password);

        final UserMsg userExpected = new UserMsg();

        new Expectations() {

            @Mocked
            private IAuthenticationDAO authenticationDAO;
            @Mocked
            private Converter<UserMsg, UserBO> userBOToMsgConverter;

            {
                Deencapsulation.setField(securityService, authenticationDAO);
                Deencapsulation.setField(securityService, userBOToMsgConverter);

                UserBO userBO = new UserBO();

                authenticationDAO.controlTempPassword(login, password);
                returns(userBO);

                userBOToMsgConverter.convert(userBO);
                returns(userExpected);
            }
        };

        UserMsg userMsg = securityService.controlTempPassword(authenticationMsg);
        Assert.assertEquals(userExpected, userMsg);
    }

    @Test
    public void testIsExistingLoginNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                userDAO.getUserByLogin(login);
                returns(null);
            }
        };

        boolean exist = securityService.isExistingLogin(login);
        Assert.assertEquals(false, exist);
    }

    @Test
    public void testIsExistingLogin() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();

                userDAO.getUserByLogin(login);
                returns(userBO);
            }
        };

        boolean exist = securityService.isExistingLogin(login);
        Assert.assertEquals(true, exist);
    }

    @Test
    public void testIsExistingEmailNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        final String email = "email";

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                userDAO.getUserByEmail(email);
                returns(null);
            }
        };

        boolean exist = securityService.isExistingEmail(email);
        Assert.assertEquals(false, exist);
    }

    @Test
    public void testIsExistingEmail() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        final String email = "email";

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();

                userDAO.getUserByEmail(email);
                returns(userBO);
            }
        };

        boolean exist = securityService.isExistingEmail(email);
        Assert.assertEquals(true, exist);
    }

    @Test
    public void testHasTempPasswordNoTempPasswordNull() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword(null);

                userDAO.getUserByLogin(login);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(encryptedLogin);
        Assert.assertEquals(false, exist);
    }

    @Test
    public void testHasTempPasswordNoTempPasswordEmpty() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword("");

                userDAO.getUserByLogin(login);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(encryptedLogin);
        Assert.assertEquals(false, exist);
    }

    @Test(expected = BusinessException.class)
    public void testHasTempPasswordNotValidCode() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private EncryptionUtils encryptionUtils;

            {
                Deencapsulation.setField(securityService, userDAO);

                EncryptionUtils.decrypt(encryptedLogin);
                throwsException(new RuntimeException());
            }
        };

        securityService.hasTempPassword(encryptedLogin);
    }

    @Test(expected = BusinessException.class)
    public void testHasTempPasswordNoUser() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                userDAO.getUserByLogin(login);
                returns(null);
            }
        };

        securityService.hasTempPassword(encryptedLogin);
    }

    @Test
    public void testHasTempPassword() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword("temppassword");

                userDAO.getUserByLogin(login);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(encryptedLogin);
        Assert.assertEquals(true, exist);
    }

    @Test
    public void testGenerateTempPassword() throws Exception {
        final ISecurityService securityService = new SecurityService();
        final String email = "email";
        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);
        final String encryptedPassword = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private INotificationDAO notificationDAO;
            @Mocked
            private EncryptionUtils encryptionUtils;

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, notificationDAO);

                UserBO userBO = new UserBO();
                userBO.setId(12L);
                userBO.setEmail(email);
                userBO.setUsername(login);

                userDAO.getUserByEmail(email);
                returns(userBO);
                EncryptionUtils.encrypt(login);
                returns(encryptedLogin);

                EncryptionUtils.getMD5(anyString);
                returns(encryptedPassword);

                userDAO.update(with(new UserBOMatcher()));

                notificationDAO.sendLostEmailNotice(userBO, anyString, anyString);
            }
        };

        securityService.generateTempPassword(email);
    }



    @Test(expected = BusinessException.class)
    public void testGenerateTempPasswordNoUserFound() throws Exception {
        final ISecurityService securityService = new SecurityService();
        final String email = "email";
        final String login = "login";
        final String encryptedLogin = EncryptionUtils.encrypt(login);

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private INotificationDAO notificationDAO;

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, notificationDAO);

                UserBO userBO = new UserBO();
                userBO.setId(12L);
                userBO.setEmail(email);
                userBO.setUsername(login);

                userDAO.getUserByEmail(email);
                returns(null);
            }
        };

        securityService.generateTempPassword(email);
    }

    @Test(expected = TechnicalException.class)
    public void testGenerateTempPasswordProblemTechnique() throws Exception {
        final ISecurityService securityService = new SecurityService();
        final String email = "email";
        final String login = "login";

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private INotificationDAO notificationDAO;
            @Mocked
            private EncryptionUtils encryptionUtils;

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, notificationDAO);

                UserBO userBO = new UserBO();
                userBO.setId(12L);
                userBO.setEmail(email);
                userBO.setUsername(login);

                userDAO.getUserByEmail(email);
                returns(userBO);
                EncryptionUtils.encrypt(login);
                throwsException(new TechnicalException(""));
            }
        };

        securityService.generateTempPassword(email);
    }



    private static class UserBOMatcher implements Matcher<UserBO> {

        @Override
        public boolean matches(Object item) {
            UserBO toMatch = (UserBO) item;
            return toMatch.getTemporaryPassword() != null;

        }

        @Override
        public void describeMismatch(Object item, Description mismatchDescription) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void describeTo(Description description) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
