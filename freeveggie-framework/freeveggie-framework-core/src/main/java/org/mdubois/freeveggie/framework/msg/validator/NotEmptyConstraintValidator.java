package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.framework.msg.NotEmpty;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class NotEmptyConstraintValidator extends SimpleConstraintValidator<NotEmpty> {

    /**
     * Using the apache {@link StringUtils} checks if a String is whitespace, empty ("") or null.<br />
     * <br />
     * StringUtils.isBlank(null)      = false<br />
     * StringUtils.isBlank("")        = false<br />
     * StringUtils.isBlank(" ")       = true<br />
     * StringUtils.isBlank("bob")     = true<br />
     * StringUtils.isBlank("  bob  ") = true<br />
     *
     * @param pValue - the String to check, may be null
     * @return true if the String is not empty and not null and not whitespace
     */
    @Override
    public boolean accept(Object pValue) {
        if (pValue instanceof String) {
            String value = (String) pValue;
            return StringUtils.isNotEmpty(value);
        } else {
            return false;
        }
    }
}
