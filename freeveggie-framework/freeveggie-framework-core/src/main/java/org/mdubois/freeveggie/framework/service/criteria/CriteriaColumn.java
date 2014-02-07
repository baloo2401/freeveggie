package org.mdubois.freeveggie.framework.service.criteria;

/**
 *
 * @author Mickae Dubois
 */
public interface CriteriaColumn {

    /**
     * Get the name of the colum on with the critera is applied.
     * @return - The column name (the name of the JPA Object property)
     */
    String getCriteriaColumn();

}
