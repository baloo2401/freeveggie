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
@Target(ElementType.FIELD)
public @interface IntegerValue {

    /**
     * The maximum that the value can be.
     * This value include the limit.
     * @return The maximum value
     */
    int max() default Integer.MAX_VALUE;

    /**
     * The maximum taht the value can be.
     * The value include the limit.
     * @return The minimum value
     */
    int min() default Integer.MIN_VALUE;
}
