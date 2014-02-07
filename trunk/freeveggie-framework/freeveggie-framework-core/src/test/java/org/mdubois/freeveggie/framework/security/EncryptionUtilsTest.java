package org.mdubois.freeveggie.framework.security;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author mdubois
 */
public class EncryptionUtilsTest {

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testEncryptDecrypt() throws Exception {
        String input = "qsrfsqdf";
        Assert.assertEquals(input,EncryptionUtils.decrypt(EncryptionUtils.encrypt(input)));
    }

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testGetMD5() {
        String input = "are";
        Assert.assertEquals(EncryptionUtils.getMD5(input),EncryptionUtils.getMD5(input));
    }

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testGetMD5Dif() {
        String input = "are";
        Assert.assertNotSame(EncryptionUtils.getMD5("are"),EncryptionUtils.getMD5("aare"));
    }

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testGetMD5Empty() {
        String input = "";
        String result = EncryptionUtils.getMD5(input);
        Assert.assertNull(result);
    }

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testGetMD5Blank() {
        String input = "   ";
        String result = EncryptionUtils.getMD5(input);
        Assert.assertNull(result);
    }

    /**
     * Test of getMD5 method, of class PasswordUtils.
     */
    @Test
    public void testGetMD5Null() {
        String input = null;
        String result = EncryptionUtils.getMD5(input);
        Assert.assertNull(result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordEmtpty() {
        String pPassword = "";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordBlank() {
        String pPassword = "   ";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordShort() {
        String pPassword = "aaer";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordShortBlank() {
        String pPassword = "aaer     ";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordShortBlank_Bis() {
        String pPassword = "aaer1234     ";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordOnlyLetter() {
        String pPassword = "aarezfdqsfqs";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordOnlyNumber() {
        String pPassword = "1222345";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePasswordWhiteSpace() {
        String pPassword = "qdsf 1234";
        boolean expResult = false;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of isSafePassword method, of class PasswordUtils.
     */
    @Test
    public void testIsSafePassword() {
        String pPassword = "qdsf1234";
        boolean expResult = true;
        boolean result = EncryptionUtils.isSafePassword(pPassword);
        Assert.assertEquals(expResult, result);
    }
}
