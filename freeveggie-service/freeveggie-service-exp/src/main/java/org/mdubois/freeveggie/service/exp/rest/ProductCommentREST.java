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
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;

/**
 *
 * @author Mickael
 */
@Path("productcomment")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class ProductCommentREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IProductBean productBean;
    
    @GET
    @Path("product/{productId}")
    @Produces({"application/json"})
    public List<ProductCommentMsg> getProductComment(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("productId") Long pProductId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductCommentCriteriaColumn.class, ProductCommentOrderColumn.class);

            return productBean.getProductComment(context, pProductId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public void addProductComment(ProductCommentMsg pProductCommentMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            productBean.comment(context, pProductCommentMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("send/{userId}")
    @Produces({"application/json"})
    public List<ProductCommentMsg> getProductCommentWrite(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, ProductCommentCriteriaColumn.class, ProductCommentOrderColumn.class);

            return productBean.getProductCommentWrite(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
