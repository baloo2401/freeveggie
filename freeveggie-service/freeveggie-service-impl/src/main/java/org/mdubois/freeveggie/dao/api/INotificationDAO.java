package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.bo.UserBO;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public interface INotificationDAO {

    /**
     * Send an email to the user who just create a account to validate it.
     * @param pUserBO - The new user.
     * @param pCode - The code to use to validate
     */
    void sendCreationUserNotice(final UserBO pUserBO, final String pCode);

    /**
     * Send a email to a user who lost his password
     * @param pRecipient - The {@link UserBO} who lost his password
     * @param pCode - The code to send
     * @param pPassword - The generated password
     */
    void sendLostEmailNotice(final UserBO pRecipient, final String pCode, final String pPassword);

    /**
     * Send a email to the requested product owner
     * @param pProductRequestBO - The {@link ProductRequestBO}
     * @param pOwner - The {@link UserBO} product owner
     */
    void sendProductRequestNotice(final ProductRequestBO pProductRequestBO);

    /**
     * Send a email to inform that a relationship request as been made.
     * @param pRelationShipBO - The {@link RelationShipBO}
     */
    void sendRelationshipRequestNotice(final RelationShipBO pRelationShipBO);
}
