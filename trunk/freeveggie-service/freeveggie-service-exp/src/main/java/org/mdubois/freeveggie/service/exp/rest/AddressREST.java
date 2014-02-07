package org.mdubois.freeveggie.service.exp.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.mdubois.freeveggie.bean.IAddressBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.IdMsg;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 *
 * @author Mickael
 */
@Path("address")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class AddressREST extends FreeveggieREST {

    /**
     * {@link IAddressBean}
     */
    @EJB
    private IAddressBean addressBean;

    @GET
    @Path("{AddressId}")
    @Produces({"application/json"})
    public AddressMsg getAddressById(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("AddressId") Long pAddressId) {
        try {
                ContextMsg context = createContext(pContextUserId);
            
            return addressBean.getAddressById(context, pAddressId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public IdMsg addAddress(AddressMsg pAddressMsg) {
        try {
            return new IdMsg(addressBean.create(pAddressMsg));
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @PUT
    @Consumes({"application/json"})
    public void updateAddress(AddressMsg pAddressMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            addressBean.update(context, pAddressMsg.getId(), pAddressMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("user/{userId}")
    @Produces({"application/json"})
    public AddressMsg getUserHomeAddressByUserId(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pAddressId) {
        try {
                ContextMsg context = createContext(pContextUserId);
            
            return addressBean.getUserHomeAddressByUserId(context, pAddressId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
