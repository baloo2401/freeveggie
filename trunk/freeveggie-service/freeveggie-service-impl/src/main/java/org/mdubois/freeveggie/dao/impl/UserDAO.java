package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.UserBO;
import static org.mdubois.freeveggie.bo.UserBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.UserOrderColumn;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class UserDAO extends ReadWriteDAO<UserBO, Long> implements IUserDAO {

	/**
	 * The default {@link UserBO} result search ordering.
	 */
	private static final UserOrderColumn DEFAULT_ORDER_COLUM = UserOrderColumn.USERNAME;
	private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
	private static final ResultOrder<UserOrderColumn> DEFAULT_ORDER = new ResultOrder<UserOrderColumn>(
			DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

	/** {@inheritDoc} */
	@Override
	public List<UserBO> getUserByCityAndProduct(
			final Integer pIdRefCityBO,
			final Integer pIdRefProductBO,
			final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("city", pIdRefCityBO);
		parameters.put("referenceProduct", pIdRefProductBO);
		return findPaginationQuery(GET_USER_BY_CITY_AND_PRODUCT, parameters,
				pTechnicalInformation);

	}

	/** {@inheritDoc} */
	@Override
	public List<UserBO> getUserByRegionAndProduct(
			final Integer pIdRefRegionBO,
			final Integer pIdRefProductBO,
			final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("region", pIdRefRegionBO);
		parameters.put("referenceProduct", pIdRefProductBO);
		return findPaginationQuery(GET_USER_BY_REGION_AND_PRODUCT, parameters,
				pTechnicalInformation);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserBO> getUserByStateAndProduct(
			final Integer pIdRefStateBO,
			final Integer pIdRefProductBO,
			final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("state", pIdRefStateBO);
		parameters.put("referenceProduct", pIdRefProductBO);
		return findPaginationQuery(GET_USER_BY_STATE_AND_PRODUCT, parameters,
				pTechnicalInformation);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserBO> getUserByCountryAndProduct(
			final Integer pIdRfeCountryBO,
			final Integer pIdRefProductBO,
			final TechnicalInformation<UserCriteriaColumn, UserOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("country", pIdRfeCountryBO);
		parameters.put("referenceProduct", pIdRefProductBO);
		return findPaginationQuery(GET_USER_BY_COUNTRY_AND_PRODUCT, parameters,
				pTechnicalInformation);
	}

	/** {@inheritDoc} */
	@Override
	public UserBO getUserByLogin(String pLogin) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("Username", pLogin);
		return findQuery(GET_USER_BY_LOGIN, parameters);
	}

	/** {@inheritDoc} */
	@Override
	public UserBO getUserByEmail(String pEmail) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("Email", pEmail);
		return findQuery(GET_USER_BY_EMAIL, parameters);
	}

	/** {@inheritDoc} */
	@Override
	public ResultOrder<UserOrderColumn> getDefaultOrder() {
		return DEFAULT_ORDER;
	}

	/** {@inheritDoc} */
	@Override
	protected Class<UserBO> getType() {
		return UserBO.class;
	}
}
