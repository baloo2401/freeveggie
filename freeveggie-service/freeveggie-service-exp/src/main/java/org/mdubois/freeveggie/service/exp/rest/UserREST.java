package org.mdubois.freeveggie.service.exp.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.mdubois.freeveggie.bean.ISubscriptionBean;
import org.mdubois.freeveggie.bean.IUserBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.exp.interceptors.WebServiceExceptionHandler;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

/**
 *
 * @author Mickael
 */
@Path("user")
@Produces({"application/json"})
@RequestScoped
@WebServiceExceptionHandler
public class UserREST extends FreeveggieREST {

    /**
     * {@link IUserBean}
     */
    @EJB
    private IUserBean userBean;

    /**
     * {@link ISubscriptionBean}
     */
    @EJB
    private ISubscriptionBean subscriptionBean;

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public UserMsg getUserById(
            @PathParam("id") final Long userId) throws BusinessException {
        return userBean.getUserById(userId);
    }

    @POST
    @Consumes({"application/json"})
    public void addUser(CreateAccountMsg pCreateAccountrMsg) {
        try {
            subscriptionBean.create(pCreateAccountrMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @PUT
    @Consumes({"application/json"})
    public void updateUser(UserMsg pUserMsg,
            @HeaderParam("userId") Long pContextUserId) {
        try {
            ContextMsg context = createContext(pContextUserId);

            userBean.update(context, pUserMsg);
        } catch (BusinessException be) {
            throw new BusinessWebException(be);
        }
    }

    @GET
    @Path("validate")
    @Produces({"application/json"})
    public String validateCode(
            @QueryParam("code") final String pCode) throws BusinessException {
        return Boolean.toString(userBean.validate(pCode));
    }

}
