package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.Method;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.msg.Required;
import org.springframework.util.StringUtils;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RequiredConstraintValidator implements MessageConstraintValidator<Required> {

    private Required annotationInstance;

    /**
     * Control if the object is not null.
     *
     * @param pValue - The value to test
     * @return true if the object is not null
     */
    @Override
    public boolean accept(Object pValue) {
        if (pValue != null) {
            if (annotationInstance.field().equals("")) {
                return pValue != null;
            } else {
                Class objectClass = pValue.getClass();
                try {
                    Method getter = objectClass.getMethod("get" + StringUtils.capitalize(annotationInstance.field()));
                    return getter.invoke(pValue) != null;
                } catch (Exception ex) {
                    throw new TechnicalException("Cannot get value for field " + annotationInstance.field(), ex);
                }

            }
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setAnnotation(Required annotation) {
        this.annotationInstance = annotation;
    }
}
