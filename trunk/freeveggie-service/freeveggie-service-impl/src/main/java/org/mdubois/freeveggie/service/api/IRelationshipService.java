package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 * This class is a service. This class represent all the method that involve {@link RelationshipBO}.
 *
 * @author Mickael Dubois
 */
public interface IRelationshipService {

    /**
     * Create a relation ship.
     * @param pRelationshipMsg - The relationship to create
     * @return The database id of the corresponding relationship
     * @throws BusinessException In case of any business issue
     */
    Long create(final RelationshipMsg pRelationshipMsg) throws BusinessException;

    /**
     * Validate a relations ship.
     * @param pRelationshipId - The {@link RelationshipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean validate(final Long pRelationshipId, @Length(min = 10, max = 512) final String pMessage)throws BusinessException;

    /**
     * Refuse a relationship.
     * @param pRelationshipId - The {@link RelationshipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean refuse(final Long pRelationshipId, @Length(min = 10, max = 512) final String pMessage)throws BusinessException;
    /**
     * Get all {@link RelationshipMsg} of a given {@link UserPartialBO}.
     * If the {@link TechnicalInformation} not say the opposite only relation ship which are pending or validate.
     *
     * @param pUserId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link RelationshipMsg}
     * @throws BusinessException In case of any business issue
     */
    List<RelationshipMsg> getRelationship(final Long pUserId, final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get the requested {@link RelationshipMsg}.
     *
     * @param pRelationshipId - The given {@link RelationshipBO}
     * @return A  {@link RelationshipMsg}
     * @throws BusinessException In case of any business issue
     */
    RelationshipMsg getRelationshipById(final Long pRelationshipId) throws BusinessException;

}