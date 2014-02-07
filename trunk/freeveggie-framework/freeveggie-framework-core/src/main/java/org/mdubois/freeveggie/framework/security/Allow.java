package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.annotation.*;
// </editor-fold>

/**
 * Define witch user role can call a method.
 * @author Mickael Dubois
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Allow {

    /**
     * List the role that is allow to user this method.
     */
    UserRole[] value();

    /**
     * The name of the method in the class to call to control authorization.
     */
    String rule() default "";
}
