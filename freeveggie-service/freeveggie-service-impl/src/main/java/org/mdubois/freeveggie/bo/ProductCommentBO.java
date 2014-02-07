package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import static org.mdubois.freeveggie.bo.ProductCommentBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The comment make on a product.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_COMMENT_PRODUCT")
@NamedQueries(value = {
    @NamedQuery(name = GET_PRODUCT_COMMENT_BY_PRODUCT, query = GET_PRODUCT_COMMENT_BY_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_COMMENT_WRITE, query = GET_PRODUCT_COMMENT_WRITE_QUERY),
    @NamedQuery(name = GET_PRODUCT_COMMENT_BY_PRODUCT_AND_WRITER, query = GET_PRODUCT_COMMENT_BY_PRODUCT_AND_WRITER_QUERY)
})
public class ProductCommentBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 7L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "cpr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_cpr_seq")
    @SequenceGenerator(name = "s_cpr_seq", sequenceName = "s_cpr_seq",
    initialValue = 1)
    private Long id;
    /**
     * The comment.
     */
    @Column(name = "cpr_comment", nullable = false)
    private String comment;
    /**
     * The user who left the comment.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cpr_usr_id", nullable = false)
    private PartialUserBO writer;
    /**
     * The garden that is commented.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cpr_prd_id", nullable = false)
    private ProductBO product;
    /**
     * The relationship state.
     */
    @Transient
    private EvaluationStatus status;
    /**
     * Technical value
     */
    @Column(name = "cpr_res_id", nullable = false)
    private Integer _status;
    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "cpr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The general note of the comment.
     */
    @Transient
    private EvaluationNote note;
    /**
     * Technical value
     */
    @Column(name = "cpr_ren_id", nullable = false)
    private Integer _note;

    /**
     * Get the comment.
     *
     * @return The comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment.
     *
     * @param pComment - The comment to set
     */
    public void setComment(String pComment) {
        this.comment = pComment;
    }

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

    /**
     * Get the evaluation note.
     *
     * @return - The note
     */
    public EvaluationNote getNote() {
        return note;
    }

    /**
     * Set the evaluation note.
     *
     * @param note - The note to set
     */
    public void setNote(EvaluationNote pNote) {
        this.note = pNote;
        if(pNote !=null){
            _note = pNote.toInt();
        } else {
            _note = -1;
        }
    }

    @PostLoad
    void populateTransientFields() {
        status = EvaluationStatus.fromInt(_status);
        note = EvaluationNote.fromInt(_note);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getProductCommentByProduct query.
         */
        public static final String GET_PRODUCT_COMMENT_BY_PRODUCT = "ProductCommentBO.getProductCommentByProduct";
        /*
         * The getProductCommentByProduct query.
         */
        public static final String GET_PRODUCT_COMMENT_BY_PRODUCT_QUERY = "SELECT e FROM ProductCommentBO e WHERE e.product.id = :product";
        /*
         * The name of getGardenLikeByGarden query.
         */
        public static final String GET_PRODUCT_COMMENT_WRITE = "ProductCommentBO.getProductCommentWrite";
        /*
         * The getGardenLikeByGarden query.
         */
        public static final String GET_PRODUCT_COMMENT_WRITE_QUERY = "SELECT e FROM ProductCommentBO e WHERE e.writer.id     = :userWriter";
        /*
         * The name of GetProductByName query.
         */
        public static final String GET_PRODUCT_COMMENT_BY_PRODUCT_AND_WRITER = "ProductCommentBO.getProductCommentByProductAndWriter";
        /*
         * The GetProductByName query.
         */
        public static final String GET_PRODUCT_COMMENT_BY_PRODUCT_AND_WRITER_QUERY = "SELECT e FROM ProductCommentBO e WHERE e.product.id = :product AND e.writer.id = :userWriter";

        /*
         * Default constructor.
         */

        private QueryNamedConstant() {
        }
    }
}
