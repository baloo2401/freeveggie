package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.api.IProductService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.impl.RightControlerService;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;

// </editor-fold>
/**
 * This class is a service class it refer all the action that can involve
 * garden.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IProductBean.class)
public class ProductBeanLocal implements IProductBean {

    /**
     * {@link IProductService}
     */
    @Inject
    private IProductService productService;
    /**
     * {@link RightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(final ContextMsg pContextMsg,
            final ProductMsg pProductMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER) && rightControlerService.isUserOwnerGarden(pContextMsg.getUser().getId(), pProductMsg.getGarden().getId())) {
            return productService.create(pProductMsg);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final ContextMsg pContextMsg,
            final UpdateProductMsg pUpdateProductMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProduct(pContextMsg
                .getUser().getId(), pUpdateProductMsg.getId())) {
            return productService.update(pUpdateProductMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blacklist(final ContextMsg pContextMsg, final Long pProductId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM, UserRole.SUPERADMIN)) {
            return productService.blacklist(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unblacklist(final ContextMsg pContextMsg,
            final Long pProductId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM, UserRole.SUPERADMIN)) {
            return productService.unblacklist(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final ContextMsg pContextMsg, final Long pProductId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProduct(pContextMsg
                .getUser().getId(), pProductId)) {
            return productService.remove(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivate(final ContextMsg pContextMsg,
            final Long pProductId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProduct(pContextMsg
                .getUser().getId(), pProductId)) {
            return productService.reactivate(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(final ContextMsg pContextMsg, final Long pProductId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM)) {
            return productService.archive(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchive(final ContextMsg pContextMsg, final Long pProductId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProduct(pContextMsg
                .getUser().getId(), pProductId)) {
            return productService.unarchive(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductMsg getProductById(final ContextMsg pContextMsg,
            final Long productId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductById(productId);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByUser(
            final ContextMsg pContextMsg,
            final Long pUserId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByUser(pUserId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByGarden(
            final ContextMsg pContextMsg,
            final Long pUserId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByGarden(pUserId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByCity(
            final ContextMsg pContextMsg,
            final Integer pRefCityId,
            final Integer pProductRefId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByCity(pRefCityId, pProductRefId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByRegion(
            final ContextMsg pContextMsg,
            final Integer pRefRegionId,
            final Integer pProductRefId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByRegion(pRefRegionId,
                    pProductRefId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByState(
            final ContextMsg pContextMsg,
            final Integer pRefStateId,
            final Integer pProductRefId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByState(pRefStateId, pProductRefId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByCountry(
            final ContextMsg pContextMsg,
            final Integer pRefCountryId,
            final Integer pProductRefId,
            final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductByCountry(pRefCountryId,
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
            final ProductCommentMsg pProductCommentMsg)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.comment(pProductCommentMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeComment(final ContextMsg pContextMsg,
            final Long pProductCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProductComment(pContextMsg
                .getUser().getId(), pProductCommentId)
                || rightControlerService.isUserInRole(pContextMsg.getUser()
                .getId(), UserRole.SUPERADMIN)) {
            return productService.removeComment(pProductCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivateComment(final ContextMsg pContextMsg,
            final Long pProductCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProductComment(pContextMsg
                .getUser().getId(), pProductCommentId)) {
            return productService.reactivateComment(pProductCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archiveComment(final ContextMsg pContextMsg,
            final Long pProductCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.SYSTEM)) {
            return productService.archiveComment(pProductCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchiveComment(final ContextMsg pContextMsg,
            final Long pProductCommentId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && rightControlerService.isUserOwnerProductComment(pContextMsg
                .getUser().getId(), pProductCommentId)) {
            return productService.unarchiveComment(pProductCommentId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCommentMsg> getProductComment(
            final ContextMsg pContextMsg,
            final Long pProductId,
            final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductComment(pProductId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCommentMsg> getProductCommentWrite(
            final ContextMsg pContextMsg,
            final Long pUserWriterId,
            final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductCommentWrite(pUserWriterId,
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
            final ProductLikeMsg pProductLikeMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.like(pProductLikeMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlike(final ContextMsg pContextMsg, final Long pProductLikeId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            productService.unlike(pProductLikeId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductLikeMsg> getProductLike(
            final ContextMsg pContextMsg,
            final Long pProductId,
            final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductLike(pProductId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductLikeMsg> getProductLikeWrite(
            final ContextMsg pContextMsg,
            final Long pUserWriterId,
            final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductLikeWrite(pUserWriterId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public Long send(ContextMsg pContextMsg, ProductRequestMsg pProductRequestMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.send(pProductRequestMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public void accept(ContextMsg pContextMsg, Long pProductRequestMsgId, String pMessage) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            productService.accept(pProductRequestMsgId, pMessage);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public void refuse(ContextMsg pContextMsg, Long pProductRequestMsgId, String pMessage) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            productService.refuse(pProductRequestMsgId, pMessage);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public ProductRequestMsg getProductRequestById(ContextMsg pContextMsg, Long id) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductRequestById(id);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<ProductRequestMsg> getProductRequestByProduct(ContextMsg pContextMsg, Long pProductId, TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductRequestByProduct(pProductId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<ProductRequestMsg> getProductRequestByGarden(ContextMsg pContextMsg, Long pGardenId, TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductRequestByGarden(pGardenId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<ProductRequestMsg> getProductRequestReceive(ContextMsg pContextMsg, Long pUserPartialId, TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductRequestReceive(pUserPartialId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<ProductRequestMsg> getProductRequestSend(ContextMsg pContextMsg, Long pUserPartialId, TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getProductRequestSend(pUserPartialId, pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }
    

    @Override
    public Long addPicture(final ContextMsg pContextMsg, final PictureMsg pAbstractPictureMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER) && rightControlerService.isUserOwnerProduct(pContextMsg
                .getUser().getId(), pAbstractPictureMsg.getObjId())) {
            return productService.addPicture(pAbstractPictureMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public PictureMsg getPicture(final ContextMsg pContextMsg, final Long pProductPictureId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getPicture(pProductPictureId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public List<PictureMsg> getPictureByProduct(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return productService.getPictureByProduct(pProductId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    @Override
    public void removePicture(final ContextMsg pContextMsg, final Long pProductPictureId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER) && rightControlerService.isUserOwnerProductPicture(pContextMsg
                .getUser().getId(), pProductPictureId)) {
            productService.removePicture(pProductPictureId);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
