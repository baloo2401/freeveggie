package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;

/**
 * This class is the data access class of the {@link ProductPictureBO} entity.
 * @author  Mickael Dubois
 */
public interface IProductPictureDAO extends IReadWriteDAO<ProductPictureBO, Long>{
    
    /**
     * The {@link ProductPictureBO} fetching the picture byte data since it is a lazy loaded field
     * @param pIdProductPicutreBO
     * @return 
     */
    ProductPictureBO getProductPictureByIdFetchImage(Long pIdProductPicutreBO);
    
    /**
     * Get all {@link {@link ProductBO} of a write on a given {@link ProductBO}.
     * @param pIdProductBO - The given {@link ProductBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link ProductPictureBO}
     */
    List<ProductPictureBO> getProductPictureByProduct(final Long pIdProductBO);

}
