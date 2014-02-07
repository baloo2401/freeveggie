package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductOrderColumn;

/**
 * This class is the data access class of the {@link ProductBO} entity.
 * @author  Mickael Dubois
 */
public interface IProductDAO extends IReadWriteDAO<ProductBO, Long>{

    /**
     * Get all {@link ProductBO} of a {@link UserPartialBO}.
     * @param pUserWriterBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByUser(final Long pUserWriterBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductBO} of a {@link UserPartialBO}.
     * @param pGardenBO - The given {@link GardenBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByGarden(final Long pGardenBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductBO} in a {@link RefCityBO} a given {@link RefProductBO}.
     * @param pIdRefCityBO - The given {@link RefCityBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByCityAndRefProduct(final Integer pIdRefCityBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductBO} in a {@link RefRegionBO} a given {@link RefProductBO}.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByRegionAndRefProduct(final Integer pIdRefRegionBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductBO} in a {@link RefStateBO} a given {@link RefProductBO}.
     * @param pIdRefStateBO - The given {@link RefStateBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByStateAndRefProduct(final Integer pIdRefStateBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductBO} in a {@link RefCountryBO} a given {@link RefProductBO}.
     * @param pIdRefCountryBO - The given {@link RefCountryBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductBO}
     */
    List<ProductBO> getProductByCountryAndRefProduct(final Integer pIdRefCountryBO, final Integer pIdRefProductBO, final TechnicalInformation<ProductCriteriaColumn, ProductOrderColumn> pTechnicalInformation);
}
