package org.mdubois.freeveggie.service.exp.exception;

import javax.ws.rs.core.Response;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mickael
 */
public class AuthorizationWebExceptionTest {

    @Test
    public void testException() {
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), new AuthorizationWebException().getResponse().getStatus());
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), new AuthorizationWebException("").getResponse().getStatus());
        Assert.assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), new AuthorizationWebException(new RuntimeException()).getResponse().getStatus());
    }
}
