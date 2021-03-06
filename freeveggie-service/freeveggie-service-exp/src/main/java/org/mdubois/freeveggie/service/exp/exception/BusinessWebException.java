package org.mdubois.freeveggie.service.exp.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This exception is to be thrown when trying to access a business method and an
 * business logic is not respected.
 *
 * @author Mickael Dubois
 */
public class BusinessWebException extends WebApplicationException {

    /**
     * Default constructor.
     */
    public BusinessWebException() {
        super(Response.Status.BAD_REQUEST);
    }

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     */
    public BusinessWebException(final String pMessage) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(pMessage).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Constructor.
     *
     * @param pException The original exception
     */
    public BusinessWebException(final Throwable pException) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(pException.getMessage()).type(MediaType.TEXT_PLAIN).build());
    }

}
