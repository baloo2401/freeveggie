package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.RefProductOrderColumn;

/**
 * This class is the data access class of the {@link RefProductBO} entity.
 *
 * @author Mickael Dubois
 */
public interface IRefProductDAO extends IReadOnlyDAO<RefProductBO, Integer> {

    /**
     * Get all {@link RefProductBO} of a given {@link RefProductBO.ProductType}.
     *
     * @param pIdProductType - The give {@link RefProductBO.ProductType}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link RefProductBO}
     */
    List<RefProductBO> getRefProducts(final Integer pIdProductType, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link RefProductBO} starting of a by a given name.
     *
     * @param pName - The beginning of the product name we are looking for.
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link RefProductBO}
     */
    List<RefProductBO> getRefProductsByName(final String pName, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link RefProductBO} that can be reap during a given {@link List}
     * of {@link RefMonthBO}.
     *
     * @param pIdRefMonth - The {@link List} of {@link RefMonthBO} id that the
     * reference product we are looking for should be reaped.
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link RefProductBO}
     */
    List<RefProductBO> getRefProductsByReapSeason(final List<Integer> pIdRefMonth, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link RefProductBO} that can be seed during a given {@link List}
     * of {@link RefMonthBO}.
     *
     * @param pIdRefMonth - The {@link List} of {@link RefMonthBO} id that the
     * reference product we are looking for should be seeded
     * @param pTechnicalInformation - The {@link TechnicalInformation} to
     * respect
     * @return A {@link List} of {@link {@link RefProductBO}
     */
    List<RefProductBO> getRefProductsBySeedSeason(final List<Integer> pIdRefMonth, final TechnicalInformation<RefProductCriteriaColumn, RefProductOrderColumn> pTechnicalInformation);
}
