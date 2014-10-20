package org.mdubois.freeveggie.service.exp.exception;

import javax.ws.rs.core.Response;
import junit.framework.Assert;
import org.junit.Test;

/**
 *
 * @author mickael
 */
public class TechnicalWebExceptionTest {

    @Test
    public void testException() {
        Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), new TechnicalWebException().getResponse().getStatus());
        Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), new TechnicalWebException("").getResponse().getStatus());
        Assert.assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), new TechnicalWebException(new RuntimeException()).getResponse().getStatus());
    }
}
