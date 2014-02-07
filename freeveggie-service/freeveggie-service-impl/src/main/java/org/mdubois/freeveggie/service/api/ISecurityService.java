package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.Required;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public interface ISecurityService {

     /**
     * Control that the password match the login.
     *
     * @param pLogin - The login to check
     * @param pPassword - The password to check
     * @return The {@link UserBO} corresponding to the given login
     */
    UserMsg controlPassword(@Required final AuthenticationMsg pAuthentificationMsg) throws BusinessException;

     /**
     * Control that the temporary password match the login.
     *
     * @param pLogin - The login to check
     * @param pPassword - The password to check
     * @return The {@link UserBO} corresponding to the given login
     */
    UserMsg controlTempPassword(@Required final AuthenticationMsg pAuthentificationMsg);

    /**
     * Change a user password.
     * @param pChangePasswordMsg - The change password information
     * @return TRUE if the change work, FALSE otherwise.
     */
    public Boolean changePassword(ChangePasswordMsg pChangePasswordMsg) throws BusinessException;

    /**
     * Check if the a temporary password is setted.
     *
     * @param pLogin - The code (representing a user) to check
     * @return TRUE if the user has generated a temporary password, FALSE
     * otherwise
     */
    boolean hasTempPassword(@Required final String pCode) throws BusinessException;

    /**
     * Control if the login is already present in the database.
     *
     * @param pLogin - The login to check
     * @return TRUE if the login is already used, FALSE otherwise
     */
    boolean isExistingLogin(@Required final String pLogin);

    /**
     * Control if the email is already present in the database.
     *
     * @param pEmail - The email to control
     * @return TRUE if the email is already used, FALSE otherwise
     */
    boolean isExistingEmail(@Required final String pEmail);

    /**
     * Save a temporary password. Send a email to the user to inform him of a new password.
     *
     * @param pEmail - The user email address you want to save a temp password for
     * @param pTempPassword - The temporary password to save
     */
    void generateTempPassword(@Required final String pEmail) throws BusinessException;
}
