package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationShipDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.CriteriaUtils;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationShipService;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
public class RelationShipService implements IRelationShipService {

	// <editor-fold defaultstate="collapsed" desc="DAO Resource's">
	/**
	 * {@link IRelationShipDAO}
	 */
	@Inject
	private IRelationShipDAO relationShipDAO;
	/**
	 * {@link IRelationShipDAO}
	 */
	@Inject
	private IUserPartialDAO userPartialDAO;
	// </editor-fold>
	// <editor-fold defaultstate="collapsed" desc="Converters">
	/**
	 * {@link Converter<RelationShipBO,RelationShipMsg>}
	 */
	// TODO : Converter does not exist
	@Inject
	private BusinessObjectConverter<RelationShipBO, RelationShipMsg> relationshipMsgConverter;
	/**
	 * {@link Converter<RelationShipBO,RelationShipMsg>}
	 */
	// TODO : Converter does not exist
	@Inject
	private Converter<RelationShipMsg, RelationShipBO> relationshipBOConverter;
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
	private static final QueryCriteria<RelationShipCriteriaColumn> CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED = new QueryCriteria<RelationShipCriteriaColumn>(
			RelationShipCriteriaColumn.STATUS, CriteriaOperation.IN,
			RELATIONSHIP_STATUS_DEFAULT);

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Action method's">
	/** {@inheritDoc} */
	@Override
	public Long create(RelationShipMsg pRelationShipMsg)
			throws BusinessException {
		PartialUserBO senderUserBO = userPartialDAO.get(pRelationShipMsg
				.getSender().getId());

		if (senderUserBO == null) {
			throw new BusinessException(
					"Try to create relationship on an inexisting user");
		}
		PartialUserBO recipientUserBO = userPartialDAO.get(pRelationShipMsg
				.getRecipient().getId());
		if (recipientUserBO == null) {
			throw new BusinessException(
					"Try to create relationship on an inexisting user");
		}

		List<RelationShipBO> existingRelationships = relationShipDAO
				.getRelationShip(senderUserBO.getId(), null);
		if (existingRelationships != null) {
			for (RelationShipBO existingRelationship : existingRelationships) {
				if (existingRelationship.getRecipient().getId()
						.equals(pRelationShipMsg.getRecipient().getId())) {
					throw new BusinessException("Relationship already exist");
				} else if (existingRelationship.getSender().getId()
						.equals(pRelationShipMsg.getRecipient().getId())) {
					throw new BusinessException("Relationship already exist");
				}
			}
		}
		RelationShipBO relationShipBO = relationshipMsgConverter
				.createNew(pRelationShipMsg);
		relationShipBO.setRecipient(recipientUserBO);
		relationShipBO.setSender(senderUserBO);

		return relationShipDAO.save(relationShipBO);
	}

	/** {@inheritDoc} */
	@Override
	public boolean validate(Long pRelationShipId, String pMessage)
			throws BusinessException {
		RelationShipBO relationShipBO = relationShipDAO.get(pRelationShipId);
		if (relationShipBO != null) {
			relationShipBO.setStatus(RelationshipStatus.VALIDED);
			relationShipBO.setAnswer(pMessage);
			relationShipDAO.update(relationShipBO);
			return true;
		} else {
			throw new BusinessException(
					"Try to validate an unknown relationship");
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean refuse(Long pRelationShipId, String pMessage)
			throws BusinessException {
		RelationShipBO relationShipBO = relationShipDAO.get(pRelationShipId);
		if (relationShipBO != null) {
			relationShipBO.setStatus(RelationshipStatus.REFUSED);
			relationShipDAO.update(relationShipBO);
			return true;
		} else {
			throw new BusinessException("Try to refuse an unknown relationship");
		}
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Get method's">
	/** {@inheritDoc} */
	@Override
	public List<RelationShipMsg> getRelationShip(
			Long pUserId,
			TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return relationshipBOConverter.convert(relationShipDAO.getRelationShip(
				pUserId, getCleanCriteriaBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public RelationShipMsg getRelationShipById(Long pRelationshipId)
			throws BusinessException {
		RelationShipBO relationShipBO = relationShipDAO.get(pRelationshipId);
		if (relationShipBO == null) {
			throw new BusinessException("Try to get an unknow relationship");
		}
		return relationshipBOConverter.convert(relationShipBO);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Private method's">
	private TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> getCleanCriteriaBO(
			final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation) {
		TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> toReturn = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();

		if (pTechnicalInformation != null) {
			toReturn.setCriterias(pTechnicalInformation.getCriterias());
			toReturn.setOrder(pTechnicalInformation.getOrder());
			toReturn.setPagination(pTechnicalInformation.getPagination());
			if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
					RelationShipCriteriaColumn.STATUS)) {
				toReturn.addCriteria(CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED);
			}
		} else {
			toReturn.addCriteria(CRITERIA_RELATIONSHIP_STATUS_EQUAL_PENDING_OR_VALIDATED);
		}
		return toReturn;
	}
	// </editor-fold>
}
