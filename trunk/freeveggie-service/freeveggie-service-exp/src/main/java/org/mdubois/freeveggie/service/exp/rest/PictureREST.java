package org.mdubois.freeveggie.service.exp.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.PictureMsg;

/**
 *
 * @author Mickael
 */
@Path("picture")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class PictureREST extends FreeveggieREST {

    /**
     * {@link IGardenBean}
     */
    @EJB
    private IGardenBean gardenBean;

    /**
     * {@link IProductBean}
     */
    @EJB
    private IProductBean productBean;

    @GET
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public List<PictureMsg> getGardenPictures(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return gardenBean.getPictureByGarden(context, pGardenId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("product/{productId}")
    @Produces({"application/json"})
    public List<PictureMsg> getProductPictures(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return productBean.getPictureByProduct(context, pProductId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @POST
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public void addGardenPicture(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId,
            @QueryParam("pictureMsg") PictureMsg pPictureMsg) {
        try {
            ContextMsg context = createContext(pContextUserId);

            gardenBean.addPicture(context, pPictureMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @POST
    @Path("product/{productId}")
    @Produces({"application/json"})
    public void addProductPicture(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId,
            @QueryParam("pictureMsg") PictureMsg pPictureMsg) {
        try {
            ContextMsg context = createContext(pContextUserId);

            productBean.addPicture(context, pPictureMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

}
