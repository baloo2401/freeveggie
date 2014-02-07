package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenLikeBOToMsgConverterTest extends AbstractConverterTest<GardenLikeMsg, GardenLikeBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final GardenLikeBO gardenLikeBO = getBusinessObject();

        GardenLikeMsg expectedResult = getExpectedMessage();
        final GardenLikeBOToMsgConverter converter = getConverter();

        GardenBO gardenBO = new GardenBO();
        PartialUserBO writer = new PartialUserBO();

        gardenLikeBO.setGarden(gardenBO);
        gardenLikeBO.setWriter(writer);

        new Expectations() {

            @Mocked
            private Converter<GardenMsg, GardenBO> mockGardenBOToMsgConverter;

            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

            {
                Deencapsulation.setField(converter, "gardenBOToMsgConverter", mockGardenBOToMsgConverter);
                Deencapsulation.setField(converter,"partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockGardenBOToMsgConverter.convert(gardenLikeBO.getGarden());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(gardenLikeBO.getWriter());
                returns(null);
            }
        };

        GardenLikeMsg result = converter.convert(gardenLikeBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public GardenLikeMsg getExpectedMessage() {

        GardenLikeMsg expectedResult = new GardenLikeMsg();
        expectedResult.setId(11L);
        expectedResult.setCreationDate(NOW);
        expectedResult.setStatus(EvaluationStatus.SETTED);


        return expectedResult;
    }

    @Override
    public GardenLikeBO getBusinessObject() {
        GardenLikeBO gardenBO = new GardenLikeBO();
        gardenBO.setId(11L);
        gardenBO.setCreationDate(NOW);
        gardenBO.setStatus(EvaluationStatus.SETTED);

        return gardenBO;
    }

    @Override
    public GardenLikeBOToMsgConverter getConverter() {
        return new GardenLikeBOToMsgConverter();
    }

}
