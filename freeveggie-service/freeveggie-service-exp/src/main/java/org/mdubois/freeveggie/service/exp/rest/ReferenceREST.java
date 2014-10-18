package org.mdubois.freeveggie.service.exp.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.bean.IReferenceBean;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.RefProductMsg;

/**
 *
 * @author Mickael
 */
@Path("reference")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class ReferenceREST extends FreeveggieREST {

    /**
     * {@link IProductBean}
     */
    @EJB
    private IReferenceBean referenceBean;

    @GET
    @Path("product")
    @Produces({"application/json"})
    public List<RefProductMsg> getRefProduct(
            @QueryParam("technicalInformation") String pJSONTechnicalInformation) {
        try {
            TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTech = JSONUtils.getTechnicalInformation(pJSONTechnicalInformation, RefProductCriteriaColumn.class, RefProductOrderColumn.class);

            return referenceBean.getRefProducts(pTech);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
