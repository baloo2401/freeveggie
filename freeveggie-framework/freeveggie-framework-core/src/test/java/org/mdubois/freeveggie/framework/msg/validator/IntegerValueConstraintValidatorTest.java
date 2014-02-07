package org.mdubois.freeveggie.framework.msg.validator;

import java.lang.annotation.Annotation;
import org.junit.Test;
import org.mdubois.freeveggie.framework.msg.IntegerValue;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class IntegerValueConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        IntegerValueConstraintValidator instance = new IntegerValueConstraintValidator();
        IntegerValue annotation = new IntegerValue() {

            @Override
            public int min() {
                return 5;
            }

            @Override
            public int max(){
                return 10;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return IntegerValue.class;
            }
        };
        instance.setAnnotation(annotation);
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
        assertTrue(instance.accept(5));
        assertTrue(instance.accept(10));
        assertFalse(instance.accept(100));
    }
}
