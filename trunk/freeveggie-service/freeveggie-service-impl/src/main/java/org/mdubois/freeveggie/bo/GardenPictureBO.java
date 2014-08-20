package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import javax.persistence.*;
import static org.mdubois.freeveggie.bo.GardenPictureBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * Comment made on a user.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_PICTURE_GARDEN")
@NamedQueries(value = {
    @NamedQuery(name = GET_GARDEN_PICTURE_BY_GARDEN, query = GET_GARDEN_COMMENT_BY_GARDEN_QUERY)
})
public class GardenPictureBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 4L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "grp_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "s_grp_seq")
    @SequenceGenerator(name = "s_grp_seq", sequenceName = "s_grp_seq",
            initialValue = 1)
    private Long id;

    /**
     * The garden.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "grp_grd_id", nullable = false)
    private GardenBO garden;
    

    /**
     * The picture mime type.
     */
    @Column(name = "grp_mine_type", nullable = false)
    private String mimeType;

    /**
     * The picture
     */
    @Lob
    @Column(name = "picture", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

    /**
     * The date and time of the creation of the comment.
     */
    @Column(name = "grp_creation_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creationDate;

    /**
     * Set the id.
     *
     * @param id - The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the id.
     *
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * Get the garden the picture is for.
     *
     * @return - The garden the picture is for
     */
    public GardenBO getGarden() {
        return garden;
    }

    /**
     * Set the garden the picture is for.
     *
     * @param pGarden - The garden the picture is for to set
     */
    public void setGarden(GardenBO pGarden) {
        this.garden = pGarden;
    }

    /**
     * Get the mime type of the picture.
     *
     * @return - The mime type of the picture
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set the mime type of the picture.
     *
     * @param pMimeType - The mime type of the picture to set
     */
    public void setMimeType(String pMimeType) {
        this.mimeType = pMimeType;
    }

    /**
     * The picture.
     *
     * @return - The picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Set the picture.
     *
     * @param pPicture - The picture to set.
     */
    public void setPicture(byte[] pPicture) {
        this.picture = pPicture;
    }

    /**
     * Get the date when the picture as been saved.
     *
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
     *
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
         * The name of GetGardenByName query.
         */
        public static final String GET_GARDEN_PICTURE_BY_GARDEN = "GardenPictureBO.getGardenPictureByGarden";
        /*
         * The GetGardenByName query.
         */
        public static final String GET_GARDEN_COMMENT_BY_GARDEN_QUERY = "SELECT e FROM GardenPictureBO e WHERE e.garden.id = :garden";
        /*
         * Default constructor.
         */

        private QueryNamedConstant() {
        }
    }
}
