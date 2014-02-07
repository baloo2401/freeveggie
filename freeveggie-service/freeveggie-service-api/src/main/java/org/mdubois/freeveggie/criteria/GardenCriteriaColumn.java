package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 * List of the {@link GardenBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum GardenCriteriaColumn implements CriteriaColumn {

    /**
     * Ordering by owner username.
     */
    OWNER("owner"), STATUS("_status");
    /**
     * The name of the field that the order enum represent.
     */
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link GardenBO} to the column search.
     */
    GardenCriteriaColumn(final String pCriteriaColumn) {
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
