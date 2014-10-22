package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is expected to be thrown when when a technical issue is meet.
 *
 * @author Mickael Business
 */
public class TechnicalException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     */
    public TechnicalException(final String pMessage) {
        super(pMessage);
    }

    /**
     * Constructor.
     *
     * @param pMessage The exception message to set
     * @param pException The orginal exception
     */
    public TechnicalException(final String pMessage, final Throwable pException) {
        super(pMessage, pException);
    }

}
