package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public interface ISecurityBean {

    /**
     * Control that the password match the login.
     * @param pAuthentificationMsg - The authentication information
     * @return The {@link UserBO} corresponding to the given login
     */
    UserMsg controlPassword(final AuthenticationMsg pAuthentificationMsg) throws BusinessException;

    /**
     * Control that the temporary password match the login.
     * @param pAuthentificationMsg - The authentication information
     * @return The {@link UserBO} corresponding to the given login
     */
    UserMsg controlTempPassword(final AuthenticationMsg pAuthentificationMsg);
    
    /**
     * Change a user password.
     * @param pChangePasswordMsg - The change password information
     * @param pContextMsg - The context call
     * @return The {@link UserBO} corresponding to the given login
     */
    Boolean changePassword(final ContextMsg pContextMsg,  final ChangePasswordMsg pChangePasswordMsg) throws BusinessException;

    /**
     * Check if the a temporary password is setted.
     * @param pCode - The code (final ContextMsg pContextMsg,representing a
     * user) to check
     * @return TRUE if the user has generated a temporary password, FALSE
     * otherwise
     */
    boolean hasTempPassword(final String pCode) throws BusinessException;

    /**
     * Control if the login is already present in the database.
     * @param pLogin - The login to check
     * @return TRUE if the login is already used, FALSE otherwise
     */
    boolean isExistingLogin(final String pLogin);

    /**
     * Control if the email is already present in the database.
     * @param pEmail - The email to control
     * @return TRUE if the email is already used, FALSE otherwise
     */
    boolean isExistingEmail(final String pEmail);

    /**
     * Save a temporary password. Send a email to the user to inform him of a
     * new password.
     * @param pEmail - The user email address you want to save a temp password
     * for
     */
    void generateTempPassword(final String pEmail) throws BusinessException;
}
