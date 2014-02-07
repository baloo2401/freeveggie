package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_CITY_AND_REF_PRODUCT;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_CITY_AND_REF_PRODUCT_QUERY;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT_QUERY;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_GARDEN;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_GARDEN_QUERY;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_REGION_AND_REF_PRODUCT;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_REGION_AND_REF_PRODUCT_QUERY;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_STATE_AND_REF_PRODUCT;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_STATE_AND_REF_PRODUCT_QUERY;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_USER;
import static org.mdubois.freeveggie.bo.ProductBO.QueryNamedConstant.GET_PRODUCT_BY_USER_QUERY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * A user product.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_PRODUCT")
@NamedQueries(value = {
	    @NamedQuery(name = GET_PRODUCT_BY_USER, query = GET_PRODUCT_BY_USER_QUERY),
	@NamedQuery(name = GET_PRODUCT_BY_GARDEN, query = GET_PRODUCT_BY_GARDEN_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_CITY_AND_REF_PRODUCT, query = GET_PRODUCT_BY_CITY_AND_REF_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_REGION_AND_REF_PRODUCT, query = GET_PRODUCT_BY_REGION_AND_REF_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_STATE_AND_REF_PRODUCT, query = GET_PRODUCT_BY_STATE_AND_REF_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT, query = GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT_QUERY)
})
public class ProductBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "prd_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_prd_seq")
    @SequenceGenerator(name = "s_prd_seq", sequenceName = "s_prd_seq",
    initialValue = 1)
    private Long id;
    /**
     * The garden that this product belong to.
     */
    @ManyToOne
    @JoinColumn(name = "prd_grd_id", nullable = false)
    private GardenBO garden;
    /**
     * The name of the product.
     */
    @Column(name = "prd_name", nullable = false)
    private String name;
    /**
     * The list of comment made on the garden.
     */
    @OneToMany(targetEntity = ProductCommentBO.class, mappedBy = "product")
    private List<ProductCommentBO> comments;
    /**
     * The list of like made on the garden.
     */
    @OneToMany(targetEntity = ProductLikeBO.class, mappedBy = "product")
    private List<ProductLikeBO> likes;
    /**
     * The list of requests made on the product.
     */
    @OneToMany(targetEntity = ProductRequestBO.class, mappedBy = "product")
    private List<ProductRequestBO> request;
    /**
     * The reference product.
     */
    @ManyToOne
    @JoinColumn(name = "prd_rpc_id", nullable = false)
    private RefProductBO referenceProduct;
    /**
     * The exchange type.
     */
    @Transient
    private ExchangeType exchangeType;
    /**
     * technical field
     */
    @Column(name = "prd_chg_id", nullable = false)
    private Integer _exchangeType;
    /**
     * The culture mode.
     */
    @Transient
    private CultureMode cultureMode;
    /**
     * technical field
     */
    @Column(name = "prd_rcm_id", nullable = false)
    private Integer _cultureMode;
    /**
     * The culture mode.
     */
    @Transient
    private CultureType cultureType;
    /**
     * technical field
     */
    @Column(name = "prd_rct_id", nullable = false)
    private Integer _cultureType;
    /**
     * The quantity of product that the user want to free.
     */
    @Column(name = "prd_quantity", nullable = false)
    private Float quantity;
    /**
     * The user comment of the product.
     */
    @Column(name = "prd_description")
    private String description;

    /**
     * The status of the product.
     */
    @Transient
    private Status status;

    /**
     * The technical value.
     */
    @Column(name = "prd_sts_id")
    private Integer _status;
    
    
    /**
     * List of the product.
     */
    @OneToMany(targetEntity = ProductPictureBO.class, mappedBy = "product")
    private List<ProductPictureBO> pictures;

    /**
     * Get the database id.
     * @return The id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The id to set.
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the garden from which this product belong to.
     * @return The garden
     */
    public GardenBO getGarden() {
        return garden;
    }

    /**
     * Set the garden that the product belong to.
     * @param pGarden - The garden to set
     */
    public void setGarden(GardenBO pGarden) {
        this.garden = pGarden;
    }

    /**
     * Get the list of comment left by user on this product.
     * @return The list of comment
     */
    public List<ProductCommentBO> getComments() {
        return comments;
    }

    /**
     * Set a list of comment.
     * @param pComments - The list of comment to set
     */
    public void setComments(List<ProductCommentBO> pComments) {
        this.comments = pComments;
    }

    /**
     * Get the reference product.
     * @return The reference product
     */
    public RefProductBO getReferenceProduct() {
        return referenceProduct;
    }

    /**
     * Set the reference product.
     * @param pReferenceProduct - The product to set
     */
    public void setReferenceProduct(RefProductBO pReferenceProduct) {
        this.referenceProduct = pReferenceProduct;
    }

    /**
     * Get the wait the product is produce.
     * @return The culture mode
     */
    public CultureMode getCultureMode() {
        return cultureMode;
    }

    /**
     * Set the wait the product is produce.
     * @param pCultureMode - The culture mode to set
     */
    public void setCultureMode(CultureMode pCultureMode) {
        this.cultureMode = pCultureMode;
        if(pCultureMode!=null){
            this._cultureMode = pCultureMode.toInt();
        } else {
            this._cultureMode = -1;
        }
    }

    /**
     * Get the wait the product is growing.
     * @return The culture type
     */
    public CultureType getCultureType() {
        return cultureType;
    }
    

    /**
     * Set the list of pictures
     * @param pictures - The list of pictures to set
     */
    public void setPictures(List<ProductPictureBO> pictures) {
        this.pictures = pictures;
    }

    /**
     * Get the list of pictures
     * @return The list of pictures
     */
    public List<ProductPictureBO> getPictures() {
        return pictures;
    }

    /**
     * Set the way the product is growing.
     * @param pCultureType - The culture type to set
     */
    public void setCultureType(CultureType pCultureType) {
        this.cultureType = pCultureType;
        if(pCultureType!=null){
            this._cultureType = pCultureType.toInt();
        } else {
            this._cultureType = -1;
        }
    }

    /**
     * Get the wait the user want to exchange his product.
     * @return The exchange type
     */
    public ExchangeType getExchangeType() {
        return exchangeType;
    }

    /**
     * Set the wait the user want to exchange his product.
     * @param pExchangeType - The exchange type to set.
     */
    public void setExchangeType(ExchangeType pExchangeType) {
        this.exchangeType = pExchangeType;
        if(pExchangeType!=null){
            this._exchangeType = pExchangeType.toInt();
        } else {
            this._exchangeType = -1;
        }
    }

    /**
     * Get the list of like that this product had.
     * @return - The list of likes
     */
    public List<ProductLikeBO> getLikes() {
        return likes;
    }

    /**
     * Set the list of like that this product has.
     * @param pLikes - The list of like to set
     */
    public void setLikes(List<ProductLikeBO> pLikes) {
        this.likes = pLikes;
    }

    /**
     * Get the quantity in gramme that the user want to free.
     * @return The quantity.
     */
    public Float getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity of product to free (in gramme).
     * @param quantity - The quantity to set
     */
    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the comment that the owner make on his product.
     * @return  The comment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the comment of the product.
     * @param description - The comment to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the request related to the product.
     * @return - The request related
     */
    public List<ProductRequestBO> getRequest() {
        return request;
    }

    /**
     * Set the related request.
     * @param request - The request to set
     */
    public void setRequest(List<ProductRequestBO> request) {
        this.request = request;
    }

    /**
     * Get the name of the product.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product.
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * Get the status of the product.
     * @return The status of the product
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the product.
     * @param pStatus - The status of the product to set
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
     * Call back run just after the object have been populated from the database.
     */
    @PostLoad
    void populateTransientFields() {
        exchangeType = ExchangeType.fromInt(_exchangeType);
        cultureMode = CultureMode.fromInt(_cultureMode);
        cultureType = CultureType.fromInt(_cultureType);
        status = Status.fromInt(_status);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getProductByUser query.
         */
        public static final String GET_PRODUCT_BY_USER = "ProductBO.getProductByUser";
        /*
         * The getProductByUser query.
         */
        public static final String GET_PRODUCT_BY_USER_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.owner.id = :owner";
        /*
         * The name of getProductByGarden query.
         */
        public static final String GET_PRODUCT_BY_GARDEN = "ProductBO.getProductByGarden";
        /*
         * The getProductByGarden query.
         */
        public static final String GET_PRODUCT_BY_GARDEN_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.id = :garden";
        /*
         * The name of getProductByCityAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_CITY_AND_REF_PRODUCT = "ProductBO.getProductByCityAndRefProduct";
        /*
         * The getProductByCityAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_CITY_AND_REF_PRODUCT_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.address.city.id = :city AND e.referenceProduct.id = :refProduct";
        /*
         * The name of getProductByRegionAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_REGION_AND_REF_PRODUCT = "ProductBO.getProductByRegionAndRefProduct";
        /*
         * The getProductByRegionAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_REGION_AND_REF_PRODUCT_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.address.city.region.id = :region AND e.referenceProduct.id = :refProduct";
        /*
         * The name of getProductByStateAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_STATE_AND_REF_PRODUCT = "ProductBO.getProductByStateAndRefProduct";
        /*
         * The getProductByStateAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_STATE_AND_REF_PRODUCT_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.address.city.region.state.id = :state AND e.referenceProduct.id = :refProduct";
        /*
         * The name of getProductByCountryAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT = "ProductBO.getProductByCountryAndRefProduct";
        /*
         * The getProductByCountryAndRefProduct query.
         */
        public static final String GET_PRODUCT_BY_COUNTRY_AND_REF_PRODUCT_QUERY = "SELECT e FROM ProductBO e WHERE e.garden.address.city.region.state.country.id = :country AND e.referenceProduct.id = :refProduct";
        /*
         * Default constructor.
         */

        private QueryNamedConstant() {
        }
    }
}
