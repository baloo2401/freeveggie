package org.mdubois.freeveggie.service.msg;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class ProductRequestMsgTest extends MessageTest<ProductRequestMsg> {

    public ProductRequestMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setMessage("The request message");
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setMessage("The request message");
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setMessage("The request message");
        instance.setQuantity(1.0f);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setMessage("The request message");
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        //Too short
        instance.setMessage("message");
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate8() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        //Too long
        instance.setMessage("messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage"
                + "messagemessagemessagemessagemessagemessagemessage");
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }



    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        ProductRequestMsg instance = new ProductRequestMsg();
        instance.setMessage("The request message");
        instance.setQuantity(1.0f);
        instance.setPickingDate(new Date());
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setRequester(user);
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(ProductMsg.class, new ProductMsg());
        toReturn.put(PartialUserMsg.class, new PartialUserMsg());
        toReturn.put(RequestStatus.class, RequestStatus.ACCEPTED);
        return toReturn;
    }
}
