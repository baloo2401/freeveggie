package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.RefCountryBO;
import static org.mdubois.freeveggie.bo.RefCountryBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IRefCountryDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefCountryOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link RefCountryBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class RefCountryDAO extends ReadOnlyDAO<RefCountryBO, Integer> implements IRefCountryDAO {

    /**
     * The default {@link RefCountryBO} result search ordering.
     */
    private static final RefCountryOrderColumn DEFAULT_ORDER_COLUM = RefCountryOrderColumn.NAME;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<RefCountryOrderColumn> DEFAULT_ORDER = new ResultOrder<RefCountryOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public RefCountryBO getRefCountryByIsoCodeA2(final String pIsoCodeA2) {

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("codeIsoA2", pIsoCodeA2.toUpperCase());
        return findQuery(GET_COUNTRY_BY_ISO_CODE_A2_NAME, parameters);

    }

    /** {@inheritDoc} */
    @Override
    public RefCountryBO getRefCountryByIsoCodeA3(final String pIsoCodeA3) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("codeIsoA3", pIsoCodeA3.toUpperCase());
        return findQuery(GET_COUNTRY_BY_ISO_CODE_A3_NAME, parameters);

    }

    /** {@inheritDoc} */
    @Override
    public RefCountryBO getRefCountryByIsoCodeNumber(final Integer pIsoCodeNumber) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("codeIsoNumber", pIsoCodeNumber);
        return findQuery(GET_COUNTRY_BY_ISO_CODE_NUMBER_NAME, parameters);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefCountryBO> getRefCountriesByName(final String pName, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("name", StringUtils.trimToEmpty(pName.toUpperCase()) + "%");
        return findPaginationQuery(GET_COUNTRY_BY_NAME_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<RefCountryOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<RefCountryBO> getType() {
        return RefCountryBO.class;
    }
}
