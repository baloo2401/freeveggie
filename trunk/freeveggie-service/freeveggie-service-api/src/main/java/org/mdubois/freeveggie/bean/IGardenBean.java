package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.SearchMsg;
// </editor-fold>

/**
 * This class is a service class it refer all the action that can involve garden.
 * @author Mickael Dubois
 */
public interface IGardenBean {

    /**
     * Create a new garden. And return the database id of the created garden.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenMsg - The {@link GardenMsg} to create
     * @return The database id of the created garden
     * @throws BusinessException In case of any business issue
     */
    Long create(final ContextMsg pContextMsg, final GardenMsg pGardenMsg) throws BusinessException;

    /**
     * Update all the information of the garden. Only the owner of the garden or a super administrator can update a garden.
     * An update is possible only on garden that have status to created.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenMsg - The info to update.
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final ContextMsg pContextMsg,final GardenMsg pGardenMsg) throws BusinessException;

    /**
     * Blacklist a {@link GardenBO}. Only a super administrator or a system user can blacklist a garden.
     * The blacklist will make this {@link GardenBO} not visible to any other user because of
     * trust issues or because to many user complain about the {@link GardenBO}. An update is possible only on garden that have status
     * to created.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean blacklist(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Unblacklist a {@link GardenBO}. Only a super administrator or a system user can unblacklist a garden.
     * It put the garden back to created.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unblacklist(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Delete a {@link GardenBO}. Only the owner of a garden or a super administrator can delete a garden.
     * Delete is not physical, it's just a status. An update is possible only on garden that have status
     * to created.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean remove(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Undelete a {@link GardenBO}. Only the owner of a garden or a super administrator can uddelete a garden.
     * Undelete a garden is possible only if the garden is deleted and it's set back to create status.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivate(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Archive a {@link GardenBO}. Only a system user can archive a garden. This operation can be made only on a
     * garden that has is status to Created.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The id of the garden to update
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archive(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Unarchive a {@link GardenBO}. Only a owner of the garden can make this update.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The id of the garden to update
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchive(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;

    /**
     * Get a {@link GardenBO} using the id.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param gardenId - The id of the database to retrive.
     * @return The {@link GardenBO} or null if the id doest represent a garden
     * @throws BusinessException In case of any business issue
     */
    GardenMsg getGardenById(final ContextMsg pContextMsg, final Long gardenId) throws BusinessException;

    /**
     * Get all {@link GardenBO} of a {@link UserBO}. If the technical information doesn't ask for the opposite
     * the service return only garden that have their status to CREATED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByUser(final ContextMsg pContextMsg, final Long pUserId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenBO} of a {@link RefCityBO} having a given {@link RefProductBO}.If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCityId - The {@link RefCityBO} city
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByCity(final ContextMsg pContextMsg, final Integer pRefCityId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenBO} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefRegionId - The given {@link RefRegionBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByRegion(final ContextMsg pContextMsg, final Integer pRefRegionId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenBO} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefStateId - The given {@link RefStateBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByState(final ContextMsg pContextMsg, final Integer pRefStateId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenBO} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pRefCountryId - The given {@link RefCountryBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByCountry(final ContextMsg pContextMsg, final Integer pRefCountryId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all  {@link GardenLikeBO} on a the given bounds
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pSearchMsg - The {@link SearchMsg}
     * @return A  {@link List} of {@link {@link GardenLikeBO}
     * @throws BusinessException In case of any business issue
     */
    public List<GardenMsg> searchGarden(ContextMsg pContextMsg, SearchMsg pSearchMsg) throws BusinessException;
    
    /**
     * Add a {@link GardenCommentBO} on a {@link GardenBO}.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenCommentMsg
     * @return The database id of the create comment
     * @throws BusinessException In case of any business issue
     */
    Long comment(final ContextMsg pContextMsg, final GardenCommentMsg pGardenCommentMsg) throws BusinessException;

