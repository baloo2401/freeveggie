package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.RequestStatus;
import static org.mdubois.freeveggie.bo.ProductRequestBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The request of a product.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REQUEST_PRODUCT")
@NamedQueries(value = {
    @NamedQuery(name = GET_PRODUCT_REQUEST_BY_PRODUCT, query = GET_PRODUCT_REQUEST_BY_PRODUCT_QUERY),
    @NamedQuery(name = GET_PRODUCT_REQUEST_BY_GARDEN, query = GET_PRODUCT_REQUEST_BY_GARDEN_QUERY),
    @NamedQuery(name = GET_PRODUCT_REQUEST_BY_SENDER, query = GET_PRODUCT_REQUEST_BY_SENDER_QUERY),
    @NamedQuery(name = GET_PRODUCT_REQUEST_BY_RECEIVER, query = GET_PRODUCT_REQUEST_BY_RECEIVER_QUERY)
})
public class ProductRequestBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 9L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "rqt_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_rqt_seq")
    @SequenceGenerator(name = "s_rqt_seq", sequenceName = "s_rqt_seq",
    initialValue = 1)
    private Long id;

    /**
     * The product requested.
     */
    @ManyToOne
    @JoinColumn(name = "rqt_prd_id", nullable = false)
    private ProductBO product;
    /**
     * The user who make the request
     */
    @ManyToOne
    @JoinColumn(name = "rqt_usr_id", nullable = false)
    private PartialUserBO requester;
    /**
     * The quantity requested.
     */
    @Column(name = "rqt_quantity", nullable = false)
    private Float quantity;
    /**
     * A message to the owner of the product from the requester.
     */
    @Column(name = "rqt_msg", nullable = false)
    private String message;
    /**
     * The answer from the owner.
     */
    @Column(name = "rqt_answer_msg")
    private String answer;
    /**
     * The status of the request.
     */
    @Transient
    private RequestStatus status;

    /**
     * Technical value.
     */
    @Column(name = "rqt_rpr_id", nullable = false)
    private Integer _status;
    /**
     * The date of the request has been send.
     */
    @Column(name = "rqt_creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    /**
     * The date of when the owner answer.
     */
    @Column(name = "rqt_answer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;
    /**
     * The date of when the request which to pick the product.
     */
    @Column(name = "rqt_picking_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pickingDate;

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
     * Get the answer message.
     * @return The answer message
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer message.
     * @param pAnswer - The message to set
     */
    public void setAnswer(String pAnswer) {
        this.answer = pAnswer;
    }

    /**
     * Get the anwser date.
     * @return The anwser date
     */
    public Date getAnswerDate() {
        if (answerDate != null) {
            return new Date(answerDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date when the anwser was given.
     * @param pAnswerDate - The date to set
     */
    public void setAnswerDate(Date pAnswerDate) {
        if (pAnswerDate != null) {
            this.answerDate = new Date(pAnswerDate.getTime());
        } else {
            this.answerDate = null;
        }
    }

    /**
     * Get hte date the request was made.
     * @return - The creation date
     */
    public Date getCreationDate() {
        if (creationDate != null) {
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date when the user made the request.
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
     * Get the message write by the requester.
     * @return The requester message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the requester message.
     * @param pMessage - The message to set
     */
    public void setMessage(String pMessage) {
        this.message = pMessage;
    }

    /**
     * Get the date when the picking should happen.
     * @return The picking date
     */
    public Date getPickingDate() {
        if(pickingDate!=null) {
            return new Date(pickingDate.getTime());
        } else {
            return null;
        }

    }

    /**
     * Set the date when the requester wich to pick the product.
     * @param pPickingDate - The picking date to set
     */
    public void setPickingDate(Date pPickingDate) {
        if(pPickingDate != null){
            this.pickingDate = new Date(pPickingDate.getTime());
        } else {
            this.pickingDate = null;
        }
    }

    /**
     * Get the product requested.
     * @return The requested product.
     */
    public ProductBO getProduct() {
        return product;
    }

    /**
     * Set the product requested.
     * @param pProduct - The product to set
     */
    public void setProduct(ProductBO pProduct) {
        this.product = pProduct;
    }

    /**
     * Get the quantity requested.
     * @return The quantity
     */
    public Float getQuantity() {
        return quantity;
    }

    /**
     * Set the quantity requested.
     * @param pQuantity - The quantity to set
     */
    public void setQuantity(Float pQuantity) {
        this.quantity = pQuantity;
    }

    /**
     * Get the user who made the request.
     * @return The requester
     */
    public PartialUserBO getRequester() {
        return requester;
    }

    /**
     * Set the user who make the request.
     * @param pRequester - The user to set
     */
    public void setRequester(PartialUserBO pRequester) {
        this.requester = pRequester;
    }

    /**
     * Get the status of the request.
     * @return The request status
     */
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Set the status request.
     * @param pStatus - The status to set
     */
    public void setStatus(RequestStatus pStatus) {
        this.status = pStatus;
        if(pStatus != null){
            _status = pStatus.toInt();
        } else {
            _status = -1;
        }
    }

    @PostLoad
    void populateTransientFields() {
        status = RequestStatus.fromInt(_status);
    }


    public static final class QueryNamedConstant {

        /*
         * The name of getProductRequestByProduct query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_PRODUCT = "ProductRequestBO.getProductRequestByProduct";
        /*
         * The getProductRequestByProduct query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_PRODUCT_QUERY = "SELECT e FROM ProductRequestBO e WHERE e.product.id = :product";
        /*
         * The nams of getProductRequestByGarden query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_GARDEN = "ProductRequestBO.getProductRequestByGarden";
        /*
         * The getProductRequestByGarden query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_GARDEN_QUERY = "SELECT e FROM ProductRequestBO e WHERE e.product.garden.id = :garden";
        /*
         * The name of getRegionByCountry query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_SENDER = "ProductRequestBO.getProductRequestBySender";
        /*
         * The getRegionByCountry query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_SENDER_QUERY = "SELECT e FROM ProductRequestBO e WHERE e.requester.id = :userBO";
        /*
         * The name of getRegionByCountry query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_RECEIVER = "ProductRequestBO.getProductRequestByReceiver";
        /*
         * The getRegionByCountry query.
         */
        public static final String GET_PRODUCT_REQUEST_BY_RECEIVER_QUERY = "SELECT e FROM ProductRequestBO e WHERE e.product.garden.owner.id = :userBO";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
