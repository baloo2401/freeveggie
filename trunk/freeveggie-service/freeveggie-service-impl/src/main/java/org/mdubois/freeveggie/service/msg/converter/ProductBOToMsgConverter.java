package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.RefProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductBOToMsgConverter extends AbstractConverter<ProductMsg, ProductBO> implements Converter<ProductMsg, ProductBO> {

    /**
     * {@link Converter<GardenMsg, GardenBO>}
     */
    @Inject
    private Converter<GardenMsg, GardenBO> gardenBOToMsgConverter;
    /**
     * {@link Converter<RefProductMsg, RefProductBO>}
     */
    @Inject
    private Converter<RefProductMsg, RefProductBO> refProductBOToMsgConverter;
    /**
     * {@link Converter<RefProductMsg, RefProductBO>}
     */
    @Inject
    private Converter<PictureMsg, ProductPictureBO> productPictureBOToMsgConverter;

    @Override
    public ProductMsg doConvert(ProductBO pProductBO) {
        ProductMsg msg = new ProductMsg();
        msg.setId(pProductBO.getId());
        msg.setCultureMode(pProductBO.getCultureMode());
        msg.setCultureType(pProductBO.getCultureType());
        msg.setDescription(pProductBO.getDescription());
        msg.setExchangeType(pProductBO.getExchangeType());
        if (pProductBO.getGarden() != null) {
            msg.setGarden(gardenBOToMsgConverter.convert(pProductBO.getGarden()));
        }
        msg.setName(pProductBO.getName());
        msg.setQuantity(pProductBO.getQuantity());
        if(pProductBO.getReferenceProduct() != null){
            msg.setReferenceProduct(refProductBOToMsgConverter.convert(pProductBO.getReferenceProduct()));
        }
        if(pProductBO.getPictures() != null){
            msg.setPictureMsg(productPictureBOToMsgConverter.convert(pProductBO.getPictures()));
        }
        msg.setStatus(pProductBO.getStatus());
        return msg;
    }
}
