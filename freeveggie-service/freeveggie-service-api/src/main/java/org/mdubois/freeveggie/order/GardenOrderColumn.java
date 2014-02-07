package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link GardenBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum GardenOrderColumn implements OrderColumn {

    /**
     * Ordering by name.
     */
    NAME("name"),
    /**
     * Ordering by the creation date.
     */
    CREATION_DATE("creationDate");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link GardenBO} to the column search.
     */
    GardenOrderColumn(final String pOrderedColum) {
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
