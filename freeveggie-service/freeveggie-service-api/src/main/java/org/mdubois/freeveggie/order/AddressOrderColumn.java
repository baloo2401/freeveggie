package org.mdubois.freeveggie.order;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 * List of the {@link AddressBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum AddressOrderColumn implements OrderColumn {

    /**
     * Ordering by city name.
     */
    CITY_NAME("city.name"),
    /**
     * Ordering by city zip code.
     */
    CITY_ZIP_CODE("city.zipCode"),
    /**
     * Ordering by region name.
     */
    REGION("city.region.name"),
    /**
     * Ordering by state name.
     */
    STATE("city.region.state.name"),
    /**
     * Ordering by country name.
     */
    COUNTRY("city.region.state.country.name");
    /**
     * The name of the field that the order enum represent.
     */
    private String orderedColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link AddressBO} to the column search.
     */
    AddressOrderColumn(final String pOrderedColum) {
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
