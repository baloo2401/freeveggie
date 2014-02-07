package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * The request of a product.
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class ProductRequestMsg extends Message {

    /**
     * The database id.
     */
    private Long id;

    /**
     * The product requested.
     */
    @Required(field = "id")
    private ProductMsg product;
    /**
     * The user who make the request
     */
    @Required(field = "id")
    private PartialUserMsg requester;
    /**
     * The quantity requested.
     */
    @Required
    private Float quantity;
    /**
     * A message to the owner of the product from the requester.
     */
    @Length(min = 10, max = 512)
    private String message;
    /**
     * The answer from the owner.
     */
    private String answer;
    /**
     * The status of the request.
     */
    private RequestStatus status;
    /**
     * The date of the request has been send.
     */
    private Date creationDate;
    /**
     * The date of when the owner answer.
     */
    private Date answerDate;
    /**
     * The date of when the request which to pick the product.
     */
    @Required
    private Date pickingDate;

    /**
     * Get the database id.
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The id to set.
     */
    public void setId(final Long pId) {
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
    public void setAnswer(final String pAnswer) {
        this.answer = pAnswer;
    }

    /**
     * Get the answer date.
     * @return The answer date
     */
    public Date getAnswerDate() {
        if (answerDate != null) {
            return new Date(answerDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date when the answer was given.
     * @param pAnswerDate - The date to set
     */
    public void setAnswerDate(final Date pAnswerDate) {
        if (pAnswerDate != null) {
            this.answerDate = new Date(pAnswerDate.getTime());
        } else {
            this.answerDate = null;
        }
    }

    /**
     * Get the date the request was made.
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
    public void setCreationDate(final Date pCreationDate) {
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
    public void setMessage(final String pMessage) {
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
     * Set the date when the requester witch to pick the product.
     * @param pPickingDate - The picking date to set
     */
    public void setPickingDate(final Date pPickingDate) {
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
    public ProductMsg getProduct() {
        return product;
    }

    /**
     * Set the product requested.
     * @param pProduct - The product to set
     */
    public void setProduct(final ProductMsg pProduct) {
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
    public void setQuantity(final Float pQuantity) {
        this.quantity = pQuantity;
    }

    /**
     * Get the user who made the request.
     * @return The requester
     */
    public PartialUserMsg getRequester() {
        return requester;
    }

    /**
     * Set the user who make the request.
     * @param pRequester - The user to set
     */
    public void setRequester(final PartialUserMsg pRequester) {
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
    public void setStatus(final RequestStatus pStatus) {
        this.status = pStatus;
    }


}
