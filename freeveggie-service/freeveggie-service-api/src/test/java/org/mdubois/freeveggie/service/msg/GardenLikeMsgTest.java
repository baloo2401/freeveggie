package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class GardenLikeMsgTest extends MessageTest<GardenLikeMsg> {

    public GardenLikeMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        GardenLikeMsg instance = new GardenLikeMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        GardenLikeMsg instance = new GardenLikeMsg();
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        GardenLikeMsg instance = new GardenLikeMsg();
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
        GardenLikeMsg instance = new GardenLikeMsg();
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
        return toReturn;
    }
}
