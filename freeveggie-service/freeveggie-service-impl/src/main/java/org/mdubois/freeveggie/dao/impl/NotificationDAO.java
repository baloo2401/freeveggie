package org.mdubois.freeveggie.dao.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.text.MessageFormat;
import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import javax.interceptor.Interceptors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.bo.UserBO;
import org.mdubois.freeveggie.dao.api.INotificationDAO;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.interceptor.DAOInterceptor;
import org.mdubois.freeveggie.framework.utils.SystemTime;
// </editor-fold>

/**
 *
 * @author mdubois
 */
@ManagedBean
@Interceptors({DAOInterceptor.class})
public class NotificationDAO implements INotificationDAO {

    // <editor-fold defaultstate="collapsed" desc="Lost password email">
    /**
     * The lost password email subject.
     */
    private static final String LOST_PASSWORD_EMAIL_SUBJECT = "Freeveggie - Lost password recovery";
    /**
     * The lost password email content.
     */
    private static final String LOST_PASSWORD_EMAIL_MESSAGE = "A request have been made to generate a new password for your account."
            + "<br /><br />"
            + "If this request have been made in purpose please click <a href=\"www.freeveggie.org/lostpasswordrecovery/{0}\">here</a>."
            + "<br /><br />"
            + "If this link doest work please copy-paste this address into your browser :"
            + "<br />"
            + "www.freeveggie.org/lostpasswordrecovery/{0}"
            + "<br /><br />"
            + "Your temporay password is : {1}"
            + "<br /><br />"
            + "If this request has not been made by you please ignore this email.";
    /**
     * Number article for product request message.
     */
    private static final Integer LOST_PASSWORD_EMAIL_NB_PARAM = 2;
    /**
     * Number article for product request message parameter 1.
     */
    private static final Integer LOST_PASSWORD_EMAIL_PARAM_1 = 0;
    /**
     * Number article for product request message parameter 1.
     */
    private static final Integer LOST_PASSWORD_EMAIL_PARAM_2 = 1;

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="The lost password email">
    /**
     * The lost password email subject.
     */
    private static final String ACCOUNT_CREATION_EMAIL_SUBJECT = "Freeveggie - Account creation email";
    /**
     * The lost password email content.
     */
    private static final String ACCOUNT_CREATION_EMAIL_MESSAGE = "We are please to inform you that your acccount have been created."
            + "<br /><br />"
            + "We need a last thing to validate your account. We need you to click on <a href=\"www.freeveggie.org/confirmaccount?id={0}\">this link</a>."
            + "<br /><br />"
            + "If this link doest work please copy-paste this address into your browser :"
            + "<br />" + "www.freeveggie.org/confirmaccount?id={0}" + "<br /><br />";
    /**
     * Number article for product request message.
     */
    private static final Integer ACCOUNT_CREATION_NB_PARAM = 1;
    /**
     * Number article for product request message parameter 1.
     */
    private static final Integer ACCOUNT_CREATION_PARAM_1 = 0;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="The friend ship request">
    /**
     * The friend ship request subject.
     */
    private static final String FRIENDSHIP_REQUEST_EMAIL_SUBJECT = "Freeveggie - Friendship request send";
    /**
     * The friend ship request subject.
     */
    private static final String FRIENDSHIP_REQUEST_EMAIL_MESSAGE = "A friendship as been send to you. <br /><br />"
            + "{0} want to add you to his friend list as his {1}. This user left you the following message :<br />"
            + "{2} <br /><br />"
            + "To validate this relationship please click <a href=\"www.freeveggie.org/validaterelationship/{3}\">here</a>";
    /**
     * Number article for product request message.
     */
    private static final Integer FRIENDSHIP_REQUEST_EMAIL_MESSAGE_NB_PARAM = 4;
    /**
     * Number article for product request message parameter 1.
     */
    private static final Integer FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_1 = 0;
    /**
     * Number article for product request message parameter 2.
     */
    private static final Integer FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_2 = 1;
    /**
     * Number article for product request message parameter 3.
     */
    private static final Integer FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_3 = 2;
    /**
     * Number article for product request message parameter 4.
     */
    private static final Integer FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_4 = 3;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="The product request">
    /**
     * The product request subject.
     */
    private static final String PRODUCT_REQUEST_EMAIL_SUBJECT = "Freeveggie - Product request send";
    /**
     * The product request message.
     */
    private static final String PRODUCT_REQUEST_EMAIL_MESSAGE = "A user send a product request. <br /><br />"
            + "{0} is requesting {1} kg of your {2} ({3}). This user left you the following message :<br />"
            + "{4} <br /><br />"
            + "To validate this request please click <a href=\"www.freeveggie.org/validateproductrequest/{5}\">here</a>";
    /**
     * Number article for product request message.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_NB_PARAM = 6;
    /**
     * Number article for product request message parameter 1.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_1 = 0;
    /**
     * Number article for product request message parameter 2.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_2 = 1;
    /**
     * Number article for product request message parameter 5.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_3 = 2;
    /**
     * Number article for product request message parameter 4.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_4 = 3;
    /**
     * Number article for product request message parameter 5.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_5 = 4;
    /**
     * Number article for product request message parameter 6.
     */
    private static final Integer PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_6 = 5;
    // </editor-fold>
    
