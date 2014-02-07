package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is to be thrown when a business method is not properly set.
 * @author Mickael Dubois
 */
public class AuthorizationDefinitionException extends DevelopmentException {

    /**
     * Constructor.
     * @param pMessage The exception message to set
     */
    public AuthorizationDefinitionException(final String pMessage) {
        super(pMessage);
    }
}
