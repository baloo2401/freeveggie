package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProfileBO;
import org.mdubois.freeveggie.dao.api.IProfileDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class ProfileDAO extends ReadWriteDAO<ProfileBO, Long> implements
		IProfileDAO {

	/** {@inheritDoc} */
	@Override
	public ResultOrder<? extends OrderColumn> getDefaultOrder() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected Class<ProfileBO> getType() {
		return ProfileBO.class;
	}
}
