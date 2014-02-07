package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class AddressMsgTest extends MessageTest<AddressMsg> {

    public AddressMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        AddressMsg instance = new AddressMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        AddressMsg instance = new AddressMsg();
        instance.setName("name");
        instance.setStreetNumber("12");
        instance.setStreetName("address");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        AddressMsg instance = new AddressMsg();
        instance.setName("name");
        instance.setStreetNumber("12");
        RefCityMsg city = new RefCityMsg();
        city.setId(1678);
        instance.setCity(city);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        AddressMsg instance = new AddressMsg();
        instance.setStreetNumber("12");
        instance.setStreetName("address");
        RefCityMsg city = new RefCityMsg();
        city.setId(1678);
        instance.setCity(city);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        AddressMsg instance = new AddressMsg();
        instance.setName("name");
        instance.setStreetNumber("12");
        instance.setStreetName("address");
        RefCityMsg city = new RefCityMsg();
        city.setId(1678);
        instance.setCity(city);
        
        instance.setStreetName("pinstance.getStreetName()");
        instance.setLocality("locality");
        instance.setAdministrativeAreaLevel1("pinstance.getAdministrativeAreaLevel1()");
        instance.setAdministrativeAreaLevel2("pinstance.getAdministrativeAreaLevel2()");
        instance.setCountry("pinstance.getCountry()");
        instance.setLatitude(1.0);
        instance.setLongitude(1.0);
        instance.setName("pinstance.getName()");
        instance.setPostalCode("pAddressBO.getPostalCode()");
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(RefCityMsg.class, new RefCityMsg());
        return toReturn;
    }
}
