package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class GardenMsgTest extends MessageTest<GardenMsg>{

    public GardenMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.setDescription("THe message description");
        instance.setName("name");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.setDescription("THe message description");
        instance.setName("name");
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.setDescription("The message description");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.setName("name");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        //Too short
        instance.setDescription("10");
        instance.setName("name");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        //Too long
        instance.setDescription("descriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription"
                + "descriptiondescriptiondescriptiondescriptiondescription");
        instance.setName("name");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        GardenMsg instance = new GardenMsg();
        instance.setDescription("THe message description");
        instance.setName("name");
        AddressMsg address = new AddressMsg();
        address.setId(12L);
        instance.setAddress(address);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setOwner(user);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(AddressMsg.class, new AddressMsg());
        toReturn.put(PartialUserMsg.class, new PartialUserMsg());
        toReturn.put(Status.class, Status.CREATED);
        return toReturn;
    }
}
