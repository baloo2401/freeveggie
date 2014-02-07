package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 *
 * List of the {@link ProductCommentBO} ordering possibility.;
 * @author Mickael Dubois
 */
public enum ProductCommentCriteriaColumn implements CriteriaColumn {

    /**
     * Ordering by the status.
     */
    STATUS("_status"),
    /**
     * Ordering by the creation date.
     */
    CREATION_DATE("creationDate"),
    /**
     * Ordering by note
     */
    NOTE("_note");

    /**
     * The name of the field that the order enum represent.
     */
    private String criteriaColum;

    /**
     * Constructor.
     * @param pOrderedColum - The expression language from  {@link ProductCommentBO} to the column search.
     */
    ProductCommentCriteriaColumn(final String pCriteriaColumn) {
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
