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
import org.mdubois.freeveggie.bean.IRelationShipBean;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;

/**
 *
 * @author Mickael
 */
@Path("relationshiprequest")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class RelationshipRequestREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IRelationShipBean relationShipBean;

    @GET
    @Path("re/{userId}")
    @Produces({"application/json"})
    public List<RelationShipMsg> getProductRequestReceive(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, RelationShipCriteriaColumn.class, RelationShipOrderColumn.class);

            return relationShipBean.getRelationShip(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @POST
    @Consumes({"application/json"})
    public void addProductRequest(RelationShipMsg pRelationShipMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            relationShipBean.create(context, pRelationShipMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
}
