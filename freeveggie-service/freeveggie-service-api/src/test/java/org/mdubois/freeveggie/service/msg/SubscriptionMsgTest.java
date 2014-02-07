package org.mdubois.freeveggie.service.msg;

import java.util.Map;
import org.junit.*;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class SubscriptionMsgTest extends MessageTest<SubscriptionMsg> {

    public SubscriptionMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieNewsletter(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate4() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setShareGardenInformation(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setSharePersonalInformation(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate6() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.FALSE);
        instance.setSharePersonalInformation(Boolean.TRUE);
        instance.setShareGardenInformation(Boolean.TRUE);
        instance.setFreeveggieNewsletter(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.TRUE);
        instance.setSharePersonalInformation(Boolean.FALSE);
        instance.setShareGardenInformation(Boolean.TRUE);
        instance.setFreeveggieNewsletter(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate8() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.TRUE);
        instance.setSharePersonalInformation(Boolean.TRUE);
        instance.setShareGardenInformation(Boolean.FALSE);
        instance.setFreeveggieNewsletter(Boolean.TRUE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate9() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.TRUE);
        instance.setSharePersonalInformation(Boolean.TRUE);
        instance.setShareGardenInformation(Boolean.TRUE);
        instance.setFreeveggieNewsletter(Boolean.FALSE);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        SubscriptionMsg instance = new SubscriptionMsg();
        instance.setFreeveggieAgreement(Boolean.TRUE);
        instance.setSharePersonalInformation(Boolean.TRUE);
        instance.setShareGardenInformation(Boolean.TRUE);
        instance.setFreeveggieNewsletter(Boolean.TRUE);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        return null;
    }
}
