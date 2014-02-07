package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Expectations;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductMsgToBOConverterTest extends BusinessObjectConverterTest<ProductBO,ProductMsg>{

//    private static final RefProductBO REF_PRODUCT_BO = new RefProductBO();

    private final ProductMsgToBOConverter converter = new ProductMsgToBOConverter();

    @Override
    public ProductBO getNewBusinessObject() {
        ProductBO  bo = new ProductBO();
        bo.setCultureMode(CultureMode.GARDEN);
        bo.setCultureType(CultureType.REGULAR);
        bo.setDescription("description");
        bo.setExchangeType(ExchangeType.SELLS);
        bo.setName("name");
        bo.setQuantity(1.0F);
        bo.setStatus(Status.CREATED);
        return bo;


    }

    @Override
    public ProductMsg getNewMessage() {
        ProductMsg  msg = new ProductMsg();
        msg.setCultureMode(CultureMode.GARDEN);
        msg.setCultureType(CultureType.REGULAR);
        msg.setDescription("description");
        msg.setExchangeType(ExchangeType.SELLS);
        msg.setName("name");
        msg.setQuantity(1.0F);
//        msg.setReferenceProduct(REF_PRODUCT_BO);

        return msg;
    }

    @Override
    public BusinessObjectConverter<ProductBO,ProductMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            {

            }
        };
    }


}
