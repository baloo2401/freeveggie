package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.bean.IProfilBean;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.api.IProfileService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.ProfileMsg;

// </editor-fold>
/**
 * This is a service class. This class represent all the method that involve
 * {@link ProfilBO}.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IProfilBean.class)
public class ProfileBeanLocal implements IProfilBean {

    /**
     * {@link IProfileService}
     */
    @Inject
    private IProfileService profilService;
    /**
     * {@link IRightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final ContextMsg pContextMsg,
            final ProfileMsg pProfilMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)
                && pContextMsg.getUser().getId().equals(pProfilMsg.getId())) {
            return profilService.update(pContextMsg.getUser().getId(),
                    pProfilMsg);
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProfileMsg getProfilById(final ContextMsg pContextMsg,
            final Long pUserId) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return profilService.getProfilById(pUserId);
        } else {
            throw new AccessNotGrantedException();
        }

    }
}
