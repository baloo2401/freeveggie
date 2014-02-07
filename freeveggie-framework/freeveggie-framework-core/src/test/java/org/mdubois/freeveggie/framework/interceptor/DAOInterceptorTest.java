package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.FileNotFoundException;
import javax.interceptor.InvocationContext;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class DAOInterceptorTest {

    @Mocked
    InvocationContext mockContext;

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateDedebugDisabled() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        final Object expectedResult = new Integer(-1);
        new Expectations() {

            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.FALSE);
                mockContext.proceed();
                returns(expectedResult);
                repeats(1);
            }
        };

        Object result = instance.manageException(mockContext);
        Assert.assertEquals(expectedResult, result);

    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateDedebugEnable() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        final Object expectedResult = new Integer(-1);
        new Expectations() {

            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.TRUE);
                mockContext.proceed();
                returns(expectedResult);
                repeats(1);
            }
        };

        Object result = instance.manageException(mockContext);
        Assert.assertEquals(expectedResult, result);

    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test(expected = TechnicalException.class)
    public void testValidateDedebugDisabledThrows() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        new Expectations() {

            @Mocked
            private Log LOGGER;

            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.FALSE);
                mockContext.proceed();
                throwsException(new RuntimeException());
            }
        };

        instance.manageException(mockContext);

    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test(expected = TechnicalException.class)
    public void testValidateDedebugEnableThrows() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        new Expectations() {

            @Mocked
            private Log LOGGER;
            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.TRUE);
                mockContext.proceed();
                throwsException(new RuntimeException());

                LOGGER.error(any, (TechnicalException)any);
            }
        };

        instance.manageException(mockContext);

    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
//    @Test
//    public void testValidateDedebugEnableThrows_controle_value() throws Exception {
//        final DAOInterceptor instance = new DAOInterceptor();
//        new Expectations() {
//
//            @Mocked
//            private Log LOGGER;
//            {
//                Deencapsulation.setField(instance, "isDebugEnable", Boolean.TRUE);
//                mockContext.proceed();
//                throwsException(new RuntimeException("argh!!! exception"));
//
//                LOGGER.error(any, (TechnicalException)any);
//            }
//        };
//
//        try {
//            instance.manageException(mockContext);
//        } catch(TechnicalException te){
//            Assert.assertEquals("argh!!! exception", te.getCause().getMessage());
//        }
//
//    }

    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testValidateDedebugEnableThrowsNonRuntime() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        new Expectations() {

            {
                Deencapsulation.setField(instance, "isDebugEnable", Boolean.TRUE);
                mockContext.proceed();
                throwsException(new FileNotFoundException("argh!!! exception"));

            }
        };


        boolean thrown = false;

        try {
            instance.manageException(mockContext);
        } catch(FileNotFoundException te){
            thrown = true;
        }

        if(!thrown){
            Assert.fail("An exception should have occur.");
        }
    }
}
