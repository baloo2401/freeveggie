package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link RefProductBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RefProductOrderColumn implements OrderColumn {

    /**
     * Ordering by product name.
     */
    NAME("name"),
    /**
     * Order by ref product type.
     */
    TYPE("_type"),
    /**
     * Order by quantity of protein.
     */
    PROTEIN("protein"),
    /**
     * Order by quantity of lipid
     */
    LIPID("lipid"),
    /**
     * Order by quantity of carbohydrate
     */
    CARBOHYDRATE("carbohydrate");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RefProductBO} to the column search.
     */
    RefProductOrderColumn(final String pOrderedColum) {
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
