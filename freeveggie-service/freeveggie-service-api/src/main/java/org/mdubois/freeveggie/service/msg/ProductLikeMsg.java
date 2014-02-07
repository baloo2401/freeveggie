package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * The fact that a user like a product.
 *
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class ProductLikeMsg extends Message {

    /**
     * The database id.
     */
    private Long id;
    /**
     * The user who left the comment.
     */
    @Required(field = "id")
    private PartialUserMsg writer;
    /**
     * The garden that is commented.
     */
    @Required(field = "id")
    private ProductMsg product;
    /**
     * The relationship state.
     */
    private EvaluationStatus status;
    /**
     * The date and time of the creation of the comment.
     */
    private Date creationDate;

    /**
     * Get the product commented.
     *
     * @return The product
     */
    public ProductMsg getProduct() {
        return product;
    }

    /**
     * Set the product being commented.
     *
     * @param pProduct - The product to set
     */
    public void setProduct(ProductMsg pProduct) {
        this.product = pProduct;
    }

    /**
     * Get the database id.
     *
     * @return The database id
     */
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
    }

    /**
     * Get the user who write the comment.
     *
     * @return The user writer
     */
    public PartialUserMsg getWriter() {
        return writer;
    }

    /**
     * Set the user who write the comment.
     *
     * @param pWriter - The user to set
     */
    public void setWriter(PartialUserMsg pWriter) {
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

}
