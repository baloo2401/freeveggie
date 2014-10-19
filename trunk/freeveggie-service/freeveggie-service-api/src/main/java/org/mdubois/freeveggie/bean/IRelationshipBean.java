package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 * This class is a service. This class represent all the method that involve {@link RelationshipBO}.
 *
 * @author Mickael Dubois
 */
public interface IRelationshipBean {

    /**
     * Create a relation ship.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRelationshipMsg - The relationship to create
     * @return The database id of the corresponding relationship
     * @throws BusinessException In case of any business issue
     */
    Long create(final ContextMsg pContextMsg, final RelationshipMsg pRelationshipMsg) throws BusinessException;

    /**
     * Validate a relations ship.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRelationshipId - The {@link RelationshipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean validate(final ContextMsg pContextMsg, final Long pRelationshipId, final String pMessage)throws BusinessException;

    /**
     * Refuse a relationship.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRelationshipId - The {@link RelationshipBO} to update
     * @param pMessage - The message to set
     * @return <code>True</code> if the update succeed, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean refuse(final ContextMsg pContextMsg, final Long pRelationshipId, final String pMessage)throws BusinessException;
    /**
     * Get all {@link RelationshipBO} of a given {@link UserBO}.
     * If the {@link TechnicalInformation} not say the opposite only relation ship which are pending or validate.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserBId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link RelationshipBO}
     * @throws BusinessException In case of any business issue
     */
    List<RelationshipMsg> getRelationship(final ContextMsg pContextMsg, final Long pUserId, final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation) throws BusinessException;

}
