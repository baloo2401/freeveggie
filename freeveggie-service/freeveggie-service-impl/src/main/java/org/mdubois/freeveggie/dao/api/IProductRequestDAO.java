package org.mdubois.freeveggie.dao.api;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
// </editor-fold>

/**
 * This class is the data access class of the {@link ProductRequestBO} entity.
 * @author Mickael Dubois
 */
public interface IProductRequestDAO extends IReadWriteDAO<ProductRequestBO, Long>{

    /**
     * Get all {@link ProductRequestBO} of a given {@link ProductBO}.
     * @param pIdProductBO - The given {@link ProductBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestBO> getProductRequestByProduct(final Long pIdProductBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductRequestBO} made on all {@link ProductBO} of a given {@link GardenBO}.
     * @param pIdGardenBO - The given {@link GardenBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestBO> getProductRequestByGarden(final Long pIdGardenBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductRequestBO} receive by a given {@link UserPartialBO}.
     * @param pIdUserPartialBO - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestBO> getProductRequestReceived(final Long pIdUserPartialBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation);

    /**
     * Get all {@link ProductRequestBO} send by a given {@link UserPartialBO}.
     * @param pIdUserPartialBO - The given {@link UserPartialBO}
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductRequestBO}
     */
    List<ProductRequestBO> getProductRequestSend(final Long pIdUserPartialBO, final TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTechnicalInformation);

}
