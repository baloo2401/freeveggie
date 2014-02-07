package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class ProductMsgTest extends MessageTest<ProductMsg>{

    public ProductMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
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
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate8() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate9() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        ProductMsg instance = new ProductMsg();
        instance.setDescription("description");
        instance.setName("name");
        instance.setCultureMode(CultureMode.GARDEN);
        instance.setCultureType(CultureType.REGULAR);
        instance.setExchangeType(ExchangeType.SELLS);
        instance.setQuantity(1.222f);
        GardenMsg garden = new GardenMsg();
        garden.setId(12L);
        instance.setGarden(garden);
        RefProductMsg refProductMsg = new RefProductMsg();
        refProductMsg.setId(167892);
        instance.setReferenceProduct(refProductMsg);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(GardenMsg.class, new GardenMsg());
        toReturn.put(RefProductMsg.class, new RefProductMsg());
        toReturn.put(ExchangeType.class, ExchangeType.GIVES);
        toReturn.put(CultureMode.class, CultureMode.BALCONY);
        toReturn.put(CultureType.class, CultureType.BIO);
        toReturn.put(Status.class, Status.CREATED);
        return toReturn;
    }
}
