package org.mdubois.freeveggie.service.api;

import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;

/**
 *
 * @author Mickael Dubois
 */
public interface ISubscriptionService {

    /**
     * Create a new user.
     * @param pUserMsg - The user to create
     * @return The database id of the user
     * @throws BusinessException 
     */
    Long create(final CreateAccountMsg pCreateAccountrMsg) throws BusinessException;
}
