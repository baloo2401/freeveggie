package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import static org.mdubois.freeveggie.bo.RelationShipBO.QueryNamedConstant.GET_RELATIONSHIP;
import static org.mdubois.freeveggie.bo.RelationShipBO.QueryNamedConstant.GET_RELATIONSHIP_QUERY;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * Link two user by a relationship. The the user one {@link UserPartialBO} is the one
 * who initiate the relationship.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_RELATIONSHIP",
uniqueConstraints =
@UniqueConstraint(columnNames = {"rlt_usr_id_sender", "rlt_usr_id_recipient"}))
@NamedQueries(value = {
    @NamedQuery(name = GET_RELATIONSHIP, query = GET_RELATIONSHIP_QUERY)
})
public class RelationShipBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 17L;

    /**
     * The database id
     */
    @Id
    @Column(name = "rlt_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_rlt_seq")
    @SequenceGenerator(name = "s_rlt_seq", sequenceName = "s_rlt_seq",
    initialValue = 1)
    private Long id;
    /**
     * The first part of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "rlt_usr_id_sender")
    private PartialUserBO sender;
    /**
     * The second part of the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "rlt_usr_id_recipient")
    private PartialUserBO recipient;
    /**
     * The relationship definition.
     */
    @Transient
    private RelationshipType type;
    /**
     * The technical value.
     */
    @Column(name = "rlt_rrt_id")
    private Integer _type;
    /**
     * The relationship state.
     */
    @Transient
    private RelationshipStatus status;
    /**
     * The relationship state.
     */
    @Column(name = "rlt_rrs_id")
    private Integer _status;
    /**
     * The date when the relationship was created.
     */
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "rlt_creation_date", nullable = false)
    private Date creationDate;
    /**
     * A comment made by the requester.
     */
    @Column(name = "rlt_answer", nullable = false, length = 512)
    private String answer;
    /**
     * A comment made by the requester.
     */
    @Column(name = "rlt_request", nullable = true, length = 512)
    private String request;

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
     * @param pId The id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the date of when the relationship started.
     * @return The creation date
     */
    public Date getCreationDate() {
        if(creationDate !=null){
            return new Date(creationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * Set the date of when the relationship started.
     * @param pCreationDate The date to set
     */
    public void setCreationDate(Date pCreationDate) {
        if(pCreationDate!=null){
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    /**
     * Get the status of the relationship.
     * @return The relationship status
     */
    public RelationshipStatus getStatus() {
        return status;
    }

    /**
     * Set the relations status.
     * @param pStatus The state to set
     */
    public void setStatus(RelationshipStatus pStatus) {
        this.status = pStatus;
        if(pStatus !=  null){
            _status = pStatus.toInt();
        } else {
            _status = -1;
        }
    }

    /**
     * Get the relationship type.
     * @return The relationship type
     */
    public RelationshipType getType() {
        return type;
    }

    /**
     * Set the relations type.
     * @param pType The type to set
     */
    public void setType(RelationshipType pType) {
        this.type = pType;
        if(pType !=  null){
            _type = pType.toInt();
        } else {
            _type = -1;
        }
    }

    /**
     * Get the first part of the relationship. It is the user who initialize the
     * relationship.
     * @return The first user
     */
    public PartialUserBO getSender() {
        return sender;
    }

    /**
     * Set the first part of the relationship. It must be the user who initialize
     * the relationship.
     * @param pSender The user to set
     */
    public void setSender(PartialUserBO pSender) {
        this.sender = pSender;
    }

    /**
     * Get the second part of the relationship.
     * @return The second user
     */
    public PartialUserBO getRecipient() {
        return recipient;
    }

    /**
     * Set the second part of the relationship.
     * @param pRecipient The user to set
     */
    public void setRecipient(PartialUserBO pRecipient) {
        this.recipient = pRecipient;
    }

    /**
     * Get the comment left by the requester.
     * @return The comment
     */
    public String getRequest() {
        return request;
    }

    /**
     * Set the comment left by the requester.
     * @param pRequest - The comment to set
     */
    public void setRequest(String pRequest) {
        this.request = pRequest;
    }

    /**
     * Get the response of the request.
     * @return The response
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Set the answer of the request.
     * @param pAnswer - The answer to set
     */
    public void setAnswer(String pAnswer) {
        this.answer = pAnswer;
    }

    @PostLoad
    void populateTransientFields() {
        type = RelationshipType.fromInt(_type);
        status = RelationshipStatus.fromInt(_status);
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getRelationShip query.
         */
        public static final String GET_RELATIONSHIP = "RelationShipBO.getRelationShip";

        /*
         * The getRelationShip query.
         */
        public static final String GET_RELATIONSHIP_QUERY = "SELECT e FROM RelationShipBO e WHERE (e.sender.id = :sender OR e.recipient.id =:recipient)";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }

}
