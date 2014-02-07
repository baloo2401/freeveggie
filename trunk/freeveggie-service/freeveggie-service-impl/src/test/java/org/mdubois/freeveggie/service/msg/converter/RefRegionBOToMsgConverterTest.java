package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefRegionMsg;
import org.mdubois.freeveggie.service.msg.RefStateMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefRegionBOToMsgConverterTest extends AbstractConverterTest<RefRegionMsg, RefRegionBO> {

    @Test
    public void testConvertFull() {
        RefRegionMsg expectedMessage = getExpectedMessage();
        RefRegionBO source = getBusinessObject();
        final RefRegionBOToMsgConverter converter = getConverter();
        final RefStateBO stateBO = new RefStateBO();
        source.setState(stateBO);

        new Expectations() {

            @Mocked
            private Converter<RefStateMsg, RefStateBO> refStateBOConverter;

            {
                Deencapsulation.setField(converter, refStateBOConverter);

                refStateBOConverter.convert(stateBO);
                returns(null);

            }
        };

        RefRegionMsg result = converter.convert(source);

        ReflectionAssert.assertLenientEquals(expectedMessage, result);

    }

    @Override
    public RefRegionMsg getExpectedMessage() {
        RefRegionMsg msg = new RefRegionMsg();
        msg.setId(23);
        msg.setName("name");
        return msg;
    }

    @Override
    public RefRegionBO getBusinessObject() {
        RefRegionBO bo = new RefRegionBO();
        bo.setId(23);
        bo.setName("name");
        return bo;
    }

    @Override
    public RefRegionBOToMsgConverter getConverter() {
        return new RefRegionBOToMsgConverter();
    }
}
