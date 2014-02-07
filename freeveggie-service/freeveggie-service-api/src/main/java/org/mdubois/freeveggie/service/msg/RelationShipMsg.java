package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.framework.msg.Length;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * Link two user by a relationship. The the user one {@link UserPartialMsg} is
 * the one who initiate the relationship.
 *
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class RelationShipMsg extends Message {

    /**
     * The database id
     */
    private Long id;
    /**
     * The first part of the relationship.
     */
    @Required(field = "id")
    private PartialUserMsg sender;
    /**
     * The second part of the relationship.
     */
    @Required(field = "id")
    private PartialUserMsg recipient;
    /**
     * The relationship definition.
     */
    @Required
    private RelationshipType type;
    /**
     * The relationship state.
     */
    private RelationshipStatus status;
    /**
     * The date when the relationship was created.
     */
    private Date creationDate;
    /**
     * A comment made by the requester.
     */
    private String answer;
    /**
     * A comment made by the requester.
     */
    @Length(min = 10, max = 512)
    private String request;

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
     * @param pId The id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the date of when the relationship started.
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
     * Set the date of when the relationship started.
     *
     * @param pCreationDate The date to set
     */
    public void setCreationDate(Date pCreationDate) {
        if (pCreationDate != null) {
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    /**
     * Get the status of the relationship.
     *
     * @return The relationship status
     */
    public RelationshipStatus getStatus() {
        return status;
    }

    /**
     * Set the relations status.
     *
     * @param pStatus The state to set
     */
    public void setStatus(RelationshipStatus pStatus) {
        this.status = pStatus;
    }

    /**
     * Get the relationship type.
     *
     * @return The relationship type
     */
    public RelationshipType getType() {
        return type;
    }

    /**
     * Set the relations type.
     *
     * @param pType The type to set
     */
    public void setType(RelationshipType pType) {
        this.type = pType;
    }

    /**
     * Get the first part of the relationship. It is the user who initialize the
     * relationship.
     *
     * @return The first user
     */
    public PartialUserMsg getSender() {
        return sender;
    }

    /**
     * Set the first part of the relationship. It must be the user who
     * initialize the relationship.
     *
     * @param pSender The user to set
     */
    public void setSender(PartialUserMsg pSender) {
        this.sender = pSender;
    }

    /**
     * Get the second part of the relationship.
     *
     * @return The second user
     */
    public PartialUserMsg getRecipient() {
        return recipient;
    }

    /**
     * Set the second part of the relationship.
     *
     * @param pRecipient The user to set
     */
    public void setRecipient(PartialUserMsg pRecipient) {
        this.recipient = pRecipient;
    }

    /**
     * Get the comment left by the requester.
     *
     * @return The comment
     */
    public String getRequest() {
        return request;
    }

    /**
     * Set the comment left by the requester.
     *
     * @param pRequest - The comment to set
     */
    public void setRequest(String pRequest) {
        this.request = pRequest;
    }

    /**
     * Get the response of the request.
     *
     * @return The response
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer of the request.
     *
     * @param pAnswer - The answer to set
     */
    public void setAnswer(String pAnswer) {
        this.answer = pAnswer;
    }
}
