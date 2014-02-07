package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.msg.AddressMsg;
// </editor-fold>

/**
 * This class give you the possibility to create an address.
 *
 * @author Mickael Dubois
 */
public interface IAddressBean {

    /**
     * Create an address.
     *
     * @param pAddressMsg - The address to create
     * @return The id of the address created
     * @throws BusinessException - In case of any business related error
     */
    Long create(final AddressMsg pAddressMsg) throws BusinessException;

    /**
     * Update an address.
     *
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pAddressMsg - The upgrade
     * @throws BusinessException - In case of any business related error
     */
    void update(final ContextMsg pContextMsg, final Long pAddressId, final AddressMsg pAddressMsg) throws BusinessException;
    
    /**
     * Get an address by id
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pAddressId - The address id
     * @throws BusinessException - In case of any business related error
     */
    AddressMsg getAddressById(final ContextMsg pContextMsg, final Long pAddressId) throws BusinessException;
    
    /**
     * Get an user home address by the user home address
     * @param pContextMsg - The {@link ContextMsg} of the service call
     * @param pUserId - The user id
     * @throws BusinessException - In case of any business related error
     */
    AddressMsg getUserHomeAddressByUserId(final ContextMsg pContextMsg, final Long pUserId) throws BusinessException;
}
