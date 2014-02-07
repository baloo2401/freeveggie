package org.mdubois.freeveggie.framework.msg.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class FalseConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        FalseConstraintValidator instance = new FalseConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept(" "));
        assertFalse(instance.accept(1));
        assertFalse(instance.accept(0L));
        assertFalse(instance.accept(new Object()));
        assertFalse(instance.accept(true));
        assertFalse(instance.accept(Boolean.TRUE));
        assertTrue(instance.accept(false));
        assertTrue(instance.accept(Boolean.FALSE));
    }
}
