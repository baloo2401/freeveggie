package org.mdubois.freeveggie.dao.impl;

import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.PartialUserBO;
import static org.mdubois.freeveggie.bo.PartialUserBO.QueryNamedConstant.GET_USER_BY_EMAIL;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class UserPartialDAO extends ReadOnlyDAO<PartialUserBO, Long> implements
		IUserPartialDAO {

	/** {@inheritDoc} */
	@Override
	public PartialUserBO getUserFromEmail(String pEmail) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("Email", pEmail);
		return findQuery(GET_USER_BY_EMAIL, parameters);
	}

	/** {@inheritDoc} */
	@Override
	protected Class<PartialUserBO> getType() {
		return PartialUserBO.class;
	}

}
