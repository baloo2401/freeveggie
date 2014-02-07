package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link GardenBO} entity.
 * @author  Mickael Dubois
 */
public interface IGardenDAO extends IReadWriteDAO<GardenBO, Long>{

    /**
     * Get all {@link GardenBO} of a {@link UserPartialBO}.
     * @param pIdUserPartialBO - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByUser(final Long pIdUserPartialBO, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenBO} of a {@link RefProductBO}.
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByProduct(final Integer pIdRefProductBO, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenBO} of a {@link RefCityBO} having a given {@link RefProductBO}.
     * @param pIdRefCityBO - The {@link RefCityBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByCityAndProduct(final Integer pIdRefCityBO, final Integer pIdRefProductBO, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenBO} of a region having a given product.
     * @param pIdRefRegionBO - The given {@link RefRegionBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByRegionAndProduct(final Integer pIdRefRegionBO, final Integer pIdRefProductBO, final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenBO} of a region having a given product.
     * @param pIdRefStateBO - The given {@link RefStateBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByStateAndProduct(final Integer pIdRefStateBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenBO} of a region having a given product.
     * @param pIdRefCountryBO - The given {@link RefCountryBO} id
     * @param pIdRefProductBO - The given {@link RefProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> getGardenByCountryAndProduct(final Integer pIdRefCountryBO, final Integer pIdRefProductBO,  final TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);

    
    /**
     * Get all  {@link GardenBO} that have a specified product on a pDistance radius of a position
     * @param pLatitudeUp - The  upper latitude to start from
     * @param pLatitudeDown - The  lowest latitude to start from
     * @param pLongitudeUp - The upper longitude to start from
     * @param pLongitudeDown - The lowest longitude to start from
     * @param pRefProductId - The product we are looking for (or null for all)
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenBO}
     */
    List<GardenBO> searchGarden(Double pLatitudeUp, Double pLatitudeDown, Double pLongitudeUp, Double pLongitudeDown,  Integer pRefProductId,  TechnicalInformation<GardenCriteriaColumn, GardenOrderColumn> pTechnicalInformation);
}
