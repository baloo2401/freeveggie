package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when input messsage used in ejb method does
 * not respect the validation framework.
 * @author Mickael Dubois
 */
public class MessageDefinitionException extends DevelopmentException {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public MessageDefinitionException(final String pMessage) {
        super(pMessage);
    }
}
