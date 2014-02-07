package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bean.IAddressBean;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.api.IAddressService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.AddressMsg;

// </editor-fold>
/**
 * This class give you the possibility to create an address.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IAddressBean.class)
public class AddressBeanLocal implements IAddressBean {

    /**
     * {@link IAddressService}
     */
    @Inject
    private IAddressService addressService;
    /**
     * {@link ISecurityService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(AddressMsg pAddressMsg) throws BusinessException {
        return addressService.create(pAddressMsg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ContextMsg pContextMsg, Long pAddressId,
            AddressMsg pAddressMsg) throws BusinessException {
        // Todo check this addresss is his
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            addressService.update(pAddressId, pAddressMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressMsg getAddressById(ContextMsg pContextMsg, Long pAddressId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return addressService.getAddressById(pAddressId);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AddressMsg getUserHomeAddressByUserId(final ContextMsg pContextMsg, final Long pUserId)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return addressService.getUserHomeAddressByUserId(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
