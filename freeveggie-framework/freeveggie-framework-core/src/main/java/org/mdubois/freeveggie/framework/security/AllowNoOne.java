package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// </editor-fold>

/**
 * Define that no user are allow to call this method.
 * @author Mickael Dubois
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AllowNoOne {
}
