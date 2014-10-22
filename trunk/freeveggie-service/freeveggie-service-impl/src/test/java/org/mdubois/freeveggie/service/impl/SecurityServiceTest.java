package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.UUID;
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
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class SecurityServiceTest {

    @Mocked
    private IAuthenticationDAO authenticationDAO;
    @Mocked
    private Converter<UserMsg, UserBO> userBOToMsgConverter;
    @Mocked
    private IUserDAO userDAO;
    @Mocked
    private INotificationDAO notificationDAO;
    @Mocked
    private UUID uuid;

    @Test
    public void teschangePassword() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
        final String newPassword = "password11234";
        final String oldPassword = "password23456";
        final Long userID = 12569L;

        changePasswordMsg.setUserId(userID);
        changePasswordMsg.setOldPassword(oldPassword);
        changePasswordMsg.setNewPassword(newPassword);

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, authenticationDAO);

                userDAO.get(userID);
                final UserBO userBO = new UserBO();
                userBO.setUsername("user");
                returns(userBO);

                authenticationDAO.controlPassword(userBO.getUsername(), oldPassword);
                returns(userBO);

                userDAO.save(userBO);
                returns(123L);
            }
        };

        securityService.changePassword(changePasswordMsg);
    }

    @Test(expected = BusinessException.class)
    public void teschangePasswordCantAuthenticate() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
        final String newPassword = "password11234";
        final String oldPassword = "password23456";
        final Long userID = 12569L;

        changePasswordMsg.setUserId(userID);
        changePasswordMsg.setOldPassword(oldPassword);
        changePasswordMsg.setNewPassword(newPassword);

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, authenticationDAO);

                userDAO.get(userID);
                final UserBO userBO = new UserBO();
                userBO.setUsername("user");
                returns(userBO);

                authenticationDAO.controlPassword(userBO.getUsername(), oldPassword);
                returns(null);
            }
        };

        securityService.changePassword(changePasswordMsg);
    }

    @Test(expected = BusinessException.class)
    public void teschangePasswordNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
        final String newPassword = "password11234";
        final String oldPassword = "password23456";
        final Long userID = 12569L;

        changePasswordMsg.setUserId(userID);
        changePasswordMsg.setOldPassword(oldPassword);
        changePasswordMsg.setNewPassword(newPassword);

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);

                userDAO.get(userID);
                returns(null);
            }
        };

        securityService.changePassword(changePasswordMsg);
    }

    @Test(expected = BusinessException.class)
    public void teschangePasswordBadNewPassword() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
        final String login = "login";
        final String newPassword = "passwo";
        final String oldPassword = "password1234";
        final Long userID = 12569L;

        changePasswordMsg.setUserId(userID);
        changePasswordMsg.setOldPassword(oldPassword);
        changePasswordMsg.setNewPassword(newPassword);

        securityService.changePassword(changePasswordMsg);
    }

    @Test(expected = BusinessException.class)
    public void teschangePasswordBadOldPassword() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
        final String login = "login";
        final String newPassword = "password123456";
        final String oldPassword = "passwor";
        final Long userID = 12569L;

        changePasswordMsg.setUserId(userID);
        changePasswordMsg.setOldPassword(oldPassword);
        changePasswordMsg.setNewPassword(newPassword);

        securityService.changePassword(changePasswordMsg);
    }

    @Test(expected = BusinessException.class)
    public void testControlPasswordNoUser() throws BusinessException {
        final ISecurityService securityService = new SecurityService();

        AuthenticationMsg authenticationMsg = new AuthenticationMsg();
        final String login = "login";
        final String password = "password";

        authenticationMsg.setLogin(login);
        authenticationMsg.setPassword(password);

        new Expectations() {

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

        final String uuid = "login";

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword(null);

                userDAO.getUserByUUID(uuid);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(uuid);
        Assert.assertEquals(false, exist);
    }

    @Test
    public void testHasTempPasswordNoTempPasswordEmpty() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String uuid = "login";

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword("");

                userDAO.getUserByUUID(uuid);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(uuid);
        Assert.assertEquals(false, exist);
    }

    @Test(expected = BusinessException.class)
    public void testHasTempPasswordNoUser() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String uuid = "login";

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);

                userDAO.getUserByUUID(uuid);
                returns(null);
            }
        };

        securityService.hasTempPassword(uuid);
    }

    @Test
    public void testHasTempPassword() throws Exception {
        final ISecurityService securityService = new SecurityService();

        final String uuid = "login";

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);

                UserBO userBO = new UserBO();
                userBO.setTemporaryPassword("temppassword");

                userDAO.getUserByUUID(uuid);
                returns(userBO);
            }
        };

        boolean exist = securityService.hasTempPassword(uuid);
        Assert.assertEquals(true, exist);
    }

    @Test
    public void testGenerateTempPassword() throws Exception {
        final ISecurityService securityService = new SecurityService();
        final String email = "email";
        final String login = "login";
        final String uuid = "1234-5679-0123";
        final String encryptedPassword = "sqdfqdsf";

        new Expectations() {

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, notificationDAO);

                UserBO userBO = new UserBO();
                userBO.setId(12L);
                userBO.setEmail(email);
                userBO.setUsername(login);

                userDAO.getUserByEmail(email);
                returns(userBO);

                EncryptionUtils.getMD5(anyString);
                returns(encryptedPassword);

                userDAO.update(with(new UserBO(), new UserBOMatcher()));

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

        new Expectations() {

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

            {
                Deencapsulation.setField(securityService, userDAO);
                Deencapsulation.setField(securityService, notificationDAO);

                UserBO userBO = new UserBO();
                userBO.setId(12L);
                userBO.setEmail(email);
                userBO.setUsername(login);

                userDAO.getUserByEmail(email);
                result = new TechnicalException("TechnicalException");
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
        public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void describeTo(Description description) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
