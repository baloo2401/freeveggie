package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class ProductLikeMsgTest extends MessageTest<ProductLikeMsg> {

    public ProductLikeMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        ProductLikeMsg instance = new ProductLikeMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        ProductLikeMsg instance = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        ProductLikeMsg instance = new ProductLikeMsg();
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
        ProductLikeMsg instance = new ProductLikeMsg();
        ProductMsg product = new ProductMsg();
        product.setId(12L);
        instance.setProduct(product);
        PartialUserMsg user = new PartialUserMsg();
        user.setId(167892L);
        instance.setWriter(user);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(ProductMsg.class, new ProductMsg());
        toReturn.put(PartialUserMsg.class, new PartialUserMsg());
        toReturn.put(EvaluationStatus.class, EvaluationStatus.SETTED);
        return toReturn;
    }
}
