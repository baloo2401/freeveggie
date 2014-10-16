package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.PictureMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductPictureMsgToBOConverter implements BusinessObjectConverter<ProductPictureBO, PictureMsg> {

    @Override
    public ProductPictureBO createNew(PictureMsg pProductRequestMsg) {
        ProductPictureBO productPictureBO = new ProductPictureBO();
        productPictureBO.setCreationDate(SystemTime.asDate());
        productPictureBO.setMimeType(pProductRequestMsg.getMimeType());
        productPictureBO.setPicture(pProductRequestMsg.getPicture());
        //productPictureBO.setProduct(null); this should be done by the service
        return productPictureBO;
    }

    @Override
    public void update(ProductPictureBO pProductRequestBO, PictureMsg pProductRequestMsg) {
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
