package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.RelationShipBO;
import static org.mdubois.freeveggie.bo.RelationShipBO.QueryNamedConstant.GET_RELATIONSHIP;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationShipDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class RelationShipDAO extends ReadWriteDAO<RelationShipBO, Long>
		implements IRelationShipDAO {

	/**
	 * The default {@link RelationShipBO} result search ordering.
	 */
	private static final RelationShipOrderColumn DEFAULT_ORDER_COLUM = RelationShipOrderColumn.CREATION_DATE;
	private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
	private static final ResultOrder<RelationShipOrderColumn> DEFAULT_ORDER = new ResultOrder<RelationShipOrderColumn>(
			DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

	/** {@inheritDoc } */
	@Override
	public List<RelationShipBO> getRelationShip(
			final Long pIdUserPartialBO,
			final TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("sender", pIdUserPartialBO);
		parameters.put("recipient", pIdUserPartialBO);
		return findPaginationQuery(GET_RELATIONSHIP, parameters,
				pTechnicalInformation);
	}

	/** {@inheritDoc } */
	@Override
	public ResultOrder<RelationShipOrderColumn> getDefaultOrder() {
		return DEFAULT_ORDER;
	}

	@Override
	protected Class<RelationShipBO> getType() {
		return RelationShipBO.class;
	}
}
