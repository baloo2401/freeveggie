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
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;

/**
 *
 * @author Mickael
 */
@Path("productrequest")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class ProductRequestREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IProductBean productBean;
    
    @GET
    @Path("{productResquestId}")
    @Produces({"application/json"})
    public ProductRequestMsg getProductRequestId(
    		@HeaderParam("userId") Long pJSONContext,
            @PathParam("productResquestId") Long pProductResquestId) {
        try {
            ContextMsg context = createContext(pJSONContext);
            return productBean.getProductRequestById(context, pProductResquestId);
        } catch (BusinessException be) {
            return null;
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public void addProductRequest(ProductRequestMsg pProductRequestMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            productBean.send(context, pProductRequestMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Path("accept/{productRequestId}")
    @Consumes({"application/json"})
    public void accept(String pMessage, 
            @PathParam("productRequestId") Long pProductRequestId,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            productBean.accept(context, pProductRequestId,pMessage);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Path("refuse/{productRequestId}")
    @Consumes({"application/json"})
    public void refuseRequest(String pMessage, 
            @PathParam("productRequestId") Long pProductRequestId,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            productBean.refuse(context, pProductRequestId,pMessage);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("receive/{userIdReceiver}")
    @Produces({"application/json"})
    public List<ProductRequestMsg> getProductRequestReceive(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userIdReceiver") Long pUserIdReceiver,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductRequestCriteriaColumn.class, ProductRequestOrderColumn.class);

            return productBean.getProductRequestReceive(context, pUserIdReceiver, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("send/{userIdSend}")
    @Produces({"application/json"})
    public List<ProductRequestMsg> getProductRequestSend(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userIdSend") Long pUserIdSend,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductRequestCriteriaColumn.class, ProductRequestOrderColumn.class);

            return productBean.getProductRequestSend(context, pUserIdSend, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("garden/{gardenId}")
    @Produces({"application/json"})
    public List<ProductRequestMsg> getProductRequestByGarden(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("gardenId") Long pGardenId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductRequestCriteriaColumn.class, ProductRequestOrderColumn.class);

            return productBean.getProductRequestByGarden(context, pGardenId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    

    @GET
    @Path("product/{productId}")
    @Produces({"application/json"})
    public List<ProductRequestMsg> getProductRequestByProduct(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductRequestCriteriaColumn.class, ProductRequestOrderColumn.class);

            return productBean.getProductRequestByProduct(context, pProductId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
