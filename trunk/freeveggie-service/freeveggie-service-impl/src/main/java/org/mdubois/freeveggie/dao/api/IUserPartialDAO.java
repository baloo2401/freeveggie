package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public interface IUserPartialDAO extends IReadOnlyDAO<PartialUserBO, Long> {
    /**
     * Get the {@link PartialUserBO} corresponding to the email.
     *
     * @param pEmail - The email to check
     * @return The {@link PartialUserBO}
     */
    PartialUserBO getUserFromEmail(final String pEmail);
}