    /**
     * {@link Session}
     */
    @Resource(name = "mail/freeveggieSession")
    private Session session;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendCreationUserNotice(UserBO pUserBO, String pCode) {
        Object[] arguments = new Object[ACCOUNT_CREATION_NB_PARAM];
        arguments[ACCOUNT_CREATION_PARAM_1] = pCode;
        sendNotice(pUserBO.getEmail(), ACCOUNT_CREATION_EMAIL_SUBJECT,
                MessageFormat.format(ACCOUNT_CREATION_EMAIL_MESSAGE, arguments));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendLostEmailNotice(UserBO pRecipient, String pCode,
            String pPassword) {
        Object[] arguments = new Object[LOST_PASSWORD_EMAIL_NB_PARAM];
        arguments[LOST_PASSWORD_EMAIL_PARAM_1] = pCode;
        arguments[LOST_PASSWORD_EMAIL_PARAM_2] = pPassword;
        sendNotice(pRecipient.getEmail(), LOST_PASSWORD_EMAIL_SUBJECT,
                MessageFormat.format(LOST_PASSWORD_EMAIL_MESSAGE, arguments));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendProductRequestNotice(ProductRequestBO pProductRequestBO) {
        Object[] arguments = new Object[PRODUCT_REQUEST_EMAIL_MESSAGE_NB_PARAM];
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_1] = pProductRequestBO.getRequester().getUsername();
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_2] = pProductRequestBO.getQuantity();
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_3] = pProductRequestBO.getProduct().getReferenceProduct()
                .getName();
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_4] = pProductRequestBO.getProduct().getName();
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_5] = pProductRequestBO.getMessage();
        arguments[PRODUCT_REQUEST_EMAIL_MESSAGE_PARAM_6] = pProductRequestBO.getId();
        sendNotice(pProductRequestBO.getProduct().getGarden().getOwner()
                .getEmail(), PRODUCT_REQUEST_EMAIL_SUBJECT,
                MessageFormat.format(PRODUCT_REQUEST_EMAIL_MESSAGE, arguments));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendRelationshipRequestNotice(RelationShipBO pRelationShipBO) {
        Object[] arguments = new Object[FRIENDSHIP_REQUEST_EMAIL_MESSAGE_NB_PARAM];
        arguments[FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_1] = pRelationShipBO.getSender().getUsername();
        arguments[FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_2] = pRelationShipBO.getType();
        arguments[FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_3] = pRelationShipBO.getRequest();
        arguments[FRIENDSHIP_REQUEST_EMAIL_MESSAGE_PARAM_4] = pRelationShipBO.getId();
        sendNotice(pRelationShipBO.getRecipient().getEmail(),
                FRIENDSHIP_REQUEST_EMAIL_SUBJECT, MessageFormat.format(
                FRIENDSHIP_REQUEST_EMAIL_MESSAGE, arguments));
    }

    private void sendNotice(final String pEmail, final String pObject,
            final String pMessage) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(pEmail, false));
            message.setSubject(pObject);
            message.setContent(pMessage, "text/html");
            message.setSentDate(SystemTime.asDate());

            // Send message
            Transport.send(message);
        } catch (MessagingException ex) {
            throw new TechnicalException(ex);
        }
    }
}
