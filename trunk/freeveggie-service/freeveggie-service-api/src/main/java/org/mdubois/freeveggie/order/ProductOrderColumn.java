package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link ProductBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum ProductOrderColumn implements OrderColumn {

    /**
     * Ordering by product user define product name.
     */
    NAME("name"),
    /**
     * Ordering by quantity.
     */
    QUANTITY("quantity"),
    /**
     * Ordering by culture type.
     */
    CULTURE_TYPE("_cultureType"),
    /**
     * Ordering by culture mode.
     */
    CULTURE_MODE("_cultureMode"),
    /**
     * Ordering by exchange type.
     */
    EXCHANGE_TYPE("_exchangeType"),
    /**
     * Ordering by status.
     */
    STATUS("_status"),
    /**
     * Ordering by reference product name.
     */
    REFERENCE_PRODUCT_NAME("referenceProduct.name");

    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link ProductBO} to the column search.
     */
    ProductOrderColumn(final String pOrderedColum) {
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
