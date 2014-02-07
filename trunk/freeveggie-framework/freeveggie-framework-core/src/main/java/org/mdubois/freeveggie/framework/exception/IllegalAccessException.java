package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when a client try to access a method but does
 *  not have the right.
 * @author Mickael Dubois
 */
public class IllegalAccessException extends BusinessException {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public IllegalAccessException(final String pMessage) {
        super(pMessage);
    }
}
