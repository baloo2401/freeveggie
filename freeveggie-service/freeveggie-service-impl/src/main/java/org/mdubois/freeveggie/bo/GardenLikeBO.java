package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import org.mdubois.freeveggie.EvaluationStatus;
import static org.mdubois.freeveggie.bo.GardenLikeBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The representation of a user liking a garden.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_LIKE_GARDEN")
@NamedQueries(value = {
    @NamedQuery(name = GET_GARDEN_LIKE_BY_GARDEN, query = GET_GARDEN_LIKE_BY_GARDEN_QUERY),
    @NamedQuery(name = GET_GARDEN_LIKE_BY_USER_AND_GARDEN_LIKE_ID, query = GET_GARDEN_LIKE_BY_USER_AND_GARDEN_LIKE_ID_QUERY),
    @NamedQuery(name = GET_GARDEN_LIKE_WRITE, query = GET_GARDEN_LIKE_WRITE_QUERY),
    @NamedQuery(name = GET_GARDEN_LIKE, query = GET_GARDEN_LIKE_QUERY)
})
public class GardenLikeBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 5L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "lgr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_lgr_seq")
    @SequenceGenerator(name = "s_lgr_seq", sequenceName = "s_lgr_seq",
    initialValue = 1)
    private Long id;
    /**
     * The user who left the comment.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "lgr_usr_id", nullable = false)
    private PartialUserBO writer;
    /**
     * The garden that is commented.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "lgr_grd_id", nullable = false)
    private GardenBO garden;
    /**
     * The relationship state.
     */
    @Transient
    private EvaluationStatus status;
    /**
     * Technical value
     */
    @Column(name = "lgr_res_id", nullable = false)
    private Integer _status;
    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "lgr_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

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
     * @return The cration date.
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
         * The name of getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE_BY_GARDEN = "GardenLikeBO.getGardenLikeByGarden";
        /*
         * The getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE_BY_GARDEN_QUERY = "SELECT e FROM GardenLikeBO e WHERE e.garden.id = :garden";
        /*
         * The name of getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE_WRITE = "GardenLikeBO.getGardenLikeByWrite";
        /*
         * The getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE_WRITE_QUERY = "SELECT e FROM GardenLikeBO e WHERE e.writer.id = :userWriter";
        /*
         * The name of GetGardenByName query.
         */
        public static final String GET_GARDEN_LIKE_BY_USER_AND_GARDEN_LIKE_ID = "GardenLikeBO.getGardenLikeByUserAndGardenLikeId";
        /*
         * The GetGardenByName query.
         */
        public static final String GET_GARDEN_LIKE_BY_USER_AND_GARDEN_LIKE_ID_QUERY = "SELECT e FROM GardenLikeBO e WHERE e.writer.id = :userWriter AND e.id = :pIdGardenLike";
        /*
         * The name of getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE = "GardenLikeBO.getGardenLike";
        /*
         * The getGardenLikeByGarden query.
         */
        public static final String GET_GARDEN_LIKE_QUERY = "SELECT e FROM GardenLikeBO e WHERE e.writer.id = :userWriter and e.garden.id = :garden";
        /*
         * Default constructor.
         */

        private QueryNamedConstant() {
        }
    }
}
