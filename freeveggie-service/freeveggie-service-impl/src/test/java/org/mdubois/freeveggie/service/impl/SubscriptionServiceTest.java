package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.api.ISubscriptionDAO;
import org.mdubois.freeveggie.dao.api.IUserDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.api.ISubscriptionService;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class SubscriptionServiceTest {

    @Test
    public void create() throws BusinessException {
        final ISubscriptionService subscriptionService = new SubscriptionService();
        final Long newUserId = 239L;
        final Long adressId = 1239L;
        final AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(adressId);
        final CreateAccountMsg userMsg = new CreateAccountMsg();
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        subscriptionMsg.setFreeveggieAgreement(Boolean.TRUE);
        subscriptionMsg.setFreeveggieNewsletter(Boolean.TRUE);
        subscriptionMsg.setShareGardenInformation(Boolean.TRUE);
        subscriptionMsg.setSharePersonalInformation(Boolean.TRUE);
        userMsg.setSubscription(subscriptionMsg);
//        userMsg.setHomeAdresse(addressMsg);
        userMsg.setEmail("mail");
        userMsg.setFirstname("firstname");
        userMsg.setLastname("lastname");
        userMsg.setPassword("password");
        userMsg.setUsername("username");

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private ISubscriptionDAO subscriptionDAO;
            @Mocked
            private INotificationDAO notificationDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;

            {
                Deencapsulation.setField(subscriptionService, userDAO);
                Deencapsulation.setField(subscriptionService, addressDAO);
                Deencapsulation.setField(subscriptionService, subscriptionDAO);
                Deencapsulation.setField(subscriptionService, notificationDAO);
                Deencapsulation.setField(subscriptionService, addressMsgConverter);

                AddressBO addressBO = new AddressBO();
                addressMsgConverter.createNew((AddressMsg)any);
                returns(addressBO);

                userDAO.getUserByLogin("username");
                returns(null);

                userDAO.getUserByEmail("mail");
                returns(null);

                //TODO check parameter with with
                userDAO.save((UserBO)any);
                returns(newUserId);
                
                subscriptionDAO.save((SubscriptionBO)any);

                notificationDAO.sendCreationUserNotice((UserBO) any, anyString);
            }
        };

        Long id = subscriptionService.create(userMsg);
        Assert.assertEquals(id, newUserId);
    }

    @Test(expected = BusinessException.class)
    public void createUserAlreadyUsed() throws BusinessException {
        final ISubscriptionService subscriptionService = new SubscriptionService();
        final Long newUserId = 239L;
        final Long adressId = 1239L;
        final AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(adressId);
        final CreateAccountMsg userMsg = new CreateAccountMsg();
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        subscriptionMsg.setFreeveggieAgreement(Boolean.TRUE);
        subscriptionMsg.setFreeveggieNewsletter(Boolean.TRUE);
        subscriptionMsg.setShareGardenInformation(Boolean.TRUE);
        subscriptionMsg.setSharePersonalInformation(Boolean.TRUE);
        userMsg.setSubscription(subscriptionMsg);
//        userMsg.setHomeAdresse(addressMsg);
        userMsg.setEmail("mail");
        userMsg.setFirstname("firstname");
        userMsg.setLastname("lastname");
        userMsg.setPassword("password");
        userMsg.setUsername("username");

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;

            {
                Deencapsulation.setField(subscriptionService, userDAO);
                Deencapsulation.setField(subscriptionService, addressDAO);
                Deencapsulation.setField(subscriptionService, addressMsgConverter);

                AddressBO addressBO = new AddressBO();
                addressMsgConverter.createNew((AddressMsg)any);
                returns(addressBO);

                addressDAO.save(addressBO);

                UserBO userBO = new UserBO();
                userDAO.getUserByLogin("username");
                returns(userBO);

            }
        };

        Long id = subscriptionService.create(userMsg);
        Assert.assertEquals(id, newUserId);
    }

    @Test(expected = BusinessException.class)
    public void createEmailAlreadyUsed() throws BusinessException {
        final ISubscriptionService subscriptionService = new SubscriptionService();
        final Long newUserId = 239L;
        final Long adressId = 1239L;
        final AddressMsg addressMsg = new AddressMsg();
        addressMsg.setId(adressId);
        final CreateAccountMsg userMsg = new CreateAccountMsg();
        SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        subscriptionMsg.setFreeveggieAgreement(Boolean.TRUE);
        subscriptionMsg.setFreeveggieNewsletter(Boolean.TRUE);
        subscriptionMsg.setShareGardenInformation(Boolean.TRUE);
        subscriptionMsg.setSharePersonalInformation(Boolean.TRUE);
        userMsg.setSubscription(subscriptionMsg);
//        userMsg.setHomeAdresse(addressMsg);
        userMsg.setEmail("mail");
        userMsg.setFirstname("firstname");
        userMsg.setLastname("lastname");
        userMsg.setPassword("password");
        userMsg.setUsername("username");

        new Expectations() {

            @Mocked
            private IUserDAO userDAO;
            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> addressMsgConverter;
            {
                Deencapsulation.setField(subscriptionService, userDAO);
                Deencapsulation.setField(subscriptionService, addressDAO);
                Deencapsulation.setField(subscriptionService, addressMsgConverter);

                AddressBO addressBO = new AddressBO();
                addressMsgConverter.createNew((AddressMsg)any);
                returns(addressBO);
                
                addressDAO.save(addressBO);

                userDAO.getUserByLogin("username");
                returns(null);

                UserBO userBO = new UserBO();
                userDAO.getUserByEmail("mail");
                returns(userBO);

            }
        };

        Long id = subscriptionService.create(userMsg);
        Assert.assertEquals(id, newUserId);
    }
}
