package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 * List of the {@link RelationshipBO} ordering possibility.
 * @author Mickael Dubois
 */
public enum RelationshipCriteriaColumn implements CriteriaColumn {


    /**
     * Ordering by relation ship type.
     */
    TYPE("_type"),
    /**
     * Ordering by the status
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date
     */
    CREATION_DATE("creationDate");
    /**
     * The name of the field that the order enum represent.
     */
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link RelationshipBO} to the column search.
     */
    RelationshipCriteriaColumn(final String pCriteriaColumn) {
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
