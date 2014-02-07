package org.mdubois.freeveggie.framework.security;

import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;

/**
 *
 * @author Mickael Dubois
 */
public class UserMsgTest {

    /**
     * Test of getUserId method, of class ContextMsg.
     */
    @Test(expected = MessageValidationException.class)
    public void testValideFail() throws MessageValidationException {
        ContextMsg instance = new ContextMsg();
        instance.validate();
    }

    /**
     * Test of getUserId method, of class ContextMsg.
     */
    @Test
    public void testValide() throws MessageValidationException {
        ContextMsg instance = new ContextMsg();

        instance.setUser(new UserContext());
        instance.getUser().setId(123L);
        instance.validate();
    }
}
