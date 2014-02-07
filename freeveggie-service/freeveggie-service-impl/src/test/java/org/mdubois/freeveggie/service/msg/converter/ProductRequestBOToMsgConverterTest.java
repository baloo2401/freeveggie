package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class ProductRequestBOToMsgConverterTest extends AbstractConverterTest<ProductRequestMsg, ProductRequestBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {
        ProductRequestMsg expectedMessage = getExpectedMessage();
        ProductRequestBO source = getBusinessObject();
        final ProductRequestBOToMsgConverter converter = getConverter();
        final ProductBO productBO = new ProductBO();
        final PartialUserBO userBO = new PartialUserBO();
        source.setRequester(userBO);
        source.setProduct(productBO);

        new Expectations() {

            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;
            @Mocked
            private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);


                mockProductBOToMsgConverter.convert(productBO);
                returns(null);


                mockPartialUserBOToMsgConverter.convert(userBO);
                returns(null);

            }
        };

        ProductRequestMsg result = converter.convert(source);

        ReflectionAssert.assertLenientEquals(expectedMessage, result);

    }

    @Override
    public ProductRequestMsg getExpectedMessage() {
        ProductRequestMsg msg = new ProductRequestMsg();
        msg.setAnswer("Answer");
        msg.setAnswerDate(NOW);
        msg.setCreationDate(NOW);
        msg.setMessage("message");
        msg.setPickingDate(NOW);
        msg.setQuantity(1.0F);
        msg.setStatus(RequestStatus.PENDING);
        return msg;
    }

    @Override
    public ProductRequestBO getBusinessObject() {
        ProductRequestBO bo = new ProductRequestBO();
        bo.setAnswer("Answer");
        bo.setAnswerDate(NOW);
        bo.setCreationDate(NOW);
        bo.setMessage("message");
        bo.setPickingDate(NOW);
        bo.setQuantity(1.0F);
        bo.setStatus(RequestStatus.PENDING);
        return bo;
    }

    @Override
    public ProductRequestBOToMsgConverter getConverter() {
        return new ProductRequestBOToMsgConverter();
    }
}
