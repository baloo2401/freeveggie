package org.mdubois.freeveggie.framework.msg.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Mickael Dubois
 */
public class ZipCodeConstraintValidatorTest {

    /**
     * Test of accept method, of class RequiredConstraintValidator.
     */
    @Test
    public void testAccept() {
        ZipCodeConstraintValidator instance = new ZipCodeConstraintValidator();
        assertFalse(instance.accept(null));
        assertFalse(instance.accept(Integer.valueOf("-100")));
        assertFalse(instance.accept(Integer.valueOf("10")));
        assertFalse(instance.accept(Integer.valueOf("100")));
        assertTrue(instance.accept(Integer.valueOf("1000")));
        assertTrue(instance.accept(Integer.valueOf("1001")));
        assertFalse(instance.accept(Integer.valueOf("100000")));
        assertFalse(instance.accept(Integer.valueOf("100001")));
    }
}
