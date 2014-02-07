package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.api.*;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.CriteriaUtils;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.api.IGardenService;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.SearchMsg;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
public class GardenService implements IGardenService {

	// <editor-fold defaultstate="collapsed" desc="DAO Resource's">
	/**
	 * {@link IGardenDAO}
	 */
	@Inject
	private IGardenDAO gardenDAO;
	/**
	 * {@link IGardenDAO}
	 */
	@Inject
	private IAddressDAO addressDAO;
	/**
	 * {@link IGardenCommentDAO}
	 */
	@Inject
	private IGardenCommentDAO gardenCommentDAO;
	/**
	 * {@link IGardenPictureDAO}
	 */
	@Inject
	private IGardenPictureDAO gardenPictureDAO;
	/**
	 * {@link IUserDAO}
	 */
	@Inject
	private IUserPartialDAO userPartialDAO;
	/**
	 * {@link IGardenLikeDAO}
	 */
	@Inject
	private IGardenLikeDAO gardenLikeDAO;
	/**
	 * {@link IGardenLikeDAO}
	 */
	@Inject
	private IRefProductDAO refProductDAO;
	// </editor-fold>
	
        // <editor-fold defaultstate="collapsed" desc="Converter Resource's">
	/**
	 * {@link Converter<GardenBO, GardenMsg>}
	 */
	@Inject
	private BusinessObjectConverter<GardenBO, GardenMsg> gardenMsgConverter;
	/**
	 * {@link Converter<AddressBO, AddressMsg>}
	 */
	@Inject
	private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;
	/**
	 * {@link Converter<GardenCommentBO,GardenCommentMsg>}
	 */
	@Inject
	private BusinessObjectConverter<GardenCommentBO, GardenCommentMsg> gardenCommentMsgConverter;
	/**
	 * {@link Converter<GardenLikeBO,GardenLikeMsg>}
	 */
	@Inject
	private BusinessObjectConverter<GardenLikeBO, GardenLikeMsg> gardenLikeMsgConverter;
        /**
         * BusinessObjectConverter<ProductPictureBO, ProductPictureMsg>
         */
	@Inject
        private BusinessObjectConverter<GardenPictureBO, PictureMsg> abstractPictureMsgConverter;
	/**
	 * {@link Converter<GardenMsg, GardenBO>}
	 */
	@Inject
	private Converter<GardenMsg, GardenBO> gardenBOConverter;
	/**
	 * {@link Converter<GardenLikeMsg, GardenLikeBO>}
	 */
	@Inject
	private Converter<GardenLikeMsg, GardenLikeBO> gardenLikeBOConverter;
	/**
	 * {@link Converter<GardenCommentMsg, GardenCommentBO>}
	 */
	@Inject
	private Converter<GardenCommentMsg, GardenCommentBO> gardenCommentBOConverter;
	/**
	 * {@link Converter<ProductPictureMsg, ProductPictureBO>}
	 */
	@Inject
	private Converter<PictureMsg, GardenPictureBO> gardenPictureBOConverter;
	// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Contants">
	/**
	 * Criteria garden like status equal to setted
	 */
	private static final QueryCriteria<GardenLikeCriteriaColumn> CRITERIA_GARDEN_LIKE_STATUS_EQUAL_SETTED = new QueryCriteria<GardenLikeCriteriaColumn>(
			GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
			EvaluationStatus.SETTED);
	/**
	 * Criteria garden comment status equal to setted
	 */
	private static final QueryCriteria<GardenCommentCriteriaColumn> CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_SETTED = new QueryCriteria<GardenCommentCriteriaColumn>(
			GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
			EvaluationStatus.SETTED);
	/**
	 * Criteria garden status equal to CREATED
	 */
	private static final QueryCriteria<GardenCriteriaColumn> CRITERIA_GARDEN_STATUS_EQUAL_CREATED = new QueryCriteria<GardenCriteriaColumn>(
			GardenCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
			Status.CREATED);

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Action on garden method's">
	/** {@inheritDoc} */
	@Override
	public Long create(GardenMsg pGardenMsg) throws BusinessException {
		GardenBO gardenBO = gardenMsgConverter.createNew(pGardenMsg);
		AddressBO addressBO;
		PartialUserBO userBO;
		if (pGardenMsg.getAddress().getId() != null) {
			addressBO = addressDAO.get(pGardenMsg.getAddress().getId());
			if (addressBO == null) {
				throw new BusinessException(
						"Try to create a garden on un inexisting garden");
			}
		} else {
			addressBO = addressMsgConverter.createNew(pGardenMsg.getAddress());
		}
		userBO = userPartialDAO.get(pGardenMsg.getOwner().getId());
		if (userBO == null) {
			throw new BusinessException(
					"Try to create a garden on un inexisting user");
		}
		gardenBO.setAddress(addressBO);
		gardenBO.setOwner(userBO);
		return gardenDAO.save(gardenBO);
	}

