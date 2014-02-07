package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.ZipCode;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ZipCodeConstraintValidator extends SimpleConstraintValidator<ZipCode> {

    private static final Integer MIN_VALUE = 1000;

    private static final Integer MAX_VALUE = 100000;

    /**
     * Control if the object is a Zip Code.
     * @param pValue - The value to test
     * @return true if the object is a Zip Code
     */
    @Override
    public boolean accept(Object pValue) {
        if(pValue instanceof Integer){
            Integer value = (Integer) pValue;
            if(value >= MIN_VALUE && value < MAX_VALUE){
                return true;
            }
        }
        return false;
    }
}
