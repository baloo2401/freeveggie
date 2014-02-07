package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.StringValue;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class StringValueConstraintValidator implements MessageConstraintValidator<StringValue> {

    private StringValue instance;

    /**
     * Control if the object is equal to the string value.
     * @param pValue - The value to test
     * @return true if the object is equal to the string value
     */
    @Override
    public boolean accept(Object pValue) {
        return instance.value().equals(pValue);
    }

    @Override
    public void setAnnotation(StringValue annotation) {
        this.instance = annotation;
    }
}
