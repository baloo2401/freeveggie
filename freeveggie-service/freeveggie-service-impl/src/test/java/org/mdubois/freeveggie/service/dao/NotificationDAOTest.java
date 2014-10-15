package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.dao.impl.NotificationDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class NotificationDAOTest {

    @Mocked
    private Message message;
    @Mocked
    private Session session;
    @Mocked
    private Transport transport;

    @Test
    public void sendCreationUserNotice() throws BusinessException, MessagingException {
        final INotificationDAO notificationDAO = new NotificationDAO();
        final String email = "email";
        final String login = "login";

        final UserBO userBO = new UserBO();
        userBO.setId(12L);
        userBO.setEmail(email);
        userBO.setUsername(login);
        final String code = "697OGP9VR6Vuih==";
        new Expectations() {

            {
                Deencapsulation.setField(notificationDAO, session);

                Transport.send((Message) any);

            }
        };
        notificationDAO.sendCreationUserNotice(userBO, code);
    }

    @Test
    public void sendLostEmailNotice() throws BusinessException, MessagingException {
        final INotificationDAO notificationDAO = new NotificationDAO();
        final String email = "email";
        final String login = "login";
        final String password = "password";

        final UserBO userBO = new UserBO();
        userBO.setId(12L);
        userBO.setEmail(email);
        userBO.setUsername(login);
        final String code = "697OGP9VR6Vuih==";
        new Expectations() {

            {
                Deencapsulation.setField(notificationDAO, session);

                Transport.send((Message) any);

            }
        };
        notificationDAO.sendLostEmailNotice(userBO, code, password);
    }

    @Test(expected = TechnicalException.class)
    public void sendLostEmailNoticeMessagingException() throws BusinessException, MessagingException {
        final INotificationDAO notificationDAO = new NotificationDAO();
        final String email = "email";
        final String login = "login";
        final String password = "password";

        final UserBO userBO = new UserBO();
        userBO.setId(12L);
        userBO.setEmail(email);
        userBO.setUsername(login);
        final String code = "697OGP9VR6Vuih==";
        new Expectations() {

            {
                Deencapsulation.setField(notificationDAO, session);

                Transport.send((Message) any);
                result = new MessagingException();
            }
        };
        notificationDAO.sendLostEmailNotice(userBO, code, password);
    }

    @Test
    public void sendProductRequestNotice() throws BusinessException, MessagingException {
        final INotificationDAO notificationDAO = new NotificationDAO();
        final String email = "email";
        final String login = "login";

        final PartialUserBO userBO = new PartialUserBO();
        userBO.setId(12L);
        userBO.setEmail(email);
        userBO.setUsername(login);
        ProductRequestBO productRequest = new ProductRequestBO();
        productRequest.setRequester(userBO);
        GardenBO gardenBO = new GardenBO();
        gardenBO.setOwner(userBO);
        ProductBO productBO = new ProductBO();
        RefProductBO refProductBO = new RefProductBO();
        productBO.setReferenceProduct(refProductBO);
        productBO.setName("product name");
        productBO.setGarden(gardenBO);
        productRequest.setProduct(productBO);
        productRequest.setQuantity(1.2f);
        new Expectations() {

            {
                Deencapsulation.setField(notificationDAO, session);

                Transport.send((Message) any);

            }
        };
        notificationDAO.sendProductRequestNotice(productRequest);
    }

    @Test
    public void sendRelationshipRequestNotice() throws BusinessException, MessagingException {
        final INotificationDAO notificationDAO = new NotificationDAO();
        final String email = "email";
        final String login = "login";

        final PartialUserBO requester = new PartialUserBO();
        requester.setId(12L);
        requester.setEmail(email);
        requester.setUsername(login);
        final PartialUserBO recipient = new PartialUserBO();
        recipient.setId(123L);
        recipient.setEmail(email);
        recipient.setUsername(login);
        RelationShipBO relationshipBO = new RelationShipBO();
        relationshipBO.setSender(requester);
        relationshipBO.setRecipient(recipient);
        relationshipBO.setRequest("request");
        relationshipBO.setType(RelationshipType.FRIEND);
        new Expectations() {

            {
                Deencapsulation.setField(notificationDAO, session);

                Transport.send((Message) any);

            }
        };
        notificationDAO.sendRelationshipRequestNotice(relationshipBO);
    }
}
