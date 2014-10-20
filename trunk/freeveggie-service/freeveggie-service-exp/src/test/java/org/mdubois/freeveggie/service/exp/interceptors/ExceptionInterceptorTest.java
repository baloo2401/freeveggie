/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.exp.interceptors;

import javax.interceptor.InvocationContext;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.service.exp.exception.AuthorizationWebException;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.exception.TechnicalWebException;

/**
 *
 * @author mickael
 */
public class ExceptionInterceptorTest {

    private final ExceptionInterceptor interceptor = new ExceptionInterceptor();

    @Mocked
    private InvocationContext ctx;

    @Test
    public void test() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = BusinessWebException.class)
    public void testBusinessException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new BusinessException(anyString);
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = TechnicalWebException.class)
    public void testTechnicalException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new TechnicalException(anyString);
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = BusinessWebException.class)
    public void testIllegalArgumentException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new IllegalArgumentException(anyString);
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = TechnicalWebException.class)
    public void testRuntimeException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new RuntimeException(anyString);
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = AuthorizationWebException.class)
    public void testAccessNotGrantedException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new AccessNotGrantedException();
            }
        };
        interceptor.translateException(ctx);
    }

    @Test(expected = Error.class)

    public void testErrorException() throws Exception {
        new Expectations() {
            {
                ctx.proceed();
                result = new Error(anyString);
            }
        };
        interceptor.translateException(ctx);
    }
}
