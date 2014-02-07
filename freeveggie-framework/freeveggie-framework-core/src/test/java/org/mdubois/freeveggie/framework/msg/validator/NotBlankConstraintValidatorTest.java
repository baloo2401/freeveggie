package org.mdubois.freeveggie.framework.msg.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class NotBlankConstraintValidatorTest {

    /**
     * Test of accept method, of class NotBlankConstraintValidator.
     */
    @Test
    public void testAccept() {
        NotBlankConstraintValidator instance = new NotBlankConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept(" "));
        assertTrue(instance.accept("test"));
        assertTrue(instance.accept("  test  "));
        assertTrue(instance.accept("1"));
    }
}
