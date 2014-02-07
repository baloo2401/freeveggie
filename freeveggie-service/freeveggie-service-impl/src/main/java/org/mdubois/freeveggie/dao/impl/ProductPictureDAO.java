package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.ManagedBean;
import javax.interceptor.Interceptors;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import static org.mdubois.freeveggie.bo.ProductPictureBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.dao.api.IProductPictureDAO;
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
public class ProductPictureDAO extends ReadWriteDAO<ProductPictureBO, Long> implements IProductPictureDAO {

    @Override
    public ProductPictureBO getProductPictureByIdFetchImage(Long pIdProductPicutreBO) {
        //This make the lazy load
        ProductPictureBO picturePictureBO =  get(pIdProductPicutreBO);
        //We manually call the image to load 
        picturePictureBO.getPicture();
        return picturePictureBO;
    } 
   
    @Override
    public List<ProductPictureBO> getProductPictureByProduct(Long pIdProductBO) {
        Map<String, Object> parameters = new TreeMap<String, Object>();
        parameters.put("product", pIdProductBO);
        return findPaginationQuery(GET_PRODUCT_PICTURE_BY_PRODUCT, parameters, (Pagination) null);
    }

   
    /** {@inheritDoc} */
    @Override
    protected Class<ProductPictureBO> getType() {
        return ProductPictureBO.class;
    }
}
