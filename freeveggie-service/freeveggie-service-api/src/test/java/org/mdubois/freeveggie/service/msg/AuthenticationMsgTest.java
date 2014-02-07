package org.mdubois.freeveggie.service.msg;

import java.util.Map;
import org.junit.*;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class AuthenticationMsgTest extends MessageTest<AuthenticationMsg> {

    public AuthenticationMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        AuthenticationMsg instance = new AuthenticationMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        AuthenticationMsg instance = new AuthenticationMsg();
        instance.setLogin("login");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        AuthenticationMsg instance = new AuthenticationMsg();
        instance.setPassword("password");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        AuthenticationMsg instance = new AuthenticationMsg();
        instance.setPassword("password");
        instance.setLogin("login");
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        return null;
    }
}
