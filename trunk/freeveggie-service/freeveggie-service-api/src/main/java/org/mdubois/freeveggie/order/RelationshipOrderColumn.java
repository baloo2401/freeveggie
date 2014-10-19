package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link RelationshipBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RelationshipOrderColumn implements OrderColumn {

    /**
     * Ordering by the recipient name.
     */
    RECIPIENT("recipient"),
    /**
     * Ordering by relation ship type.
     */
    TYPE("_type"),
    /**
     * Ordering by the status
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date
     */
    CREATION_DATE("creationDate");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RelationshipBO} to the column search.
     */
    RelationshipOrderColumn(final String pOrderedColum) {
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
