package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.annotation.Annotation;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 * @param <T> - Annotation that the constraint validate@param T - Annotation that the constraint validate
 */
public interface MessageConstraintValidator<T extends Annotation> {

    /**
     * Set the instance constraint annotation.
     * @param annotation - The instance annotation
     */
    void setAnnotation(T annotation);

    /**
     * Answer if the instance object respect the instance annotation constraint.
     * @param value The object to control
     * @return <b>true</b> if the object respect the constraint <b>false</b> otherwise
     */
    boolean accept(Object value);
}
