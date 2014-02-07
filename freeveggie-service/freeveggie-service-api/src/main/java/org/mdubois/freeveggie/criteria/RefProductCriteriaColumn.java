package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 * List of the {@link RefProductBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RefProductCriteriaColumn implements CriteriaColumn {

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
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RefProductBO} to the column search.
     */
    RefProductCriteriaColumn(final String pCriteriaColum) {
        this.criteriaColum = pCriteriaColum;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getCriteriaColumn() {
        return criteriaColum;
    }
}
