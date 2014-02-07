package org.mdubois.freeveggie.framework.security;

/**
 * Class to validate if a password is safe
 * @author Mickael
 */
public class PasswordValidator {
    private PasswordValidator(){}
    
    public static boolean isValid(final String password){
        if(password.trim().length() < 8){
            return false;
        } else if(password.contains(" ")){
            return false;
        } else {
            return true;
        }
    }
}
