package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
// </editor-fold>

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefStateMsg extends Message {

    /**
     * The database id.
     */
    private Integer id;
    /**
     * The state name.
     */
    private String name;
    /**
     * The country.
     */
    private RefCountryMsg country;

    /**
     * The country of the state.
     * @return The country
     */
    public RefCountryMsg getCountry() {
        return country;
    }

    /**
     * Set the country.
     * @param pCountry - The country to set
     */
    public void setCountry(RefCountryMsg pCountry) {
        this.country = pCountry;
    }

    /**
     * Get the database id.
     * @return The database id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the region name.
     * @return The region name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the region name.
     * @param name - The region to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
