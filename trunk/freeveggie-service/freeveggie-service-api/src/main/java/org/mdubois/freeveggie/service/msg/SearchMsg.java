package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 * The representation of an address.
 *
 * @author Mickael DuMsgis
 */
@XmlRootElement
public class SearchMsg extends Message {
    /**
     * THe upperest latitude
     */
    @Required
    private Double latitudeUp;
    
    /**
     * The lowest latitude
     */
    @Required
    private Double latitudeDown;
    
    /**
     * The upper longitude
     */
    @Required
    private Double longitudeUp;
    
    /**
     * The lowest longitude.
     */
    @Required
    private Double longitudeDown;
    
    /**
     * The ref product id.
     */
    private Integer refProductId;

    /**
     * Get the upper latitude.
     * @return The upper latitude
     */
    public Double getLatitudeUp() {
        return latitudeUp;
    }

    /**
     * Set the upper latitude.
     * @param latitudeUp - The upper latitude to set
     */
    public void setLatitudeUp(Double latitudeUp) {
        this.latitudeUp = latitudeUp;
    }

    /**
     * Get the lowest latitude.
     * @return The lowest latitude
     */
    public Double getLatitudeDown() {
        return latitudeDown;
    }

    /**
     * Set the lowest latitude.
     * @param latitudeDown The lowest latitude to set
     */
    public void setLatitudeDown(Double latitudeDown) {
        this.latitudeDown = latitudeDown;
    }
    /**
     * Get the upper longitude.
     * @return The upper longitude
     */
    public Double getLongitudeUp() {
        return longitudeUp;
    }

    /**
     * Set the upper longitude.
     * @param longitudeUp - The upper longitude to set
     */
    public void setLongitudeUp(Double longitudeUp) {
        this.longitudeUp = longitudeUp;
    }

    /**
     * Get the lowest longitude.
     * @return The lowest longitude
     */
    public Double getLongitudeDown() {
        return longitudeDown;
    }

    /**
     * Set the lowest longitude.
     * @param longitudeDown The lowest longitude to set
     */
    public void setLongitudeDown(Double longitudeDown) {
        this.longitudeDown = longitudeDown;
    }

    /**
     * Get the product ref id.
     * @return The product ref id 
     */
    public Integer getRefProductId() {
        return refProductId;
    }

    /**
     * Set the product ref id
     * @param refProductId The product ref id to set
     */
    public void setRefProductId(Integer refProductId) {
        this.refProductId = refProductId;
    }
}
