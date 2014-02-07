package org.mdubois.freeveggie.service.exp.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.bean.ISecurityBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

/**
 *
 * @author Mickael
 */
@Path("security")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class SecurityREST extends FreeveggieREST{

    /**
     * {@link IProductBean}
     */
    @EJB
    private ISecurityBean securityBean;

    @GET
    @Path("controlPassword")
    @Produces({"application/json"})
    public UserMsg controlPassword(
            @QueryParam("login") final String login,
            @QueryParam("password") final String password) {
        AuthenticationMsg authentificationMsg = new AuthenticationMsg();
        authentificationMsg.setLogin(login);
        authentificationMsg.setPassword(password);
        try {
            return securityBean.controlPassword(authentificationMsg);
        } catch (BusinessException be){
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("controlTempPassword")
    @Produces({"application/json"})
    public UserMsg controlTempPassword(
            @QueryParam("login") final String login,
            @QueryParam("password") final String password) {
        AuthenticationMsg authentificationMsg = new AuthenticationMsg();
        authentificationMsg.setLogin(login);
        authentificationMsg.setPassword(password);
        return securityBean.controlTempPassword(authentificationMsg);
    }
    
    
    
    @POST
    @Path("changePassword")
    @Consumes({"application/json"})
    public void changePassword(ChangePasswordMsg pChangePasswordMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);
            
            securityBean.changePassword(context, pChangePasswordMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("hasTempPassword")
    @Produces({"application/json"})
    public String hasTempPassword(
            @QueryParam("login") final String pCode) throws BusinessException {
        return Boolean.toString(securityBean.hasTempPassword(pCode));
    }

    @GET
    @Path("isExistingLogin")
    @Produces({"application/json"})
    public String isExistingLogin(
            @QueryParam("login") final String pLogin) {
        return Boolean.toString(securityBean.isExistingLogin(pLogin));
    }

    @GET
    @Path("isExistingEmail")
    @Produces({"application/json"})
    public String isExistingEmail(
            @QueryParam("email") final String pEmail) {
        return Boolean.toString(securityBean.isExistingEmail(pEmail));
    }

    @GET
    @Path("generateTempPassword")
    @Produces({"application/json"})
    public String generateTempPassword(
            @QueryParam("email") final String pEmail) throws BusinessException {
        securityBean.generateTempPassword(pEmail);
        return Boolean.toString(true);
    }
}