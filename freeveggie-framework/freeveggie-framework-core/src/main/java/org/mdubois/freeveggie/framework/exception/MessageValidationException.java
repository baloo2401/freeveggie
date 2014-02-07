package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when calling a method with a non valid input
 * message.
 * @author Mickael Dubois
 */
public class MessageValidationException extends InvalidParamException {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public MessageValidationException(final String pMessage) {
        super(pMessage);
    }

}
