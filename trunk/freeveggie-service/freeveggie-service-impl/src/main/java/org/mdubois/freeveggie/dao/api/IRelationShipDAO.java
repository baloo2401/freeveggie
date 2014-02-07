package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RelationShipBO} entity.
 * @author Mickael Dubois
 */
public interface IRelationShipDAO extends IReadWriteDAO<RelationShipBO, Long>{

    /**
     * Get all {@link RelationShipBO} that a given {@link UserPartialBO}.
     * @param pIdUserPartialBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link RelationShipBO}
     */
    List<RelationShipBO> getRelationShip(final Long pIdUserPartialBO, final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation);

}
