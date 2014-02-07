package org.mdubois.freeveggie.framework.msg.validator;

import java.lang.annotation.Annotation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mdubois.freeveggie.framework.msg.Length;

/**
 *
 * @author Mickael Dubois
 */
public class LengthConstraintValidatorTest {

    /**
     * Test of accept method, of class LengthConstraintValidator.
     */
    @Test
    public void testAccept() {
        LengthConstraintValidator instance = new LengthConstraintValidator();
        Length length = new Length() {

            @Override
            public int min() {
                return 5;
            }

            @Override
            public int max() {
                return 10;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return Length.class;
            }
        };
        instance.setAnnotation(length);
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept("12"));
        assertFalse(instance.accept("test"));
        assertTrue(instance.accept("12345"));
        assertTrue(instance.accept("0123456789"));
        assertFalse(instance.accept("01234567890123456789"));
    }
}
