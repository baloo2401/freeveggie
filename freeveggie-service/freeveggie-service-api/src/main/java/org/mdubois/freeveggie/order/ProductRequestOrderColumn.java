package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link ProductRequestBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum ProductRequestOrderColumn implements OrderColumn {

    /**
     * Ordering by requester username.
     */
    REQUESTER("requester.username"),
    /**
     * Ordering by reference product name.
     */
    PRODUCT_REF("product.referenceProduct.name"),
    /**
     * Ordering by producer username.
     */
    PRODUCER("product.garden.owner.username"),
    /**
     * Ordering by status.
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date.
     */
    CREATION_DATE("creationDate"),
    /**
     * Ordering by quantity.
     */
    QUANTITY("quantity"),
    /**
     * Ordering by answer date.
     */
    ANSWER_DATE("answerDate"),
    /**
     * Ordering by picking date.
     */
    PICKING_DATE("pickingDate");

    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link ProductRequestBO} to the column search.
     */
    ProductRequestOrderColumn(final String pOrderedColum) {
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
