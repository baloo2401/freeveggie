package org.mdubois.freeveggie.bean.local;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import org.mdubois.freeveggie.bean.IRelationshipBean;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.interceptor.MessageValidatorInterceptor;
import org.mdubois.freeveggie.framework.interceptor.TraceInterceptor;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationshipService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

// </editor-fold>
/**
 * This class is a service. This class represent all the method that involve
 * {@link RelationshipBO}.
 *
 * @author Mickael Dubois
 */
@Stateless
@Interceptors({MessageValidatorInterceptor.class, TraceInterceptor.class})
@Local(IRelationshipBean.class)
public class RelationshipBeanLocal implements IRelationshipBean {

    /**
     * {@link IRelationshipService}
     */
    @Inject
    private IRelationshipService relationshipService;
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
            final RelationshipMsg pRelationshipMsg) throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (pContextMsg.getUser().getId()
                    .equals(pRelationshipMsg.getSender().getId())) {
                return relationshipService.create(pRelationshipMsg);
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
            final Long pRelationshipId, final String pMessage)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (rightControlerService.isUserOwnerRelationship(pContextMsg
                    .getUser().getId(), pRelationshipId)) {
                return relationshipService.validate(pRelationshipId, pMessage);
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
            final Long pRelationshipId, final String pMessage)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            if (rightControlerService.isUserOwnerRelationship(pContextMsg
                    .getUser().getId(), pRelationshipId)) {
                return relationshipService.refuse(pRelationshipId, pMessage);
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
    public List<RelationshipMsg> getRelationship(
            final ContextMsg pContextMsg,
            final Long pUserId,
            final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation)
            throws BusinessException {
        if (rightControlerService.isUserInRole(pContextMsg.getUser().getId(),
                UserRole.USER)) {
            return relationshipService.getRelationship(pUserId,
                    pTechnicalInformation);
        } else {
            throw new AccessNotGrantedException();
        }
    }
}
