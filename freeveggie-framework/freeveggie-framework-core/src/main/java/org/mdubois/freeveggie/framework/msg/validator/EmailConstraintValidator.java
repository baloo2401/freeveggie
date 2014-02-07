package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mdubois.freeveggie.framework.msg.Email;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class EmailConstraintValidator extends SimpleConstraintValidator<Email> {
    
    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private Pattern pattern;

    public EmailConstraintValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Use the apache {@link EmailValidator} it control that the object is a
     * valid email address.
     *
     * @param pValue - the String to check, may be null
     * @return true if the object represent a valid string email
     */
    @Override
    public boolean accept(Object pValue) {
        if (pValue instanceof String) {
            String email = (String) pValue;
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        } else {
            return false;
        }
    }
}
