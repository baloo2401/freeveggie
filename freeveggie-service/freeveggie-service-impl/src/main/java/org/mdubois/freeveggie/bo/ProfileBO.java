package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The user profile.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_PROFIL")
public class ProfileBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 10L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "prf_id")
    private Long id;
    /**
     * The user personal picture
     */
    @Column(name = "prf_img_filename")
    private String userPictureFilename;

    /**
     * A personal description.
     */
    @Column(name = "prf_description")
    private String personalDescription;

    /**
     * A sentence on how to participate to the idea.
     */
    @Column(name = "prf_participation")
    private String participation;

    /**
     * Share an experience.
     */
    @Column(name = "prf_experience")
    private String experience;

    /**
     * Give what the user is interest in.
     */
    @Column(name = "prf_interest")
    private String interest;

    /**
     * The user philosophy in life.
     */
    @Column(name = "prf_philosophy")
    private String philosophy;

    /**
     * Anything the user want to say.
     */
    @Column(name = "prf_other")
    private String other;

    /**
     * The user prefered meals.
     */
    @Column(name = "prf_meals")
    private String preferedMeals;

    /**
     * The user reason to be on the website.
     */
    @Column(name = "prf_reason")
    private String reason;

    /**
     * Get the databse id.
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
     * Get the picture of the garden.
     * @return - The picture
     */
    public String getUserPictureFilename() {
        return userPictureFilename;
    }

    /**
     * Set the picture of the garden.
     * @param pUserPictureFilename - The picture to set
     */
    public void setUserPictureFilename(String pUserPictureFilename) {
        this.userPictureFilename = pUserPictureFilename;
    }

    /**
     * Get an expresion message.
     * @return The message
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Set the expression message.
     * @param pExperience - The message to set
     */
    public void setExperience(String pExperience) {
        this.experience = pExperience;
    }

    /**
     * Get an interest message.
     * @return The message
     */
    public String getInterest() {
        return interest;
    }

    /**
     * Set the interest message.
     * @param interest - The message to set
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * Get a message that the user want to write.
     * @return The message
     */
    public String getOther() {
        return other;
    }

    /**
     * Set the user message.
     * @param other The message to set
     */
    public void setOther(String other) {
        this.other = other;
    }

    /**
     * Get the message left about particiaption
     * @return The message
     */
    public String getParticipation() {
        return participation;
    }

    /**
     * Set the messsage for participation idea.
     * @param participation - The message to set
     */
    public void setParticipation(String participation) {
        this.participation = participation;
    }

    /**
     * Get the personal description.
     * @return The description
     */
    public String getPersonalDescription() {
        return personalDescription;
    }

    /**
     * Set the persnal description.
     * @param personalDescription - The description to set
     */
    public void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription;
    }

    /**
     * Get the phylosophie message of the user.
     * @return The message
     */
    public String getPhilosophy() {
        return philosophy;
    }

    /**
     * Set the philosophie message.
     * @param philosophy - The message to set
     */
    public void setPhilosophy(String philosophy) {
        this.philosophy = philosophy;
    }

    /**
     * Get the prefered meal message.
     * @return The message
     */
    public String getPreferedMeals() {
        return preferedMeals;
    }

    /**
     * Set the perfered meal message.
     * @param preferedMeals - The message to set
     */
    public void setPreferedMeals(String preferedMeals) {
        this.preferedMeals = preferedMeals;
    }

    /**
     * Get the user reason of been a freeveggie user.
     * @return The user reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the user reason of been a freeveggie user
     * @param reason - The reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }


}