    /**
     * Delete a {@link GardenCommentBO}. Only the writer of the comment or a super administrator can delete a garden.
     * Delete is not physical, it's just a status. An update is possible only on garden that have status
     * to created.
     * @param pContextMsg
     * @param pGardenCommentId
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException
     */
    boolean removeComment(final ContextMsg pContextMsg, final Long pGardenCommentId) throws BusinessException;

    /**
     * Undelete a {@link GardenCommentBO}. Only the writer of the comment or a super administrator can undelete a comment.
     * Only a comment that is set to delete can be undeleted. Reactivate will but the comment to setted.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenCommentId - The comment to undelete
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivateComment(final ContextMsg pContextMsg, final Long pGardenCommentId) throws BusinessException;

    /**
     * Archive a {@link GardenCommentBO}. Only a system user can archive a garden. This operation can be made only on a
     * garden that has is status to Created.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenCommentId - The comment to archive
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archiveComment(final ContextMsg pContextMsg, final Long pGardenCommentId) throws BusinessException;

    /**
     * Unarchive a {@link GardenCommentBO}. Only a owner of the garden can make this update.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenCommentId - The comment to unarchive
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchiveComment(final ContextMsg pContextMsg, final Long pGardenCommentId) throws BusinessException;

    /**
     * Get all {@link {@link GardenBO} of a write on a given {@link GardenBO}. If the technical information doesn't
     * ask for the opposite the service return only comment that have their status to SETTED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given garden
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenCommentBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenCommentMsg> getGardenComment(final ContextMsg pContextMsg, final Long pGardenId, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenCommentBO} of a write by a given {@link UserBO}. If the technical information doesn't
     * ask for the opposite the service return only comment that have their status to SETTED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserWriterId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     *  @return A  {@link List} of {@link {@link GardenCommentBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenCommentMsg> getGardenCommentWrite(final ContextMsg pContextMsg, final Long pUserWriterId, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a like tag on a garden. If the like already exist the request will be either ignore or will but the like back to setted.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenLikeMsg - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long like(final ContextMsg pContextMsg, final GardenLikeMsg pGardenLikeMsg) throws BusinessException;

    /**
     * Unlike tag on a garden. This will just change the status of the like to removed. if the like doest exist an exception will be thrown.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenLikeId - The like
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long unlike(final ContextMsg pContextMsg, final Long pGardenLikeId) throws BusinessException;

    /**
     * Get all {@link GardenLikeBO} of a given {@link GardenBO}.If the technical information doesn't
     * ask for the opposite the service return only like that have their status to SETTED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenLikeBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenLikeMsg> getGardenLike(final ContextMsg pContextMsg, final Long pGardenId, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenLikeBO} write by a given {@link UserBO}.If the technical information doesn't
     * ask for the opposite the service return only like that have their status to SETTED.
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserWriterId - The given {@link UserBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenLikeBO}
     * @throws BusinessException In case of any business issue
     */
    List<GardenLikeMsg> getGardenLikeWrite(final ContextMsg pContextMsg, final Long pUserWriterId, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Add a picture to a garden
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pAbstractPictureMsg - The garden picture to add
     * @throws BusinessException In case of any business issue
     */
    Long addPicture(final ContextMsg pContextMsg, final PictureMsg pAbstractPictureMsg) throws BusinessException;
    /**
     * Get a picture to a garden
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenPictureId - The garden picture id to get
     * @throws BusinessException In case of any business issue
     */
    PictureMsg getPicture(final ContextMsg pContextMsg, final Long pGardenPictureId) throws BusinessException;
    /**
     * Get all picture off a garden
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenId - The garden id to look for
     * @throws BusinessException In case of any business issue
     */
    List<PictureMsg> getPictureByGarden(final ContextMsg pContextMsg, final Long pGardenId) throws BusinessException;
    /**
     * Remove a picture to a garden
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pGardenPictureId - The garden picture id to remove
     * @throws BusinessException In case of any business issue
     */
    void removePicture(final ContextMsg pContextMsg, final Long pGardenPictureId) throws BusinessException;
}
