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
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.IdMsg;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.UpdateProductMsg;

/**
 *
 * @author Mickael
 */
@Path("product")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class ProductREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IProductBean productBean;

    @GET
    @Path("{productId}")
    @Produces({"application/json"})
    public ProductMsg getProductById(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return productBean.getProductById(context, pProductId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @POST
    @Consumes({"application/json"})
    public IdMsg addProduct(ProductMsg pProductMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            return new IdMsg(productBean.create(context, pProductMsg));
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @PUT
    @Consumes({"application/json"})
    public void updateProduct(UpdateProductMsg pUpdateProductMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            productBean.update(context, pUpdateProductMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @DELETE
    @Path("{productId}")
    public void deleteProductById(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            productBean.remove(context, pProductId);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("user/{userId}")
    @Produces({"application/json"})
    public List<ProductMsg> getProductByUser(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductCriteriaColumn.class, ProductOrderColumn.class);

            return productBean.getProductByUser(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public List<ProductMsg> getProductByGarden(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductCriteriaColumn.class, ProductOrderColumn.class);

            return productBean.getProductByGarden(context, pGardenId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
