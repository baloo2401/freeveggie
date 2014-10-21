package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public final class EncryptionUtils {

    /**
     * The MD5 cryptographic has function
     */
    private static final String MD5_CRYPTOGRAPHIC_HASH_FUNCTION = "MD5";
    /**
     * The numeric pattern.
     */
    private static final String NUMERIC_PATTERN = ".*\\d.*";

    private EncryptionUtils() {
    }

    /**
     * Generate the MD5 hash
     *
     * @param input - The password needing to be hashed
     * @return The hash
     */
    public static String getMD5(final String input) {
        String md5 = null;
        if (StringUtils.isBlank(input)) {
            return null;
        }
        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance(MD5_CRYPTOGRAPHIC_HASH_FUNCTION);

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            throw new TechnicalException(e);
        }
        return md5;
    }

    /**
     * Control that the password is safe. To be safe it need to satisfied the
     * following rules : <ul> <li>Has at least 6 characters</li> <li>Has
     * number</li> <li>Has letter</li> </ul>
     *
     * @param pPassword
     * @return
     */
    public static boolean isSafePassword(final String pPassword) {
        if (pPassword.trim().length() < 6) {
            return false;
        } else if (!hasNumber(pPassword)) {
            return false;
        } else if (!hasLetter(pPassword)) {
            return false;
        } else if (hasSpace(pPassword)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Control that at least on of the character is a number
     *
     * @param pPassword - The password to control
     * @return TRUE if the password have a number, FALSE otherwise
     */
    private static boolean hasSpace(final String pPassword) {
        for (int i = 0; i < pPassword.length(); i++) {
            if (pPassword.substring(i, i + 1).equals(" ")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Control that at least on of the character is a number
     *
     * @param pPassword - The password to control
     * @return TRUE if the password have a number, FALSE otherwise
     */
    private static boolean hasNumber(final String pPassword) {
        Pattern p = Pattern.compile(NUMERIC_PATTERN);
        Matcher m = p.matcher(pPassword);
        return m.matches();
    }

    /**
     * Control that at least on of the character is a letter
     *
     * @param pPassword - The password to control
     * @return TRUE if the password have a letter, FALSE otherwise
     */
    private static boolean hasLetter(final String pPassword) {
        Pattern p = Pattern.compile(NUMERIC_PATTERN);
        Matcher m;
        for (int i = 0; i < pPassword.length(); i++) {
            m = p.matcher(pPassword.substring(i, i + 1));
            if (!m.matches()) {
                return true;
            }
        }
        return false;
    }
}
