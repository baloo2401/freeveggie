package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
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
     * The AES cryptographic has function
     */
    private static final String AES_CRYPTOGRAPHIC_HASH_FUNCTION = "AES";
    /**
     * The Blowfish cryptographic has function
     */
    private static final String BLOWFISH_CRYPTOGRAPHIC_HASH_FUNCTION = "Blowfish";
    /**
     * The key need for EAS
     */
    private static final byte[] KEY_VALUE =
            new byte[]{'2', '0', '1', '2', 'f', 'r', 'e', 'e', 'v', 'e', 'g', 'g', 'i', 'e', '^', '&'};
    /**
     * The numeric pattern.
     */
    private static final String NUMERIC_PATTERN = ".*\\d.*";

    private EncryptionUtils() {
    }

    public static String encrypt(String plaintextString) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
            SecretKey secretkey = keygenerator.generateKey();

            System.out.println(plaintextString + " " + bytesToHex(plaintextString.getBytes()) + " " + Arrays.toString(plaintextString.getBytes()));

            SecretKeySpec key = new SecretKeySpec(secretkey.getEncoded(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(plaintextString.getBytes());
            return bytesToHex(encrypted);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

    public static String decrypt(String encryptedString) {
        try {
            KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
            SecretKey secretkey = keygenerator.generateKey();

            String plaintextString = "StackOverflow";
            System.out.println(plaintextString + " " + bytesToHex(plaintextString.getBytes()) + " " + Arrays.toString(plaintextString.getBytes()));

            SecretKeySpec key = new SecretKeySpec(secretkey.getEncoded(), "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(hexToBytes(encryptedString));
            return bytesToHex(decrypted);
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
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

    public static byte[] hexToBytes(String str) {
        if (str == null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i = 0; i < len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            }
            return buffer;
        }

    }

    public static String bytesToHex(byte[] data) {
        if (data == null) {
            return null;
        } else {
            int len = data.length;
            String str = "";
            for (int i = 0; i < len; i++) {
                if ((data[i] & 0xFF) < 16) {
                    str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
                } else {
                    str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
                }
            }
            return str.toUpperCase();
        }
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

    private static Key generateKey() {
        Key key = new SecretKeySpec(KEY_VALUE, AES_CRYPTOGRAPHIC_HASH_FUNCTION);
        return key;
    }
}
