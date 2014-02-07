package org.mdubois.freeveggie.service.exp.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * This exception is expected to be thrown when when a technical issue is meet.
 * @author Mickael Business
 */
public class TechnicalWebException extends WebApplicationException {

     /**
     * Default constructor.
     */
    public TechnicalWebException(){
        super(Response.Status.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public TechnicalWebException(final String pMessage) {
       super(new Throwable(pMessage), Response.Status.INTERNAL_SERVER_ERROR);
    }
    /**
     * Constructor.
     * @param pMessage The exception message to set
     * @param pCause The original exception
     */
    public TechnicalWebException(final String pMessage, final Throwable pCause) {
        super(new Throwable(pMessage, pCause), Response.Status.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Constructor.
     * @param pException The original exception
     */
    public TechnicalWebException(final Throwable pException){
        super(pException, Response.Status.INTERNAL_SERVER_ERROR);
    }
    
}
