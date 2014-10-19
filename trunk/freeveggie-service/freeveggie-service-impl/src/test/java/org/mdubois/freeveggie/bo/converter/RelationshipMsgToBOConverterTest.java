package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipMsgToBOConverterTest extends BusinessObjectConverterTest<RelationshipBO, RelationshipMsg> {

    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;
    private final RelationshipMsgToBOConverter converter = new RelationshipMsgToBOConverter();
    private final static Date NOW = new Date();

    private PartialUserMsg recipient = new PartialUserMsg();
    private PartialUserMsg sender = new PartialUserMsg();

    @Test(expected = UnsupportedOperationException.class)
    @Override
    public void testUpdate() {
        super.testUpdate();
    }

    @Override
    public RelationshipBO getNewBusinessObject() {
        RelationshipBO bo = new RelationshipBO();
        bo.setRequest("request");
        bo.setType(RelationshipType.FRIEND);
        bo.setCreationDate(NOW);
        bo.setStatus(RelationshipStatus.PENDING);
        return bo;

    }

    @Override
    public RelationshipMsg getNewMessage() {
        RelationshipMsg msg = new RelationshipMsg();
        msg.setRecipient(recipient);
        msg.setRequest("request");
        msg.setSender(sender);
        msg.setType(RelationshipType.FRIEND);
        return msg;
    }

    @Override
    public BusinessObjectConverter<RelationshipBO, RelationshipMsg> getConverter() {
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
