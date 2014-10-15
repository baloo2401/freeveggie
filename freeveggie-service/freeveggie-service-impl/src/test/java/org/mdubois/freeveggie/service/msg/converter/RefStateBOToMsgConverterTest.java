package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultcountry="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefCountryMsg;
import org.mdubois.freeveggie.service.msg.RefStateMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefStateBOToMsgConverterTest extends AbstractConverterTest<RefStateMsg, RefStateBO> {

    @Mocked
    private Converter<RefCountryMsg, RefCountryBO> refCountryBOConverter;

    @Test
    public void testConvertFull() {
        RefStateMsg expectedMessage = getExpectedMessage();
        RefStateBO source = getBusinessObject();
        final RefStateBOToMsgConverter converter = getConverter();
        final RefCountryBO countryBO = new RefCountryBO();
        source.setCountry(countryBO);

        new Expectations() {

            {
                Deencapsulation.setField(converter, refCountryBOConverter);

                refCountryBOConverter.convert(countryBO);
                returns(null);

            }
        };

        RefStateMsg result = converter.convert(source);

        ReflectionAssert.assertLenientEquals(expectedMessage, result);

    }

    @Override
    public RefStateMsg getExpectedMessage() {
        RefStateMsg msg = new RefStateMsg();
        msg.setId(23);
        msg.setName("name");
        return msg;
    }

    @Override
    public RefStateBO getBusinessObject() {
        RefStateBO bo = new RefStateBO();
        bo.setId(23);
        bo.setName("name");
        return bo;
    }

    @Override
    public RefStateBOToMsgConverter getConverter() {
        return new RefStateBOToMsgConverter();
    }
}
