package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@Interceptor
public class DAOInterceptor {

    /**
     * Refractor all runtime exception to a new technical exception (encapsulate)
     * @param ctx - The context call.
     * @return The value of the method initialy called
     * @throws Exception method exception
     */
    @AroundInvoke
    public final Object manageException(final InvocationContext ctx) throws Exception {
            try {
                return ctx.proceed();
            } catch(RuntimeException e) {
                throw new TechnicalException("An DAO exception occur", e);
            }
    }
}
