package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link UserBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum UserOrderColumn implements OrderColumn {

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
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link UserBO} to the column search.
     */
    UserOrderColumn(final String pOrderedColum) {
        this.orderedColum = pOrderedColum;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getOrderedColumn() {
        return orderedColum;
    }
}
