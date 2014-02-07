package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class GardenCommentMsgTest extends MessageTest<GardenCommentMsg>{

    public GardenCommentMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        instance.setComment("The message comment");
        instance.setNote(EvaluationNote.BAD);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        instance.setComment("The message comment");
        instance.setNote(EvaluationNote.BAD);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        instance.setComment("The message comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        instance.setNote(EvaluationNote.BAD);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        //Too short
        instance.setComment("123");
        instance.setNote(EvaluationNote.BAD);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        //Too short
        instance.setComment("commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment"
                + "commentcommentcommentcommentcommentcommentcomment");
        instance.setNote(EvaluationNote.BAD);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        GardenCommentMsg instance = new GardenCommentMsg();
        //Too short
        instance.setComment("The message comment");
        instance.setNote(EvaluationNote.BAD);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(GardenMsg.class, new GardenMsg());
        toReturn.put(PartialUserMsg.class, new PartialUserMsg());
        toReturn.put(EvaluationStatus.class, EvaluationStatus.SETTED);
        toReturn.put(EvaluationNote.class, EvaluationNote.GOOD);
        return toReturn;
    }
}
