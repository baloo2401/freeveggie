package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.dao.api.ISubscriptionDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class SubscriptionDAO extends ReadWriteDAO<SubscriptionBO, Long>
		implements ISubscriptionDAO {

	/** {@inheritDoc} */
	@Override
	public ResultOrder getDefaultOrder() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected Class<SubscriptionBO> getType() {
		return SubscriptionBO.class;
	}
}
