package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.IntegerValue;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class IntegerValueConstraintValidator implements MessageConstraintValidator<IntegerValue> {

    private IntegerValue instance;

    /**
     * Controle if the object is equal to the integer value.
     * @param pValue - The value to test
     * @return true if the object is equal to the integer value
     */
    @Override
    public boolean accept(Object pValue) {
        if (pValue instanceof Integer) {
            Integer value = (Integer) pValue;
            if (value < instance.min()) {
                return false;
            } else {
                return value <= instance.max();
            }
        } else {
            return false;
        }
    }

    @Override
    public void setAnnotation(IntegerValue annotation) {
        this.instance = annotation;
    }
}
