package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public interface ISubscriptionBean {

    Long create(final CreateAccountMsg pAddressMsg) throws BusinessException;
}
