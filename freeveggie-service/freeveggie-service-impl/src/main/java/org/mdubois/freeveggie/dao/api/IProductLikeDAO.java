package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link ProductLikeBO} entity.
 * @author Mickael Dubois
 */
public interface IProductLikeDAO extends IReadWriteDAO<ProductLikeBO, Long>{

    /**
     * Get all {@link ProductLikeBO} of a given {@link ProductBO}.
     * @param pIdProductBO - The given {@link ProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductLikeBO}
     */
    List<ProductLikeBO> getProductLikeByProduct(final Long pIdProductBO, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductLikeBO} write by a given {@link UserPartialBO}.
     * @param pIdUserWriterBO - The given {@link UserPartialBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductLikeBO}
     */
    List<ProductLikeBO> getProductLikeByWriter(final Long pIdUserWriterBO, final TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> pTechnicalInformation);

    /**
     * Get a product like
     * @param pIdUserWriterBO - The {@link UserPartialBO} id who wrote the like
     * @param pIdProductBO - The {@link ProductBO} id on which the like was setted
     * @return {@link ProductLikeBO}
     */
    ProductLikeBO getProductLikeByUserAndProduct(final Long pIdUserWriterBO, final Long pIdProductBO);
}
