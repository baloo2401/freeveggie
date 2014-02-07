package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 *
 * List of the {@link ProductCommentBO} ordering possibility.;
 * @author Mickael Dubois
 */
public enum ProductCommentOrderColumn implements OrderColumn {

    /**
     * Ordering by the status.
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date.
     */
    CREATION_DATE("creationDate"),
    /**
     * Ordering by note
     */
    NOTE("_note");

    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link ProductCommentBO} to the column search.
     */
    ProductCommentOrderColumn(final String pOrderedColum) {
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
