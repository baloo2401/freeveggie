package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 * List of the {@link ProductBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum ProductCriteriaColumn implements CriteriaColumn {

    /**
     * Filtering by product user define product name.
     */
    NAME("name"),
    /**
     * Filtering by quantity.
     */
    QUANTITY("quantity"),
    /**
     * Filtering by culture type.
     */
    CULTURE_TYPE("_cultureType"),
    /**
     * Filtering by culture mode.
     */
    CULTURE_MODE("_cultureMode"),
    /**
     * Filtering by exchange type.
     */
    EXCHANGE_TYPE("_exchangeType"),
    /**
     * Filtering by status.
     */
    STATUS("_status"),
    /**
     * Filtering by reference product name.
     */
    REFERENCE_PRODUCT_NAME("referenceProduct.name");

    /**
     * The name of the field that the order enum represent.
     */
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link ProductBO} to the column search.
     */
    ProductCriteriaColumn(final String pCriteriaColumn) {
        this.criteriaColum = pCriteriaColumn;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getCriteriaColumn() {
        return criteriaColum;
    }
}
