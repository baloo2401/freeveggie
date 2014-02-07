package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import static org.mdubois.freeveggie.bo.ProductRequestBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductRequestDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class ProductRequestDAO extends ReadWriteDAO<ProductRequestBO, Long> implements IProductRequestDAO {

    /**
     * The default {@link ProductRequestBO} result search ordering.
     */
    private static final ProductRequestOrderColumn DEFAULT_ORDER_COLUM = ProductRequestOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<ProductRequestOrderColumn> DEFAULT_ORDER = new ResultOrder<ProductRequestOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<ProductRequestBO> getProductRequestByProduct(final Long pIdProductBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("product", pIdProductBO);
        return findPaginationQuery(GET_PRODUCT_REQUEST_BY_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductRequestBO> getProductRequestByGarden(final Long pIdGardenBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) {
        //TODO : Controle that a least on field set

        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        return findPaginationQuery(GET_PRODUCT_REQUEST_BY_GARDEN, parameters, pTechnicalInformation);

    }

    /** {@inheritDoc} */
    @Override
    public List<ProductRequestBO> getProductRequestSend(final Long pIdUserPartialBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userBO", pIdUserPartialBO);
        return findPaginationQuery(GET_PRODUCT_REQUEST_BY_SENDER, parameters, pTechnicalInformation);

    }

    /** {@inheritDoc} */
    @Override
    public List<ProductRequestBO> getProductRequestReceived(final Long pIdUserPartialBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userBO", pIdUserPartialBO);
        return findPaginationQuery(GET_PRODUCT_REQUEST_BY_RECEIVER, parameters, pTechnicalInformation);

    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<ProductRequestOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<ProductRequestBO> getType() {
        return ProductRequestBO.class;
    }
}
