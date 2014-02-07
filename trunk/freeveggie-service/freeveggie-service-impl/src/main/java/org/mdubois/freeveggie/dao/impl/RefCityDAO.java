package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.RefCityBO;
import static org.mdubois.freeveggie.bo.RefCityBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IRefCityDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefCityOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RefCityBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class RefCityDAO extends ReadOnlyDAO<RefCityBO, Integer> implements IRefCityDAO {
    //TODO Add to all this method the country id and to the same for state and region 
    /**
     * The default {@link RefCityBO} result search ordering.
     */
    private static final RefCityOrderColumn DEFAULT_ORDER_COLUM = RefCityOrderColumn.NAME;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<RefCityOrderColumn> DEFAULT_ORDER = new ResultOrder<RefCityOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<RefCityBO> getRefCitiesByName(final String pName, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("name", StringUtils.trimToEmpty(pName.toUpperCase()) + "%");
        return findPaginationQuery(GET_CITY_BY_NAME_NAME, parameters, pPagination);
    }
    
    /** {@inheritDoc} */
    @Override
    public List<RefCityBO> getRefCitiesByZipCode(final Integer pZipCode, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("zipCode", pZipCode + "%");
        return findPaginationQuery(GET_CITY_BY_ZIP_CODE_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefCityBO> getRefCitiesByRegion(final Integer pIdRefRegionBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("region", pIdRefRegionBO);
        return findPaginationQuery(GET_CITY_BY_REGION_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefCityBO> getRefCitiesByState(final Integer pIdRefStateBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("state", pIdRefStateBO);
        return findPaginationQuery(GET_CITY_BY_STATE_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefCityBO> getRefCitiesByCountry(final Integer pIdRefCountryBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        return findPaginationQuery(GET_CITY_BY_COUNTRY_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<RefCityOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<RefCityBO> getType() {
        return RefCityBO.class;
    }
}
