package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class RelationshipMsgTest extends MessageTest<RelationshipMsg> {

    public RelationshipMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.setRequest("The message request");
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.setRequest("The message request");
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.setRequest("The message request");
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        //Message to short
        instance.setRequest("1234");
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        //Message to long
        instance.setRequest("requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest"
                + "requestrequestrequestrequestrequestrequest");
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        RelationshipMsg instance = new RelationshipMsg();
        instance.setRequest("The message request");
        instance.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(123L);
        instance.setSender(sender);
        PartialUserMsg recipent = new PartialUserMsg();
        recipent.setId(12L);
        instance.setRecipient(recipent);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(RelationshipType.class, RelationshipType.FREEVEGGIE_FRIEND);
        toReturn.put(PartialUserMsg.class, new PartialUserMsg());
        toReturn.put(RelationshipStatus.class, RelationshipStatus.PENDING);
        return toReturn;
    }
}
