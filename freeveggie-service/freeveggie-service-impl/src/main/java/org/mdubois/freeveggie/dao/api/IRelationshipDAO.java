package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RelationshipBO} entity.
 * @author Mickael Dubois
 */
public interface IRelationshipDAO extends IReadWriteDAO<RelationshipBO, Long>{

    /**
     * Get all {@link RelationshipBO} that a given {@link UserPartialBO}.
     * @param pIdUserPartialBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link RelationshipBO}
     */
    List<RelationshipBO> getRelationship(final Long pIdUserPartialBO, final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation);

}
