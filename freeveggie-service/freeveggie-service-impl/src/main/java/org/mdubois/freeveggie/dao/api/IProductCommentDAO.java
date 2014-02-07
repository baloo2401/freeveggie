package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link ProductCommentBO} entity.
 * @author  Mickael Dubois
 */
public interface IProductCommentDAO extends IReadWriteDAO<ProductCommentBO, Long>{

    /**
     * Get all {@link ProductCommentBO} of a given {@link ProductBO}.
     * @param pIdProductBO - The given {@link ProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductCommentBO}
     */
    List<ProductCommentBO> getProductCommentByProduct(final Long pIdProductBO, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductCommentBO} write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductCommentBO}
     */
    List<ProductCommentBO> getProductCommentByWriter(final Long pIdUserWriterBO, final TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> pTechnicalInformation);

    /**
     * Get the unique {@link ProductCommentBO} of a {@link ProductBO} write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pIdProductBO - The {@link GardenBO} id on witch the garden was write
     *  @return A  {@link List} of {@link {@link GardenCommentBO}
     */
    ProductCommentBO getProductCommentByUserAndProduct(final Long pIdUserWriterBO, final Long pIdProductBO);
}
