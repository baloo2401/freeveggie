package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class LastExchangeProductBOToMsgConverterTest extends AbstractConverterTest<LastExchangeProductMsg, LastExchangeProductBO> {

    @Mocked
    private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final LastExchangeProductBO mostSharedProductBO = getBusinessObject();

        LastExchangeProductMsg expectedResult = getExpectedMessage();
        final LastExchangeProductBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        Double quantity = new Double(1.22);

        mostSharedProductBO.setProduct(productBO);
        mostSharedProductBO.setLastExchangeDate(NOW);

        new Expectations() {

            {
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);

                mockProductBOToMsgConverter.convert(mostSharedProductBO.getProduct());
                returns(null);

            }
        };

        LastExchangeProductMsg result = converter.convert(mostSharedProductBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public LastExchangeProductMsg getExpectedMessage() {

        LastExchangeProductMsg expectedResult = new LastExchangeProductMsg();
        expectedResult.setLastExchangeDate(NOW);

        return expectedResult;
    }

    @Override
    public LastExchangeProductBO getBusinessObject() {
        LastExchangeProductBO productBO = new LastExchangeProductBO();
        productBO.setLastExchangeDate(NOW);

        return productBO;
    }

    @Override
    public LastExchangeProductBOToMsgConverter getConverter() {
        return new LastExchangeProductBOToMsgConverter();
    }

}
