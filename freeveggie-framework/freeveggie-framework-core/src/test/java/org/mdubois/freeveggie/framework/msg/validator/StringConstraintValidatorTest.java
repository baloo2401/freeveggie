package org.mdubois.freeveggie.framework.msg.validator;

import java.lang.annotation.Annotation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mdubois.freeveggie.framework.msg.StringValue;

/**
 *
 * @author Mickael Dubois
 */
public class StringConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        StringValueConstraintValidator instance = new StringValueConstraintValidator();
        StringValue stringValue = new StringValue() {

            @Override
            public String value() {
                return "value to test";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return StringValue.class;
            }
        };
        instance.setAnnotation(stringValue);
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept(" "));
        assertFalse(instance.accept(1));
        assertFalse(instance.accept(0L));
        assertFalse(instance.accept(new Object()));
        assertFalse(instance.accept(true));
        assertFalse(instance.accept(Boolean.TRUE));
        assertFalse(instance.accept(false));
        assertFalse(instance.accept(Boolean.FALSE));
        assertFalse(instance.accept(123));
        assertFalse(instance.accept("123"));
        assertFalse(instance.accept(100000));
        assertTrue(instance.accept("value to test"));
    }
}
