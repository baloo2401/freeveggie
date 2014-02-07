package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when devellopement rule is not respected, so
 * that the framework can not work properly.
 * @author Mickael Dubois
 */
public class DevelopmentException extends TechnicalException {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public DevelopmentException(final String pMessage) {
        super(pMessage);
    }
}
