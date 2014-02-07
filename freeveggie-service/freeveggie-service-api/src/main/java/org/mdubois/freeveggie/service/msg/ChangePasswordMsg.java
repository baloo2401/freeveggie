package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;

/**
 *
 * @author mdubois
 */
@XmlRootElement
public class ChangePasswordMsg extends Message {

    /**
     * The user login.
     */
    @Required
    private Long userId;

    /**
     * The potential user password.
     */
    @Required
    private String oldPassword;
    
    /**
     * The potential user password.
     */
    @Required
    private String newPassword;

    /**
     * Get the user id.
     * @return The user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Set the user id.
     * @param userId - The user id to set
     */
    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    /**
     * Get new password.
     * @return The new password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Set the new password.
     * @param newPassword - The new user password
     */
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Get the old password.
     * @return The old password
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Set the old password
     * @param oldPassword - The new password to set
     */
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

   
}
