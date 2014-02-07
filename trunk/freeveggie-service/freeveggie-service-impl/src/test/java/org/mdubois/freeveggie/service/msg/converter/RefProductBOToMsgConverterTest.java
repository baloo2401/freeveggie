package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.bo.RefMonthBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefProductMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefProductBOToMsgConverterTest extends AbstractConverterTest<RefProductMsg, RefProductBO> {

    @Test
    public void testConvertFull() {
        RefProductMsg expectedMessage = getExpectedMessage();
        RefProductBO source = getBusinessObject();
        final RefProductBOToMsgConverter converter = getConverter();
        final List<RefMonthBO> seedSeason = new ArrayList<RefMonthBO>();
        RefMonthBO february = new RefMonthBO();
        seedSeason.add(february);
        final List<RefMonthBO> reapSeason = new ArrayList<RefMonthBO>();
        RefMonthBO march = new RefMonthBO();
        reapSeason.add(march);
        source.setSeedMonths(seedSeason);
        source.setReapMonths(reapSeason);

        new Expectations() {

            @Mocked
            private Converter<Month, RefMonthBO> refMontgBOConverter;

            {
                Deencapsulation.setField(converter, refMontgBOConverter);

                refMontgBOConverter.convert(reapSeason);
                returns(null);

                refMontgBOConverter.convert(seedSeason);
                returns(null);

            }
        };

        RefProductMsg result = converter.convert(source);

        ReflectionAssert.assertLenientEquals(expectedMessage, result);

    }

    @Override
    public RefProductMsg getExpectedMessage() {
        RefProductMsg msg = new RefProductMsg();
        msg.setCarbohydrate(2.0D);
        msg.setId(123);
        msg.setLipid(2.4D);
        msg.setName("name");
        msg.setPictureFilename("picture");
        msg.setProtein(3.5D);
        msg.setType(ProductType.FRUIT);
        return msg;
    }

    @Override
    public RefProductBO getBusinessObject() {
        RefProductBO bo = new RefProductBO();
        bo.setCarbohydrate(2.0D);
        bo.setId(123);
        bo.setLipid(2.4D);
        bo.setName("name");
        bo.setPictureFilename("picture");
        bo.setProtein(3.5D);
        bo.setType(ProductType.FRUIT);
        return bo;
    }

    @Override
    public RefProductBOToMsgConverter getConverter() {
        return new RefProductBOToMsgConverter();
    }
}
