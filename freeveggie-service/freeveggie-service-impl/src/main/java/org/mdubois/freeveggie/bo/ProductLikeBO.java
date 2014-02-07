package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.EvaluationStatus;
import static org.mdubois.freeveggie.bo.ProductLikeBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The fact that a user like a product.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_LIKE_PRODUCT")
@NamedQueries(value = {
    @NamedQuery(name = GET_PRODUCT_LIKE_BY_PRODUCT, query = GET_PRODUCT_LIKE_BY_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_LIKE_BY_USER_AND_PRODUCT_LIKE_ID, query = GET_PRODUCT_LIKE_BY_USER_AND_PRODUCT_LIKE_ID_QUERY),
    @NamedQuery(name = GET_PRODUCT_LIKE_WRITE, query = GET_PRODUCT_LIKE_WRITE_QUERY),
    @NamedQuery(name = GET_PRODUCT_LIKE, query = GET_PRODUCT_LIKE_QUERY)
})
public class ProductLikeBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 8L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "lpr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_lpr_seq")
    @SequenceGenerator(name = "s_lpr_seq", sequenceName = "s_lpr_seq",
    initialValue = 1)
    private Long id;
    /**
     * The user who left the comment.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "lpr_usr_id", nullable = false)
    private PartialUserBO writer;
    /**
     * The garden that is commented.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "lpr_prd_id", nullable = false)
    private ProductBO product;
    /**
     * The relationship state.
     */
    @Transient
    private EvaluationStatus status;
    /**
     * Technical value.
     */
    @Column(name = "lpr_res_id", nullable = false)
    private Integer _status;
    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "lpr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * Get the product commented.
     *
     * @return The product
     */
    public ProductBO getProduct() {
        return product;
    }

    /**
     * Set the product being commented.
     *
     * @param pProduct - The product to set
     */
    public void setProduct(ProductBO pProduct) {
        this.product = pProduct;
    }

    /**
     * Get the database id.
     *
     * @return The database id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     *
     * @param pId - The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the status of the comment.
     *
     * @return The comment status
     */
    public EvaluationStatus getStatus() {
        return status;
    }

    /**
     * Set the comment status.
     *
     * @param pStatus - The comment status to set
     */
    public void setStatus(EvaluationStatus pStatus) {
        this.status = pStatus;
        if(pStatus !=null){
            _status = status.toInt();
        } else {
            _status = -1;
        }
    }

    /**
     * Get the user who write the comment.
     *
     * @return The user writer
     */
    public PartialUserBO getWriter() {
        return writer;
    }

    /**
     * Set the user who write the comment.
     *
     * @param pWriter - The user to set
     */
    public void setWriter(PartialUserBO pWriter) {
        this.writer = pWriter;
    }

    /**
     * Get the date and time that the comment have been create.
     *
     * @return The creation date.
     */
    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date and time of creation.
     *
     * @param pCreationDate - The creation date
     */
    public void setCreationDate(Date pCreationDate) {
        if (pCreationDate != null) {
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    @PostLoad
    void populateTransientFields() {
        status = EvaluationStatus.fromInt(_status);
    }

    public static final class QueryNamedConstant {
        /*
         * The name of getProductLikeByProduct query.
         */

        public static final String GET_PRODUCT_LIKE_BY_PRODUCT = "ProductLikeBO.getProductLikeByProduct";
        /*
         * The getProductLikeByProduct query.
         */
        public static final String GET_PRODUCT_LIKE_BY_PRODUCT_QUERY = "SELECT e FROM ProductLikeBO e WHERE e.product.id = :product";
        /*
         * The name of getProductLikeWrite query.
         */
        public static final String GET_PRODUCT_LIKE_WRITE = "ProductLikeBO.getProductLikeWrite";
        /*
         * The getProductLikeWrite query.
         */
        public static final String GET_PRODUCT_LIKE_WRITE_QUERY = "SELECT e FROM ProductLikeBO e WHERE e.writer.id = :userWriter";
        /*
         * The name of GetProductByName query.
         */
        public static final String GET_PRODUCT_LIKE_BY_USER_AND_PRODUCT_LIKE_ID = "ProductLikeBO.getProductLikeByUserAndProductLikeId";
        /*
         * The GetProductByName query.
         */
        public static final String GET_PRODUCT_LIKE_BY_USER_AND_PRODUCT_LIKE_ID_QUERY = "SELECT e FROM ProductLikeBO e WHERE e.writer.id  = :userWriter AND e.id = :pIdProductLike";
        /*
         * The name of getProductLike query.
         */
        public static final String GET_PRODUCT_LIKE = "ProductLikeBO.getProductLike";
        /*
         * The getProductLikeQuery.
         */
        public static final String GET_PRODUCT_LIKE_QUERY = "SELECT e FROM ProductLikeBO e WHERE e.writer.id = :userWriter and e.product.id = :product";
        /*
         * Default constructor.
         */

        private QueryNamedConstant() {
        }
    }
}
