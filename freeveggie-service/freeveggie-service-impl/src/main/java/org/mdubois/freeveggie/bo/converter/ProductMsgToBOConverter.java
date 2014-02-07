package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Dubois Mickael
 */
public class ProductMsgToBOConverter implements BusinessObjectConverter<ProductBO, ProductMsg> {

    @Override
    public ProductBO createNew(ProductMsg pProductMsg) {
        ProductBO productBO = new ProductBO();
        productBO.setCultureMode(pProductMsg.getCultureMode());
        productBO.setCultureType(pProductMsg.getCultureType());
        productBO.setDescription(pProductMsg.getDescription());
        productBO.setExchangeType(pProductMsg.getExchangeType());
        productBO.setName(pProductMsg.getName());
        productBO.setQuantity(pProductMsg.getQuantity());
        productBO.setStatus(Status.CREATED);
        return productBO;
    }

    @Override
    public void update(ProductBO pProductBO, ProductMsg pProductMsg) {
        pProductBO.setCultureMode(pProductMsg.getCultureMode());
        pProductBO.setCultureType(pProductMsg.getCultureType());
        pProductBO.setDescription(pProductMsg.getDescription());
        pProductBO.setExchangeType(pProductMsg.getExchangeType());
        pProductBO.setName(pProductMsg.getName());
        pProductBO.setQuantity(pProductMsg.getQuantity());
    }
}
