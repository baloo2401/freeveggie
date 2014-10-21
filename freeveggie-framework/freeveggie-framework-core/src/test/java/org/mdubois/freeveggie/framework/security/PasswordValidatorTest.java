package org.mdubois.freeveggie.framework.security;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class to validate if a password is safe
 *
 * @author Mickael
 */
public class PasswordValidatorTest {

    @Test
    public void testIsValid(final String password) {
        Assert.assertFalse(PasswordValidator.isValid(""));
        Assert.assertFalse(PasswordValidator.isValid("a"));
        Assert.assertFalse(PasswordValidator.isValid(" "));
        Assert.assertFalse(PasswordValidator.isValid("aaa"));
        Assert.assertFalse(PasswordValidator.isValid("aaaaaaa"));
        Assert.assertFalse(PasswordValidator.isValid(null));

        Assert.assertTrue(PasswordValidator.isValid("dsfsqfqsbiodufgbqdsvf@"));
    }
}
