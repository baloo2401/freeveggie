package org.mdubois.freeveggie.framework.msg.validator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mickael Dubois
 */
public class EmailConstraintValidatorTest {
   

    /**
     * Test of accept method, of class EmailConstraintValidator.
     */
    @Test
    public void testAccept() {
        EmailConstraintValidator instance = new EmailConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(""));
        assertFalse(instance.accept("test"));
        assertFalse(instance.accept("test@"));
        assertFalse(instance.accept("@qdf"));
        assertFalse(instance.accept("t@td.f"));
        assertTrue(instance.accept("td@td.fr"));
        assertTrue(instance.accept("td@td.com"));
        assertTrue(instance.accept("td@td.net"));
        assertTrue(instance.accept("td@td.info"));
        assertTrue(instance.accept("td@td.com.au"));
        assertTrue(instance.accept("t.t@t.com.au"));
        assertTrue(instance.accept("t.t.t@t.com.au"));
        assertTrue(instance.accept("t-d@t.com.au"));
        assertTrue(instance.accept("t@tdd-test.com"));
        assertTrue(instance.accept("qsdf-t@tdd-test.com"));
    }
}
