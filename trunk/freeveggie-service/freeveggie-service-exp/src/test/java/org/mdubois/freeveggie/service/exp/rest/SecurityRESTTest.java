package org.mdubois.freeveggie.service.exp.rest;

import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bean.ISecurityBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;

/**
 *
 * @author Mickael
 */
public class SecurityRESTTest {

    private final SecurityREST service = new SecurityREST();

    @Mocked
    private ISecurityBean securityBean;

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
