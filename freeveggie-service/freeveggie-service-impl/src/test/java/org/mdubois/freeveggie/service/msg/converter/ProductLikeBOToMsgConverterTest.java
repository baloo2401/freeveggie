package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductLikeBOToMsgConverterTest extends AbstractConverterTest<ProductLikeMsg, ProductLikeBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final ProductLikeBO productLikeBO = getBusinessObject();

        ProductLikeMsg expectedResult = getExpectedMessage();
        final ProductLikeBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        PartialUserBO writer = new PartialUserBO();

        productLikeBO.setProduct(productBO);
        productLikeBO.setWriter(writer);

        new Expectations() {

            @Mocked
            private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);
                Deencapsulation.setField(converter,"partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockProductBOToMsgConverter.convert(productLikeBO.getProduct());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(productLikeBO.getWriter());
                returns(null);
            }
        };

        ProductLikeMsg result = converter.convert(productLikeBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public ProductLikeMsg getExpectedMessage() {

        ProductLikeMsg expectedResult = new ProductLikeMsg();
        expectedResult.setId(11L);
        expectedResult.setCreationDate(NOW);
        expectedResult.setStatus(EvaluationStatus.SETTED);


        return expectedResult;
    }

    @Override
    public ProductLikeBO getBusinessObject() {
        ProductLikeBO productBO = new ProductLikeBO();
        productBO.setId(11L);
        productBO.setCreationDate(NOW);
        productBO.setStatus(EvaluationStatus.SETTED);

        return productBO;
    }

    @Override
    public ProductLikeBOToMsgConverter getConverter() {
        return new ProductLikeBOToMsgConverter();
    }

}
