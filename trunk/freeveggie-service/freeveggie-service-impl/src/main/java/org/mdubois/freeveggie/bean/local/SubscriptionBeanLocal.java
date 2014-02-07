package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.bean.ISubscriptionBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.service.api.ISubscriptionService;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

// </editor-fold>
/**
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(ISubscriptionBean.class)
public class SubscriptionBeanLocal implements ISubscriptionBean {

    /**
     * {@link ISubscriptionService}
     */
    @Inject
    private ISubscriptionService subscriptionService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(CreateAccountMsg pCreateAccountMsg) throws BusinessException {
        return subscriptionService.create(pCreateAccountMsg);
    }
}
