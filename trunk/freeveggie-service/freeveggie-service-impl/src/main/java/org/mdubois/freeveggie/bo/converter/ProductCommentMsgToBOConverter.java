package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductCommentMsgToBOConverter implements BusinessObjectConverter<ProductCommentBO, ProductCommentMsg> {

    @Override
    public ProductCommentBO createNew(ProductCommentMsg pProductCommentMsg) {
        ProductCommentBO productCommentBO = new ProductCommentBO();
        productCommentBO.setComment(pProductCommentMsg.getComment());
        productCommentBO.setCreationDate(SystemTime.asDate());
        //productCommentBO.setProduct(null); have to be set by the service
        productCommentBO.setNote(pProductCommentMsg.getNote());
        productCommentBO.setStatus(EvaluationStatus.SETTED);
        //productCommentBO.setWriter(null); have to be set by the service
        return productCommentBO;
    }

    @Override
    public void update(ProductCommentBO pProductCommentBO, ProductCommentMsg pProductCommentMsg) {
        pProductCommentBO.setComment(pProductCommentMsg.getComment());
        //productCommentBO.setCreationDate(SystemTime.asDate()); should not be change
        //productCommentBO.setProduct(null); have to be set by the service
        pProductCommentBO.setNote(pProductCommentMsg.getNote());
        //pProductCommentBO.setStatus(pProductCommentMsg.getStatus()); should not be change
        //productCommentBO.setWriter(null); have to be set by the service
    }
}
