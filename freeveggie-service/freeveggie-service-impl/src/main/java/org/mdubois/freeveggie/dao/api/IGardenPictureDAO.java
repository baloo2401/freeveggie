package org.mdubois.freeveggie.dao.api;

import java.util.List;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;

/**
 * This class is the data access class of the {@link GardenPictureBO} entity.
 * @author  Mickael Dubois
 */
public interface IGardenPictureDAO extends IReadWriteDAO<GardenPictureBO, Long>{

    
    /**
     * The {@link GardenPictureBO} fetching the picture byte data since it is a lazy loaded field
     * @param pIdGardenPicutreBO
     * @return 
     */
    GardenPictureBO getGardenPictureByIdFetchImage(Long pIdGardenPicutreBO);
    
    /**
     * Get all {@link {@link GardenBO} of a write on a given {@link GardenBO}.
     * @param pIdGardenBO - The given {@link GardenBO} id
     * @param pTechnicalInformation - The {@link TechnicalInformation} to respect
     * @return A  {@link List} of {@link {@link GardenPictureBO}
     */
    List<GardenPictureBO> getGardenPictureByGarden(final Long pIdGardenBO);

}
