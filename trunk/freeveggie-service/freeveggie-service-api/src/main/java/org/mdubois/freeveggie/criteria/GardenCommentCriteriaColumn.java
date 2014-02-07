package org.mdubois.freeveggie.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
// </editor-fold>

/**
 * List of criteria that a search can make on a {@link GardenCommentBO}.
 * @author Mickael Dubois
 */
public enum GardenCommentCriteriaColumn implements CriteriaColumn {


    /**
     * Minimal note
     */
    NOTE("_note"),
    /**
     * Status
     */
    STATUS("_status"),
    /**
     *  The date the comment have been create.
     */
    CREATION_DATE("creationDate");

    private String criteriaColum;

    private GardenCommentCriteriaColumn(String pCriteriaColumn) {
        this.criteriaColum = pCriteriaColumn;
    }



    @Override
    public String getCriteriaColumn() {
        return criteriaColum;
    }



}