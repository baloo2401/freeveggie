package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import static org.mdubois.freeveggie.bo.ProductPictureBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * Comment made on a user.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_PICTURE_PRODUCT")

@NamedQueries(value = {
    @NamedQuery(name = GET_PRODUCT_PICTURE_BY_PRODUCT, query = GET_PRODUCT_COMMENT_BY_PRODUCT_QUERY)
})
//TODO Make this class and GardenPictureBO extends the same class and then reduce the number of converter
public class ProductPictureBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 4L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "prp_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_grp_seq")
    @SequenceGenerator(name = "s_grp_seq", sequenceName = "s_grp_seq",
    initialValue = 1)
    private Long id;

    /**
     * The product.
     */
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "prp_prd_id", nullable=false)
    private ProductBO product;
    
    /**
     * The picture mime type.
     */
    @Column(name = "prp_mine_type", nullable=false)
    private String mimeType; 
    
    /**
     * The picture
     */
    @Lob
    @Column(name = "picture", nullable=false)
    @Basic(fetch=FetchType.LAZY)
    private byte[] picture;

    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "prp_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * Set the id.
     * @param id - The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    /**
     * Get the id.
     * @return the id 
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Get the product the picture is for.
     * @return -  The product the picture is for
     */
    public ProductBO getProduct() {
        return product;
    }

    /**
     * Set the product the picture is for.
     * @param pProduct - The product the picture is for to set
     */
    public void setProduct(ProductBO pProduct) {
        this.product = pProduct;
    }

    /**
     * Get the mime type of the picture.
     * @return - The mime type of the picture
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set the mime type of the picture.
     * @param pMimeType - The mime type of the picture to set
     */
    public void setMimeType(String pMimeType) {
        this.mimeType = pMimeType;
    }

    /**
     * The picture.
     * @return - The picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Set the picture.
     * @param pPicture - The picture to set.
     */
    public void setPicture(byte[] pPicture) {
        this.picture = pPicture;
    }

    /**
     * Get the date when the picture as been saved.
     * @return - The date when the picture as been saved
     */
    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date when the picture as been saved.
     * @param pCreationDate - The date when the picture as been saved to set
     */
    public void setCreationDate(Date pCreationDate) {
        if (pCreationDate != null) {
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    public static final class QueryNamedConstant {

        /*
         * The name of GetProductByName query.
         */
        public static final String GET_PRODUCT_PICTURE_BY_PRODUCT = "ProductPictureBO.getProductPictureByProduct";
        /*
         * The GetProductByName query.
         */
        public static final String GET_PRODUCT_COMMENT_BY_PRODUCT_QUERY = "SELECT e FROM ProductPictureBO e WHERE e.product.id = :product";
        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
