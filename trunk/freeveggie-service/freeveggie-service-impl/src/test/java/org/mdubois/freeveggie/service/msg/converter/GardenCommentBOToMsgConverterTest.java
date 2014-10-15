package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenCommentBOToMsgConverterTest extends AbstractConverterTest<GardenCommentMsg, GardenCommentBO> {

    @Mocked
    private Converter<GardenMsg, GardenBO> mockGardenBOToMsgConverter;

    @Mocked
    private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final GardenCommentBO productCommentBO = getBusinessObject();

        GardenCommentMsg expectedResult = getExpectedMessage();
        final GardenCommentBOToMsgConverter converter = getConverter();

        GardenBO productBO = new GardenBO();
        PartialUserBO writer = new PartialUserBO();

        productCommentBO.setGarden(productBO);
        productCommentBO.setWriter(writer);

        new Expectations() {

            {
                Deencapsulation.setField(converter, "gardenBOToMsgConverter", mockGardenBOToMsgConverter);
                Deencapsulation.setField(converter, "partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockGardenBOToMsgConverter.convert(productCommentBO.getGarden());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(productCommentBO.getWriter());
                returns(null);
            }
        };

        GardenCommentMsg result = converter.convert(productCommentBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public GardenCommentMsg getExpectedMessage() {

        GardenCommentMsg expectedResult = new GardenCommentMsg();
        expectedResult.setId(11L);
        expectedResult.setCreationDate(NOW);
        expectedResult.setStatus(EvaluationStatus.SETTED);
        expectedResult.setComment("comment");
        expectedResult.setNote(EvaluationNote.BAD);

        return expectedResult;
    }

    @Override
    public GardenCommentBO getBusinessObject() {
        GardenCommentBO productBO = new GardenCommentBO();
        productBO.setId(11L);
        productBO.setCreationDate(NOW);
        productBO.setStatus(EvaluationStatus.SETTED);
        productBO.setComment("comment");
        productBO.setNote(EvaluationNote.BAD);

        return productBO;
    }

    @Override
    public GardenCommentBOToMsgConverter getConverter() {
        return new GardenCommentBOToMsgConverter();
    }

}
