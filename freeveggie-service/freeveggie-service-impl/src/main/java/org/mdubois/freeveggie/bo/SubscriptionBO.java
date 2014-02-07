package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The user agreement status.
 *
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_SUBSCRIPTION")
@SuppressWarnings("serial")
public class SubscriptionBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 18L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "sbr_id")
    private Long id;
    /**
     * User agree the freeveggieAgreement.
     */
    @Column(name = "sbr_freeveggie_agreement")
    private Boolean freeveggieAgreement;
    /**
     * User agree to the weekly news letter.
     */
    @Column(name = "sbr_newsletter")
    private Boolean freeveggieNewsletter;
    /**
     * User agree to share personal information.
     */
    @Column(name = "sbr_share_personal")
    private Boolean sharePersonalInformation;
    /**
     * User agree to share his garden information.
     */
    @Column(name = "sbr_share_garden")
    private Boolean shareGardenInformation;

    /**
     * Get the freeveggie agreement signature.
     *
     * @return The freveggie agrrement signature
     */
    public boolean getFreeveggieAgreement() {
        return freeveggieAgreement;
    }

    /**
     * Set the freeveggie agreement signature.
     *
     * @param pFreeveggieAgreement The value to set.
     */
    public void setFreeveggieAgreement(boolean pFreeveggieAgreement) {
        this.freeveggieAgreement = pFreeveggieAgreement;
    }

    /**
     * Get the freeveggie newsletter signature.
     *
     * @return The signature
     */
    public boolean getFreeveggieNewsletter() {
        return freeveggieNewsletter;
    }

    /**
     * Set the freeveggie newsletter signature.
     *
     * @param pFreeveggieNewsletter The value to set
     */
    public void setFreeveggieNewsletter(boolean pFreeveggieNewsletter) {
        this.freeveggieNewsletter = pFreeveggieNewsletter;
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
     * @param id The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the share garden signature.
     *
     * @return The signature value
     */
    public boolean getShareGardenInformation() {
        return shareGardenInformation;
    }

    /**
     * Set the share garden signature.
     *
     * @param pShareGardenInformation The value to set
     */
    public void setShareGardenInformation(boolean pShareGardenInformation) {
        this.shareGardenInformation = pShareGardenInformation;
    }

    /**
     * Get the share personal information signature.
     *
     * @return The value of the signature
     */
    public boolean getSharePersonalInformation() {
        return sharePersonalInformation;
    }

    /**
     * Set the share personal information signature.
     *
     * @param pSharePersonalInformation The value to set
     */
    public void setSharePersonalInformation(boolean pSharePersonalInformation) {
        this.sharePersonalInformation = pSharePersonalInformation;
    }
}
