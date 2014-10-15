package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenCommentMsgToBOConverterTest extends BusinessObjectConverterTest<GardenCommentBO, GardenCommentMsg> {

    @Mocked
    private final SystemTime systemTime = null;

    private final PartialUserMsg userMsg = new PartialUserMsg();
    private final GardenMsg gardenMsg = new GardenMsg();

    private final GardenCommentMsgToBOConverter converter = new GardenCommentMsgToBOConverter();

    private static final Date NOW = new Date();

    @Override
    public GardenCommentBO getNewBusinessObject() {
        GardenCommentBO bo = new GardenCommentBO();
        bo.setComment("comment");
        bo.setCreationDate(NOW);
        bo.setNote(EvaluationNote.BAD);
        bo.setStatus(EvaluationStatus.SETTED);
        return bo;

    }

    @Override
    public GardenCommentMsg getNewMessage() {
        GardenCommentMsg msg = new GardenCommentMsg();
        msg.setComment("comment");
        msg.setNote(EvaluationNote.BAD);
        msg.setWriter(userMsg);
        msg.setGarden(gardenMsg);
        return msg;
    }

    @Override
    public BusinessObjectConverter<GardenCommentBO, GardenCommentMsg> getConverter() {
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
