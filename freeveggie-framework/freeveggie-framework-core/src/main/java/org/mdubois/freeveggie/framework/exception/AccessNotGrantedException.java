package org.mdubois.freeveggie.framework.exception;

/**
 * This exception is thrown when a client try to access a method for which he
 * does not have credentials for. For example some method are allow only to the
 * system. If a user try to access it this exception will be thrown.
 * @author Mickael Dubois
 */
public class AccessNotGrantedException extends IllegalAccessException {

    /**
     * Constructor.
     */
    public AccessNotGrantedException() {
        super("Client not authorized for this invocation.");
    }
}
