package org.mdubois.freeveggie.framework.exception;

/**
 * The exception is thrown when a client try to access a method that have been
 * configure to be granted to no one.
 * @author Mickael Dubois
 */
public class AllowNoOneMethodAccessException extends IllegalAccessException {

    /**
     * Constructor.
     */
    public AllowNoOneMethodAccessException() {
        super("The method you are trying to access has a deny all access");
    }
}
