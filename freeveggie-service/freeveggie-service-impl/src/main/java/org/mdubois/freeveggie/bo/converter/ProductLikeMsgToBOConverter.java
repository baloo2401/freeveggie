package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductLikeMsgToBOConverter implements BusinessObjectConverter<ProductLikeBO, ProductLikeMsg> {

    @Override
    public ProductLikeBO createNew(ProductLikeMsg pProductCommentMsg) {
        ProductLikeBO productLikeBO = new ProductLikeBO();
        productLikeBO.setCreationDate(SystemTime.asDate());
        //ProductLikeBO.setProduct(null); have to be set by the service
        productLikeBO.setStatus(EvaluationStatus.SETTED);
        //ProductLikeBO.setWriter(null); have to be set by the service
        return productLikeBO;
    }

    @Override
    public void update(ProductLikeBO pProductLikeBO, ProductLikeMsg pProductCommentMsg) {
        //pProductLikeBO.setCreationDate(SystemTime.asDate()); should not be changed
        //ProductLikeBO.setProduct(null); have to be set by the service
        //pProductLikeBO.setStatus(pProductCommentMsg.getStatus()); have to be set by the service
        //ProductLikeBO.setWriter(null); have to be set by the service
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
