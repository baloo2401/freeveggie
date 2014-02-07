package org.mdubois.freeveggie.service.exp.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.mdubois.freeveggie.bean.IProfilBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.ProfileMsg;

/**
 *
 * @author Mickael
 */
@Path("profil")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class ProfilREST extends FreeveggieREST {

    /**
     * {@link IProfilBean}
     */
    @EJB
    private IProfilBean profilBean;
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ProfileMsg getProfilById(
            @PathParam("id") final Long profilId,
            @HeaderParam("userId") final Long pContextProfilId) throws BusinessException {
        ContextMsg context = createContext(pContextProfilId);
        return profilBean.getProfilById(context, profilId);
    }
    
    @PUT
    @Consumes({"application/json"})
    public void updateProfil(ProfileMsg pProfilMsg, 
        @HeaderParam("userId") Long pContextProfilId) {
        try {
            ContextMsg context = createContext(pContextProfilId);
            
            profilBean.update(context, pProfilMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
}