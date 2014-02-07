package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import static org.mdubois.freeveggie.bo.GardenPictureBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IGardenPictureDAO;
import org.mdubois.freeveggie.framework.dao.ReadWriteDAO;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.service.Pagination;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@ManagedBean
@Interceptors({
    DAOInterceptor.class
})
public class GardenPictureDAO extends ReadWriteDAO<GardenPictureBO, Long> implements IGardenPictureDAO {

    
    @Override
    public GardenPictureBO getGardenPictureByIdFetchImage(Long pIdProductPictureBO) {
        //This make the lazy load
        GardenPictureBO gardenPictureBO =  get(pIdProductPictureBO);
        //We manually call the image to load 
        gardenPictureBO.getPicture();
        return gardenPictureBO;
    }
    
    @Override
    public List<GardenPictureBO> getGardenPictureByGarden(Long pIdGardenBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("garden", pIdGardenBO);
        return findPaginationQuery(GET_GARDEN_PICTURE_BY_GARDEN, parameters, (Pagination) null);
    }

   
    /** {@inheritDoc} */
    @Override
    protected Class<GardenPictureBO> getType() {
        return GardenPictureBO.class;
    }
}
