package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link RefCountryBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RefCountryOrderColumn implements OrderColumn {

    /**
     * Ordering by country name.
     */
    NAME("name"),
    /**
     * Ordering by iso country code a2.
     */
    CODE_ISO_A2("codeIsoA2"),
    /**
     * Ordering by iso country code a3.
     */
    CODE_ISO_A3("codeIsoA3"),
    /**
     * Ordering by iso country code number.
     */
    CODE_ISO_NUMBER("codeIsoNumber");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RefCountryBO} to the column search.
     */
    RefCountryOrderColumn(final String pOrderedColum) {
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
