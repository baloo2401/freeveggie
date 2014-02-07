package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.AddressBO;
import static org.mdubois.freeveggie.bo.AddressBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.AddressOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class AddressDAO extends ReadWriteDAO<AddressBO, Long> implements IAddressDAO {

    /**
     * The default {@link AddressBO} result search ordering.
     */
    private static final AddressOrderColumn DEFAULT_ORDER_COLUM = AddressOrderColumn.CITY_NAME;
    /**
     * The default {@link OrderWay} result search way.
     */
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    /**
     * The default {@link ResultOrder}
     */
    private static final ResultOrder<AddressOrderColumn> DEFAULT_ORDER = new ResultOrder<AddressOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public AddressBO getUserHomeAddressByUserId(Long pIdUserBO) {

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userId", pIdUserBO);
        return findQuery(GET_ADDRESS_BY_USER, parameters);
    }
    
    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByCountry(Integer pIdRefCountryBO, Pagination pPagination) {

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        return findPaginationQuery(GET_ADDRESS_BY_COUNTRY, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByCountry(final Integer pIdRefCountryBO, final String pCity, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        parameters.put("city", pCity.toUpperCase());
        return findPaginationQuery(GET_ADDRESS_BY_COUNTRY_AND_CITY_NAME, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByCountry(final Integer pIdRefCountryBO, final int pZipCode, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        parameters.put("zipCode", pZipCode);
        return findPaginationQuery(GET_ADDRESS_BY_COUNTRY_AND_ZIP_CODE, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByCity(final Integer pIdRefCityBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("refCity", pIdRefCityBO);
        return findPaginationQuery(GET_ADDRESS_BY_CITY, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByRegion(final Integer pIdRefRegionBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("region", pIdRefRegionBO);
        return findPaginationQuery(GET_ADDRESS_BY_REGION, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public List<AddressBO> getAddressByState(final Integer pIdRefStateBO, final Pagination pPagination) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("state", pIdRefStateBO);
        return findPaginationQuery(GET_ADDRESS_BY_STATE, parameters, pPagination);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<AddressOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<AddressBO> getType() {
        return AddressBO.class;
    }
}
