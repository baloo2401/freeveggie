package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>


/**
 * List of the {@link UserBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum UserCriteriaColumn implements CriteriaColumn {

    /**
     * Ordering by the user lastname.
     */
    LASTNAME("lastname"),
    /**
     * Ordering by the user firstname.
     */
    FIRSTNAME("firstname"),
    /**
     * Ordering by the username.
     */
    USERNAME("username"),
    /**
     * Ordering by user last connexion.
     */
    LAST_CONNEXION("lastConnexion"),
    /**
     * Ordering by status.
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date.
     */
    CREATION_DATE("creationDate"),
    /**
     * Ordering by user role.
     */
    ROLE("_role");
    /**
     * The name of the field that the order enum represent.
     */
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link UserBO} to the column search.
     */
    UserCriteriaColumn(final String pCriteriaColumn) {
        this.criteriaColum = pCriteriaColumn;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getCriteriaColumn() {
        return criteriaColum;
    }
}
