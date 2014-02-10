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
    @Test(expected = TechnicalException.class)
    public void testWrapRuntimeException() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        new Expectations() {

            @Mocked
            private Log LOGGER;

            {
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
    @Test(expected = CheckedException.class)
    public void testNotWrapCheckedException() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        new Expectations() {

            @Mocked
            private Log LOGGER;

            {
                mockContext.proceed();
                throwsException(new CheckedException());
            }
        };

        instance.manageException(mockContext);

    }



    /**
     * Test of validate method, of class MessageValidatorInterceptor.
     *
     * @throws Exception
     */
    @Test
    public void testNoException() throws Exception {
        final DAOInterceptor instance = new DAOInterceptor();
        final Object expectedResult = new Integer(-1);
        new Expectations() {

            {
                mockContext.proceed();
                returns(expectedResult);
                repeats(1);
            }
        };

        Object result = instance.manageException(mockContext);
        Assert.assertEquals(expectedResult, result);

    }
}
class CheckedException extends Exception {
    
}