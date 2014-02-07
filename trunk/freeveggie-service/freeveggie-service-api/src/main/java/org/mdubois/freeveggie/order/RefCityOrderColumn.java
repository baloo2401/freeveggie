package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link RefCityBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RefCityOrderColumn implements OrderColumn {

    /**
     * Ordering by city name.
     */
    NAME("name"),
    /**
     * Ordering by zip code.
     */
    ZIP_CODE("zipCode");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RefCityBO} to the column search.
     */
    RefCityOrderColumn(final String pOrderedColum) {
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
