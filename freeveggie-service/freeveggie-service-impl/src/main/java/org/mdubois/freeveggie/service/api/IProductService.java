package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;
// </editor-fold>

/**
 * This class is a service class it refer all the action that can involve
 * product like business object.
 *
 * @author Mickael Dubois
 */
public interface IProductService {

    /**
     * Create a new product. And return the database id of the created product.
     *
     * @param pProductMsg - The {@link ProductMsg} to create
     * @return The database id of the created product
     * @throws BusinessException In case of any business issue
     */
    Long create(final ProductMsg pProductMsg) throws BusinessException;

    /**
     * Update all the information of the product. run An update is possible only
     * on product that have status to created.
     *
     * @param pProductId - The product to update.
     * @param pProductMsg - The info to update.
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final UpdateProductMsg pUpdateProductMsg) throws BusinessException;

    /**
     * Blacklist a {@link ProductBO}. run The blacklist will make this
     * {@link ProductBO} not visible to any other user because of trust issues
     * or because to many user complain about the {@link ProductBO}. An update
     * is possible only on product that have status to created.
     *
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean blacklist(final Long pProductId) throws BusinessException;

    /**
     * Unblacklist a {@link ProductBO}. It put the product back to created.
     *
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unblacklist(final Long pProductId) throws BusinessException;

    /**
     * Delete a {@link ProductBO}. Delete is not physical, it's just a status.
     * An update is possible only on product that have status to created.
     *
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean remove(final Long pProductId) throws BusinessException;

    /**
     * Undelete a {@link ProductBO}. Undelete a product is possible only if the
     * product is deleted and it's set back to create status.
     *
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivate(final Long pProductId) throws BusinessException;

    /**
     * Archive a {@link ProductBO}. This operation can be made only on a product
     * that has is status to Created.
     *
     * @param pProductId - The id of the product to update
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archive(final Long pProductId) throws BusinessException;

    /**
     * Unarchive a {@link ProductBO}. Only a owner of the product can make this
     * update.
     *
     * @param pProductId - The id of the product to update
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchive(final Long pProductId) throws BusinessException;

    /**
     * Get a {@link ProductMsg} using the id.
     *
     * @param id - The id of the database to retrieve.
     * @return The {@link ProductMsg} or null if the id doesn't represent a
     * product
     * @throws BusinessException In case of any business issue
     */
    ProductMsg getProductById(final Long id) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a {@link UserPartialBO}. If the technical
     * information doesn't ask for the opposite the service return only product
     * that have their status to CREATED.
     *
     * @param pUserId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByUser(final Long pUserId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a {@link GardenBO}. If the technical
     * information doesn't ask for the opposite the service return only product
     * that have their status to CREATED.
     *
     * @param pGardenId - The given {@link UserGardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByGarden(final Long pGardenId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a {@link RefCityBO} having a given
     * {@link RefProductBO}.If the technical information doesn't ask for the
     * opposite the service return only product that have their status to
     * CREATED.
     *
     * @param pRefCityId - The {@link RefCityBO} city
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByCity(final Integer pRefCityId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pRefRegionId - The given {@link RefRegionBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByRegion(final Integer pRefRegionId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pRefStateId - The given {@link RefStateBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByState(final Integer pRefStateId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductMsg} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pRefCountryId - The given {@link RefCountryBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link ProductMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByCountry(final Integer pRefCountryId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Add a {@link ProductCommentBO} on a {@link ProductBO}.
     *
     * @param pProductCommentMsg - The given {@link ProductCommentMsg}
     * @return The database id of the create comment
     * @throws BusinessException In case of any business issue
     */
    Long comment(final ProductCommentMsg pProductCommentMsg) throws BusinessException;

    /**
     * Delete a {@link ProductCommentBO}. Delete is not physical, it's just a
     * status. An update is possible only on product that have status to
     * created.
     *
     * @param pProductCommentId
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException
     */
    boolean removeComment(final Long pProductCommentId) throws BusinessException;

    /**
     * Undelete a {@link ProductCommentBO}. Only a comment that is set to delete
     * can be undeleted. Reactivate will but the comment to setted.
     *
     * @param pProductCommentId - The comment to undelete
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivateComment(final Long pProductCommentId) throws BusinessException;

    /**
     * Archive a {@link ProductCommentBO}. This operation can be made only on a
     * product that has is status to Created.
     *
     * @param pProductCommentId - The comment to archive
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archiveComment(final Long pProductCommentId) throws BusinessException;

    /**
     * Unarchive a {@link ProductCommentBO}.
     *
     * @param pProductCommentId - The comment to unarchive
     * @return <codeTrue</code> if the update run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchiveComment(final Long pProductCommentId) throws BusinessException;

    /**
     * Get all {@link  ProductCommentMsg} of a write on a given
     * {@link ProductBO}. If the technical information doesn't ask for the
     * opposite the service return only comment that have their status to
     * SETTED.
     *
     * @param pProductId - The given product
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link  ProductCommentMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductCommentMsg> getProductComment(final Long pProductId, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductCommentMsg} of a write by a given
     * {@link UserPartialBO}. If the technical information doesn't ask for the
     * opposite the service return only comment that have their status to
     * SETTED.
     *
     * @param pUserWriterId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link  ProductCommentMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductCommentMsg> getProductCommentWrite(final Long pUserWriterId, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a like tag on a product. If the like already exist the request
     * will be either ignore or will but the like back to setted.
     *
     * @param pProductLikeMsg - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long like(final ProductLikeMsg pProductLikeMsg) throws BusinessException;

    /**
     * Unlike tag on a product. This will just change the status of the like to
     * removed. if the like doesn't exist an exception will be thrown.
     *
     * @param pProductLikeMsg - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    void unlike(final Long pProductLikeId) throws BusinessException;

    /**
     * Get all {@link ProductLikeMsg} of a given {@link ProductBO}.If the
     * technical information doesn't ask for the opposite the service return
     * only like that have their status to SETTED.
     *
     * @param pProductId - The given {@link ProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link  ProductLikeMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductLikeMsg> getProductLike(final Long pProductId, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductLikeMsg} write by a given {@link UserPartialBO}.If
     * the technical information doesn't ask for the opposite the service return
     * only like that have their status to SETTED.
     *
     * @param pUserWriterId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link  ProductLikeMsg}
     * @throws BusinessException In case of any business issue
     */
    List<ProductLikeMsg> getProductLikeWrite(final Long pUserWriterId, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a {@link ProductRequestMsg}
     *
     * @param pProductRequestMsg - The {@link ProductRequestMsg} to create
     * @return - The id of the {@link ProductRequestMsg} created
     * @throws BusinessException In case of any business issue
     */
    Long send(final ProductRequestMsg pProductRequestMsg) throws BusinessException;

    /**
     * Accept a pending {@link ProductRequestMsg}
     *
     * @param pProductRequestMsgId - The id of the {@link ProductRequestMsg} to
     * accept
     * @param pMessage - The answering message
     * @throws BusinessException In case of any business issue
     */
    void accept(final Long pProductRequestMsgId, @Length(min = 10, max = 512) final String pMessage) throws BusinessException;

    /**
     * Refuse a pending {@link ProductRequestMsg}
     *
     * @param pProductRequestMsgId - The id of the {@link ProductRequestMsg} to
     * refuse
     * @param pMessage - The answering message
     * @throws BusinessException In case of any business issue
     */
    void refuse(final Long pProductRequestMsgId, @Length(min = 10, max = 512) final String pMessage) throws BusinessException;

    /**
     * Get a {@link ProductRequestMsg} using the id.
     *
     * @param id - The id of the database to retrieve.
     * @return The {@link ProductRequestMsg} or null if the id doesn't represent
     * a product
     * @throws BusinessException In case of any business issue
     */
    ProductRequestMsg getProductRequestById(final Long id) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} of a given {@link ProductBO}.
     *
     * @param pProductId - The given {@link ProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestByProduct(final Long pProductId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} made on all {@link ProductBO} of a given
     * {@link GardenBO}.
     *
     * @param pGardenId - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestByGarden(final Long pGardenId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} receive by a given
     * {@link UserPartialBO}.
     *
     * @param pUserPartialId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestReceive(final Long pUserPartialId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} send by a given {@link UserPartialBO}.
     *
     * @param pUserPartialId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestSend(final Long pUserPartialId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Add a picture to a product
     *
     * @param abstractPictureMsg - The product picture to add
     * @return The picture id
     * @throws BusinessException In case of any business issue
     */
    Long addPicture(PictureMsg abstractPictureMsg) throws BusinessException;

    /**
     * Get a picture to a product
     *
     * @param productPictureId - The product picture id to get
     * @throws BusinessException In case of any business issue
     */
    PictureMsg getPicture(Long productPictureId) throws BusinessException;

    /**
     * Get all picture off a product
     *
     * @param productId - The product id to look for
     * @throws BusinessException In case of any business issue
     */
    List<PictureMsg> getPictureByProduct(Long productId) throws BusinessException;

    /**
     * Remove a picture to a product
     *
     * @param productPictureId - The product picture id to remove
     * @throws BusinessException In case of any business issue
     */
    void removePicture(Long productPictureId) throws BusinessException;
}
