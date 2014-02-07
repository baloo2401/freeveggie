package org.mdubois.freeveggie.service.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.msg.ProfileMsg;
// </editor-fold>

/**
 * This is a service class. This class represent all the method that involve {@link ProfilBO}.
 *
 * @author Mickael Dubois
 */
public interface IProfileService {

    /**
     * Update a {@link ProfilBO}.
     * @param pUserId - The user to update
     * @param pProfilMsg - The info to update
     * @return <codeTrue</code> if the update run successfully, <code>False</code> otherwise
     * @throws BusinessException In case of any business issue
     */
    boolean update(final Long pUserId, final ProfileMsg pProfilMsg) throws BusinessException;

    /**
     * Return the {@link ProfilBO} of a given user.
     * @param pUserId - The id of the user which we want the profile from.
     * @return Return the given {@link ProfilBO}, null in case it
     * @throws BusinessException In case of any business issue
     */
    ProfileMsg getProfilById(final Long pUserId) throws BusinessException;

}