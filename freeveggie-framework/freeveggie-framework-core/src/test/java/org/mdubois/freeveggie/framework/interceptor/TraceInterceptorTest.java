package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.Method;
import javax.interceptor.InvocationContext;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
//import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class TraceInterceptorTest {

    @Mocked
    InvocationContext mockContext;

//    @After
//    public void tearDown() throws Exception {
//        Mockit.restoreAllOriginalDefinitions();
//    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateDedebugDisabled() throws Exception {
        final TraceInterceptor instance = new TraceInterceptor();
        final Object expectedResult = new Integer(-1);
        new Expectations() {

            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.FALSE);
                mockContext.proceed();
                returns(expectedResult);
                times = 1;
            }
        };

        Object result = instance.log(mockContext);
        Assert.assertEquals(expectedResult, result);

    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
//    @Test
//    public void testValidateDedebugEnable() throws Exception {
//        final TraceInterceptor instance = new TraceInterceptor();
//        final Object expectedResult = new Integer(-1);
//        new Expectations() {
//
//            @Mocked
//            private Log LOGGER;
//            {
//                Deencapsulation.setField(instance, "isDebugEnable", Boolean.TRUE);
//                mockContext.proceed();
//                returns(expectedResult);
//                times = 1;
//
//                Method method = this.getClass().getMethods()[0];
//                mockContext.getMethod();
//                returns(method);
//                times = 1;
//
//                LOGGER.debug(any);
//            }
//        };
//
//        Object result = instance.log(mockContext);
//        Assert.assertEquals(expectedResult, result);
//
//    }
}
