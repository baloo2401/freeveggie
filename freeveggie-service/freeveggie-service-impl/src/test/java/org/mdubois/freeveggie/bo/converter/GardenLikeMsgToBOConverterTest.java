package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenLikeMsgToBOConverterTest extends BusinessObjectConverterTest<GardenLikeBO, GardenLikeMsg> {

    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;

    private PartialUserMsg userMsg = new PartialUserMsg();
    private GardenMsg gardenMsg = new GardenMsg();

    private final GardenLikeMsgToBOConverter converter = new GardenLikeMsgToBOConverter();

    private static final Date NOW = new Date();

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void testUpdate() {
        super.testUpdate();
    }

    @Override
    public GardenLikeBO getNewBusinessObject() {
        GardenLikeBO bo = new GardenLikeBO();
        bo.setCreationDate(NOW);
        bo.setStatus(EvaluationStatus.SETTED);
        return bo;

    }

    @Override
    public GardenLikeMsg getNewMessage() {
        GardenLikeMsg msg = new GardenLikeMsg();
        msg.setWriter(userMsg);
        msg.setGarden(gardenMsg);
        return msg;
    }

    @Override
    public BusinessObjectConverter<GardenLikeBO, GardenLikeMsg> getConverter() {
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
