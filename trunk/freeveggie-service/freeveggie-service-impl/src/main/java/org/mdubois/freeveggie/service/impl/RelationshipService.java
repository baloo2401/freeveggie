package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationshipDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.CriteriaUtils;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationshipService;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
public class RelationshipService implements IRelationshipService {

	// <editor-fold defaultstate="collapsed" desc="DAO Resource's">
	/**
	 * {@link IRelationshipDAO}
	 */
	@Inject
	private IRelationshipDAO relationshipDAO;
	/**
	 * {@link IRelationshipDAO}
	 */
	@Inject
	private IUserPartialDAO userPartialDAO;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Converters">
	/**
	 * {@link Converter<RelationshipBO,RelationshipMsg>}
	 */
	// TODO : Converter does not exist
	@Inject
	private BusinessObjectConverter<RelationshipBO, RelationshipMsg> relationshipMsgConverter;
	/**
	 * {@link Converter<RelationshipBO,RelationshipMsg>}
	 */
	// TODO : Converter does not exist
	@Inject
	private Converter<RelationshipMsg, RelationshipBO> relationshipBOConverter;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Constants">
	private static final List<RelationshipStatus> RELATIONSHIP_STATUS_DEFAULT = new ArrayList<RelationshipStatus>();
	static {
		RELATIONSHIP_STATUS_DEFAULT.add(RelationshipStatus.PENDING);
		RELATIONSHIP_STATUS_DEFAULT.add(RelationshipStatus.VALIDED);
	}
	/**
	 * Criteria relationship status equal pending
	 */
	private static final QueryCriteria<RelationshipCriteriaColumn> CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED = new QueryCriteria<RelationshipCriteriaColumn>(
			RelationshipCriteriaColumn.STATUS, CriteriaOperation.IN,
			RELATIONSHIP_STATUS_DEFAULT);

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Action method's">
	/** {@inheritDoc} */
	@Override
	public Long create(RelationshipMsg pRelationshipMsg)
			throws BusinessException {
		PartialUserBO senderUserBO = userPartialDAO.get(pRelationshipMsg
				.getSender().getId());

		if (senderUserBO == null) {
			throw new BusinessException(
					"Try to create relationship on an inexisting user");
		}
		PartialUserBO recipientUserBO = userPartialDAO.get(pRelationshipMsg
				.getRecipient().getId());
		if (recipientUserBO == null) {
			throw new BusinessException(
					"Try to create relationship on an inexisting user");
		}

		List<RelationshipBO> existingRelationships = relationshipDAO
				.getRelationship(senderUserBO.getId(), null);
		if (existingRelationships != null) {
			for (RelationshipBO existingRelationship : existingRelationships) {
				if (existingRelationship.getRecipient().getId()
						.equals(pRelationshipMsg.getRecipient().getId())) {
					throw new BusinessException("Relationship already exist");
				} else if (existingRelationship.getSender().getId()
						.equals(pRelationshipMsg.getRecipient().getId())) {
					throw new BusinessException("Relationship already exist");
				}
			}
		}
		RelationshipBO relationshipBO = relationshipMsgConverter
				.createNew(pRelationshipMsg);
		relationshipBO.setRecipient(recipientUserBO);
		relationshipBO.setSender(senderUserBO);

		return relationshipDAO.save(relationshipBO);
	}

	/** {@inheritDoc} */
	@Override
	public boolean validate(Long pRelationshipId, String pMessage)
			throws BusinessException {
		RelationshipBO relationshipBO = relationshipDAO.get(pRelationshipId);
		if (relationshipBO != null) {
			relationshipBO.setStatus(RelationshipStatus.VALIDED);
			relationshipBO.setAnswer(pMessage);
			relationshipDAO.update(relationshipBO);
			return true;
		} else {
			throw new BusinessException(
					"Try to validate an unknown relationship");
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean refuse(Long pRelationshipId, String pMessage)
			throws BusinessException {
		RelationshipBO relationshipBO = relationshipDAO.get(pRelationshipId);
		if (relationshipBO != null) {
			relationshipBO.setStatus(RelationshipStatus.REFUSED);
			relationshipDAO.update(relationshipBO);
			return true;
		} else {
			throw new BusinessException("Try to refuse an unknown relationship");
		}
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Get method's">
	/** {@inheritDoc} */
	@Override
	public List<RelationshipMsg> getRelationship(
			Long pUserId,
			TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return relationshipBOConverter.convert(relationshipDAO.getRelationship(
				pUserId, getCleanCriteriaBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public RelationshipMsg getRelationshipById(Long pRelationshipId)
			throws BusinessException {
		RelationshipBO relationshipBO = relationshipDAO.get(pRelationshipId);
		if (relationshipBO == null) {
			throw new BusinessException("Try to get an unknow relationship");
		}
		return relationshipBOConverter.convert(relationshipBO);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Private method's">
	private TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> getCleanCriteriaBO(
			final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation) {
		TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> toReturn = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();

		if (pTechnicalInformation != null) {
			toReturn.setCriterias(pTechnicalInformation.getCriterias());
			toReturn.setOrder(pTechnicalInformation.getOrder());
			toReturn.setPagination(pTechnicalInformation.getPagination());
			if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
					RelationshipCriteriaColumn.STATUS)) {
				toReturn.addCriteria(CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED);
			}
		} else {
			toReturn.addCriteria(CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED);
		}
		return toReturn;
	}
	// </editor-fold>
}
