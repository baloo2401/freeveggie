package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import org.junit.Test;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductPictureBOToMsgConverterTest extends AbstractConverterTest<PictureMsg, ProductPictureBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final ProductPictureBO productPictureBO = getBusinessObject();

        PictureMsg expectedResult = getExpectedMessage();
        final ProductPictureBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        productBO.setId(10L);
        productPictureBO.setProduct(productBO);
        productPictureBO.setMimeType("getMimeType");
        productPictureBO.setPicture("ProductRequestMsg.getPicture()".getBytes());

        PictureMsg result = converter.convert(productPictureBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public PictureMsg getExpectedMessage() {

        PictureMsg expectedResult = new PictureMsg();
        expectedResult.setId(11L);
        expectedResult.setMimeType("getMimeType");
        expectedResult.setPicture("ProductRequestMsg.getPicture()".getBytes());
        expectedResult.setObjId(10L);

        return expectedResult;
    }

    @Override
    public ProductPictureBO getBusinessObject() {
        ProductPictureBO productPictureBO = new ProductPictureBO();
        productPictureBO.setId(11L);
        productPictureBO.setCreationDate(NOW);
        productPictureBO.setMimeType("getMimeType");
        productPictureBO.setPicture("ProductRequestMsg.getPicture()".getBytes());
        final ProductBO productBO = new ProductBO();
        productBO.setId(10L);
        productPictureBO.setProduct(productBO);
        return productPictureBO;
    }

    @Override
    public ProductPictureBOToMsgConverter getConverter() {
        return new ProductPictureBOToMsgConverter();
    }

}
