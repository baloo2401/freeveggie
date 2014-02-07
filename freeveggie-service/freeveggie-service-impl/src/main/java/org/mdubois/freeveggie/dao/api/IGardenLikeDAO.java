package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link GardenLikeBO} entity.
 * @author Mickael Dubois
 */
public interface IGardenLikeDAO extends IReadWriteDAO<GardenLikeBO, Long>{

    /**
     * Get all {@link GardenLikeBO} of a given {@link GardenBO}.
     * @param pIdGardenBO - The given {@link GardenBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenLikeBO}
     */
    List<GardenLikeBO> getGardenLikeByGarden(final Long pIdGardenBO, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenLikeBO} write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenLikeBO}
     */
    List<GardenLikeBO> getGardenLikeByWriter(final Long pIdUserWriterBO, final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation);

    /**
     * Get a product like
     * @param pIdUserWriterBO - The {@link UserPartialBO} id who wrote the like
     * @param pIdGardenBO - The {@link GardenBO} id on which the like was setted
     * @return {@link GardenLikeBO}
     */
    GardenLikeBO getGardenLikeByUserAndGarden(final Long pIdUserWriterBO, final Long pIdGardenBO);
}
