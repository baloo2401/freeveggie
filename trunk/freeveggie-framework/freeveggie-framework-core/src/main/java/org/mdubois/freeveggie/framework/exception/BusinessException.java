package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when trying to access a business method and
 * an business logic is not respected.
 * @author Mickael Dubois
 */
public class BusinessException extends Exception {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public BusinessException(final String pMessage) {
        super(pMessage);
    }

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public BusinessException(final String pMessage, final Throwable cause) {
        super(pMessage, cause);
    }

}
