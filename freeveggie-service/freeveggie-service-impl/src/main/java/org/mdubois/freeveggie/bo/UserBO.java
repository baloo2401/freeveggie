package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import org.mdubois.freeveggie.UserStatus;
import static org.mdubois.freeveggie.bo.UserBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.msg.validator.EmailConstraintValidator;
import org.mdubois.freeveggie.framework.security.UserRole;
// </editor-fold>

/**
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_USER",
uniqueConstraints =
@UniqueConstraint(columnNames = {"usr_username", "usr_email_address"}))
@NamedQueries(value = {
    @NamedQuery(name = GET_USER_BY_CITY_AND_PRODUCT, query = GET_USER_BY_CITY_AND_PRODUCT_QUERY),
    @NamedQuery(name = GET_USER_BY_REGION_AND_PRODUCT, query = GET_USER_BY_REGION_AND_PRODUCT_QUERY),
    @NamedQuery(name = GET_USER_BY_STATE_AND_PRODUCT, query = GET_USER_BY_STATE_AND_PRODUCT_QUERY),
    @NamedQuery(name = GET_USER_BY_COUNTRY_AND_PRODUCT, query = GET_USER_BY_COUNTRY_AND_PRODUCT_QUERY),
    @NamedQuery(name = GET_USER_BY_LOGIN, query = GET_USER_BY_LOGIN_QUERY),
    @NamedQuery(name = GET_USER_BY_UUID, query = GET_USER_BY_UUID_QUERY),
    @NamedQuery(name = GET_USER_BY_EMAIL, query = GET_USER_BY_EMAIL_QUERY)
})
public class UserBO extends BusinessObject<Long> implements Comparable<UserBO> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 19L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "usr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_usr_seq")
    @SequenceGenerator(name = "s_usr_seq", sequenceName = "s_usr_seq",
    initialValue = 1)
    private Long id;
    /**
     * The date when the user subscribe.
     */
    @Column(name = "usr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The date when the user subscribe.
     */
    @Column(name = "usr_cancellation_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date cancellationDate;
    /**
     * The date when the user subscribe.
     */
    @Column(name = "usr_blacklisted_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date blacklistedDate;
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
     * The user username.
     */
    @Column(name = "usr_username", nullable = false)
    private String username;
    /**
     * The user username.
     */
    @Column(name = "usr_uuid", nullable = false)
    private String uuid;
    /**
     * The user password.
     */
    @Column(name = "usr_password", nullable = false)
    private String password;
    /**
     * Temporary password
     */
    @Column(name = "usr_temp_password")
    private String temporaryPassword;
    /**
     * The user email address.
     */
    @Pattern(regexp= EmailConstraintValidator.EMAIL_PATTERN, message="Invalid email")
    @Column(name = "usr_email_address", nullable = false)
    private String email;
    /**
     * The list of the user garden.
     */
    @OneToMany(targetEntity = GardenBO.class, mappedBy = "owner")
    private List<GardenBO> gardens;
    /**
     * The user address.
     */
    @OneToOne(optional = false)
    @JoinColumn(name = "usr_adr_id", nullable = false)
    private AddressBO homeAddress;
    /**
     * The user profile.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private ProfileBO profile;
    /**
     * The user subscription.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private SubscriptionBO subscription;
    /**
     * The instant state of the user.
     */
    @Transient
    private UserStatus status;

    @Column(name = "usr_rus_id", nullable = false)
    private Integer _status;

    /**
     * The user last connection.
     */
    @Column(name = "usr_last_connexion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnexion;

    /**
     * The instant state of the user.
     */
    @Transient
    private UserRole role;

    /**
     * The technical value.
     */
    @Column(name = "usr_rur_id", nullable = false)
    private Integer _role;

    /**
     * The image profile.
     */
    @Column(name = "usr_img_filename")
    private String profileImgFilename;

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
     * Get the user garden list.
     * @return The user garden list
     */
    public List<GardenBO> getGardens() {
        return gardens;
    }

    /**
     * Set the user garden list.
     * @param pGardens The garden list to set
     */
    public void setGardens(List<GardenBO> pGardens) {
        this.gardens = pGardens;
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
    public AddressBO getHomeAddress() {
        return homeAddress;
    }

    /**
     * Set the user home address.
     * @param pHomeAddress The home address to set
     */
    public void setHomeAddress(AddressBO pHomeAddress) {
        this.homeAddress = pHomeAddress;
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
    public ProfileBO getProfile() {
        return profile;
    }

    /**
     * Set the user profile
     * @param pProfile The user profile to set
     */
    public void setProfile(ProfileBO pProfile) {
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
        if(pStatus !=null){
            _status = status.toInt();
        } else {
            _status = 0;
        }
    }

    /**
     * Get the user subscription information.
     * @return The subscription information
     */
    public SubscriptionBO getSubscription() {
        return subscription;
    }

    /**
     * Set the user subscription information.
     * @param pSubscription The information to set
     */
    public void setSubscription(SubscriptionBO pSubscription) {
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
     * @return The last connextion time
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
     * @param pRole - The user role to set
     */
    public void setRole(UserRole pRole) {
        this.role = pRole;
        if(pRole !=null){
            _role = pRole.toInt();
        } else {
            _role = -1;
        }
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

    /**
     * Get the user UUID.
     * @return The user UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Set the user UUID.
     * @param pUUID - The user UUID to set
     */
    public void setUuid(String pUUID) {
        this.uuid = pUUID;
    }
    
    

    /**
     * Call back run just after the object have been populated from the database.
     */
    @PostLoad
    void populateTransientFields() {
        status = UserStatus.fromInt(_status);
        role = UserRole.fromInt(_role);
    }

    @Override
    public int compareTo(UserBO pUser) {
        String compareThis = this.getFirstname() + " " + this.getLastname();
        String compareObj = pUser.getFirstname() + " " + pUser.getLastname();
        return compareThis.compareTo(compareObj);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getUserByCityAndProduct query.
         */
        public static final String GET_USER_BY_CITY_AND_PRODUCT = "UserBO.getUserByCityAndProduct";
        /*
         * The getUserByCityAndProduct query.
         */
        public static final String GET_USER_BY_CITY_AND_PRODUCT_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " join e.gardens as garden "
                                            + " join garden.products as product "
                                            + " WHERE product.garden.address.city.id = :city "
                                            + " AND product.referenceProduct.id = :referenceProduct";

        /*
         * The name of getUserByRegionAndProduct query.
         */
        public static final String GET_USER_BY_REGION_AND_PRODUCT = "UserBO.getUserByRegionAndProduct";
        /*
         * The getUserByRegionAndProduct query.
         */
        public static final String GET_USER_BY_REGION_AND_PRODUCT_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " join e.gardens as garden "
                                            + " join garden.products as product "
                                            + " WHERE product.garden.address.city.region.id = :region"
                                            + " AND product.referenceProduct.id = :referenceProduct";

        /*
         * The name of getUserByStateAndProduct query.
         */
        public static final String GET_USER_BY_STATE_AND_PRODUCT = "UserBO.getUserByStateAndProduct";
        /*
         * The getUserByStateAndProduct query.
         */
        public static final String GET_USER_BY_STATE_AND_PRODUCT_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " join e.gardens as garden "
                                            + " join garden.products as product "
                                            + " WHERE product.garden.address.city.region.state.id = :state"
                                            + " AND product.referenceProduct.id = :referenceProduct";

        /*
         * The name of getUserByCountryAndProduct query.
         */
        public static final String GET_USER_BY_COUNTRY_AND_PRODUCT = "UserBO.getUserByCountryAndProduct";
        /*
         * The getUserByCountryAndProduct query.
         */
        public static final String GET_USER_BY_COUNTRY_AND_PRODUCT_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " join e.gardens as garden "
                                            + " join garden.products as product "
                                            + " WHERE product.garden.address.city.region.state.country.id = :country"
                                            + " AND product.referenceProduct.id = :referenceProduct";

        /*
         * The name of getUserByLogin query.
         */
        public static final String GET_USER_BY_LOGIN = "UserBO.getUserByLogin";
        /*
         * The getUserByLogi query.
         */
        public static final String GET_USER_BY_LOGIN_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " WHERE e.username = :Username";

        /*
         * The name of getUserByLogin query.
         */
        public static final String GET_USER_BY_UUID = "UserBO.getUserByUUID";
        /*
         * The getUserByLogi query.
         */
        public static final String GET_USER_BY_UUID_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " WHERE e.uuid = :uuid";

        /*
         * The name of getUserByEmail query.
         */
        public static final String GET_USER_BY_EMAIL = "UserBO.getUserByEmail";
        /*
         * The getUserByEmail query.
         */
        public static final String GET_USER_BY_EMAIL_QUERY = "SELECT DISTINCT e FROM UserBO e "
                                            + " WHERE e.email = :Email";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
