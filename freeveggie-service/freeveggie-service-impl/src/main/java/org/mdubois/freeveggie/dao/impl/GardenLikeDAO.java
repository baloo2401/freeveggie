package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import static org.mdubois.freeveggie.bo.GardenLikeBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenLikeDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class GardenLikeDAO extends ReadWriteDAO<GardenLikeBO, Long> implements IGardenLikeDAO {

    /**
     * The default {@link GardenLikeBO} result search ordering.
     */
    private static final GardenLikeOrderColumn DEFAULT_ORDER_COLUM = GardenLikeOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<GardenLikeOrderColumn> DEFAULT_ORDER = new ResultOrder<GardenLikeOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<GardenLikeBO> getGardenLikeByGarden(final Long pIdGardenBO, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        return findPaginationQuery(GET_GARDEN_LIKE_BY_GARDEN, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenLikeBO> getGardenLikeByWriter(final Long pIdUserWriterBO, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        return findPaginationQuery(GET_GARDEN_LIKE_WRITE, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public GardenLikeBO getGardenLikeByUserAndGarden(final Long pIdUserWriterBO, final Long pIdGardenBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("userWriter", pIdUserWriterBO);
        parameters.put("garden", pIdGardenBO);
        return findQuery(GET_GARDEN_LIKE, parameters);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<GardenLikeOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<GardenLikeBO> getType() {
        return GardenLikeBO.class;
    }
}
