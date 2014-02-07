package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@Interceptor
public class TraceInterceptor {

    // <editor-fold defaultstate="collapsed" desc="Log constants">
    /**
     * Logger.
     */
    private static final Log LOGGER = LogFactory.getLog(TraceInterceptor.class);
    /**
     * Is the mode debug enable.
     */
    private static Boolean isDebugEnable = Boolean.FALSE;
    // </editor-fold>

    static{
        isDebugEnable = LOGGER.isDebugEnabled();
    }

    /**
     * Log every invocation.
     * @param ctx The context call
     * @return The value of the method initialy called
     * @throws Exception method exception
     */
    @AroundInvoke
    public final Object log(final InvocationContext ctx) throws Exception {
        if (isDebugEnable) {
            long start = System.currentTimeMillis();
            try {
                return ctx.proceed();
            } finally {
                long time = System.currentTimeMillis() - start;
                String method = ctx.getClass().getName() + "." + ctx.
                        getMethod().getName() + "()";
                LOGGER.debug("TracingInterceptor : Invocating " + method
                        + " method took " + time + "ms");
            }
        } else {
            return ctx.proceed();
        }
    }
}
