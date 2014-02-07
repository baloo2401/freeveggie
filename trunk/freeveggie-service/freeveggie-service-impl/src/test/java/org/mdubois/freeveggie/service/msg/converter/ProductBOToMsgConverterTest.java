package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductBOToMsgConverterTest extends AbstractConverterTest<ProductMsg, ProductBO> {


//    private static final RefProductBO REF_PRODUCT_BO = new RefProductBO();

    @Test
    public void testConvertFull() {

        final ProductBO productBO = getBusinessObject();
        ProductMsg expectedResult = getExpectedMessage();
        final ProductBOToMsgConverter converter = getConverter();
        final GardenBO gardenBO = new GardenBO();
        final ArrayList<ProductCommentBO> productComments = new ArrayList<ProductCommentBO>();
        productComments.add(new ProductCommentBO());
        final ArrayList<ProductLikeBO> productLikes = new ArrayList<ProductLikeBO>();
        productLikes.add(new ProductLikeBO());

        productBO.setGarden(gardenBO);
        productBO.setComments(productComments);
        productBO.setLikes(productLikes);

        new Expectations() {

            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "gardenBOToMsgConverter", mockGardenBOToMsgConverter);

                mockGardenBOToMsgConverter.convert(productBO.getGarden());
                returns(null);

            }
        };

        ProductMsg result = converter.convert(productBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public ProductMsg getExpectedMessage() {

        ProductMsg expectedResult = new ProductMsg();
        expectedResult.setId(11L);
        expectedResult.setCultureMode(CultureMode.GARDEN);
        expectedResult.setCultureType(CultureType.REGULAR);
        expectedResult.setDescription("Description");
        expectedResult.setExchangeType(ExchangeType.SELLS);
        expectedResult.setName("name");
        expectedResult.setQuantity(2.0F);
//        expectedResult.setReferenceProduct(REF_PRODUCT_BO);
        expectedResult.setStatus(Status.CREATED);

        return expectedResult;
    }

    @Override
    public ProductBO getBusinessObject() {

        ProductBO productBO = new ProductBO();
        productBO.setId(11L);
        productBO.setCultureMode(CultureMode.GARDEN);
        productBO.setCultureType(CultureType.REGULAR);
        productBO.setDescription("Description");
        productBO.setExchangeType(ExchangeType.SELLS);
        productBO.setName("name");
        productBO.setQuantity(2.0F);
//        productBO.setReferenceProduct(REF_PRODUCT_BO);
        productBO.setStatus(Status.CREATED);

        return productBO;
    }

    @Override
    public ProductBOToMsgConverter getConverter() {
        return new ProductBOToMsgConverter();
    }

}
