package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.inject.Inject;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.IProductCommentDAO;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IProductLikeDAO;
import org.mdubois.freeveggie.dao.api.IProductPictureDAO;
import org.mdubois.freeveggie.dao.api.IProductRequestDAO;
import org.mdubois.freeveggie.dao.api.IRefProductDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.utils.CriteriaUtils;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.api.IProductService;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;

// </editor-fold>
/**
 *
 * @author Mickael Dubois
 */
public class ProductService implements IProductService {

    // <editor-fold defaultstate="collapsed" desc="DAO Resource's">
    /**
     * {@link IProductDAO}
     */
    @Inject
    private IProductDAO productDAO;
    /**
     * {@link IProductCommentDAO}
     */
    @Inject
    private IProductCommentDAO productCommentDAO;
    /**
     * {@link IProductLikeDAO}
     */
    @Inject
    private IProductLikeDAO productLikeDAO;
    /**
     * {@link IProductLikeDAO}
     */
    @Inject
    private IProductRequestDAO productRequestDAO;
    /**
     * {@link IProductPictureDAO}
     */
    @Inject
    private IProductPictureDAO productPictureDAO;
    /**
     * {@link IRefProductDAO}
     */
    @Inject
    private IRefProductDAO refProductDAO;
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
     * {@link IGardenDAO}
     */
    @Inject
    private INotificationDAO notificationDAO;
	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Converter Resource's">
    /**
     * {@link Converter<ProductBO, ProductMsg>}
     */
    @Inject
    private BusinessObjectConverter<ProductBO, ProductMsg> productMsgConverter;
    /**
     * {@link Converter<ProductBO, ProductMsg>}
     */
    @Inject
    private BusinessObjectConverter<ProductBO, UpdateProductMsg> updateProductMsgConverter;
    /**
     * {@link Converter<GardenLikeBO,GardenLikeMsg>}
     */
    @Inject
    private BusinessObjectConverter<ProductLikeBO, ProductLikeMsg> productLikeMsgConverter;
    /**
     * {@link Converter<GardenCommentBO,GardenCommentMsg>}
     */
    @Inject
    private BusinessObjectConverter<ProductCommentBO, ProductCommentMsg> productCommentMsgConverter;
    /**
     * {@link Converter<ProductRequestBO,ProductRequestMsg>}
     */
    @Inject
    private BusinessObjectConverter<ProductRequestBO, ProductRequestMsg> productRequestMsgConverter;

