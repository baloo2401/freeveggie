package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductCommentBOToMsgConverterTest extends AbstractConverterTest<ProductCommentMsg, ProductCommentBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final ProductCommentBO productCommentBO = getBusinessObject();

        ProductCommentMsg expectedResult = getExpectedMessage();
        final ProductCommentBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        PartialUserBO writer = new PartialUserBO();

        productCommentBO.setProduct(productBO);
        productCommentBO.setWriter(writer);

        new Expectations() {

            @Mocked
            private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);
                Deencapsulation.setField(converter,"partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockProductBOToMsgConverter.convert(productCommentBO.getProduct());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(productCommentBO.getWriter());
                returns(null);
            }
        };

        ProductCommentMsg result = converter.convert(productCommentBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public ProductCommentMsg getExpectedMessage() {

        ProductCommentMsg expectedResult = new ProductCommentMsg();
        expectedResult.setId(11L);
        expectedResult.setCreationDate(NOW);
        expectedResult.setStatus(EvaluationStatus.SETTED);
        expectedResult.setComment("comment");
        expectedResult.setNote(EvaluationNote.BAD);

        return expectedResult;
    }

    @Override
    public ProductCommentBO getBusinessObject() {
        ProductCommentBO productBO = new ProductCommentBO();
        productBO.setId(11L);
        productBO.setCreationDate(NOW);
        productBO.setStatus(EvaluationStatus.SETTED);
        productBO.setComment("comment");
        productBO.setNote(EvaluationNote.BAD);

        return productBO;
    }

    @Override
    public ProductCommentBOToMsgConverter getConverter() {
        return new ProductCommentBOToMsgConverter();
    }

}
