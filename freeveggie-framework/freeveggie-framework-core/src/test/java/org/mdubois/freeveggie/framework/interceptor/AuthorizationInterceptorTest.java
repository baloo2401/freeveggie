package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.AuthorizationDefinitionException;
import org.mdubois.freeveggie.framework.exception.IllegalAccessException;
import org.mdubois.freeveggie.framework.security.Allow;
import org.mdubois.freeveggie.framework.security.AllowAll;
import org.mdubois.freeveggie.framework.security.AllowNoOne;
import org.mdubois.freeveggie.framework.security.AllowNotLoggedIn;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.utils.UserUtils;
import org.springframework.core.annotation.AnnotationUtils;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class AuthorizationInterceptorTest {

    @Mocked
    InvocationContext mockCtx;
    @Mocked
    AnnotationUtils mockAnnotationUtils;
    @Mocked
    AllowNoOne mockAllowNoOne;
    @Mocked
    AllowAll mockAllowAll;
    @Mocked
    Allow mockAllow;
    @Mocked
    UserUtils mockUserUtils;
    @Mocked
    AllowNotLoggedIn mockAllowNotLoggedIn;

//    @After
//    public void tearDown() throws Exception {
//        Mockit.restoreAllOriginalDefinitions();
//    }

    /**
     * Test of validate method, of class {@link AuthorizationInterceptor}.
     *
     * @throws Exception
     */
    @Test(expected = IllegalAccessException.class)
    public void testValidateNoUserSet() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();

        new NonStrictExpectations() {

            {

                parameters[0] = null;
                mockCtx.getParameters();
                returns(parameters);
                minTimes = 1;
            }
        };

        instance.validate(mockCtx);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test(expected = IllegalAccessException.class)
    public void testValidateAllowNoOne() throws Exception {

        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();
        new NonStrictExpectations() {

            {
                parameters[0] = new ContextMsg();
                mockCtx.getParameters();
                returns(parameters);

                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, AllowNoOne.class);
                returns(mockAllowNoOne);

            }
        };

        instance.validate(mockCtx);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateAllowAll() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();
        final Object expResult = new Object();
        Object result;
        new NonStrictExpectations() {

            {
                parameters[0] = new ContextMsg();
                mockCtx.getParameters();
                returns(parameters);

                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, AllowAll.class);
                returns(mockAllowAll);

                mockCtx.proceed();
                returns(expResult);
            }
        };

        result = instance.validate(mockCtx);
        assertEquals(expResult, result);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test(expected = AccessNotGrantedException.class)
    public void testValidateAllow() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();
        new NonStrictExpectations() {

            {
                parameters[0] = new ContextMsg();
                mockCtx.getParameters();
                returns(parameters);

                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, Allow.class);
                returns(mockAllow);

                UserUtils.isUserInRole((UserRole) any, (UserRole) any);
                returns(false);

            }
        };

        instance.validate(mockCtx);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateAllowed() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();


        final Object expResult = new Object();
        Object result;

        new NonStrictExpectations() {

            {
                ContextMsg context = new ContextMsg();
                context.setUser(new UserContext());
                parameters[0] = context;
                mockCtx.getParameters();
                returns(parameters);

                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, Allow.class);
                returns(mockAllow);

                mockAllow.value();
                returns(new UserRole[1]);

                UserUtils.isUserInRole((UserRole) any, (UserRole) any);
                returns(Boolean.TRUE);

                mockCtx.proceed();
                returns(expResult);
            }
        };

        result = instance.validate(mockCtx);
        assertEquals(result, expResult);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateNotLoggedIn() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();


        final Object expResult = new Object();
        Object result;

        new Expectations() {

            {
                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, AllowNotLoggedIn.class);
                returns(mockAllowNotLoggedIn);

                mockCtx.proceed();
                returns(expResult);
            }
        };

        result = instance.validate(mockCtx);
        assertEquals(result, expResult);
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test(expected = AuthorizationDefinitionException.class)
    public void testValidateDefintionError() throws Exception {
        final Object[] parameters = new Object[2];
        parameters[1] = new Object();
        AuthorizationInterceptor instance = new AuthorizationInterceptor();
        new NonStrictExpectations() {

            {
                parameters[0] = new ContextMsg();
                mockCtx.getParameters();
                returns(parameters);

                mockCtx.getMethod();

                AnnotationUtils.findAnnotation((Method) any, (Class) any);
                returns(null);

            }
        };

        instance.validate(mockCtx);
    }
}
