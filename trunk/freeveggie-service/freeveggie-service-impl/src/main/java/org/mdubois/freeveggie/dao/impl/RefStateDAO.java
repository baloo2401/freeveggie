package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.RefStateBO;
import static org.mdubois.freeveggie.bo.RefStateBO.QueryNamedConstant.GET_STATE_BY_COUNTRY_NAME;
import static org.mdubois.freeveggie.bo.RefStateBO.QueryNamedConstant.GET_STATE_BY_NAME_NAME;
import org.mdubois.freeveggie.dao.api.IRefStateDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefStateOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RefStateBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class RefStateDAO extends ReadOnlyDAO<RefStateBO, Integer> implements IRefStateDAO {

    /**
     * The default {@link RefStateBO} result search ordering.
     */
    private static final RefStateOrderColumn DEFAULT_ORDER_COLUM = RefStateOrderColumn.NAME;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<RefStateOrderColumn> DEFAULT_ORDER = new ResultOrder<RefStateOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<RefStateBO> getRefStatesByName(final String pName, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("name", StringUtils.trimToEmpty(pName.toUpperCase()) + "%");
        return findPaginationQuery(GET_STATE_BY_NAME_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefStateBO> getRefStatesByCountry(final Integer pIdRefCountryBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        return findPaginationQuery(GET_STATE_BY_COUNTRY_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<RefStateOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<RefStateBO> getType() {
        return RefStateBO.class;
    }
}
