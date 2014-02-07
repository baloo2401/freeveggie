package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
// </editor-fold>

/**
 * This class is a service. This class represent all the method that involve {@link RelationShipBO}.
 *
 * @author Mickael Dubois
 */
public interface IRelationShipService {

    /**
     * Create a relation ship.
     * @param pRelationShipMsg - The relationship to create
     * @return The database id of the corresponding relationship
     * @throws BusinessException In case of any business issue
     */
    Long create(final RelationShipMsg pRelationShipMsg) throws BusinessException;

    /**
     * Validate a relations ship.
     * @param pRelationShipId - The {@link RelationShipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean validate(final Long pRelationShipId, @Length(min = 10, max = 512) final String pMessage)throws BusinessException;

    /**
     * Refuse a relationship.
     * @param pRelationShipId - The {@link RelationShipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean refuse(final Long pRelationShipId, @Length(min = 10, max = 512) final String pMessage)throws BusinessException;
    /**
     * Get all {@link RelationShipMsg} of a given {@link UserPartialBO}.
     * If the {@link TechnicalInformation} not say the opposite only relation ship which are pending or validate.
     *
     * @param pUserId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RelationShipMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RelationShipMsg> getRelationShip(final Long pUserId, final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get the requested {@link RelationShipMsg}.
     *
     * @param pRelationshipId - The given {@link RelationShipBO}
     * @return A  {@link RelationShipMsg}
     * @throws BusinessException In case of any business issue
     */
    RelationShipMsg getRelationShipById(final Long pRelationshipId) throws BusinessException;

}