package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.RelationshipBO;
import static org.mdubois.freeveggie.bo.RelationshipBO.QueryNamedConstant.GET_RELATIONSHIP;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationshipDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;

// </editor-fold>

/**
 * 
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({ DAOInterceptor.class })
public class RelationshipDAO extends ReadWriteDAO<RelationshipBO, Long>
		implements IRelationshipDAO {

	/**
	 * The default {@link RelationshipBO} result search ordering.
	 */
	private static final RelationshipOrderColumn DEFAULT_ORDER_COLUM = RelationshipOrderColumn.CREATION_DATE;
	private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
	private static final ResultOrder<RelationshipOrderColumn> DEFAULT_ORDER = new ResultOrder<RelationshipOrderColumn>(
			DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

	/** {@inheritDoc } */
	@Override
	public List<RelationshipBO> getRelationship(
			final Long pIdUserPartialBO,
			final TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> pTechnicalInformation) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put("sender", pIdUserPartialBO);
		parameters.put("recipient", pIdUserPartialBO);
		return findPaginationQuery(GET_RELATIONSHIP, parameters,
				pTechnicalInformation);
	}

	/** {@inheritDoc } */
	@Override
	public ResultOrder<RelationshipOrderColumn> getDefaultOrder() {
		return DEFAULT_ORDER;
	}

	@Override
	protected Class<RelationshipBO> getType() {
		return RelationshipBO.class;
	}
}
