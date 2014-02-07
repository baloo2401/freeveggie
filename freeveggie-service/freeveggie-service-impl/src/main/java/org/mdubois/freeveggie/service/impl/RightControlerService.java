package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.dao.api.*;
import org.mdubois.freeveggie.framework.exception.AuthenticationException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.utils.UserUtils;
import org.mdubois.freeveggie.service.api.IRightControlerService;

// </editor-fold>
/**
 * Control the user write to call
 *
 * @author mdubois
 */
public class RightControlerService implements IRightControlerService {

    /**
     * {@link IUserPartialDAO}
     */
    @Inject
    private IUserPartialDAO userPartialDAO;
    /**
     * {@link IGardenDAO}
     */
    @Inject
    private IGardenDAO gardenDAO;
    /**
     * {@link IProductDAO}
     */
    @Inject
    private IProductDAO productDAO;
    /**
     * {@link IGardenCommentDAO}
     */
    @Inject
    private IGardenCommentDAO gardenCommentDAO;
    /**
     * {@link IProductCommentDAO}
     */
    @Inject
    private IProductCommentDAO productCommentDAO;
    /**
     * {@link IGardenLikeDAO}
     */
    @Inject
    private IGardenLikeDAO gardenLikeDAO;
    /**
     * {@link IProductLikeDAO}
     */
    @Inject
    private IProductLikeDAO productLikeDAO;
    /**
     * {@link IProductPictureDAO}
     */
    @Inject
    private IProductPictureDAO productPictureDAO;
    /**
     * {@link IGardenPictureDAO}
     */
    @Inject
    private IGardenPictureDAO gardenPictureDAO;
    /**
     * {@link IRelationShipDAO}
     */
    @Inject
    private IRelationShipDAO relationShipDAO;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserInRole(final Long pIdUser, final UserRole... pUserRole)
            throws BusinessException {
        PartialUserBO partialUserBO = userPartialDAO.get(pIdUser);
        if (partialUserBO != null) {
            for (UserRole userRole : pUserRole) {
                if (UserUtils.isUserInRole(partialUserBO.getRole(), userRole)) {
                    return true;
                }
            }
        } else {
            throw new AuthenticationException();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerGarden(final Long pIdUser, final Long pIdGarden)
            throws BusinessException {
        GardenBO gardenBO = gardenDAO.get(pIdGarden);
        return gardenBO != null && gardenBO.getOwner().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerProduct(final Long pIdUser, final Long pIdProduct)
            throws BusinessException {
        ProductBO productBO = productDAO.get(pIdProduct);
        return productBO != null
                && productBO.getGarden().getOwner().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerGardenComment(final Long pIdUser,
            final Long pIdGardenComment) throws BusinessException {
        GardenCommentBO gardenCommentBO = gardenCommentDAO
                .get(pIdGardenComment);
        return gardenCommentBO != null
                && gardenCommentBO.getWriter().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerProductComment(final Long pIdUser,
            final Long pIdProductComment) throws BusinessException {
        ProductCommentBO productCommentBO = productCommentDAO
                .get(pIdProductComment);
        return productCommentBO != null
                && productCommentBO.getWriter().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerGardenLike(final Long pIdUser,
            final Long pIdGardenLike) throws BusinessException {
        GardenLikeBO gardenLikeBO = gardenLikeDAO.get(pIdGardenLike);
        return gardenLikeBO != null
                && gardenLikeBO.getWriter().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerProductLike(final Long pIdUser,
            final Long pIdProductLike) {
        ProductLikeBO productLikeBO = productLikeDAO.get(pIdProductLike);
        return productLikeBO != null
                && productLikeBO.getWriter().getId().equals(pIdUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerRelationship(Long pUserId, Long pRelationShipId) {
        RelationShipBO relationShipBO = relationShipDAO.get(pRelationShipId);
        return relationShipBO != null
                && relationShipBO.getRecipient().getId().equals(pUserId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerProductPicture(Long pUserId, Long pProductPictureId) {
        ProductPictureBO productPictureBO = productPictureDAO.get(pProductPictureId);
        return productPictureBO.getProduct().getGarden().getOwner().getId().equals(pUserId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserOwnerGardenPicture(Long pUserId, Long pGardenPictureId) {
        GardenPictureBO gardenPictureBO = gardenPictureDAO.get(pGardenPictureId);
        return gardenPictureBO.getGarden().getOwner().getId().equals(pUserId);
    }
}
