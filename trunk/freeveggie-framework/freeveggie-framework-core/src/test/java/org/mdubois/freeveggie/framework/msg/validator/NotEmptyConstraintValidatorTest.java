package org.mdubois.freeveggie.framework.msg.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class NotEmptyConstraintValidatorTest {

    /**
     * Test of accept method, of class NotEmptyConstraintValidator.
     */
    @Test
    public void testAccept() {
        NotEmptyConstraintValidator instance = new NotEmptyConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertTrue(instance.accept(" "));
        assertTrue(instance.accept("test"));
        assertTrue(instance.accept("  test  "));
        assertTrue(instance.accept("1"));
    }
}
