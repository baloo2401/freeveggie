package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import static org.mdubois.freeveggie.bo.ProductLikeBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductLikeDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class ProductLikeDAO extends ReadWriteDAO<ProductLikeBO, Long> implements IProductLikeDAO {

    /**
     * The default {@link ProductLikeBO} result search ordering.
     */
    private static final ProductLikeOrderColumn DEFAULT_ORDER_COLUM = ProductLikeOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<ProductLikeOrderColumn> DEFAULT_ORDER = new ResultOrder<ProductLikeOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<ProductLikeBO> getProductLikeByProduct(final Long pIdProductBO, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("product", pIdProductBO);
        return findPaginationQuery(GET_PRODUCT_LIKE_BY_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductLikeBO> getProductLikeByWriter(final Long pIdUserWriterBO, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        return findPaginationQuery(GET_PRODUCT_LIKE_WRITE, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public ProductLikeBO getProductLikeByUserAndProduct(final Long pIdUserWriterBO, final Long pIdProductBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        parameters.put("product", pIdProductBO);
        return findQuery(GET_PRODUCT_LIKE, parameters);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<ProductLikeOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<ProductLikeBO> getType() {
        return ProductLikeBO.class;
    }
}
