package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.apache.commons.lang.StringUtils;
import org.mdubois.freeveggie.bo.RefProductBO;
import static org.mdubois.freeveggie.bo.RefProductBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRefProductDAO;
import org.mdubois.freeveggie.framework.dao.ReadOnlyDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.RefProductOrderColumn;

// </editor-fold>
/**
 * This class is the data access class of the {@link RefProductBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class RefProductDAO extends ReadOnlyDAO<RefProductBO, Integer> implements IRefProductDAO {

    /**
     * The default {@link RefProductBO} result search ordering.
     */
    private static final RefProductOrderColumn DEFAULT_ORDER_COLUM = RefProductOrderColumn.NAME;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<RefProductOrderColumn> DEFAULT_ORDER = new ResultOrder<RefProductOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<RefProductBO> getRefProducts(final Integer pIdProductType, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("productType", pIdProductType);
        return findPaginationQuery(GET_PRODUCT_BY_PRODUCT_TYPE_NAME, parameters, pTechnicalInformation);
    }
 
    /** {@inheritDoc} */
    @Override
    public List<RefProductBO> getRefProductsByName(final String pName, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("name", StringUtils.trimToEmpty(pName.toUpperCase()) + "%");
        return findPaginationQuery(GET_PRODUCT_BY_NAME_NAME, parameters, pTechnicalInformation);

    }

    /** {@inheritDoc} */
    @Override
    public List<RefProductBO> getRefProductsByReapSeason(final List<Integer> pIdRefMonth, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) {

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("reapMonths", pIdRefMonth);
        return findPaginationQuery(GET_PRODUCT_BY_REAPSEASON_NAME, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<RefProductBO> getRefProductsBySeedSeason(final List<Integer> pIdRefMonth, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation) {

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("seedMonths", pIdRefMonth);
        return findPaginationQuery(GET_PRODUCT_BY_SEEDSEASON_NAME, parameters, pTechnicalInformation);

    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<RefProductOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<RefProductBO> getType() {
        return RefProductBO.class;
    }
}
