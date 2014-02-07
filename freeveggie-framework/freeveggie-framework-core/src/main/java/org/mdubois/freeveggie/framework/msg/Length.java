package org.mdubois.freeveggie.framework.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Length {

    /**
     * The minimum length for a String.
     * @return The minimum length
     */
    int min() default 0;

    /**
     * The maximum length for a String.
     * @return The maximum length
     */
    int max() default 255;

}
