package org.mdubois.freeveggie.service.msg.converter;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class MostSharedProductBOToMsgConverterTest extends AbstractConverterTest<MostSharedProductMsg, MostSharedProductBO> {

    @Test
    public void testConvertFull() {

        final MostSharedProductBO mostSharedProductBO = getBusinessObject();

        MostSharedProductMsg expectedResult = getExpectedMessage();
        final MostSharedProductBOToMsgConverter converter = getConverter();

        ProductBO productBO = new ProductBO();
        Double quantity = new Double(2.00);

        mostSharedProductBO.setProduct(productBO);
        mostSharedProductBO.setQuantityShared(quantity);

        new Expectations() {

            @Mocked
            private Converter<ProductMsg, ProductBO> mockProductBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "productBOToMsgConverter", mockProductBOToMsgConverter);

                mockProductBOToMsgConverter.convert(mostSharedProductBO.getProduct());
                returns(null);

            }
        };

        MostSharedProductMsg result = converter.convert(mostSharedProductBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public MostSharedProductMsg getExpectedMessage() {
        MostSharedProductMsg expResult = new MostSharedProductMsg();
        expResult.setQuantityShared(2.00);

        return expResult;
    }

    @Override
    public MostSharedProductBO getBusinessObject() {
        MostSharedProductBO pAddressBO = new MostSharedProductBO();
        pAddressBO.setQuantityShared(2.00);

        return pAddressBO;
    }

    @Override
    public MostSharedProductBOToMsgConverter getConverter() {
        return new MostSharedProductBOToMsgConverter();
    }
}
