package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.api.IGardenService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.SearchMsg;

// </editor-fold>
/**
 * This class is a service class it refer all the action that can involve
 * garden.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IGardenBean.class)
public class GardenBeanLocal implements IGardenBean {

    /**
     * {@link IGardenService}
     */
    @Inject
    private IGardenService gardenService;
    /**
     * {@link RightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(final ContextMsg pContextMsg, final GardenMsg pGardenMsg)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.create(pGardenMsg);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final ContextMsg pContextMsg,
            final GardenMsg pGardenMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGarden(pContextMsg
                .getUser().getId(), pGardenMsg.getId())) {
            return gardenService.update(pGardenMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blacklist(final ContextMsg pContextMsg, final Long pGardenId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM, UserRole.SUPERADMIN)) {
            return gardenService.blacklist(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unblacklist(final ContextMsg pContextMsg,
            final Long pGardenId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM, UserRole.SUPERADMIN)) {
            return gardenService.unblacklist(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final ContextMsg pContextMsg, final Long pGardenId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGarden(pContextMsg
                .getUser().getId(), pGardenId)) {
            return gardenService.remove(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivate(final ContextMsg pContextMsg, final Long pGardenId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGarden(pContextMsg
                .getUser().getId(), pGardenId)) {
            return gardenService.reactivate(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(final ContextMsg pContextMsg, final Long pGardenId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM)) {
            return gardenService.archive(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchive(final ContextMsg pContextMsg, final Long pGardenId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGarden(pContextMsg
                .getUser().getId(), pGardenId)) {
            return gardenService.unarchive(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GardenMsg getGardenById(final ContextMsg pContextMsg,
            final Long gardenId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenById(gardenId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenMsg> getGardenByUser(
            final ContextMsg pContextMsg,
            final Long pUserId,
            final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService
                    .getGardenByUser(pUserId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenMsg> getGardenByCity(
            final ContextMsg pContextMsg,
            final Integer pRefCityId,
            final Integer pProductRefId,
            final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenByCity(pRefCityId, pProductRefId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenMsg> getGardenByRegion(
            final ContextMsg pContextMsg,
            final Integer pRefRegionId,
            final Integer pProductRefId,
            final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenByRegion(pRefRegionId, pProductRefId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenMsg> getGardenByState(
            final ContextMsg pContextMsg,
            final Integer pRefStateId,
            final Integer pProductRefId,
            final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenByState(pRefStateId, pProductRefId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenMsg> getGardenByCountry(
            final ContextMsg pContextMsg,
            final Integer pRefCountryId,
            final Integer pProductRefId,
            final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenByCountry(pRefCountryId,
                    pProductRefId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long comment(final ContextMsg pContextMsg,
            final GardenCommentMsg pGardenCommentMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.comment(pGardenCommentMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeComment(final ContextMsg pContextMsg,
            final Long pGardenCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGardenComment(pContextMsg
                .getUser().getId(), pGardenCommentId)
                || rightControlerService.isUserInRole(pContextMsg.getUser()
                .getId(), UserRole.SUPERADMIN)) {
            return gardenService.removeComment(pGardenCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivateComment(final ContextMsg pContextMsg,
            final Long pGardenCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGardenComment(pContextMsg
                .getUser().getId(), pGardenCommentId)) {
            return gardenService.reactivateComment(pGardenCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archiveComment(final ContextMsg pContextMsg,
            final Long pGardenCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM)) {
            return gardenService.archiveComment(pGardenCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchiveComment(final ContextMsg pContextMsg,
            final Long pGardenCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerGardenComment(pContextMsg
                .getUser().getId(), pGardenCommentId)) {
            return gardenService.unarchiveComment(pGardenCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenCommentMsg> getGardenComment(
            final ContextMsg pContextMsg,
            final Long pGardenId,
            final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenComment(pGardenId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenCommentMsg> getGardenCommentWrite(
            final ContextMsg pContextMsg,
            final Long pUserWriterId,
            final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenCommentWrite(pUserWriterId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long like(final ContextMsg pContextMsg,
            final GardenLikeMsg pGardenLikeMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.like(pGardenLikeMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long unlike(final ContextMsg pContextMsg, final Long pGardenLikeId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.unlike(pGardenLikeId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenLikeMsg> getGardenLike(
            final ContextMsg pContextMsg,
            final Long pGardenId,
            final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService
                    .getGardenLike(pGardenId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GardenLikeMsg> getGardenLikeWrite(
            final ContextMsg pContextMsg,
            final Long pUserWriterId,
            final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getGardenLikeWrite(pUserWriterId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<GardenMsg> searchGarden(ContextMsg pContextMsg, SearchMsg pSearchMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.searchGarden(pSearchMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public Long addPicture(final ContextMsg pContextMsg, final PictureMsg pAbstractPictureMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER) && rightControlerService.isUserOwnerGarden(pContextMsg
                .getUser().getId(), pAbstractPictureMsg.getObjId())) {
            return gardenService.addPicture(pAbstractPictureMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public PictureMsg getPicture(final ContextMsg pContextMsg, final Long pGardenPictureId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getPicture(pGardenPictureId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<PictureMsg> getPictureByGarden(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return gardenService.getPictureByGarden(pGardenId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public void removePicture(final ContextMsg pContextMsg, final Long pGardenPictureId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER) && rightControlerService.isUserOwnerGardenPicture(pContextMsg
                .getUser().getId(), pGardenPictureId)) {
            gardenService.removePicture(pGardenPictureId);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
