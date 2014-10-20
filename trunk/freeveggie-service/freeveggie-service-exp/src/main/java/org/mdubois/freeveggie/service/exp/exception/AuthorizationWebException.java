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
public class AuthorizationWebException extends WebApplicationException {

    /**
     * Default constructor.
     */
    public AuthorizationWebException() {
        super(Response.Status.UNAUTHORIZED);
    }

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     */
    public AuthorizationWebException(final String pMessage) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(pMessage).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Constructor.
     *
     * @param pException The original exception
     */
    public AuthorizationWebException(final Throwable pException) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(pException.getMessage()).type(MediaType.TEXT_PLAIN).build());
    }

}
