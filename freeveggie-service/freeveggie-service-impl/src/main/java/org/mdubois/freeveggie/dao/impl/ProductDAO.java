package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProductBO;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link ProductBO} entity.
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class ProductDAO extends ReadWriteDAO<ProductBO, Long> implements IProductDAO {

    /**
     * The default {@link ProductBO} result search ordering.
     */
    private static final ProductOrderColumn DEFAULT_ORDER_COLUM = ProductOrderColumn.CULTURE_MODE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<ProductOrderColumn> DEFAULT_ORDER = new ResultOrder<ProductOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByUser(final Long pIdUserWriterBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("owner", pIdUserWriterBO);
        return findPaginationQuery(GET_PRODUCT_BY_USER, parameters, pTechnicalInformation);
    }
    
    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByGarden(final Long pIdGardenBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        return findPaginationQuery(GET_PRODUCT_BY_GARDEN, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByCityAndRefProduct(final Integer pIdRefCityBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("city", pIdRefCityBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_PRODUCT_BY_CITY_AND_REF_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByRegionAndRefProduct(final Integer pIdRefRegionBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("region", pIdRefRegionBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_PRODUCT_BY_REGION_AND_REF_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByStateAndRefProduct(final Integer pIdRefStateBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("state", pIdRefStateBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_PRODUCT_BY_STATE_AND_REF_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductBO> getProductByCountryAndRefProduct(final Integer pIdRefCountryBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<ProductOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<ProductBO> getType() {
        return ProductBO.class;
    }
}
