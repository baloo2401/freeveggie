package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.False;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class FalseConstraintValidator extends SimpleConstraintValidator<False> {

    /**
     * Controle if the object is equals to FALSE.
     * @param pValue - The value to test
     * @return true if the object is equal to FALSE
     */
    @Override
    public boolean accept(Object pValue) {
        return Boolean.FALSE.equals(pValue);
    }
}
