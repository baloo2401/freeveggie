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
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;

/**
 *
 * @author Mickael
 */
@Path("gardencomment")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class GardenCommentREST extends FreeveggieREST {

    /**
     * {@link IGardenBean}
     */
    @EJB
    private IGardenBean gardenBean;
    
    @GET
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public List<GardenCommentMsg> getGardenComment(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, GardenCommentCriteriaColumn.class, GardenCommentOrderColumn.class);

            return gardenBean.getGardenComment(context, pGardenId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public void addGardenComment(GardenCommentMsg pGardenCommentMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            gardenBean.comment(context, pGardenCommentMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("send/{userId}")
    @Produces({"application/json"})
    public List<GardenCommentMsg> getGardenCommentWrite(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, GardenCommentCriteriaColumn.class, GardenCommentOrderColumn.class);

            return gardenBean.getGardenCommentWrite(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
