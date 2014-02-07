package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import static org.mdubois.freeveggie.bo.ProductCommentBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IProductCommentDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class ProductCommentDAO extends ReadWriteDAO<ProductCommentBO, Long> implements IProductCommentDAO {

    /**
     * The default {@link ProductCommentBO} result search ordering.
     */
    private static final ProductCommentOrderColumn DEFAULT_ORDER_COLUM = ProductCommentOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<ProductCommentOrderColumn> DEFAULT_ORDER = new ResultOrder<ProductCommentOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<ProductCommentBO> getProductCommentByProduct(final Long pIdProductBO, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("product", pIdProductBO);
        return findPaginationQuery(GET_PRODUCT_COMMENT_BY_PRODUCT, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<ProductCommentBO> getProductCommentByWriter(final Long pIdUserWriterBO, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        return findPaginationQuery(GET_PRODUCT_COMMENT_WRITE, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public ProductCommentBO getProductCommentByUserAndProduct(final Long pIdUserWriterBO, final Long pIdProductBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("product", pIdProductBO);
        parameters.put("userWriter", pIdUserWriterBO);
        return findQuery(GET_PRODUCT_COMMENT_BY_PRODUCT_AND_WRITER, parameters);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<ProductCommentOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<ProductCommentBO> getType() {
        return ProductCommentBO.class;
    }
}
