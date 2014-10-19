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
import org.mdubois.freeveggie.bean.IRelationshipBean;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

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
    private IRelationshipBean relationshipBean;

    @GET
    @Path("re/{userId}")
    @Produces({"application/json"})
    public List<RelationshipMsg> getRelationshipRequestReceive(
            @HeaderParam("userId") Long pContextUserId,
            @PathParam("userId") Long pUserId,
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            ContextMsg context = createContext(pContextUserId);
            TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, RelationshipCriteriaColumn.class, RelationshipOrderColumn.class);

            return relationshipBean.getRelationship(context, pUserId, pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @POST
    @Consumes({"application/json"})
    public void addRelationshipRequest(RelationshipMsg pRelationshipMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            relationshipBean.create(context, pRelationshipMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

}
