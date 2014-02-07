package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
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
public interface IGardenService {

    /**
     * Create a new garden. And return the database id of the created garden.
     * @param pGardenMsg - The {@link GardenMsg} to create
     * @return The database id of the created garden
     * @throws BusinessException In case of any business issue
     */
    Long create(final GardenMsg pGardenMsg) throws BusinessException;

    /**
     * Update all the information of the garden.
     * An update is possible only on garden that have status to created.
     * @param pGardenMsg - The info to update.
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final GardenMsg pGardenMsg) throws BusinessException;

    /**
     * Blacklist a {@link GardenBO}.
     * The blacklist will make this {@link GardenBO} not visible to any other user because of
     * trust issues or because to many user complain about the {@link GardenBO}. An update is possible only on garden that have status
     * to created.
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean blacklist(final Long pGardenId) throws BusinessException;

    /**
     * Unblacklist a {@link GardenBO}.
     * It put the garden back to created.
     *
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unblacklist(final Long pGardenId) throws BusinessException;

    /**
     * Delete a {@link GardenBO}.
     * Delete is not physical, it's just a status. An update is possible only on garden that have status
     * to created.
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean remove(final Long pGardenId) throws BusinessException;

    /**
     * Undelete a {@link GardenBO}.
     * Undelete a garden is possible only if the garden is deleted and it's set back to create status.
     * @param pGardenId - The garden to blacklist
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivate(final Long pGardenId) throws BusinessException;

    /**
     * Archive a {@link GardenBO}.
     * garden that has is status to Created.
     * @param pGardenId - The id of the garden to update
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archive(final Long pGardenId) throws BusinessException;

    /**
     * Unarchive a {@link GardenBO}.
     * @param pGardenId - The id of the garden to update
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchive(final Long pGardenId) throws BusinessException;

    /**
     * Get a {@link GardenMsg} using the id.
     * @param pGardenId - The id of the database to retrieve.
     * @return The {@link GardenMsg} or null if the id doesn't represent a garden
     * @throws BusinessException In case of any business issue
     */
    GardenMsg getGardenById(final Long pGardenId) throws BusinessException;

    /**
     * Get all {@link GardenMsg} of a {@link UserPartialBO}. If the technical information doesn't ask for the opposite
     * the service return only garden that have their status to CREATED.
     * @param pUserId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByUser(final Long pUserId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenMsg} of a {@link RefCityBO} having a given {@link RefProductBO}.If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pRefCityId - The {@link RefCityBO} city
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByCity(final Integer pRefCityId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenMsg} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pRefRegionId - The given {@link RefRegionBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByRegion(final Integer pRefRegionId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenMsg} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.

     * @param pRefStateId - The given {@link RefStateBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByState(final Integer pRefStateId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenMsg} of a region having a given product. If the technical information doesn't
     * ask for the opposite the service return only garden that have their status to CREATED.
     * @param pRefCountryId - The given {@link RefCountryBO}
     * @param pProductRefId - The given {@link RefProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> getGardenByCountry(final Integer pRefCountryId, final Integer pProductRefId, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all  {@link GardenMsg} on a 50 kilometer radius of a position?
     * @param pSearchMsg - {@link The SearchMsg}
     * @return A  {@link List} of {@link GardenMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenMsg> searchGarden(SearchMsg pSearchMsg) throws BusinessException;

    /**
     * Add a {@link GardenCommentBO} on a {@link GardenBO}.
     * @param pGardenCommentMsg - - The given {@link GardenCommentMsg}
     * @return The database id of the create comment
     * @throws BusinessException In case of any business issue
     */
    Long comment(final GardenCommentMsg pGardenCommentMsg) throws BusinessException;

    /**
     * Delete a {@link GardenCommentBO}. Only the writer of the comment or a super administrator can delete a garden.
     * Delete is not physical, it's just a status. An update is possible only on garden that have status
     * to created.
     * @param pContextMsg
     * @param pGardenCommentId
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException
     */
    boolean removeComment(final Long pGardenCommentId) throws BusinessException;

    /**
     * Undelete a {@link GardenCommentBO}. Only the writer of the comment or a super administrator can undelete a comment.
     * Only a comment that is set to delete can be undeleted. Reactivate will but the comment to setted.
     * @param pGardenCommentId - The comment to undelete
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean reactivateComment(final Long pGardenCommentId) throws BusinessException;

    /**
     * Archive a {@link GardenCommentBO}. Only a system user can archive a garden. This operation can be made only on a
     * garden that has is status to Created.
     * @param pGardenCommentId - The comment to archive
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean archiveComment(final Long pGardenCommentId) throws BusinessException;

    /**
     * Unarchive a {@link GardenCommentBO}.
     * @param pGardenCommentId - The comment to unarchive
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean unarchiveComment(final Long pGardenCommentId) throws BusinessException;

    /**
     * Get all {@link GardenCommentMsg} of a write on a given {@link GardenBO}. If the technical information doesn't
     * ask for the opposite the service return only comment that have their status to SETTED.
     * @param pGardenId - The given garden
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenCommentMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenCommentMsg> getGardenComment(final Long pGardenId, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenCommentMsg} of a write by a given {@link UserPartialBO}. If the technical information doesn't
     * ask for the opposite the service return only comment that have their status to SETTED.
     * @param pUserWriterId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     *  @return A  {@link List} of {@link GardenCommentMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenCommentMsg> getGardenCommentWrite(final Long pUserWriterId, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Create a like tag on a garden. If the like already exist the request will be either ignore or will but the like back to setted.
     * @param pGardenLikeMsg - The like informations
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long like(final GardenLikeMsg pGardenLikeMsg) throws BusinessException;

    /**
     * Unlike tag on a garden. This will just change the status of the like to removed. if the like doesn't exist an exception will be thrown.
     * @param pGardenLikeId - The like
     * @return The database id of the like.
     * @throws BusinessException In case of any business issue
     */
    Long unlike(final Long pGardenLikeId) throws BusinessException;

    /**
     * Get all {@link GardenLikeMsg} of a given {@link GardenBO}.If the technical information doesn't
     * ask for the opposite the service return only like that have their status to SETTED.
     * @param pGardenId - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenLikeMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenLikeMsg> getGardenLike(final Long pGardenId, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) throws BusinessException;

    /**
     * Get all {@link GardenLikeMsg} write by a given {@link UserPartialBO}.If the technical information doesn't
     * ask for the opposite the service return only like that have their status to SETTED.
     * @param pUserWriterId - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link GardenLikeMsg}
     * @throws BusinessException In case of any business issue
     */
    List<GardenLikeMsg> getGardenLikeWrite(final Long pUserWriterId, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) throws BusinessException;
    
    /**
     * Add a picture to a garden
     * @param abstractPictureMsg - The garden picture to add
     * @throws BusinessException In case of any business issue
     */
    Long addPicture(PictureMsg abstractPictureMsg) throws BusinessException;
    /**
     * Get a picture to a garden
     * @param gardenPictureId - The garden picture id to get
     * @throws BusinessException In case of any business issue
     */
    PictureMsg getPicture(Long gardenPictureId) throws BusinessException;
    /**
     * Get all picture off a garden
     * @param gardenId - The garden id to look for
     * @throws BusinessException In case of any business issue
     */
    List<PictureMsg> getPictureByGarden(Long gardenId) throws BusinessException;
    /**
     * Remove a picture to a garden
     * @param gardenPictureId - The garden picture id to remove
     * @throws BusinessException In case of any business issue
     */
    void removePicture(Long gardenPictureId) throws BusinessException;
}
