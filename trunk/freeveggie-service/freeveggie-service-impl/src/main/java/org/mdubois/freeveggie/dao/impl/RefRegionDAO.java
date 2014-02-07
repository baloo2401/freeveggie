package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.RefRegionBO;
import static org.mdubois.freeveggie.bo.RefRegionBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IRefRegionDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefRegionOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RefRegionBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class RefRegionDAO extends ReadOnlyDAO<RefRegionBO, Integer> implements IRefRegionDAO {

    /**
     * The default {@link RefRegionBO} result search ordering.
     */
    private static final RefRegionOrderColumn DEFAULT_ORDER_COLUM = RefRegionOrderColumn.NAME;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<RefRegionOrderColumn> DEFAULT_ORDER = new ResultOrder<RefRegionOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<RefRegionBO> getRefRegionsByName(final String pName, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("name", StringUtils.trimToEmpty(pName.toUpperCase()) + "%");
        return findPaginationQuery(GET_REGION_BY_NAME_NAME, parameters, pPagination);

    }
    
    /** {@inheritDoc} */
    @Override
    public List<RefRegionBO> getRefRegionsByState(final Integer pIdRefStateBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("state", pIdRefStateBO);
        return findPaginationQuery(GET_REGION_BY_STATE_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefRegionBO> getRefRegionsByCountry(final Integer pIdRefCountryBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        return findPaginationQuery(GET_REGION_BY_COUNTRY_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<RefRegionOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<RefRegionBO> getType() {
        return RefRegionBO.class;
    }
}
