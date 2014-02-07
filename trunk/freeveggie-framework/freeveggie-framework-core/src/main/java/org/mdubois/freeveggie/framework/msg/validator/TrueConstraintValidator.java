package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.True;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class TrueConstraintValidator extends SimpleConstraintValidator<True> {

    /**
     * Controle if the object is equal to TRUE.
     * @param pValue - The value to test
     * @return true if the object is equal to TRUE
     */
    @Override
    public boolean accept(Object pValue) {
        return Boolean.TRUE.equals(pValue);
    }
}
