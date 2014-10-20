package org.mdubois.freeveggie.service.exp.exception;

import javax.ws.rs.core.Response;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mickael
 */
public class BusinessWebExceptionTest {

    @Test
    public void testException() {
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), new BusinessWebException().getResponse().getStatus());
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), new BusinessWebException("").getResponse().getStatus());
        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), new BusinessWebException(new RuntimeException()).getResponse().getStatus());
    }
}
