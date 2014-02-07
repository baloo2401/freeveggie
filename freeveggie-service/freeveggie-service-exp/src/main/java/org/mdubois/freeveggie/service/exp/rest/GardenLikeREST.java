package org.mdubois.freeveggie.service.exp.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;

/**
 *
 * @author Mickael
 */
@Path("gardenlike")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class GardenLikeREST extends FreeveggieREST {

    /**
     * {@link IGardenBean}
     */
    @EJB
    private IGardenBean gardenBean;
    
    @GET
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public List<GardenLikeMsg> getGardenLike(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, GardenLikeCriteriaColumn.class, GardenLikeOrderColumn.class);

            return gardenBean.getGardenLike(context, pGardenId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public void addGardenLike(GardenLikeMsg pGardenLikeMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            gardenBean.like(context, pGardenLikeMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("send/{userId}")
    @Produces({"application/json"})
    public List<GardenLikeMsg> getGardenLikeWrite(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, GardenLikeCriteriaColumn.class, GardenLikeOrderColumn.class);

            return gardenBean.getGardenLikeWrite(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
