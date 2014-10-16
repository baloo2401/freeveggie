package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.PictureMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductPictureMsgToBOConverterTest extends BusinessObjectConverterTest<ProductPictureBO, PictureMsg> {

    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;

    private final ProductPictureMsgToBOConverter converter = new ProductPictureMsgToBOConverter();

    private static final Date NOW = new Date();

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void testUpdate() {
        super.testUpdate();
    }

    @Override
    public ProductPictureBO getNewBusinessObject() {
        ProductPictureBO bo = new ProductPictureBO();
        bo.setCreationDate(NOW);
        bo.setMimeType("getMimeType");
        bo.setPicture("ProductRequestMsg.getPicture()".getBytes());
        return bo;

    }

    @Override
    public PictureMsg getNewMessage() {
        PictureMsg msg = new PictureMsg();
        msg.setCreationDate(NOW);
        msg.setMimeType("getMimeType");
        msg.setPicture("ProductRequestMsg.getPicture()".getBytes());
        return msg;
    }

    @Override
    public BusinessObjectConverter<ProductPictureBO, PictureMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations() {

            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }

}
