package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import org.junit.Test;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.service.msg.PictureMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenPictureBOToMsgConverterTest extends AbstractConverterTest<PictureMsg, GardenPictureBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final GardenPictureBO gardenPictureBO = getBusinessObject();

        PictureMsg expectedResult = getExpectedMessage();
        final GardenPictureBOToMsgConverter converter = getConverter();

        GardenBO gardenBO = new GardenBO();
        gardenBO.setId(10L);
        gardenPictureBO.setGarden(gardenBO);
        gardenPictureBO.setMimeType("getMimeType");
        gardenPictureBO.setPicture("GardenRequestMsg.getPicture()".getBytes());

        PictureMsg result = converter.convert(gardenPictureBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public PictureMsg getExpectedMessage() {

        PictureMsg expectedResult = new PictureMsg();
        expectedResult.setId(11L);
        expectedResult.setMimeType("getMimeType");
        expectedResult.setPicture("GardenRequestMsg.getPicture()".getBytes());
        expectedResult.setObjId(10L);

        return expectedResult;
    }

    @Override
    public GardenPictureBO getBusinessObject() {
        GardenPictureBO gardenPictureBO = new GardenPictureBO();
        gardenPictureBO.setId(11L);
        gardenPictureBO.setCreationDate(NOW);
        gardenPictureBO.setMimeType("getMimeType");
        gardenPictureBO.setPicture("GardenRequestMsg.getPicture()".getBytes());
        final GardenBO gardenBO = new GardenBO();
        gardenBO.setId(10L);
        gardenPictureBO.setGarden(gardenBO);
        return gardenPictureBO;
    }

    @Override
    public GardenPictureBOToMsgConverter getConverter() {
        return new GardenPictureBOToMsgConverter();
    }

}