	/** {@inheritDoc} */
	@Override
	public boolean update(GardenMsg pGardenMsg) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenMsg.getId());

		if (gardenBO != null) {
			gardenMsgConverter.update(gardenBO, pGardenMsg);
			gardenDAO.update(gardenBO);
			return true;
		} else {
			throw new BusinessException("Try to update an unknown user");
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean blacklist(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.CREATED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.BLACKLISTED);
				gardenDAO.update(gardenBO);
			} else if (!Status.BLACKLISTED.equals(gardenBO.getStatus())) {
				throw new BusinessException(
						"Can only blacklist a created garden");
			}
		} else {
			throw new BusinessException(
					"Try to blacklikst an inexisting garden");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean unblacklist(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.BLACKLISTED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.CREATED);
				gardenDAO.update(gardenBO);
			} else if (!Status.CREATED.equals(gardenBO.getStatus())) {
				throw new BusinessException(
						"Can only unblacklist a blacklisted garden");
			}
		} else {
			throw new BusinessException(
					"Try to unblacklikst an inexisting garden");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean remove(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.CREATED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.DELETED);
				gardenDAO.update(gardenBO);
			} else if (!Status.DELETED.equals(gardenBO.getStatus())) {
				throw new BusinessException("Can only remove a created garden");
			}
		} else {
			throw new BusinessException("Try to remove an inexisting garden");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean reactivate(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.DELETED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.CREATED);
				gardenDAO.update(gardenBO);
			} else if (!Status.CREATED.equals(gardenBO.getStatus())) {
				throw new BusinessException(
						"Can only reactivate a delete garden");
			}
		} else {
			throw new BusinessException(
					"Try to reactivate an inexisting garden");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean archive(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.CREATED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.ARCHIVED);
				gardenDAO.update(gardenBO);
			} else if (!Status.ARCHIVED.equals(gardenBO.getStatus())) {
				throw new BusinessException("Can only archive a create garden");
			}
		} else {
			throw new BusinessException("Try to archive an inexisting garden");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean unarchive(Long pGardenId) throws BusinessException {
		GardenBO gardenBO = gardenDAO.get(pGardenId);
		if (gardenBO != null) {
			if (Status.ARCHIVED.equals(gardenBO.getStatus())) {
				gardenBO.setStatus(Status.CREATED);
				gardenDAO.update(gardenBO);
			} else if (!Status.CREATED.equals(gardenBO.getStatus())) {
				throw new BusinessException(
						"Can only unarchive a archived garden");
			}
		} else {
			throw new BusinessException("Try to unarchive an inexisting garden");
		}
		return true;
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Get garden(s) method's">
	/** {@inheritDoc} */
	@Override
	public GardenMsg getGardenById(Long id) throws BusinessException {
		GardenBO garden = gardenDAO.get(id);
		if (garden != null) {
			return gardenBOConverter.convert(garden);
		} else {
			throw new BusinessException("garden with id " + id
					+ " does not exist");
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenMsg> getGardenByUser(
			Long pUserId,
			TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenBOConverter.convert(gardenDAO.getGardenByUser(pUserId,
				getCleanCriteriaGardenBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenMsg> getGardenByCity(
			Integer pRefCityId,
			Integer pProductRefId,
			TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
		return gardenBOConverter.convert(gardenDAO.getGardenByCityAndProduct(
				pRefCityId, pProductRefId,
				getCleanCriteriaGardenBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenMsg> getGardenByRegion(
			Integer pRefRegionId,
			Integer pProductRefId,
			TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
		return gardenBOConverter.convert(gardenDAO.getGardenByRegionAndProduct(
				pRefRegionId, pProductRefId,
				getCleanCriteriaGardenBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenMsg> getGardenByState(
			Integer pRefStateId,
			Integer pProductRefId,
			TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
		return gardenBOConverter.convert(gardenDAO.getGardenByStateAndProduct(
				pRefStateId, pProductRefId,
				getCleanCriteriaGardenBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenMsg> getGardenByCountry(
			Integer pRefCountryId,
			Integer pProductRefId,
			TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenBOConverter.convert(gardenDAO
				.getGardenByCountryAndProduct(pRefCountryId, pProductRefId,
						getCleanCriteriaGardenBO(pTechnicalInformation)));
	}

        
        @Override
        public List<GardenMsg> searchGarden(SearchMsg pSearchMsg) throws  BusinessException{
            Integer productId = -1;
            if(pSearchMsg.getRefProductId() != null){
                 RefProductBO refProductBO = refProductDAO.get(pSearchMsg.getRefProductId());
                if(refProductBO == null){
                     throw new BusinessException("Looking for a product that doest not exist");
                } else {
                    productId = refProductBO.getId();
                }
            }
            return gardenBOConverter.convert(gardenDAO
				.searchGarden(pSearchMsg.getLatitudeUp(), pSearchMsg.getLatitudeDown(), 
                                    pSearchMsg.getLongitudeUp(),pSearchMsg.getLongitudeDown(), productId,
                                    getCleanCriteriaGardenBO(null)));
        }
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Comment's methods">
	/** {@inheritDoc} */
	@Override
	public Long comment(GardenCommentMsg pGardenCommentMsg)
			throws BusinessException {
		PartialUserBO userBO = userPartialDAO.get(pGardenCommentMsg.getWriter()
				.getId());
		// We controle that the user exit
		if (userBO != null) {
			GardenBO gardenBO = gardenDAO.get(pGardenCommentMsg.getGarden()
					.getId());
			// We control that the garden exist
			if (gardenBO != null) {
				if (!userBO.equals(gardenBO.getOwner())) {
					GardenCommentBO gardenCommentBO = gardenCommentDAO
							.getGardenCommentByUserAndGarden(userBO.getId(),
									gardenBO.getId());
					// We controle that a comment of this user on this garden
					// doest not exist already
					if (gardenCommentBO == null) {
						gardenCommentBO = gardenCommentMsgConverter
								.createNew(pGardenCommentMsg);
						gardenCommentBO.setGarden(gardenBO);
						gardenCommentBO.setWriter(userBO);
						return gardenCommentDAO.save(gardenCommentBO);
					} else {
						throw new BusinessException(
								"A comment from this user on this garden have already been made");
					}
				} else {
					throw new BusinessException(
							"The owner of a garden can comment on it.");
				}
			} else {
				throw new BusinessException(
						"Asking for garden comment for a unknown garden");
			}
		} else {
			throw new BusinessException(
					"Asking for garden comment for a unknown garden");
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean removeComment(Long pGardenCommentId)
			throws BusinessException {
		GardenCommentBO gardenCommentBO = gardenCommentDAO
				.get(pGardenCommentId);
		if (gardenCommentBO != null) {
			if (gardenCommentBO.getStatus().equals(EvaluationStatus.SETTED)) {
				gardenCommentBO.setStatus(EvaluationStatus.REMOVED);
				gardenCommentDAO.update(gardenCommentBO);
			} else if (!gardenCommentBO.getStatus().equals(
					EvaluationStatus.REMOVED)) {
				throw new BusinessException(
						"You can only remove setted comment");
			}
		} else {
			throw new BusinessException("Deleting an unknown garden comment");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean reactivateComment(Long pGardenCommentId)
			throws BusinessException {
		GardenCommentBO gardenCommentBO = gardenCommentDAO
				.get(pGardenCommentId);
		if (gardenCommentBO != null) {
			if (gardenCommentBO.getStatus().equals(EvaluationStatus.REMOVED)) {
				gardenCommentBO.setStatus(EvaluationStatus.SETTED);
				gardenCommentDAO.update(gardenCommentBO);
			} else if (!gardenCommentBO.getStatus().equals(
					EvaluationStatus.SETTED)) {
				throw new BusinessException(
						"You can only reactivate a removed comment");
			}
		} else {
			throw new BusinessException(
					"Try to reactivate an unknown garden comment");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean archiveComment(Long pGardenCommentId)
			throws BusinessException {
		GardenCommentBO gardenCommentBO = gardenCommentDAO
				.get(pGardenCommentId);
		if (gardenCommentBO != null) {
			if (gardenCommentBO.getStatus().equals(EvaluationStatus.SETTED)) {
				gardenCommentBO.setStatus(EvaluationStatus.ARCHIVED);
				gardenCommentDAO.update(gardenCommentBO);
			} else if (!gardenCommentBO.getStatus().equals(
					EvaluationStatus.ARCHIVED)) {
				throw new BusinessException(
						"You can only archive setted comment");
			}
		} else {
			throw new BusinessException(
					"Try to archive an unknown garden comment");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean unarchiveComment(Long pGardenCommentId)
			throws BusinessException {
		GardenCommentBO gardenCommentBO = gardenCommentDAO
				.get(pGardenCommentId);
		if (gardenCommentBO != null) {
			if (gardenCommentBO.getStatus().equals(EvaluationStatus.ARCHIVED)) {
				gardenCommentBO.setStatus(EvaluationStatus.SETTED);
				gardenCommentDAO.update(gardenCommentBO);
			} else if (gardenCommentBO.getStatus().equals(
					EvaluationStatus.REMOVED)) {
				throw new BusinessException(
						"You can only unarchive archived comment");
			}
		} else {
			throw new BusinessException(
					"Try to archive an unknown garden comment");
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenCommentMsg> getGardenComment(
			Long pGardenId,
			TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenCommentBOConverter
				.convert(gardenCommentDAO.getGardenCommentByGarden(pGardenId,
						getCleanCriteriaGardenCommentBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenCommentMsg> getGardenCommentWrite(
			Long pUserWriterId,
			TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenCommentBOConverter
				.convert(gardenCommentDAO.getGardenCommentByWriter(
						pUserWriterId,
						getCleanCriteriaGardenCommentBO(pTechnicalInformation)));
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="Like's methods">
	/** {@inheritDoc} */
	@Override
	public Long like(GardenLikeMsg pGardenLikeMsg) throws BusinessException {

		PartialUserBO userBO = userPartialDAO.get(pGardenLikeMsg.getWriter()
				.getId());
		if (userBO != null) {
			GardenBO gardenBO = gardenDAO.get(pGardenLikeMsg.getGarden()
					.getId());
			if (gardenBO != null) {
				if (!userBO.equals(gardenBO.getOwner())) {
					GardenLikeBO gardenLikeBO = gardenLikeDAO
							.getGardenLikeByUserAndGarden(userBO.getId(),
									gardenBO.getId());
					if (gardenLikeBO == null) {
						gardenLikeBO = gardenLikeMsgConverter
								.createNew(pGardenLikeMsg);
						gardenLikeBO.setGarden(gardenBO);
						gardenLikeBO.setWriter(userBO);
						return gardenLikeDAO.save(gardenLikeBO);
					} else {
						throw new BusinessException(
								"Try to like a garden that you by a user that already like it");
					}
				} else {
					throw new BusinessException("You can like your own garden");
				}
			} else {
				throw new BusinessException(
						"Asking for garden like for a unknown user");
			}
		} else {
			throw new BusinessException(
					"Asking for garden like for a unknown user");
		}

	}

	/** {@inheritDoc} */
	@Override
	public Long unlike(Long pGardenLikeId) throws BusinessException {

		GardenLikeBO gardenLikeBO = gardenLikeDAO.get(pGardenLikeId);
		if (gardenLikeBO != null) {
			if (!EvaluationStatus.ARCHIVED.equals(gardenLikeBO.getStatus())) {
				gardenLikeBO.setStatus(EvaluationStatus.REMOVED);
				return gardenLikeDAO.save(gardenLikeBO);
			} else {
				throw new BusinessException("Can't unlike a archived like.");
			}
		} else {
			throw new BusinessException("Asking for a like doest not exit");
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenLikeMsg> getGardenLike(
			Long pGardenId,
			TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenLikeBOConverter.convert(gardenLikeDAO
				.getGardenLikeByGarden(pGardenId,
						getCleanCriteriaGardenLikeBO(pTechnicalInformation)));
	}

	/** {@inheritDoc} */
	@Override
	public List<GardenLikeMsg> getGardenLikeWrite(
			Long pUserWriterId,
			TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation)
			throws BusinessException {
		return gardenLikeBOConverter.convert(gardenLikeDAO
				.getGardenLikeByWriter(pUserWriterId,
						getCleanCriteriaGardenLikeBO(pTechnicalInformation)));
	}

	// </editor-fold>

                    
	// <editor-fold defaultstate="collapsed" desc="Picture method's">
	/** {@inheritDoc} */
        @Override
	public Long addPicture(PictureMsg abstractPictureMsg) throws BusinessException{
            GardenBO gardenBO = gardenDAO.get(abstractPictureMsg.getObjId());
            if(gardenBO != null){
                GardenPictureBO gardenPictureBO = abstractPictureMsgConverter.createNew(abstractPictureMsg);
                gardenPictureBO.setGarden(gardenBO);
                return gardenPictureDAO.save(gardenPictureBO);
            } else {
                throw new BusinessException("Try to add a picture to a unknow garden");
            }
        }
    
	@Override
	public PictureMsg getPicture(Long gardenPictureId) throws BusinessException{
            GardenPictureBO gardenPictureBO = gardenPictureDAO.get(gardenPictureId);
            if(gardenPictureBO != null){
                return gardenPictureBOConverter.convert(gardenPictureBO);
            } else {
                throw new BusinessException("Try to get a picture with a unknow id");
            }
        }
    
	@Override
	public List<PictureMsg> getPictureByGarden(Long gardenId) throws BusinessException{
            List<GardenPictureBO> gardenPictureBO = gardenPictureDAO.getGardenPictureByGarden(gardenId);
            if(gardenPictureBO != null){
                return gardenPictureBOConverter.convert(gardenPictureBO);
            } else {
                throw new BusinessException("Try to get a picture with a unknow id");
            }
        }
    
	@Override
	public void removePicture(Long gardenPictureId) throws BusinessException{
            GardenPictureBO gardenPictureBO = gardenPictureDAO.get(gardenPictureId);
            if(gardenPictureBO != null){
                gardenPictureDAO.delete(gardenPictureBO);
            } else {
                throw new BusinessException("Try to get a picture with a unknow id");
            }
        }
        
        // </editor-fold>
        
	// <editor-fold defaultstate="collapsed" desc="Private's methods">
	private TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> getCleanCriteriaGardenLikeBO(
			final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) {
		TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> toReturn = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();

		if (pTechnicalInformation != null) {
			toReturn.setCriterias(pTechnicalInformation.getCriterias());
			toReturn.setOrder(pTechnicalInformation.getOrder());
			toReturn.setPagination(pTechnicalInformation.getPagination());
			if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
					GardenLikeCriteriaColumn.STATUS)) {
				toReturn.addCriteria(CRITERIA_GARDEN_LIKE_STATUS_EQUAL_SETTED);
			}
		} else {
			toReturn.addCriteria(CRITERIA_GARDEN_LIKE_STATUS_EQUAL_SETTED);
		}
		return toReturn;
	}

	private TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> getCleanCriteriaGardenCommentBO(
			final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) {
		TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> toReturn = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();

		if (pTechnicalInformation != null) {
			toReturn.setCriterias(pTechnicalInformation.getCriterias());
			toReturn.setOrder(pTechnicalInformation.getOrder());
			toReturn.setPagination(pTechnicalInformation.getPagination());
			if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
					GardenCommentCriteriaColumn.STATUS)) {
				toReturn.addCriteria(CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_SETTED);
			}
		} else {
			toReturn.addCriteria(CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_SETTED);
		}
		return toReturn;
	}

	private TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> getCleanCriteriaGardenBO(
			final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
		TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> toReturn = new TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn>();

		if (pTechnicalInformation != null) {
			toReturn.setCriterias(pTechnicalInformation.getCriterias());
			toReturn.setOrder(pTechnicalInformation.getOrder());
			toReturn.setPagination(pTechnicalInformation.getPagination());
			if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
					GardenCriteriaColumn.STATUS)) {
				toReturn.addCriteria(CRITERIA_GARDEN_STATUS_EQUAL_CREATED);
			}
		} else {
			toReturn.addCriteria(CRITERIA_GARDEN_STATUS_EQUAL_CREATED);
		}
		return toReturn;
	}
	// </editor-fold>
}
