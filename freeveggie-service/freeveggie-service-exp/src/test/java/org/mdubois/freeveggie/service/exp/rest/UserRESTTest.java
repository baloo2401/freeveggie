package org.mdubois.freeveggie.service.exp.rest;

import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.ISubscriptionBean;
import org.mdubois.freeveggie.bean.IUserBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

/**
 *
 * @author Mickael
 */
public class UserRESTTest {

    private final UserREST service = new UserREST();

    @Mocked
    private IUserBean userBean;

    @Mocked
    private ISubscriptionBean subscriptionBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, userBean);
        Deencapsulation.setField(service, subscriptionBean);
    }

    @Test
    public void testGetUserById() throws BusinessException {

        final long userId = 10L;
        new Expectations() {
            {
                userBean.getUserById(userId);
                returns(null);
            }
        };
        Assert.assertNull(service.getUserById(userId));
    }

    @Test
    public void testGetUserByIdWithReturn() throws BusinessException {

        final long userId = 10L;
        final UserMsg user = new UserMsg();
        new Expectations() {
            {
                userBean.getUserById(userId);
                returns(user);
            }
        };
        Assert.assertEquals(user, service.getUserById(userId));
    }

    @Test
    public void testAddUser() throws BusinessException {

        new Expectations() {
            {
                subscriptionBean.create(null);
                returns(null);
            }
        };
        service.addUser(null);
    }

    @Test
    public void testAddUserWithValue() throws BusinessException {

        final CreateAccountMsg user = new CreateAccountMsg();
        new Expectations() {
            {
                subscriptionBean.create(user);
                returns(null);
            }
        };
        service.addUser(user);
    }

    @Test
    public void testAddUserWithValueAndReturn() throws BusinessException {

        final CreateAccountMsg user = new CreateAccountMsg();
        final Long userId = 123L;
        new Expectations() {
            {
                subscriptionBean.create(user);
                returns(userId);
            }
        };

        service.addUser(user);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddUserException() throws BusinessException {

        final CreateAccountMsg user = new CreateAccountMsg();
        final Long userId = 123L;
        new Expectations() {
            {
                subscriptionBean.create(user);
                result = new BusinessException("");
            }
        };

        service.addUser(user);
    }

    @Test
    public void testUpdateUser() throws BusinessException {

        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                userBean.update(pContextMsg, null);
                returns(true);
            }
        };
        service.updateUser(null, null);
    }

    @Test
    public void testUpdateUserWithValue() throws BusinessException {

        final UserMsg user = new UserMsg();
        final Long userId = 10l;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                userBean.update(pContextMsg, user);
                returns(false);
            }
        };
        service.updateUser(user, userId);
    }

    @Test
    public void testUpdateUserWithValueAndReturn() throws BusinessException {

        final UserMsg user = new UserMsg();
        final Long userId = 10l;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                userBean.update(pContextMsg, user);
                returns(true);
            }
        };
        service.updateUser(user, userId);
    }

    @Test(expected = BusinessWebException.class)
    public void testUpdateUserException() throws BusinessException {
        final UserMsg user = new UserMsg();
        final Long userId = 10l;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                userBean.update(pContextMsg, user);
                result = new BusinessException("");
            }
        };
        service.updateUser(user, userId);

    }

    @Test
    public void testValidateCodeFalse() throws BusinessException {

        final String code = "";
        new Expectations() {
            {
                userBean.validate(code);
                returns(false);
            }
        };
        Assert.assertEquals("false", service.validateCode(code));
    }

    @Test
    public void testValidateCodeWithReturnTrue() throws BusinessException {

        final String code = "";
        new Expectations() {
            {
                userBean.validate(code);
                returns(true);
            }
        };
        Assert.assertEquals("true", service.validateCode(code));
    }
}
