package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.UserBO;
// </editor-fold>

/**
 * List all of the method need for authentication.
 *
 * @author Mickael Dubois
 */
public interface IAuthenticationDAO {

    /**
     * Control that the password match the login.
     *
     * @param pLogin - The login to check
     * @param pPassword - The password to check
     * @return The {@link UserBO} corresponding to the given login
     */
    UserBO controlPassword(final String pLogin, final String pPassword);

    /**
     * Control that the temporary password match the login.
     *
     * @param pLogin - The login to check
     * @param pPassword - The password to check
     * @return The {@link UserBO} corresponding to the given login
     */
    UserBO controlTempPassword(final String pLogin, final String pPassword);

}
