package org.mdubois.freeveggie.service.exp.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.IdMsg;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.SearchMsg;

/**
 *
 * @author Mickael
 */
@Path("garden")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class GardenREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IGardenBean gardenBean;

    @GET
    @Path("{gardenId}")
    @Produces({"application/json"})
    public GardenMsg getGardenById(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return gardenBean.getGardenById(context, pGardenId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @POST
    @Consumes({"application/json"})
    public IdMsg addGarden(GardenMsg pGardenMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return new IdMsg(gardenBean.create(context, pGardenMsg));
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @PUT
    @Consumes({"application/json"})
    public void updateGarden(GardenMsg pGardenMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            gardenBean.update(context, pGardenMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @DELETE
    @Path("{gardenId}")
    public void deleteGardenById(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            gardenBean.remove(context, pGardenId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("search/{latitudeUp}/{latitudeDown}/{longitudeUp}/{longitudeDown}/refproduct/{refProductId}")
    @Produces({"application/json"})
    public List<GardenMsg> searchGarden(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("latitudeUp") Double pLatitudeUp,
            @PathParam("latitudeDown") Double pLatitudeDown,
            @PathParam("longitudeUp") Double pLongitudeUp,
            @PathParam("longitudeDown") Double pLongitudeDown,
            @PathParam("refProductId") Integer pRefProductId) throws BusinessException {
        ContextMsg context = createContext(pContextUserId);
        SearchMsg searchMsg = new SearchMsg();
        searchMsg.setLatitudeDown(pLatitudeDown);
        searchMsg.setLatitudeUp(pLatitudeUp);
        searchMsg.setLongitudeDown(pLongitudeDown);
        searchMsg.setLongitudeUp(pLongitudeUp);
        if (pRefProductId != null && pRefProductId > 0) {
            searchMsg.setRefProductId(pRefProductId);
        }
        return gardenBean.searchGarden(context, searchMsg);
    }

    @GET
    @Path("user/{userId}")
    @Produces({"application/json"})
    public List<GardenMsg> getGardenByUser(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, GardenCriteriaColumn.class, GardenOrderColumn.class);

            return gardenBean.getGardenByUser(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
