package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;

/**
 *
 * @author mdubois
 */
@XmlRootElement
public class AuthenticationMsg extends Message {

    /**
     * The user login.
     */
    @Required
    private String login;

    /**
     * The potential user password.
     */
    @Required
    private String password;

    /**
     * Get the user login.
     * @return The user login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the user login.
     * @param pLogin - The user login to set
     */
    public void setLogin(String pLogin) {
        this.login = pLogin;
    }

    /**
     * Get the potential user password.
     * @return - The potential user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the potential user password
     * @param pPassword - The potential user password to set
     */
    public void setPassword(String pPassword) {
        this.password = pPassword;
    }


}
