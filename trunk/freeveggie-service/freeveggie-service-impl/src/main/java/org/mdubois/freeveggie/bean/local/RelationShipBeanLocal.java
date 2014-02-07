package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.bean.IRelationShipBean;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationShipService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;

// </editor-fold>
/**
 * This class is a service. This class represent all the method that involve
 * {@link RelationShipBO}.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IRelationShipBean.class)
public class RelationShipBeanLocal implements IRelationShipBean {

    /**
     * {@link IRelationShipService}
     */
    @Inject
    private IRelationShipService relationShipService;
    /**
     * {@link IRightControlerService}
     */
    @Inject
    private IRightControlerService rightControlerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(final ContextMsg pContextMsg,
            final RelationShipMsg pRelationShipMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (pContextMsg.getUser().getId()
                    .equals(pRelationShipMsg.getSender().getId())) {
                return relationShipService.create(pRelationShipMsg);
            } else {
                throw new AccessNotGrantedException();
            }
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final ContextMsg pContextMsg,
            final Long pRelationShipId, final String pMessage)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (rightControlerService.isUserOwnerRelationship(pContextMsg
                    .getUser().getId(), pRelationShipId)) {
                return relationShipService.validate(pRelationShipId, pMessage);
            } else {
                throw new AccessNotGrantedException();
            }
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean refuse(final ContextMsg pContextMsg,
            final Long pRelationShipId, final String pMessage)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (rightControlerService.isUserOwnerRelationship(pContextMsg
                    .getUser().getId(), pRelationShipId)) {
                return relationShipService.refuse(pRelationShipId, pMessage);
            } else {
                throw new AccessNotGrantedException();
            }
        } else {
            throw new AccessNotGrantedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RelationShipMsg> getRelationShip(
            final ContextMsg pContextMsg,
            final Long pUserId,
            final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return relationShipService.getRelationShip(pUserId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
