package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.True;

/**
 * The user agreement status.
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class SubscriptionMsg extends Message {

    /**
     * The database id.
     */
    private Long id;

    /**
     * User agree the freeveggieAgreement.
     */
    @True
    private Boolean freeveggieAgreement;

    /**
     * User agree to the weekly news letter.
     */
    @True
    private Boolean freeveggieNewsletter;

    /**
     * User agree to share personal information.
     */
    @True
    private Boolean sharePersonalInformation;

    /**
     * User agree to share his garden information.
     */
    @True
    private Boolean shareGardenInformation;

    /**
     * Get the freeveggie agreement signature.
     * @return The freveggie agrrement signature
     */
    public Boolean getFreeveggieAgreement() {
        return freeveggieAgreement;
    }

    /**
     * Set the freeveggie agreement signature.
     * @param pFreeveggieAgreement The value to set.
     */
    public void setFreeveggieAgreement(Boolean pFreeveggieAgreement) {
        this.freeveggieAgreement = pFreeveggieAgreement;
    }

    /**
     * Get the freeveggie newsletter signature.
     * @return The signature
     */
    public Boolean getFreeveggieNewsletter() {
        return freeveggieNewsletter;
    }

    /**
     * Set the freeveggie newsletter signature.
     * @param pFreeveggieNewsletter The value to set
     */
    public void setFreeveggieNewsletter(Boolean pFreeveggieNewsletter) {
        this.freeveggieNewsletter = pFreeveggieNewsletter;
    }

    /**
     * Get the database id.
     * @return The database id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param id The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the share garden signature.
     * @return The signature value
     */
    public Boolean getShareGardenInformation() {
        return shareGardenInformation;
    }

    /**
     * Set the share garden signature.
     * @param pShareGardenInformation The value to set
     */
    public void setShareGardenInformation(Boolean pShareGardenInformation) {
        this.shareGardenInformation = pShareGardenInformation;
    }

    /**
     * Get the share personal information signature.
     * @return The value of the signature
     */
    public Boolean getSharePersonalInformation() {
        return sharePersonalInformation;
    }

    /**
     * Set the share personal information signature.
     * @param pSharePersonalInformation The value to set
     */
    public void setSharePersonalInformation(Boolean pSharePersonalInformation) {
        this.sharePersonalInformation = pSharePersonalInformation;
    }

}