    /**
     * BusinessObjectConverter<ProductPictureBO, PictureMsg>
     */
    @Inject
    private BusinessObjectConverter<ProductPictureBO, PictureMsg> abstractPictureMsgConverter;
    /**
     * {@link Converter<ProductMsg, ProductBO>}
     */
    @Inject
    private Converter<ProductMsg, ProductBO> productBOConverter;
    /**
     * {@link Converter<ProductCommentMsg, ProductCommentBO>}
     */
    @Inject
    private Converter<ProductCommentMsg, ProductCommentBO> productCommentBOConverter;
    /**
     * {@link Converter<ProductLikeMsg, ProductLikeBO>}
     */
    @Inject
    private Converter<ProductLikeMsg, ProductLikeBO> productLikeBOConverter;
    /**
     * {@link Converter<ProductLikeMsg, ProductLikeBO>}
     */
    @Inject
    private Converter<ProductRequestMsg, ProductRequestBO> productRequestBOConverter;
    /**
     * {@link Converter<AbstractPictureMsg, ProductPictureBO>}
     */
    @Inject
    private Converter<PictureMsg, ProductPictureBO> productPictureBOConverter;

        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Contants">
    /**
     * Criteria g like status equal setted
     */
    private static final QueryCriteria<ProductCriteriaColumn> CRITERIA_PRODUCT_STATUS_EQUAL_SETTED = new QueryCriteria<ProductCriteriaColumn>(
            ProductCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
            Status.CREATED);
    /**
     * Criteria product requete status equal setted
     */
    private static final QueryCriteria<ProductRequestCriteriaColumn> CRITERIA_PRODUCT_REQUEST_STATUS_EQUAL_SETTED = new QueryCriteria<ProductRequestCriteriaColumn>(
            ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
            RequestStatus.ACCEPTED);
    /**
     * Criteria product like status equal setted
     */
    private static final QueryCriteria<ProductLikeCriteriaColumn> CRITERIA_PRODUCT_LIKE_STATUS_EQUAL_SETTED = new QueryCriteria<ProductLikeCriteriaColumn>(
            ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
            EvaluationStatus.SETTED);
    /**
     * Criteria product comment status equal to setted
     */
    private static final QueryCriteria<ProductCommentCriteriaColumn> CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_SETTED = new QueryCriteria<ProductCommentCriteriaColumn>(
            ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
            EvaluationStatus.SETTED);

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Action on product method's">
    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(ProductMsg pProductMsg) throws BusinessException {
        RefProductBO refProductBO = refProductDAO.get(pProductMsg
                .getReferenceProduct().getId());
        if (refProductBO == null) {
            throw new BusinessException(
                    "Try to create a product on an inexisting reference product");
        }
        GardenBO gardenBO = gardenDAO.get(pProductMsg.getGarden().getId());
        if (gardenBO != null) {
            ProductBO productBO = productMsgConverter.createNew(pProductMsg);
            productBO.setGarden(gardenBO);
            productBO.setReferenceProduct(refProductBO);
            return productDAO.save(productBO);
        } else {
            throw new BusinessException(
                    "Try to create a product on an inexisting garden");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(UpdateProductMsg pUpdateProductMsg) throws BusinessException {
        ProductBO productBO = productDAO.get(pUpdateProductMsg.getId());

        if (productBO != null) {
            RefProductBO refProductBO = refProductDAO.get(pUpdateProductMsg
                    .getReferenceProduct().getId());
            if (refProductBO == null) {
                throw new BusinessException(
                        "Try to create a product on an inexisting reference product");
            }
            updateProductMsgConverter.update(productBO, pUpdateProductMsg);
            productBO.setReferenceProduct(refProductBO);
            productDAO.update(productBO);
            return true;
        } else {
            throw new BusinessException("Try to update an unknown product");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean blacklist(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.CREATED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.BLACKLISTED);
                productDAO.update(productBO);
            } else if (!Status.BLACKLISTED.equals(productBO.getStatus())) {
                throw new BusinessException(
                        "Can only blacklist a created product");
            }
        } else {
            throw new BusinessException(
                    "Try to blacklikst an inexisting product");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unblacklist(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.BLACKLISTED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.CREATED);
                productDAO.update(productBO);
            } else if (!Status.CREATED.equals(productBO.getStatus())) {
                throw new BusinessException(
                        "Can only unblacklist a blacklisted product");
            }
        } else {
            throw new BusinessException(
                    "Try to unblacklikst an inexisting product");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.CREATED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.DELETED);
                productDAO.update(productBO);
            } else if (!Status.DELETED.equals(productBO.getStatus())) {
                throw new BusinessException("Can only remove a created product");
            }
        } else {
            throw new BusinessException("Try to remove an inexisting product");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivate(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.DELETED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.CREATED);
                productDAO.update(productBO);
            } else if (!Status.CREATED.equals(productBO.getStatus())) {
                throw new BusinessException(
                        "Can only reactivate a delete product");
            }
        } else {
            throw new BusinessException(
                    "Try to reactivate an inexisting product");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archive(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.CREATED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.ARCHIVED);
                productDAO.update(productBO);
            } else if (!Status.ARCHIVED.equals(productBO.getStatus())) {
                throw new BusinessException("Can only archive a create product");
            }
        } else {
            throw new BusinessException("Try to archive an inexisting product");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchive(Long pProductId) throws BusinessException {
        ProductBO productBO = productDAO.get(pProductId);
        if (productBO != null) {
            if (Status.ARCHIVED.equals(productBO.getStatus())) {
                productBO.setStatus(Status.CREATED);
                productDAO.update(productBO);
            } else if (!Status.CREATED.equals(productBO.getStatus())) {
                throw new BusinessException(
                        "Can only unarchive a archived product");
            }
        } else {
            throw new BusinessException(
                    "Try to unarchive an inexisting product");
        }
        return true;
    }

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Get product method's">
    /**
     * {@inheritDoc}
     */
    @Override
    public ProductMsg getProductById(Long id) throws BusinessException {
        ProductBO product = productDAO.get(id);
        if (product != null) {
            return productBOConverter.convert(product);
        } else {
            throw new BusinessException("product with id " + id
                    + " does not exist");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByUser(
            Long pUserId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO.getProductByUser(pUserId,
                getCleanCriteriaProductBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByGarden(
            Long pGardenId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO.getProductByGarden(pGardenId,
                getCleanCriteriaProductBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByCity(
            Integer pRefCityId,
            Integer pProductRefId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO
                .getProductByCityAndRefProduct(pRefCityId, pProductRefId,
                        getCleanCriteriaProductBO(pTechnicalInformation)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByRegion(
            Integer pRefRegionId,
            Integer pProductRefId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO
                .getProductByRegionAndRefProduct(pRefRegionId, pProductRefId,
                        getCleanCriteriaProductBO(pTechnicalInformation)));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByState(
            Integer pRefStateId,
            Integer pProductRefId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO
                .getProductByStateAndRefProduct(pRefStateId, pProductRefId,
                        getCleanCriteriaProductBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductMsg> getProductByCountry(
            Integer pRefCountryId,
            Integer pProductRefId,
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productBOConverter.convert(productDAO
                .getProductByCountryAndRefProduct(pRefCountryId, pProductRefId,
                        getCleanCriteriaProductBO(pTechnicalInformation)));
    }

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Comment method's">
    /**
     * {@inheritDoc}
     */
    @Override
    public Long comment(ProductCommentMsg pProductCommentMsg)
            throws BusinessException {
        PartialUserBO userBO = userPartialDAO.get(pProductCommentMsg
                .getWriter().getId());
        // We controle that the user exit
        if (userBO != null) {
            ProductBO productBO = productDAO.get(pProductCommentMsg
                    .getProduct().getId());
            // We control that the product exist
            if (productBO != null) {
                if (!userBO.equals(productBO.getGarden().getOwner())) {
                    ProductCommentBO productCommentBO = productCommentDAO
                            .getProductCommentByUserAndProduct(userBO.getId(),
                                    productBO.getId());
					// We controle that a comment of this user on this product
                    // doest not exist already
                    if (productCommentBO == null) {
                        productCommentBO = productCommentMsgConverter
                                .createNew(pProductCommentMsg);
                        productCommentBO.setProduct(productBO);
                        productCommentBO.setWriter(userBO);
                        return productCommentDAO.save(productCommentBO);
                    } else {
                        throw new BusinessException(
                                "A comment from this user on this product have already been made");
                    }
                } else {
                    throw new BusinessException(
                            "The owner of a product can comment on it.");
                }
            } else {
                throw new BusinessException(
                        "Asking for product comment for a unknown product");
            }
        } else {
            throw new BusinessException(
                    "Asking for product comment for a unknown user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeComment(Long pProductCommentId)
            throws BusinessException {
        ProductCommentBO productCommentBO = productCommentDAO
                .get(pProductCommentId);
        if (productCommentBO != null) {
            if (productCommentBO.getStatus().equals(EvaluationStatus.SETTED)) {
                productCommentBO.setStatus(EvaluationStatus.REMOVED);
                productCommentDAO.update(productCommentBO);
            } else if (!productCommentBO.getStatus().equals(
                    EvaluationStatus.REMOVED)) {
                throw new BusinessException(
                        "You can only remove setted comment");
            }
        } else {
            throw new BusinessException("Deleting an unknown product comment");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean reactivateComment(Long pProductCommentId)
            throws BusinessException {
        ProductCommentBO productCommentBO = productCommentDAO
                .get(pProductCommentId);
        if (productCommentBO != null) {
            if (productCommentBO.getStatus().equals(EvaluationStatus.REMOVED)) {
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                productCommentDAO.update(productCommentBO);
            } else if (!productCommentBO.getStatus().equals(
                    EvaluationStatus.SETTED)) {
                throw new BusinessException(
                        "You can only reactivate a removed comment");
            }
        } else {
            throw new BusinessException(
                    "Try to reactivate an unknown product comment");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean archiveComment(Long pProductCommentId)
            throws BusinessException {
        ProductCommentBO productCommentBO = productCommentDAO
                .get(pProductCommentId);
        if (productCommentBO != null) {
            if (productCommentBO.getStatus().equals(EvaluationStatus.SETTED)) {
                productCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                productCommentDAO.update(productCommentBO);
            } else if (!productCommentBO.getStatus().equals(
                    EvaluationStatus.ARCHIVED)) {
                throw new BusinessException(
                        "You can only archive setted comment");
            }
        } else {
            throw new BusinessException(
                    "Try to archive an unknown product comment");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean unarchiveComment(Long pProductCommentId)
            throws BusinessException {
        ProductCommentBO productCommentBO = productCommentDAO
                .get(pProductCommentId);
        if (productCommentBO != null) {
            if (productCommentBO.getStatus().equals(EvaluationStatus.ARCHIVED)) {
                productCommentBO.setStatus(EvaluationStatus.SETTED);
                productCommentDAO.update(productCommentBO);
            } else if (productCommentBO.getStatus().equals(
                    EvaluationStatus.REMOVED)) {
                throw new BusinessException(
                        "You can only unarchive archived comment");
            }
        } else {
            throw new BusinessException(
                    "Try to archive an unknown product comment");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCommentMsg> getProductComment(
            Long pProductId,
            TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productCommentBOConverter
                .convert(productCommentDAO
                        .getProductCommentByProduct(
                                pProductId,
                                getCleanCriteriaProductCommentBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCommentMsg> getProductCommentWrite(
            Long pUserWriterId,
            TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productCommentBOConverter
                .convert(productCommentDAO
                        .getProductCommentByWriter(
                                pUserWriterId,
                                getCleanCriteriaProductCommentBO(pTechnicalInformation)));
    }

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Like method's">
    /**
     * {@inheritDoc}
     */
    @Override
    public Long like(ProductLikeMsg pProductLikeMsg) throws BusinessException {

        PartialUserBO userBO = userPartialDAO.get(pProductLikeMsg.getWriter()
                .getId());
        if (userBO != null) {
            ProductBO productBO = productDAO.get(pProductLikeMsg.getProduct()
                    .getId());
            if (productBO != null) {
                if (!userBO.equals(productBO.getGarden().getOwner())) {
                    ProductLikeBO productLikeBO = productLikeDAO
                            .getProductLikeByUserAndProduct(userBO.getId(),
                                    productBO.getId());
                    if (productLikeBO == null) {
                        productLikeBO = productLikeMsgConverter
                                .createNew(pProductLikeMsg);
                        productLikeBO.setProduct(productBO);
                        productLikeBO.setWriter(userBO);
                        return productLikeDAO.save(productLikeBO);
                    } else {
                        throw new BusinessException(
                                "Can't like a product twice");
                    }
                } else {
                    throw new BusinessException(
                            "Can't like on your own product");
                }
            } else {
                throw new BusinessException(
                        "Asking for like on an unknown product");
            }
        } else {
            throw new BusinessException(
                    "Asking for like on a product for a unknown user");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlike(Long pProductLikeId) throws BusinessException {
        ProductLikeBO productLikeBO = productLikeDAO.get(pProductLikeId);
        if (productLikeBO != null) {
            if (!EvaluationStatus.ARCHIVED.equals(productLikeBO.getStatus())) {
                productLikeBO.setStatus(EvaluationStatus.REMOVED);
                productLikeDAO.save(productLikeBO);
            } else {
                throw new BusinessException("Can't remove an archived like");
            }
        } else {
            throw new BusinessException("Asking for an unexisting like");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductLikeMsg> getProductLike(
            Long pProductId,
            TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) {
        return productLikeBOConverter.convert(productLikeDAO
                .getProductLikeByProduct(pProductId,
                        getCleanCriteriaProductLikeBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductLikeMsg> getProductLikeWrite(
            Long pUserWriterId,
            TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) {
        return productLikeBOConverter.convert(productLikeDAO
                .getProductLikeByWriter(pUserWriterId,
                        getCleanCriteriaProductLikeBO(pTechnicalInformation)));
    }

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Product request method's">
    @Override
    public ProductRequestMsg getProductRequestById(Long id) throws BusinessException {
        ProductRequestBO productRequest = productRequestDAO.get(id);
        if (productRequest != null) {
            return productRequestBOConverter.convert(productRequest);
        } else {
            throw new BusinessException("Product request with id " + id
                    + " does not exist");
        }
    }

    @Override
    public Long send(ProductRequestMsg pProductRequestMsg)
            throws BusinessException {
        Long proudctRequestId = null;
        PartialUserBO partialUserBO = userPartialDAO.get(pProductRequestMsg
                .getRequester().getId());
        if (partialUserBO != null) {
            ProductBO productBO = productDAO.get(pProductRequestMsg
                    .getProduct().getId());
            if (productBO != null) {
                ProductRequestBO productRequestBO = productRequestMsgConverter
                        .createNew(pProductRequestMsg);
                productRequestBO.setProduct(productBO);
                productRequestBO.setRequester(partialUserBO);
                proudctRequestId = productRequestDAO.save(productRequestBO);

                notificationDAO.sendProductRequestNotice(productRequestBO);
            } else {
                throw new BusinessException(
                        "Try to send a request for an unknow product");
            }
        } else {
            throw new BusinessException(
                    "Try to send a request with a unknow user");
        }

        return proudctRequestId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(Long pProductRequestMsgId, String pMessage)
            throws BusinessException {
        ProductRequestBO productRequestBO = productRequestDAO
                .get(pProductRequestMsgId);
        if (productRequestBO != null) {
            productRequestBO.setAnswerDate(SystemTime.asDate());
            productRequestBO.setAnswer(pMessage);
            productRequestBO.setStatus(RequestStatus.ACCEPTED);
            productRequestDAO.update(productRequestBO);
        } else {
            throw new BusinessException("Try to accept an unknow request");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refuse(Long pProductRequestMsgId, String pMessage)
            throws BusinessException {
        ProductRequestBO productRequestBO = productRequestDAO
                .get(pProductRequestMsgId);
        if (productRequestBO != null) {
            productRequestBO.setAnswerDate(SystemTime.asDate());
            productRequestBO.setAnswer(pMessage);
            productRequestBO.setStatus(RequestStatus.ACCEPTED);
            productRequestDAO.update(productRequestBO);
        } else {
            throw new BusinessException("Try to accept an unknow request");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRequestMsg> getProductRequestByProduct(
            Long pProductId,
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productRequestBOConverter
                .convert(productRequestDAO
                        .getProductRequestByProduct(
                                pProductId,
                                getCleanCriteriaProductRequestBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRequestMsg> getProductRequestByGarden(
            Long pGardenId,
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productRequestBOConverter
                .convert(productRequestDAO
                        .getProductRequestByGarden(
                                pGardenId,
                                getCleanCriteriaProductRequestBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRequestMsg> getProductRequestReceive(
            Long pUserPartialId,
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productRequestBOConverter
                .convert(productRequestDAO
                        .getProductRequestReceived(
                                pUserPartialId,
                                getCleanCriteriaProductRequestBO(pTechnicalInformation)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductRequestMsg> getProductRequestSend(
            Long pIdUserPartial,
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation)
            throws BusinessException {
        return productRequestBOConverter
                .convert(productRequestDAO
                        .getProductRequestSend(
                                pIdUserPartial,
                                getCleanCriteriaProductRequestBO(pTechnicalInformation)));
    }

	// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Picture method's">
    /**
     * {@inheritDoc}
     */
    @Override
    public Long addPicture(PictureMsg abstractPictureMsg) throws BusinessException {
        ProductBO productBO = productDAO.get(abstractPictureMsg.getObjId());
        if (productBO != null) {
            ProductPictureBO productPictureBO = abstractPictureMsgConverter.createNew(abstractPictureMsg);
            productPictureBO.setProduct(productBO);
            return productPictureDAO.save(productPictureBO);
        } else {
            throw new BusinessException("Try to add a picture to a unknow product");
        }
    }

    @Override
    public PictureMsg getPicture(Long productPictureId) throws BusinessException {
        ProductPictureBO productPictureBO = productPictureDAO.get(productPictureId);
        if (productPictureBO != null) {
            return productPictureBOConverter.convert(productPictureBO);
        } else {
            throw new BusinessException("Try to get a picture with a unknow id");
        }
    }

    @Override
    public List<PictureMsg> getPictureByProduct(Long productId) throws BusinessException {
        List<ProductPictureBO> productPictureBO = productPictureDAO.getProductPictureByProduct(productId);
        if (productPictureBO != null) {
            return productPictureBOConverter.convert(productPictureBO);
        } else {
            throw new BusinessException("Try to get a picture with a unknow id");
        }
    }

    @Override
    public void removePicture(Long productPictureId) throws BusinessException {
        ProductPictureBO productPictureBO = productPictureDAO.get(productPictureId);
        if (productPictureBO != null) {
            productPictureDAO.delete(productPictureBO);
        } else {
            throw new BusinessException("Try to get a picture with a unknow id");
        }
    }

        // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Private method's">
    private TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> getCleanCriteriaProductCommentBO(
            final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) {
        TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> toReturn = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();

        if (pTechnicalInformation != null) {
            toReturn.setCriterias(pTechnicalInformation.getCriterias());
            toReturn.setOrder(pTechnicalInformation.getOrder());
            toReturn.setPagination(pTechnicalInformation.getPagination());
            if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
                    ProductCommentCriteriaColumn.STATUS)) {
                toReturn.addCriteria(CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_SETTED);
            }
        } else {
            toReturn.addCriteria(CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_SETTED);
        }
        return toReturn;
    }

    private TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> getCleanCriteriaProductLikeBO(
            final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) {
        TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> toReturn = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();

        if (pTechnicalInformation != null) {
            toReturn.setCriterias(pTechnicalInformation.getCriterias());
            toReturn.setOrder(pTechnicalInformation.getOrder());
            toReturn.setPagination(pTechnicalInformation.getPagination());
            if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
                    ProductLikeCriteriaColumn.STATUS)) {
                toReturn.addCriteria(CRITERIA_PRODUCT_LIKE_STATUS_EQUAL_SETTED);
            }
        } else {
            toReturn.addCriteria(CRITERIA_PRODUCT_LIKE_STATUS_EQUAL_SETTED);
        }
        return toReturn;
    }

    private TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> getCleanCriteriaProductBO(
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> toReturn = new TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn>();

        if (pTechnicalInformation != null) {
            toReturn.setCriterias(pTechnicalInformation.getCriterias());
            toReturn.setOrder(pTechnicalInformation.getOrder());
            toReturn.setPagination(pTechnicalInformation.getPagination());
            if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
                    ProductCriteriaColumn.STATUS)) {
                toReturn.addCriteria(CRITERIA_PRODUCT_STATUS_EQUAL_SETTED);
            }
        } else {
            toReturn.addCriteria(CRITERIA_PRODUCT_STATUS_EQUAL_SETTED);
        }
        return toReturn;
    }

    private TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> getCleanCriteriaProductRequestBO(
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) {
        TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> toReturn = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        if (pTechnicalInformation != null) {
            toReturn.setCriterias(pTechnicalInformation.getCriterias());
            toReturn.setOrder(pTechnicalInformation.getOrder());
            toReturn.setPagination(pTechnicalInformation.getPagination());
            if (!CriteriaUtils.isCriteriaPresent(toReturn.getCriterias(),
                    ProductRequestCriteriaColumn.STATUS)) {
                toReturn.addCriteria(CRITERIA_PRODUCT_REQUEST_STATUS_EQUAL_SETTED);
            }
        } else {
            toReturn.addCriteria(CRITERIA_PRODUCT_REQUEST_STATUS_EQUAL_SETTED);
        }
        return toReturn;
    }
    // </editor-fold>

}
