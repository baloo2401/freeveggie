package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.RefProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenBOToMsgConverter extends AbstractConverter<GardenMsg, GardenBO> implements Converter<GardenMsg, GardenBO> {

    /**
     * {@link Converter<AddressMsg, AddressBO>}
     */
    @Inject
    private Converter<AddressMsg, AddressBO> addressBOToMsgConverter;
    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;
    /**
     * {@link Converter<RefProductMsg, RefProductBO>}
     */
    @Inject
    private Converter<RefProductMsg, RefProductBO> refProductBOToMsgConverter;
    /**
     * {@link Converter<RefProductMsg, RefProductBO>}
     */
    @Inject
    private Converter<PictureMsg, GardenPictureBO> gardenPictureBOToMsgConverter;

    @Override
    public GardenMsg doConvert(GardenBO pGardenBO) {
        GardenMsg msg = new GardenMsg();
        msg.setId(pGardenBO.getId());
        if (pGardenBO.getAddress() != null) {
            msg.setAddress(addressBOToMsgConverter.convert(pGardenBO.getAddress()));
        }
        msg.setDescription(pGardenBO.getDescription());
        msg.setName(pGardenBO.getName());
        msg.setProducts(new ArrayList<ProductMsg>());
        if (pGardenBO.getOwner() != null) {
            msg.setOwner(partialUserBOToMsgConverter.convert(pGardenBO.getOwner()));
        }
        msg.setCreationDate(pGardenBO.getCreationDate());
        msg.setStatus(pGardenBO.getStatus());
        //We do not use the product convert otherwise we have a infinit recurent loop
        msg.setProducts(new ArrayList<ProductMsg>());
        if(pGardenBO.getProducts() != null) {
                for (ProductBO pProductBO : pGardenBO.getProducts()) {
                        ProductMsg productMsg = new ProductMsg();
                        productMsg.setId(pProductBO.getId());
                        productMsg.setCultureMode(pProductBO.getCultureMode());
                        productMsg.setCultureType(pProductBO.getCultureType());
                        productMsg.setDescription(pProductBO.getDescription());
                        productMsg.setExchangeType(pProductBO.getExchangeType());
                        productMsg.setName(pProductBO.getName());
                        productMsg.setQuantity(pProductBO.getQuantity());
                        if(pProductBO.getReferenceProduct() != null){
                                productMsg.setReferenceProduct(refProductBOToMsgConverter.convert(pProductBO.getReferenceProduct()));
                        }
                        productMsg.setStatus(pProductBO.getStatus());
                        msg.getProducts().add(productMsg);
                }
        }
        if(pGardenBO.getPictures() != null){
            msg.setPictureMsg(gardenPictureBOToMsgConverter.convert(pGardenBO.getPictures()));
        }
        return msg;
    }
}
