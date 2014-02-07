package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.mdubois.freeveggie.Status;
import static org.mdubois.freeveggie.bo.GardenBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The garden of a user.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_GARDEN")
@NamedQueries(value = {
    @NamedQuery(name = GET_GARDEN_BY_USER, query = GET_GARDEN_BY_USER_QUERY),
    @NamedQuery(name = GET_GARDEN_BY_CITY, query = GET_GARDEN_BY_CITY_QUERY),
    @NamedQuery(name = GET_GARDEN_BY_REGION, query = GET_GARDEN_BY_REGION_QUERY),
    @NamedQuery(name = GET_GARDEN_BY_STATE, query = GET_GARDEN_BY_STATE_QUERY),
    @NamedQuery(name = GET_GARDEN_BY_COUNTRY, query = GET_GARDEN_BY_COUNTRY_QUERY),
    @NamedQuery(name = GET_GARDEN_BY_PRODUCT, query = GET_GARDEN_BY_PRODUCT_QUERY),
    @NamedQuery(name = SEARCH_GARDEN, query = SEARCH_GARDEN_QUERY)
})
public class GardenBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 3L;
    @Id
    @Column(name = "grd_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_grd_seq")
    @SequenceGenerator(name = "s_grd_seq", sequenceName = "s_grd_seq",
    initialValue = 1)
    private Long id;
    /**
     * A given name of the garden. The user define it.
     */
    @Column(name = "grd_name", nullable = false)
    private String name;
    /**
     * A description given by the owner.
     */
    @Column(name = "grd_description")
    private String description;
    /**
     * List of the product.
     */
    @OneToMany(targetEntity = ProductBO.class, mappedBy = "garden")
    private List<ProductBO> products;
    /**
     * The list of comment made on the garden.
     */
    @OneToMany(targetEntity = GardenCommentBO.class, mappedBy = "garden")
    private List<GardenCommentBO> comments;
    /**
     * The list of like made on the garden.
     */
    @OneToMany(targetEntity = GardenLikeBO.class, mappedBy = "garden")
    private List<GardenLikeBO> likes;
    /**
     * The user address.
     */
    @OneToOne(optional = true)
    @JoinColumn(name = "grd_adr_id", nullable = true)
    private AddressBO address;
    /**
     * The date when the garden have been created.
     */
    @Column(name = "grd_creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The owner of the garden.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "grd_usr_id")
    private PartialUserBO owner;
    /**
     * The status of the garden.
     */
    @Transient
    private Status status;
    /**
     * The technical value.
     */
    @Column(name = "grd_sts_id")
    private Integer _status;
    /**
     * List of the product.
     */
    @OneToMany(targetEntity = GardenPictureBO.class, mappedBy = "garden")
    private List<GardenPictureBO> pictures;

    /**
     * Get the owner of the garden.
     *
     * @return The owner
     */
    public PartialUserBO getOwner() {
        return owner;
    }

    /**
     * Set the owner of the user.
     *
     * @param owner The owner to set
     */
    public void setOwner(PartialUserBO owner) {
        this.owner = owner;
    }

    /**
     * Get the id database.
     *
     * @return The id database
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the id database.
     *
     * @param pId - The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the user description of the garden.
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the user description.
     *
     * @param pDescription - The description to set
     */
    public void setDescription(String pDescription) {
        this.description = pDescription;
    }

    /**
     * Get the garden name set by th user.
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the garden.
     *
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Get the list of product related to the garden.
     *
     * @return The list of product
     */
    public List<ProductBO> getProducts() {
        return products;
    }

    /**
     * Set the list of product.
     *
     * @param pProducts - The list of product to set
     */
    public void setProducts(List<ProductBO> pProducts) {
        this.products = pProducts;
    }

    /**
     * Get the list of comment left by user on this garden.
     *
     * @return The list of comment
     */
    public List<GardenCommentBO> getComments() {
        return comments;
    }

    /**
     * Set a list of comment.
     *
     * @param pComments - The list of comment to set
     */
    public void setComments(List<GardenCommentBO> pComments) {
        this.comments = pComments;
    }

    /**
     * Get the list of like that this garden had.
     *
     * @return - The list of likes
     */
    public List<GardenLikeBO> getLikes() {
        return likes;
    }

    /**
     * Set the list of like that this product has.
     *
     * @param pLikes - The list of like to set
     */
    public void setLikes(List<GardenLikeBO> pLikes) {
        this.likes = pLikes;
    }

    /**
     * Get the address of the garden.
     *
     * @return The address
     */
    public AddressBO getAddress() {
        return address;
    }

    /**
     * Set the address of the garden.
     *
     * @param pAddress - The address to set
     */
    public void setAddress(AddressBO pAddress) {
        this.address = pAddress;
    }

    /**
     * Get the date the user crate the garden.
     *
     * @return The creation date
     */
    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date of creation
     *
     * @param pCreationDate - The date to set
     */
    public void setCreationDate(Date pCreationDate) {
        if(pCreationDate!=null){
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    /**
     * Get the status of the garden.
     *
     * @return The status of the garden
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the list of pictures
     * @param pictures - The list of pictures to set
     */
    public void setPictures(List<GardenPictureBO> pictures) {
        this.pictures = pictures;
    }

    /**
     * Get the list of pictures
     * @return The list of pictures
     */
    public List<GardenPictureBO> getPictures() {
        return pictures;
    }

    /**
     * Set the status of the garden.
     *
     * @param pStatus - The status of the garden to set
     */
    public void setStatus(Status pStatus) {
        this.status = pStatus;
        if(pStatus !=null){
            _status = pStatus.toInt();
        } else {
            _status = 1;
        }
    }

    /**
     * Call back run just after the object have been populated from the
     * database.
     */
    @PostLoad
    void populateTransientFields() {
        status = Status.fromInt(_status);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getGardenByUser query.
         */
        public static final String GET_GARDEN_BY_USER = "GardenBO.getGardenByUser";
        /*
         * The getGardenByUser query.
         */
        public static final String GET_GARDEN_BY_USER_QUERY = "SELECT e FROM GardenBO e WHERE e.owner.id = :owner";
        /*
         * The name of getGardenByCity query.
         */
        public static final String GET_GARDEN_BY_CITY = "GardenBO.getGardenByCity";
        /*
         * The getGardenByCity query.
         */
        public static final String GET_GARDEN_BY_CITY_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + " WHERE e.address.city.id = :city AND product.referenceProduct.id = :refProduct";
        /*
         * The name of getGardenByRegion query.
         */
        public static final String GET_GARDEN_BY_REGION = "GardenBO.getGardenByRegion";
        /*
         * The getGardenByRegion query.
         */
        public static final String GET_GARDEN_BY_REGION_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + " WHERE e.address.city.region.id = :region AND product.referenceProduct.id = :refProduct";
        /*
         * The name of getGardenByState query.
         */
        public static final String GET_GARDEN_BY_STATE = "GardenBO.getGardenByState";
        /*
         * The getGardenByState query.
         */
        public static final String GET_GARDEN_BY_STATE_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + "  WHERE e.address.city.region.state.id = :state AND product.referenceProduct.id = :refProduct";
        /*
         * The name of getGardenByCountry query.
         */
        public static final String GET_GARDEN_BY_COUNTRY = "GardenBO.getGardenByCountry";
        /*
         * The getGardenByCountry query.
         */
        public static final String GET_GARDEN_BY_COUNTRY_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + "  WHERE e.address.city.region.state.country.id = :country AND product.referenceProduct.id = :refProduct";
        /*
         * The name of getGardenByProduct query.
         */
        public static final String GET_GARDEN_BY_PRODUCT = "GardenBO.getGardenByProduct";
        /*
         * The getGardenByProduct query.
         */
        public static final String GET_GARDEN_BY_PRODUCT_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + "  WHERE product.referenceProduct.id = :refProduct";
        
        /*
         * The name of getGardenByPosition query.
         */
        public static final String SEARCH_GARDEN = "GardenBO.getGardenByPosition";
        /*
         * The getGardenByProduct query.
         */
        public static final String SEARCH_GARDEN_QUERY = "SELECT DISTINCT e FROM GardenBO e "
                + " join e.products as product "
                + " WHERE EXISTS (SELECT p FROM ProductBO p WHERE p.garden = e AND p.quantity > 0)"
                + " AND (:refProductId = -1 OR product.referenceProduct.id = :refProductId)"
                + " AND e.address.longitude BETWEEN :longitudeDown AND :longitudeUp "
                + " AND e.address.latitude BETWEEN :latitudeDown AND :latitudeUp";
        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
