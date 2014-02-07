package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bean.ISecurityBean;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.service.api.ISecurityService;
import org.mdubois.freeveggie.service.msg.AuthenticationMsg;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

// </editor-fold>
/**
 *
 * @author mdubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(ISecurityBean.class)
public class SecurityBeanLocal implements ISecurityBean {

    /**
     * {@link ISecurityService}
     */
    @Inject
    private ISecurityService securityService;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg controlPassword(AuthenticationMsg pAuthentificationMsg)  throws BusinessException {
        return securityService.controlPassword(pAuthentificationMsg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserMsg controlTempPassword(AuthenticationMsg pAuthentificationMsg) {
        return securityService.controlTempPassword(pAuthentificationMsg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasTempPassword(String pCode) throws BusinessException {
        return securityService.hasTempPassword(pCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExistingLogin(String pLogin) {
        return securityService.isExistingLogin(pLogin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExistingEmail(String pEmail) {
        return securityService.isExistingEmail(pEmail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateTempPassword(String pEmail) throws BusinessException {
        securityService.generateTempPassword(pEmail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean changePassword(final ContextMsg pContextMsg, ChangePasswordMsg pChangePasswordMsg) throws BusinessException {
        if(pContextMsg.getUser().getId().equals(pChangePasswordMsg.getUserId())) {
            return securityService.changePassword(pChangePasswordMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
