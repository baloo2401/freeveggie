package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.msg.Length;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class LengthConstraintValidator implements MessageConstraintValidator<Length> {

    private Length annotationInstance;

    /**
     * Control that the object is a String and that it length is upper or equal
     * the minimum size and under or equal to the maximum size;
     *
     * @param pValue - the String to check, may be null
     * @return true if the object String that respect the length
     */
    @Override
    public boolean accept(Object pValue) {
        if (pValue == null && annotationInstance.min() == 0) {
            return true;
        }
        if (pValue instanceof String) {
            String value = (String) pValue;
            return (value.length() >= annotationInstance.min()
                    && value.length() <= annotationInstance.max()) ;

        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAnnotation(Length annotation) {
        this.annotationInstance = annotation;
    }
}
