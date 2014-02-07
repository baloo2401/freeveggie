package org.mdubois.freeveggie.service.api;

import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 * This class give you the possibility to create an address.
 * @author Mickael Dubois
 */
public interface IAddressService {

    /**
     * Create an address.
     * @param pAddressMsg - The address to create
     * @return The id of the address created
     * @throws BusinessException - In case of any business related error
     */
    Long create(final AddressMsg pAddressMsg) throws BusinessException;

    /**
     * Update an address.
     * @param pAddressId - The id of the address to update
     * @param pAddressMsg - The upgrade
     * @throws BusinessException - In case of any business related error
     */
    void update(final Long pAddressId, final AddressMsg pAddressMsg) throws BusinessException;
    
    /**
     * Get an address by id
     * @param pAddressId - The address id
     * @throws BusinessException - In case of any business related error
     */
    AddressMsg getAddressById(final Long pAddressId) throws BusinessException;
    
    /**
     * Get an user home address by user id
     * @param pUserId - The user id
     * @throws BusinessException - In case of any business related error
     */
    AddressMsg getUserHomeAddressByUserId(final Long pUserId) throws BusinessException;
    
}
