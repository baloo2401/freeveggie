package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bean.local.SecurityBeanLocal;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.api.ISecurityService;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class SecurityBeanTest {

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void controlPassword() throws BusinessException {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.controlPassword(pAuthentificationMsg);
                returns(userMsg);
            }
        };
        UserMsg result = securityBean.controlPassword(pAuthentificationMsg);
        Assert.assertEquals(result, userMsg);
    }

    @Test
    public void controlTempPassword() {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final AuthenticationMsg pAuthentificationMsg = new AuthenticationMsg();
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.controlTempPassword(pAuthentificationMsg);
                returns(userMsg);
            }
        };
        UserMsg result = securityBean.controlTempPassword(pAuthentificationMsg);
        Assert.assertEquals(result, userMsg);
    }

    @Test
    public void hasTempPassword() throws BusinessException {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final String pCode = "pCode";
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.hasTempPassword(pCode);
                returns(true);
            }
        };
        boolean result = securityBean.hasTempPassword(pCode);
        Assert.assertEquals(result, true);
    }

    @Test
    public void isExistingLogin() {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final String pLogin = "pLogin";
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.isExistingLogin(pLogin);
                returns(true);
            }
        };
        boolean result = securityBean.isExistingLogin(pLogin);
        Assert.assertEquals(result, true);
    }

    @Test
    public void isExistingEmail() {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final String pEmail = "pEmail";
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.isExistingEmail(pEmail);
                returns(false);
            }
        };
        boolean result = securityBean.isExistingEmail(pEmail);
        Assert.assertEquals(result, false);
    }

    @Test
    public void generateTempPassword() throws BusinessException {
        final ISecurityBean securityBean = new SecurityBeanLocal();
        final String pEmail = "pEmail";
        final UserMsg userMsg = new UserMsg();
        new Expectations(){
            @Mocked
            private ISecurityService securityService;
            {
                Deencapsulation.setField(securityBean, securityService);
                
                securityService.generateTempPassword(pEmail);
            }
        };
        securityBean.generateTempPassword(pEmail);
    }
}
