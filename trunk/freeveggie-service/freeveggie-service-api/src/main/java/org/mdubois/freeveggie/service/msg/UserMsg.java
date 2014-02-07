package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.framework.msg.Email;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
import org.mdubois.freeveggie.framework.security.UserRole;
// </editor-fold>

/**
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class UserMsg extends Message {

    /**
     * The database id.
     */
    private Long id;
    /**
     * The date when the user subscribe.
     */
    private Date creationDate;
    /**
     * The date when the user subscribe.
     */
    private Date cancellationDate;
    /**
     * The date when the user subscribe.
     */
    private Date blacklistedDate;
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
    private String password;
    /**
     * Temporary password
     */
    private String temporaryPassword;
    /**
     * The user email address.
     */
    @Email
    private String email;
    /**
     * The user address.
     */
    private AddressMsg homeAdresse;
    /**
     * The user profile.
     */
    private ProfileMsg profile;
    /**
     * The user subscription.
     */
    @Required
    private SubscriptionMsg subscription;
    /**
     * The instant state of the user.
     */
    private UserStatus status;

    /**
     * The user last connection.
     */
    private Date lastConnexion;

    /**
     * The instant state of the user.
     */
    private UserRole role;

    /**
     * The image profile.
     */
    private String profileImgFilename;

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
     * Get a copy of the date when the user get blacklisted.
     *
     * @return The blacklisting date
     */
    public Date getBlacklistedDate() {
        if(blacklistedDate != null){
            return new Date(blacklistedDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set a new instance of the black listing date.
     * @param pBlacklistedDate The date to set.
     */
    public void setBlacklistedDate(Date pBlacklistedDate) {
        if(pBlacklistedDate != null) {
            this.blacklistedDate = new Date(pBlacklistedDate.getTime());
        } else {
            this.blacklistedDate = null;
        }
    }

    /**
     * Get the date of when the user delete his account.
     * @return The cancellation date
     */
    public Date getCancellationDate() {
        if(cancellationDate != null){
            return new Date(cancellationDate.getTime());
        } else {
            return null;
        }

    }

    /**
     * Set the date of when the user delete his account.
     * @param pCancellationDate  The date to set
     */
    public void setCancellationDate(Date pCancellationDate) {
        if(pCancellationDate != null){
            this.cancellationDate = new Date(pCancellationDate.getTime());
        } else {
            this.cancellationDate = null;
        }
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
     * Set the date of the creaton account.
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
    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    /**
     * Get the user home address.
     * @return The user home address
     */
    public AddressMsg getHomeAdresse() {
        return homeAdresse;
    }

    /**
     * Set the user home address.
     * @param pHomeAdresse The home address to set
     */
    public void setHomeAdresse(AddressMsg pHomeAdresse) {
        this.homeAdresse = pHomeAdresse;
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
    public void setPassword(String pPassword) {
        this.password = pPassword;
    }

    /**
     * Get the user profile
     * @return The user profile
     */
    public ProfileMsg getProfile() {
        return profile;
    }

    /**
     * Set the user profile
     * @param pProfil The user profile to set
     */
    public void setProfile(ProfileMsg pProfile) {
        this.profile = pProfile;
    }

    /**
     * Get the user instant status.
     * @return The status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Set the user status.
     * @param pStatus The status to set
     */
    public void setStatus(UserStatus pStatus) {
        this.status = pStatus;
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
    public void setSubscription(SubscriptionMsg pSubscription) {
        this.subscription = pSubscription;
    }

    /**
     * Get the user temporary password.
     * @return The temporary password
     */
    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    /**
     * Set the temporary password.
     * @param pTemporaryPassword The password to set
     */
    public void setTemporaryPassword(String pTemporaryPassword) {
        this.temporaryPassword = pTemporaryPassword;
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
