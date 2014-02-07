package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
import org.mdubois.freeveggie.framework.utils.SystemTime;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipMsgToBOConverterTest extends BusinessObjectConverterTest<RelationShipBO,RelationShipMsg>{


    private final RelationshipMsgToBOConverter converter = new RelationshipMsgToBOConverter();
    private final static Date NOW = new Date();

    private PartialUserMsg recipient = new PartialUserMsg();
    private PartialUserMsg sender = new PartialUserMsg();

    @Override
    public RelationShipBO getNewBusinessObject() {
        RelationShipBO  bo = new RelationShipBO();
        bo.setRequest("request");
        bo.setType(RelationshipType.FRIEND);
        bo.setCreationDate(NOW);
        bo.setStatus(RelationshipStatus.PENDING);
        return bo;


    }

    @Override
    public RelationShipMsg getNewMessage() {
        RelationShipMsg  msg = new RelationShipMsg();
        msg.setRecipient(recipient);
        msg.setRequest("request");
        msg.setSender(sender);
        msg.setType(RelationshipType.FRIEND);
        return msg;
    }

    @Override
    public BusinessObjectConverter<RelationShipBO,RelationShipMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            @Mocked
            @SuppressWarnings("unused")
            private final SystemTime systemTime = null;
            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }


}
