package org.mdubois.freeveggie.service.exp.interceptors;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.exception.TechnicalWebException;
// </editor-fold>

/**
 * The interceptor control that input messages are valid.
 *
 * @author Mickael Dubois
 */
@WebServiceExceptionHandler
@Interceptor
public class ExceptionInterceptor {

    /**
     *
     * @param ctx The context call
     * @return The value of the method initially called
     * @throws Exception when the invocation parameters are not valid
     */
    @AroundInvoke
    public final Object validate(final InvocationContext ctx) throws Exception {
        try {
            return ctx.proceed();
        } catch (AccessNotGrantedException ange){
            throw new BusinessWebException(ange, Response.Status.UNAUTHORIZED);
        }catch(BusinessException be) {
            throw new BusinessWebException(be, Response.Status.BAD_REQUEST);
        } catch(TechnicalException te) {
            throw new TechnicalWebException(te);
        }
        
    }
}
