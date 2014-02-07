package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
import org.mdubois.freeveggie.service.msg.RefRegionMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefCityBOToMsgConverterTest extends AbstractConverterTest<RefCityMsg, RefCityBO> {

    @Test
    public void testConvertFull() {
        RefCityMsg expectedMessage = getExpectedMessage();
        RefCityBO source = getBusinessObject();
        final RefCityBOToMsgConverter converter = getConverter();
        final RefRegionBO regionBO = new RefRegionBO();
        source.setRegion(regionBO);

        new Expectations() {

            @Mocked
            private Converter<RefRegionMsg, RefRegionBO> refRegionBOConverter;

            {
                Deencapsulation.setField(converter, refRegionBOConverter);

                refRegionBOConverter.convert(regionBO);
                returns(null);

            }
        };

        RefCityMsg result = converter.convert(source);

        ReflectionAssert.assertLenientEquals(expectedMessage, result);

    }

    @Override
    public RefCityMsg getExpectedMessage() {
        RefCityMsg msg = new RefCityMsg();
        msg.setId(23);
        msg.setName("name");
        msg.setZipCode(Long.valueOf("123"));
        return msg;
    }

    @Override
    public RefCityBO getBusinessObject() {
        RefCityBO bo = new RefCityBO();
        bo.setId(23);
        bo.setName("name");
        bo.setZipCode("123");
        return bo;
    }

    @Override
    public RefCityBOToMsgConverter getConverter() {
        return new RefCityBOToMsgConverter();
    }
}
