package org.mdubois.freeveggie.service.msg.converter;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class BestRatedProductBOToMsgConverterTest extends AbstractConverterTest<BestRatedProductMsg, BestRatedProductBO> {

    @Test
    public void testConvertFull() {

        final BestRatedProductBO mostSharedProductBO = getBusinessObject();

        BestRatedProductMsg expectedResult = getExpectedMessage();
        final BestRatedProductBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        Double averageNote = new Double(2.00);

        mostSharedProductBO.setProduct(productBO);
        mostSharedProductBO.setAverageNote(averageNote);

        new Expectations() {

            @Mocked
            private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);

                mockProductBOToMsgConverter.convert(mostSharedProductBO.getProduct());
                returns(null);

            }
        };

        BestRatedProductMsg result = converter.convert(mostSharedProductBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public BestRatedProductMsg getExpectedMessage() {
        BestRatedProductMsg expResult = new BestRatedProductMsg();
        expResult.setAverageNote(2.00);

        return expResult;
    }

    @Override
    public BestRatedProductBO getBusinessObject() {
        BestRatedProductBO pAddressBO = new BestRatedProductBO();
        pAddressBO.setAverageNote(2.00);

        return pAddressBO;
    }

    @Override
    public BestRatedProductBOToMsgConverter getConverter() {
        return new BestRatedProductBOToMsgConverter();
    }
}
