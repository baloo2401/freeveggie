package org.mdubois.freeveggie.framework.msg.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class TrueConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        TrueConstraintValidator instance = new TrueConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept(" "));
        assertFalse(instance.accept(1));
        assertFalse(instance.accept(0L));
        assertFalse(instance.accept(new Object()));
        assertFalse(instance.accept(false));
        assertFalse(instance.accept(Boolean.FALSE));
        assertTrue(instance.accept(true));
        assertTrue(instance.accept(Boolean.TRUE));
    }
}
