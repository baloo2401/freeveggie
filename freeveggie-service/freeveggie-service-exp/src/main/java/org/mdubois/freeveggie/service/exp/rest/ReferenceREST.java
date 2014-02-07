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
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
import org.mdubois.freeveggie.service.msg.RefCountryMsg;
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
    
    @GET
    @Path("country")
    @Produces({"application/json"})
    public List<RefCountryMsg> getRefCountry(
            @QueryParam("countryName") String pCountryName,
            @QueryParam("pagination") String pJSONTechnicalInformation) {
        try {
            Pagination pPagination = JSONUtils.getPagination(pJSONTechnicalInformation);
            
            return referenceBean.getRefCountriesByName(pCountryName, pPagination);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("city")
    @Produces({"application/json"})
    public List<RefCityMsg> getRefCity(
            @QueryParam("cityName") String pCityName,
            @QueryParam("pagination") String pJSONTechnicalInformation) {
        try {
            Pagination pPagination = JSONUtils.getPagination(pJSONTechnicalInformation);
            
            return referenceBean.getRefCitiesByName(pCityName, pPagination);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
    
    @GET
    @Path("zipCode")
    @Produces({"application/json"})
    public List<RefCityMsg> getRefCityByZipCode(
            @QueryParam("zipCode") Integer pZipCode,
            @QueryParam("pagination") String pJSONTechnicalInformation) {
        try {
            Pagination pPagination = JSONUtils.getPagination(pJSONTechnicalInformation);
            
            return referenceBean.getRefCitiesByZipCode(pZipCode, pPagination);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }
}
