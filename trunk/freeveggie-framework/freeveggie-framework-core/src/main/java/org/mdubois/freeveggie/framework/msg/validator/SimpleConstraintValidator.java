package org.mdubois.freeveggie.framework.msg.validator;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.annotation.Annotation;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public abstract class SimpleConstraintValidator<T extends Annotation> implements MessageConstraintValidator<T>{

    @Override
    public void setAnnotation(T annotation){
    }
}
