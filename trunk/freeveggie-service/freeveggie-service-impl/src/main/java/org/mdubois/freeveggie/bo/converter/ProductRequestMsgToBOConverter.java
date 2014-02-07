package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductRequestMsgToBOConverter implements BusinessObjectConverter<ProductRequestBO, ProductRequestMsg> {

    @Override
    public ProductRequestBO createNew(ProductRequestMsg pProductRequestMsg) {
        ProductRequestBO productRequestBO = new ProductRequestBO();
        productRequestBO.setPickingDate(pProductRequestMsg.getPickingDate());
        productRequestBO.setMessage(pProductRequestMsg.getMessage());
        productRequestBO.setCreationDate(SystemTime.asDate());
        productRequestBO.setQuantity(pProductRequestMsg.getQuantity());
        //productRequestBO.setProduct(null); have to be set by the service
        productRequestBO.setStatus(RequestStatus.PENDING);
        //productRequestBO.setRequester(null); have to be set by the service
        return productRequestBO;
    }

    @Override
    public void update(ProductRequestBO pProductRequestBO, ProductRequestMsg pProductRequestMsg) {
        throw new UnsupportedOperationException("This methode should never be called");
    }
}
