package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Email;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class CreateAccountMsg extends Message {

    /**
     * The user first name.
     */
    @Required
    private String firstname;
    /**
     * The user last name.
     */
    @Required
    private String lastname;
    /**
     * The user username.
     */
    @Required
    private String username;
    /**
     * The user password.
     */
    @Required
    private String password;
    /**
     * The user email address.
     */
    @Email
    private String email;
    /**
     * The user subscription.
     */
    @Required
    private SubscriptionMsg subscription;

    /**
     * The user address
     */
    @Required
    private AddressMsg addressMsg;


    /**
     * Get the user first name.
     * @return The user first name
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the user first name.
     * @param pFirstname The first name to set
     */
    public void setFirstname(final String pFirstname) {
        this.firstname = pFirstname;
    }

    /**
     * Get the user last name.
     * @return The user last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the user last name.
     * @param pLastname The last name to set
     */
    public void setLastname(final String pLastname) {
        this.lastname = pLastname;
    }

    /**
     * Get the email of the user.
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the user email address
     * @param pEmail The email address to set.
     */
    public void setEmail(final String pEmail) {
        this.email = pEmail;
    }

    /**
     * Get the user password.
     * @return The user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the user password.
     * @param pPassword The password to set
     */
    public void setPassword(final String pPassword) {
        this.password = pPassword;
    }

    /**
     * Get the user subscription information.
     * @return The subscription information
     */
    public SubscriptionMsg getSubscription() {
        return subscription;
    }

    /**
     * Set the user subscription information.
     * @param pSubscription The information to set
     */
    public void setSubscription(final SubscriptionMsg pSubscription) {
        this.subscription = pSubscription;
    }

    /**
     * Get the user login.
     * @return The user login
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the user login.
     * @param pUsername The login to set
     */
    public void setUsername(final String pUsername) {
        this.username = pUsername;
    }

    /**
     * Get the user address.
     * @return The user address
     */
    public AddressMsg getAddressMsg() {
        return addressMsg;
    }

    /**
     * Set the user address.
     * @param addressMsg The user address to set
     */
    public void setAddressMsg(AddressMsg addressMsg) {
        this.addressMsg = addressMsg;
    }
    

}
