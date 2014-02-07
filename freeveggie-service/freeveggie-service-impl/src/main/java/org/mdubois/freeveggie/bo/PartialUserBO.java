package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.framework.security.UserRole;
import static org.mdubois.freeveggie.bo.PartialUserBO.QueryNamedConstant.GET_USER_BY_EMAIL;
import static org.mdubois.freeveggie.bo.PartialUserBO.QueryNamedConstant.GET_USER_BY_EMAIL_REQUEST;
import org.mdubois.freeveggie.framework.bo.BusinessObject;

// </editor-fold>

/**
 * @author Mickael Dubois
 */
@Entity()
@Table(name = "T_USER")
@Cacheable(value = true)
@NamedQueries(value = {
    @NamedQuery(name = GET_USER_BY_EMAIL, query = GET_USER_BY_EMAIL_REQUEST)
})
public class PartialUserBO extends BusinessObject<Long>  implements Comparable<PartialUserBO> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 19L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "usr_id")
    private Long id;
    /**
     * The date when the user subscribe.
     */
    @Column(name = "usr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The user username.
     */
    @Column(name = "usr_username", nullable = false)
    private String username;

    /**
     * The user last connection.
     */
    @Column(name = "usr_last_connexion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnexion;

    /**
     * The image profile.
     */
    @Column(name = "usr_img_filename",nullable=true)
    private String profileImgFilename;
    /**
     * The user email address.
     */
    @Column(name = "usr_email_address", nullable = false)
    private String email;
    /**
     * The user first name.
     */
    @Column(name = "usr_firstname", nullable = false)
    private String firstname;
    /**
     * The user last name.
     */
    @Column(name = "usr_lastname", nullable = false)
    private String lastname;

    /**
     * The instant role of the user.
     */
    @Transient
    private UserRole role;

    /**
     * The technical value.
     */
    @Column(name = "usr_rur_id  ", nullable = false)
    private Integer _role;

    /**
     * Get the database id.
     * @return The database id
     */
    @Override
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

    @PostLoad
    void populateTransientFields() {
        role = UserRole.fromInt(_role);
    }

    @Override
    public int compareTo(PartialUserBO pPartialUserBO) {
        String compareThis = this.getUsername();
        String compareObj = pPartialUserBO.getUsername();
        return compareThis.compareTo(compareObj);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getUserByCityAndProduct query.
         */
        public static final String GET_USER_BY_EMAIL = "PartialUserBO.getUserByEmail";

        public static final String GET_USER_BY_EMAIL_REQUEST = "SELECT e FROM PartialUserBO e WHERE e.email = :Email";
    }

}
