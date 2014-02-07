package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class UserMsgTest extends MessageTest<UserMsg>{

    public UserMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email@email.fr");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setLastname("lastname");
        instance.setPassword("password");
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        instance.setSubscription(subscriptionMsg);
        instance.setUsername("usernam");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email.fr");
        instance.setFirstname("firstname");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setLastname("lastname");
        instance.setPassword("password");
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        instance.setSubscription(subscriptionMsg);
        instance.setUsername("usernam");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email@email.fr");
        instance.setFirstname("firstname");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setPassword("password");
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        instance.setSubscription(subscriptionMsg);
        instance.setUsername("usernam");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate7() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email@email.fr");
        instance.setFirstname("firstname");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setLastname("lastname");
        instance.setPassword("password");
        instance.setUsername("usernam");
        instance.validate();
    }


    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate8() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email@email.fr");
        instance.setFirstname("firstname");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setLastname("lastname");
        instance.setPassword("password");
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        instance.setSubscription(subscriptionMsg);
        instance.validate();
    }


    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        UserMsg instance = new UserMsg();
        instance.setEmail("email@email.fr");
        instance.setFirstname("firstname");
        AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(1L);
        instance.setHomeAdresse(addressMsg);
        instance.setLastname("lastname");
        instance.setPassword("password");
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        instance.setSubscription(subscriptionMsg);
        instance.setUsername("usernam");
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(AddressMsg.class, new AddressMsg());
        toReturn.put(ProfileMsg.class, new ProfileMsg());
        toReturn.put(SubscriptionMsg.class, new SubscriptionMsg());
        toReturn.put(UserStatus.class, UserStatus.NEW);
        toReturn.put(UserRole.class, UserRole.USER);
        return toReturn;
    }
}
