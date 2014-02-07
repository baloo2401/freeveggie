package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;

/**
 * This class is the data access class of the {@link GardenCommentBO} entity.
 * @author  Mickael Dubois
 */
public interface IGardenCommentDAO extends IReadWriteDAO<GardenCommentBO, Long>{

    /**
     * Get all {@link {@link GardenBO} of a write on a given {@link GardenBO}.
     * @param pIdGardenBO - The given {@link GardenBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenCommentBO}
     */
    List<GardenCommentBO> getGardenCommentByGarden(final Long pIdGardenBO, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link GardenCommentBO} of a write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     *  @return A  {@link List} of {@link {@link GardenCommentBO}
     */
    List<GardenCommentBO> getGardenCommentByWriter(final Long pIdUserWriterBO, final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation);

    /**
     * Get the unique {@link GardenCommentBO} of a {@link GardenBO} write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pIdGardenBO - The {@link GardenBO} on witch the garden was write
     *  @return A  {@link List} of {@link {@link GardenCommentBO}
     */
    GardenCommentBO getGardenCommentByUserAndGarden(final Long pIdUserWriterBO, final Long pIdGardenBO);
}
