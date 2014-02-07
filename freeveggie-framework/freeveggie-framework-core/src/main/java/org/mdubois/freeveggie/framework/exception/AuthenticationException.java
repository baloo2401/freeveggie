package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is thrown when a client try to access a method but the user is not valid.
 * @author Mickael Dubois
 */
public class AuthenticationException extends IllegalAccessException {

    /**
     * Constructor.
     */
    public AuthenticationException() {
        super("No user found");
    }
}
