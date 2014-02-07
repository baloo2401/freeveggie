package org.mdubois.freeveggie.framework.interceptor;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.AllowNoOneMethodAccessException;
import org.mdubois.freeveggie.framework.exception.AuthorizationDefinitionException;
import org.mdubois.freeveggie.framework.exception.IllegalAccessException;
import org.mdubois.freeveggie.framework.security.*;
import org.mdubois.freeveggie.framework.utils.UserUtils;
import org.springframework.core.annotation.AnnotationUtils;
// </editor-fold>

/**
 *  The interceptor control that clients are allow to invoke a method using
 * annotations : Allow, AllowNoOne, AllowAll.
 * @author Mickael Dubois
 */
public class AuthorizationInterceptor {

    // <editor-fold defaultstate="collapsed" desc="Exception message constants">
    /**
     * Message for business method not properly set.
     */
    private static final String BUSINESS_METHOD_NOT_PROPERLY_SET = "Business "
            + "method is not properly configured";
    /**
     * Message for client not set.
     */
    private static final String CLIENT_NOT_SET = "Client can not invoke this "
            + "method without a valid user message";
    // </editor-fold>

    /**
     * If the client calling is allow to call this method the method will be
     * called. Otherwise an AccessControlException will be thrown.
     * @param ctx The context call
     * @return The value of the method initialy called
     * @throws Exception when an illegal call is made
     */
    @AroundInvoke
    public final Object validate(final InvocationContext ctx) throws Exception {
        Object proccessResult = null;
        if (AnnotationUtils.findAnnotation(ctx.getMethod(), AllowNotLoggedIn.class)
                != null) {
            proccessResult = ctx.proceed();
        } else if (!isUserSet(ctx)) {
            throw new IllegalAccessException(CLIENT_NOT_SET);
        } else {
            if (AnnotationUtils.findAnnotation(ctx.getMethod(), AllowAll.class)
                    != null) {
                proccessResult = ctx.proceed();
            } else if (AnnotationUtils.findAnnotation(ctx.getMethod(),
                    Allow.class) != null) {
                Allow allow = AnnotationUtils.findAnnotation(ctx.getMethod(),
                        Allow.class);
                ContextMsg userUsed = (ContextMsg) ctx.getParameters()[0];
                UserRole[] rolesAllowed = allow.value();
                boolean isUserInGoodRole = false;
                for (UserRole roleAllow : rolesAllowed) {
                    if (UserUtils.isUserInRole(userUsed.getUser().getRole(),
                            roleAllow)) {
                        isUserInGoodRole = true;
                        break;
                    }
                }
                if (!isUserInGoodRole) {
                    throw new AccessNotGrantedException();
                } else {
                    proccessResult = ctx.proceed();
                }
            } else if (AnnotationUtils.findAnnotation(ctx.getMethod(),
                    AllowNoOne.class) != null) {
                throw new AllowNoOneMethodAccessException();
            } else {
                //Every method have to have a access control anotation
                throw new AuthorizationDefinitionException(
                        BUSINESS_METHOD_NOT_PROPERLY_SET);
            }
        }
        return proccessResult;
    }

    /**
     * Utility method that control that the user input message have been set.
     * @param ctx The context call
     * @return <code>true</code> if the user is set, <code>false</code>
     * otherwise
     */
    private boolean isUserSet(final InvocationContext ctx) {
        boolean isUserSet = false;
        Object obj = ctx.getParameters()[0];
        if (obj instanceof ContextMsg) {
            isUserSet = true;
        }
        return isUserSet;
    }
}
