package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.security.ContextMsg;
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
 * product.
 *
 * @author Mickael Dubois
 */
public interface IProductBean {

    /**
     * Create a new product. And return the database id of the created product.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductMsg - The {@link ProductMsg} to create
     * @return The database id of the created product
     * @throws BusinessException In case of any business issue
     */
    Long create(final ContextMsg pContextMsg, final ProductMsg pProductMsg) throws BusinessException;

    /**
     * Update all the information of the product. Only the owner of the product
     * or a super administrator can update a product. An update is possible only
     * on product that have status to created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The product to update.
     * @param pUpdateProductMsg - The info to update.
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final ContextMsg pContextMsg, final UpdateProductMsg pUpdateProductMsg) throws BusinessException;

    /**
     * Blacklist a {@link ProductBO}. Only a super administrator or a system
     * user can blacklist a product. The blacklist will make this
     * {@link ProductBO} not visible to any other user because of trust issues
     * or because to many user complain about the {@link ProductBO}. An update
     * is possible only on product that have status to created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean blacklist(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Unblacklist a {@link ProductBO}. Only a super administrator or a system
     * user can unblacklist a product. It put the product back to created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unblacklist(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Delete a {@link ProductBO}. Only the owner of a product or a super
     * administrator can delete a product. Delete is not physical, it's just a
     * status. An update is possible only on product that have status to
     * created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean remove(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Undelete a {@link ProductBO}. Only the owner of a product or a super
     * administrator can undelete a product. Undelete a product is possible only
     * if the product is deleted and it's set back to create status.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The product to blacklist
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivate(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Archive a {@link ProductBO}. Only a system user can archive a product.
     * This operation can be made only on a product that has is status to
     * Created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The id of the product to update
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archive(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Unarchive a {@link ProductBO}. Only a owner of the product can make this
     * update.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The id of the product to update
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchive(final ContextMsg pContextMsg, final Long pProductId) throws BusinessException;

    /**
     * Get a {@link ProductBO} using the id.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param id - The id of the database to retrive.
     * @return The {@link ProductBO} or null if the id doesn't represent a
     * product
     * @throws BusinessException In case of any business issue
     */
    ProductMsg getProductById(final ContextMsg pContextMsg, final Long id) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a {@link UserBO}. If the technical
     * information doesn't ask for the opposite the service return only product
     * that have their status to CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByUser(final ContextMsg pContextMsg, final Long pUserId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a {@link GardenBO}. If the technical
     * information doesn't ask for the opposite the service return only product
     * that have their status to CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByGarden(final ContextMsg pContextMsg, final Long pGardenId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a {@link RefCityBO} having a given
     * {@link RefProductBO}.If the technical information doesn't ask for the
     * opposite the service return only product that have their status to
     * CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityId - The {@link RefCityBO} city
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByCity(final ContextMsg pContextMsg, final Integer pRefCityId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionId - The given {@link RefRegionBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByRegion(final ContextMsg pContextMsg, final Integer pRefRegionId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefStateId - The given {@link RefStateBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByState(final ContextMsg pContextMsg, final Integer pRefStateId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductBO} of a region having a given product. If the
     * technical information doesn't ask for the opposite the service return
     * only product that have their status to CREATED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCountryId - The given {@link RefCountryBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductMsg> getProductByCountry(final ContextMsg pContextMsg, final Integer pRefCountryId, final Integer pProductRefId, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Add a {@link ProductCommentBO} on a {@link ProductBO}.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductCommentMsg
     * @return The database id of the create comment
     * @throws BusinessException In case of any business issue
     */
    Long comment(final ContextMsg pContextMsg, final ProductCommentMsg pProductCommentMsg) throws BusinessException;

    /**
     * Delete a {@link ProductCommentBO}. Only the writer of the comment or a
     * super administrator can delete a product. Delete is not physical, it's
     * just a status. An update is possible only on product that have status to
     * created.
     *
     * @param pContextMsg
     * @param pProductCommentId
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException
     */
    boolean removeComment(final ContextMsg pContextMsg, final Long pProductCommentId) throws BusinessException;

    /**
     * Undelete a {@link ProductCommentBO}. Only the writer of the comment or a
     * super administrator can undelete a comment. Only a comment that is set to
     * delete can be undeleted. Reactivate will but the comment to setted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductCommentId - The comment to undelete
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivateComment(final ContextMsg pContextMsg, final Long pProductCommentId) throws BusinessException;

    /**
     * Archive a {@link ProductCommentBO}. Only a system user can archive a
     * product. This operation can be made only on a product that has is status
     * to Created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductCommentId - The comment to archive
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archiveComment(final ContextMsg pContextMsg, final Long pProductCommentId) throws BusinessException;

    /**
     * Unarchive a {@link ProductCommentBO}. Only a owner of the product can
     * make this update.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductCommentId - The comment to unarchive
     * @return <codeTrue</code> if the update did run successfully,
     * <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchiveComment(final ContextMsg pContextMsg, final Long pProductCommentId) throws BusinessException;

    /**
     * Get all {@link {@link ProductBO} of a write on a given {@link ProductBO}. If the technical information doesn't
     * ask for the opposite the service return only comment that have their status to SETTED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The given product
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductCommentBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductCommentMsg> getProductComment(final ContextMsg pContextMsg, final Long pProductId, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductCommentBO} of a write by a given {@link UserBO}. If
     * the technical information doesn't ask for the opposite the service return
     * only comment that have their status to SETTED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserWriterId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductCommentBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductCommentMsg> getProductCommentWrite(final ContextMsg pContextMsg, final Long pUserWriterId, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a like tag on a product. If the like already exist the request
     * will be either ignore or will but the like back to setted.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductLikeMsg - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long like(final ContextMsg pContextMsg, final ProductLikeMsg pProductLikeMsg) throws BusinessException;

    /**
     * Unlike tag on a product. This will just change the status of the like to
     * removed. if the like doest exist an exception will be thrown.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductLikeId - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    void unlike(final ContextMsg pContextMsg, final Long pProductLikeId) throws BusinessException;

    /**
     * Get all {@link ProductLikeBO} of a given {@link ProductBO}.If the
     * technical information doesn't ask for the opposite the service return
     * only like that have their status to SETTED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pProductId - The given {@link ProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductLikeBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductLikeMsg> getProductLike(final ContextMsg pContextMsg, final Long pProductId, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductLikeBO} write by a given {@link UserBO}.If the
     * technical information doesn't ask for the opposite the service return
     * only like that have their status to SETTED.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserWriterId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductLikeBO}
     * @throws BusinessException In case of any business issue
     */
    List<ProductLikeMsg> getProductLikeWrite(final ContextMsg pContextMsg, final Long pUserWriterId, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a {@link ProductRequestMsg}
     *
     * @param pProductRequestMsg - The {@link ProductRequestMsg} to create
     * @return - The id of the {@link ProductRequestMsg} created
     * @throws BusinessException In case of any business issue
     */
    Long send(final ContextMsg pContextMsg, final ProductRequestMsg pProductRequestMsg) throws BusinessException;

    /**
     * Accept a pending {@link ProductRequestMsg}
     *
     * @param pProductRequestMsgId - The id of the {@link ProductRequestMsg} to
     * accept
     * @param pMessage - The answering message
     * @throws BusinessException In case of any business issue
     */
    void accept(final ContextMsg pContextMsg, final Long pProductRequestMsgId, @Length(min = 10, max = 512) final String pMessage) throws BusinessException;

    /**
     * Refuse a pending {@link ProductRequestMsg}
     *
     * @param pProductRequestMsgId - The id of the {@link ProductRequestMsg} to
     * refuse
     * @param pMessage - The answering message
     * @throws BusinessException In case of any business issue
     */
    void refuse(final ContextMsg pContextMsg, final Long pProductRequestMsgId, @Length(min = 10, max = 512) final String pMessage) throws BusinessException;

    /**
     * Get a {@link ProductRequestMsg} using the id.
     *
     * @param pContextMsg - The user context of the method call
     * @param id - The id of the database to retrieve.
     * @return The {@link ProductRequestMsg} or null if the id doesn't represent
     * a product
     * @throws BusinessException In case of any business issue
     */
    ProductRequestMsg getProductRequestById(final ContextMsg pContextMsg, final Long id) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} of a given {@link ProductBO}.
     *
     * @param pProductId - The given {@link ProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestByProduct(final ContextMsg pContextMsg, final Long pProductId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} made on all {@link ProductBO} of a given
     * {@link GardenBO}.
     *
     * @param pGardenId - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestByGarden(final ContextMsg pContextMsg, final Long pGardenId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} receive by a given
     * {@link UserPartialBO}.
     *
     * @param pUserPartialId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestReceive(final ContextMsg pContextMsg, final Long pUserPartialId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link ProductRequestBO} send by a given {@link UserPartialBO}.
     *
     * @param pUserPartialId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestMsg> getProductRequestSend(final ContextMsg pContextMsg, final Long pUserPartialId, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Add a picture to a product
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pictureMsg - The product picture to add
     * @return The image id
     * @throws BusinessException In case of any business issue
     */
    Long addPicture(final ContextMsg pContextMsg, final PictureMsg pictureMsg) throws BusinessException;

    /**
     * Get a picture to a product
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param productPictureId - The product picture id to get
     * @throws BusinessException In case of any business issue
     */
    PictureMsg getPicture(final ContextMsg pContextMsg, final Long productPictureId) throws BusinessException;

    /**
     * Get all picture off a product
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param productId - The product id to look for
     * @throws BusinessException In case of any business issue
     */
    List<PictureMsg> getPictureByProduct(final ContextMsg pContextMsg, final Long productId) throws BusinessException;

    /**
     * Remove a picture to a product
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param productPictureId - The product picture id to remove
     * @throws BusinessException In case of any business issue
     */
    void removePicture(final ContextMsg pContextMsg, final Long productPictureId) throws BusinessException;
}
