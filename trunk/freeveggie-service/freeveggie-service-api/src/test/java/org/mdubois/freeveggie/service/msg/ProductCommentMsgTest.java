package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class ProductCommentMsgTest extends MessageTest<ProductCommentMsg> {

    public ProductCommentMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        ProductCommentMsg instance = new ProductCommentMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        ProductCommentMsg instance = new ProductCommentMsg();
        instance.setComment("The message comment");
        instance.setNote(EvaluationNote.BAD);
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        ProductCommentMsg instance = new ProductCommentMsg();
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
        ProductCommentMsg instance = new ProductCommentMsg();
        instance.setComment("The message comment");
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
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
        ProductCommentMsg instance = new ProductCommentMsg();
        instance.setNote(EvaluationNote.BAD);
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
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
        ProductCommentMsg instance = new ProductCommentMsg();
        //Too short
        instance.setComment("123");
        instance.setNote(EvaluationNote.BAD);
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
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
        ProductCommentMsg instance = new ProductCommentMsg();
        //Too long
        instance.setComment("The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment"
                + "The message commentThe message commentThe message comment");
        instance.setNote(EvaluationNote.BAD);
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
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
        ProductCommentMsg instance = new ProductCommentMsg();
        instance.setComment("The message comment");
        instance.setNote(EvaluationNote.BAD);
        ProductMsg Product = new ProductMsg();
        Product.setId(12L);
        instance.setProduct(Product);
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
        toReturn.put(EvaluationNote.class, EvaluationNote.GOOD);
        return toReturn;
    }
}
