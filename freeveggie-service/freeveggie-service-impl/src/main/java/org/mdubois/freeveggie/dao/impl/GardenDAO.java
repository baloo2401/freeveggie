package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.GardenBO;
import static org.mdubois.freeveggie.bo.GardenBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class GardenDAO extends ReadWriteDAO<GardenBO, Long> implements IGardenDAO {

    /**
     * The default {@link GardenBO} result search ordering.
     */
    private static final GardenOrderColumn DEFAULT_ORDER_COLUM = GardenOrderColumn.CREATION_DATE;
    private static final OrderWay DEFAULT_ORDER_WAY = OrderWay.ASC;
    private static final ResultOrder<GardenOrderColumn> DEFAULT_ORDER = new ResultOrder<GardenOrderColumn>(DEFAULT_ORDER_COLUM, DEFAULT_ORDER_WAY);

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByUser(final Long pIdUserPartialBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("owner", pIdUserPartialBO);
        return findPaginationQuery(GET_GARDEN_BY_USER, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByCityAndProduct(final Integer pIdRefCityBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("city", pIdRefCityBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_GARDEN_BY_CITY, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByRegionAndProduct(final Integer pIdRefRegionBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("region", pIdRefRegionBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_GARDEN_BY_REGION, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByStateAndProduct(final Integer pIdRefStateBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("state", pIdRefStateBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_GARDEN_BY_STATE, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByCountryAndProduct(final Integer pIdRefCountryBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("country", pIdRefCountryBO);
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_GARDEN_BY_COUNTRY, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public List<GardenBO> getGardenByProduct(final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("refProduct", pIdRefProductBO);
        return findPaginationQuery(GET_GARDEN_BY_PRODUCT, parameters, pTechnicalInformation);
    }

    @Override
    public List<GardenBO> searchGarden(Double pLatitudeUp, Double pLatitudeDown, Double pLongitudeUp, Double pLongitudeDown, Integer pRefProductId, TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("latitudeUp", pLatitudeUp);
        parameters.put("latitudeDown", pLatitudeDown);
        parameters.put("longitudeUp", pLongitudeUp);
        parameters.put("longitudeDown", pLongitudeDown);
        parameters.put("refProductId", pRefProductId);
        return findPaginationQuery(SEARCH_GARDEN, parameters, pTechnicalInformation);
    }

    /** {@inheritDoc} */
    @Override
    public ResultOrder<GardenOrderColumn> getDefaultOrder() {
        return DEFAULT_ORDER;
    }

    /** {@inheritDoc} */
    @Override
    protected Class<GardenBO> getType() {
        return GardenBO.class;
    }
}