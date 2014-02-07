package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import static org.mdubois.freeveggie.bo.GardenCommentBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * Comment made on a user.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_COMMENT_GARDEN")
@NamedQueries(value = {
    @NamedQuery(name = GET_GARDEN_COMMENT_BY_GARDEN, query = GET_GARDEN_COMMENT_BY_GARDEN_QUERY),
    @NamedQuery(name = GET_GARDEN_COMMENT_WROTE, query = GET_GARDEN_COMMENT_WROTE_QUERY),
    @NamedQuery(name = GET_GARDEN_COMMENT_BY_GARDEN_AND_WRITER, query = GET_GARDEN_COMMENT_BY_GARDEN_AND_WRITER_QUERY)

})
public class GardenCommentBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 4L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "cgr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_cgr_seq")
    @SequenceGenerator(name = "s_cgr_seq", sequenceName = "s_cgr_seq",
    initialValue = 1)
    private Long id;

    /**
     * The comment.
     */
    @Column(name = "cgr_comment", nullable=false)
    private String comment;

    /**
     * The user who left the comment.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cgr_usr_id", nullable = false)
    private PartialUserBO writer;

    /**
     * The garden that is commented.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "cgr_grd_id", nullable = false)
    private GardenBO garden;

    /**
     * The relationship state.
     */
    @Transient
    private EvaluationStatus status;

    /**
     * Technical value.
     */
    @Column(name = "cgr_res_id",nullable=false)
    private Integer _status;

    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "cgr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * The general note of the comment.
     */
    @Transient
    private EvaluationNote note;

    /**
     * Technical value.
     */
    @Column(name = "cgr_ren_id", nullable = false)
    private Integer _note;
    /**
     * Get the comment.
     * @return The comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the comment.
     * @param pComment - The comment to set
     */
    public void setComment(String pComment) {
        this.comment = pComment;
    }

    /**
     * Get the garden commented.
     * @return The garden
     */
    public GardenBO getGarden() {
        return garden;
    }

    /**
     * Set the garden being commented.
     * @param pGarden - The garden to set
     */
    public void setGarden(GardenBO pGarden) {
        this.garden = pGarden;
    }

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
     * @param pId - The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the status of the comment.
     * @return The comment status
     */
    public EvaluationStatus getStatus() {
        return status;
    }

    /**
     * Set the comment status.
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
     * @return The user writer
     */
    public PartialUserBO getWriter() {
        return writer;
    }

    /**
     * Set the user who write the comment.
     * @param pWriter - The user to set
     */
    public void setWriter(PartialUserBO pWriter) {
        this.writer = pWriter;
    }

    /**
     * Get the date and time that the comment have been create.
     * @return The creation date.
     */
    public Date getCreationDate() {
        if(creationDate!=null){
            return new Date(creationDate.getTime());
        }
        return null;
    }

    /**
     * Set the date and time of creation.
     * @param pCreationDate - The creation date
     */
    public void setCreationDate(Date pCreationDate) {
        if(pCreationDate!=null){
            this.creationDate = new Date(pCreationDate.getTime());
        } else {
            this.creationDate = null;
        }
    }

    /**
     * Get the evaluation note.
     * @return - The note
     */
    public EvaluationNote getNote() {
        return note;
    }

    /**
     * Set the evaluation note.
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
         * The name of GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_BY_GARDEN = "GardenCommentBO.getGardenCommentByGarden";
        /*
         * The GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_BY_GARDEN_QUERY = "SELECT e FROM GardenCommentBO e WHERE e.garden.id = :garden";
        /*
         * The name of GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_WROTE = "GardenCommentBO.getGardenCommentWrote";
        /*
         * The GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_WROTE_QUERY = "SELECT e FROM GardenCommentBO e WHERE e.writer.id = :userWriter";
        /*
         * The name of GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_BY_GARDEN_AND_WRITER = "GardenCommentBO.getGardenCommentByGardenAndWriter";
        /*
         * The GetProductByName query.
         */
        public static final String GET_GARDEN_COMMENT_BY_GARDEN_AND_WRITER_QUERY = "SELECT e FROM GardenCommentBO e WHERE e.garden.id = :garden AND e.writer.id = :userWriter";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
