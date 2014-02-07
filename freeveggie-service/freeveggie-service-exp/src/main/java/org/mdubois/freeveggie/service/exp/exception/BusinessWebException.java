package org.mdubois.freeveggie.service.exp.exception;

import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
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
     * @param pMessage The exception message to set
     */
    public BusinessWebException(final String pMessage, final Throwable cause) {
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
    
     /**
     * Default constructor.
     */
    public BusinessWebException(final Response.Status pStatus) {
        super(pStatus);
    }

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     */
    public BusinessWebException(final String pMessage, final Response.Status pStatus) {
        super(Response.status(pStatus)
             .entity(pMessage).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     */
    public BusinessWebException(final String pMessage, final Throwable cause, final Response.Status pStatus) {
        super(Response.status(pStatus)
             .entity(pMessage).type(MediaType.TEXT_PLAIN).build());
    }

    /**
     * Constructor.
     *
     * @param pException The original exception
     */
    public BusinessWebException(final Throwable pException, final Response.Status pStatus) {
        super(Response.status(pStatus)
             .entity(pException.getMessage()).type(MediaType.TEXT_PLAIN).build());
    }
}
