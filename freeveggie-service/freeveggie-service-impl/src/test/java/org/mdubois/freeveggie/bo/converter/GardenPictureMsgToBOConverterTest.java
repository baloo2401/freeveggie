package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.PictureMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenPictureMsgToBOConverterTest extends BusinessObjectConverterTest<GardenPictureBO, PictureMsg> {

    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;

    private PartialUserMsg userMsg = new PartialUserMsg();
    private GardenMsg gardenMsg = new GardenMsg();

    private final GardenPictureMsgToBOConverter converter = new GardenPictureMsgToBOConverter();

    private static final Date NOW = new Date();

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void testUpdate() {
        super.testUpdate();
    }

    @Override
    public GardenPictureBO getNewBusinessObject() {
        GardenPictureBO bo = new GardenPictureBO();
        bo.setCreationDate(NOW);
        bo.setMimeType("getMimeType");
        bo.setPicture("GardenRequestMsg.getPicture()".getBytes());
        return bo;

    }

    @Override
    public PictureMsg getNewMessage() {
        PictureMsg msg = new PictureMsg();
        msg.setCreationDate(NOW);
        msg.setMimeType("getMimeType");
        msg.setPicture("GardenRequestMsg.getPicture()".getBytes());
        return msg;
    }

    @Override
    public BusinessObjectConverter<GardenPictureBO, PictureMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations() {

            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }

}
