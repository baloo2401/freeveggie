package org.mdubois    .freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.security.UserRole;
// </editor-fold>

/**
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class PartialUserMsg extends Message {

    /**
     * The database id.
     */
    private Long id;
    /**
     * The date when the user subscribe.
     */
    private Date creationDate;

    /**
     * The user username.
     */
    private String username;

    /**
     * The user last connection.
     */
    private Date lastConnexion;

    /**
     * The image profile.
     */
    private String profileImgFilename;
    /**
     * The user first name.
     */
    private String firstname;
    /**
     * The user last name.
     */
    private String lastname;

    /**
     * The instant role of the user.
     */
    private UserRole role;

    /**
     * Get the database id.
     * @return The database id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The database id to set
     */
    public void setId(final Long pId) {
        this.id = pId;
    }

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
    public void setFirstname(String pFirstname) {
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
    public void setLastname(String pLastname) {
        this.lastname = pLastname;
    }

    /**
     * Get the date of when the user create his account.
     * @return The creation date
     */
    public Date getCreationDate() {
        if(creationDate!=null){
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date of the creation account.
     * @param pCreationDate The date to set
     */
    public void setCreationDate(Date pCreationDate) {
        if(pCreationDate !=null){
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
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
    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    /**
     * Get the last time the user where connected.
     * @return The last connection time
     */
    public Date getLastConnexion() {
        if(lastConnexion != null){
            return new Date(lastConnexion.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date of when the user get online the last time.
     * @param pLastConnexion - The time to set
     */
    public void setLastConnexion(Date pLastConnexion) {
        if(pLastConnexion != null){
            this.lastConnexion = new Date(pLastConnexion.getTime());
        } else {
            this.lastConnexion = null;
        }
    }

    /**
     * Get the role of the user.
     * @return - The user role.
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set the user role.
     * @param role - The user role to set
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Get the image profile.
     * @return The image profile.
     */
    public String getProfileImgFilename() {
        return profileImgFilename;
    }

    /**
     * Set the image profile.
     * @param profileImgFilename  - The image profile to set
     */
    public void setProfileImgFilename(String profileImgFilename) {
        this.profileImgFilename = profileImgFilename;
    }

}
