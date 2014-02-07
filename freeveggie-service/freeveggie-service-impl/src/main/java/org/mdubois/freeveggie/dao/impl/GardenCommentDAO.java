package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import static org.mdubois.freeveggie.bo.GardenCommentBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenCommentDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class GardenCommentDAO extends ReadWriteDAO<GardenCommentBO, Long> implements IGardenCommentDAO {

    /**
     * The default {@link GardenCommentBO} result search ordering.
     */
    private static final GardenCommentOrderColumn DEFAULT_ORDER_COLUM = GardenCommentOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<GardenCommentOrderColumn> DEFAULT_ORDER = new ResultOrder<GardenCommentOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<GardenCommentBO> getGardenCommentByGarden(final Long pIdGardenBO, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        return findPaginationQuery(GET_GARDEN_COMMENT_BY_GARDEN, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenCommentBO> getGardenCommentByWriter(final Long pIdUserWriterBO, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        return findPaginationQuery(GET_GARDEN_COMMENT_WROTE, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public GardenCommentBO getGardenCommentByUserAndGarden(final Long pIdUserWriterBO, final Long pIdGardenBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        parameters.put("userWriter", pIdUserWriterBO);
        return findQuery(GET_GARDEN_COMMENT_BY_GARDEN_AND_WRITER, parameters);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<GardenCommentOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<GardenCommentBO> getType() {
        return GardenCommentBO.class;
    }
}
