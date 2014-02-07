/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.api;

import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.UserRole;

/**
 *
 * @author mdubois
 */
public interface IRightControlerService {

    /**
     * Return true if the given user have one of the role asked
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pUserRole - Te liste of {@link UserRole} to test
     * @return True if the user have one of the role
     * @throws BusinessException If the user doesn't exist, False otherwise
     */
    boolean isUserInRole(final Long pIdUser, final UserRole... pUserRole) throws BusinessException;

    /**
     * Return true if the user is the owner of the garden
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdGarden - The {@link GardenBO} id to test.
     * @return True if the user is the owner of the garden, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerGarden(final Long pIdUser, final Long pIdGarden) throws BusinessException;

    /**
     * Return true if the garden comment have been made by the given user.
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdGardenComment - The {@link GardenCommentBO} id to test.
     * @return True if the user is the writer of the comment, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerGardenComment(final Long pIdUser, final Long pIdGardenComment) throws BusinessException;

    /**
     * Return true if the product like was write by the given user
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdGardenLike - The {@link GardenLikeBO} id to test.
     * @return True if the user is the writer of the product like, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerGardenLike(final Long pIdUser, final Long pIdGardenLike) throws BusinessException;

    /**
     * Return true if the product is own by the given user
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdProduct - The {@link ProductBO} id to test.
     * @return True if the user is the owner of the product, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerProduct(final Long pIdUser, final Long pIdProduct) throws BusinessException;

    /**
     * Return true if the product comment was write by the given user
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdProductComment - The {@link ProductCommentBO} id to test.
     * @return True if the user is the writer of the product comment, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerProductComment(final Long pIdUser, final Long pIdProductComment) throws BusinessException;

    /**
     * Return true if the product like was write by the given user
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdProductLike - The {@link ProductLikeBO} id to test.
     * @return True if the user is the writer of the product like, False otherwise
     * @throws BusinessException
     */
    boolean isUserOwnerProductLike(final Long pIdUser, final Long pIdProductLike);

    /**
     * Return true if the relationship as been send to this user.
     * @param pIdUser - The {@link PartialUserBO} id to test.
     * @param pIdProductLike - The {@link RelationShipBO} id to test.
     * @return True if the given user is the recipient of the relationship, False otherwise
     */
    boolean isUserOwnerRelationship(Long pUserId, Long pRelationShipId);

    /**
     * Return true if the picture from a product is own by the user, False otherwise
     * @param pUserId - The user to check
     * @param pGardenPictureId - The picture to check
     * @return True if the picture is own by the user, False otherwise
     */
    boolean isUserOwnerProductPicture(Long pUserId, Long pProductPictureId);

    /**
     * Return true if the picture from a garden is own by the user, False otherwise
     * @param pUserId - The user to check
     * @param pGardenPictureId - The picture to check
     * @return True if the picture is own by the user, False otherwise
     */
    boolean isUserOwnerGardenPicture(Long pUserId, Long pProductPictureId);

}
