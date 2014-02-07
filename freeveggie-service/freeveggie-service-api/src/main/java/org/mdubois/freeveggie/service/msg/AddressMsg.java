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
public class AddressMsg extends Message {

    /**
     * The database id.
     */
    private Long id;
    /**
     * The name of the address.
     */
    @Required
    private String name;
    /**
     * The street number.
     */
    private String streetNumber;
    /**
     * First line for the address
     */
    @Required
    private String streetName;
    /**
     * The address city.
     */
    private RefCityMsg city;

    /**
     * The user city name.
     */
    @Required
    private String locality;

    /**
     * The user administrative area level 2.
     */
    @Required
    private String administrativeAreaLevel2;

    /**
     * The user administrative area level 1.
     */
    @Required
    private String administrativeAreaLevel1;

    /**
     * The user country.
     */
    @Required
    private String country;

    /**
     * The user postal code.
     */
    @Required
    private String postalCode;

    /**
     * The address latitude.
     */
    @Required
    private Double latitude;

    /**
     * The address longitude.
     */
    @Required
    private Double longitude;

    /**
     * Get the address first line.
     *
     * @return The address first line
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Set the address first line.
     *
     * @param pStreetName The address line to set
     */
    public void setStreetName(String pStreetName) {
        this.streetName = pStreetName;
    }

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
     * @param pId The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the address name.
     *
     * @return The address name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the address name.
     *
     * @param pName The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * The address street number.
     *
     * @return The street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Set the address street number
     *
     * @param pStreetNumber The street number to set
     */
    public void setStreetNumber(String pStreetNumber) {
        this.streetNumber = pStreetNumber;
    }

    /**
     * Get the city.
     *
     * @return The city
     */
    public RefCityMsg getCity() {
        return city;
    }

    /**
     * Set the city.
     *
     * @param city - The city to set
     */
    public void setCity(RefCityMsg city) {
        this.city = city;
    }
    
        /**
     * Get the user address city name.
     * @return The user address city name
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Set the user address city name
     * @param pLocality - The user address city name to set
     */
    public void setLocality(final String pLocality) {
        this.locality = pLocality;
    }

    /**
     * Get the user address administrative area level 2.
     * @return The user address administrative area level 2
     */
    public String getAdministrativeAreaLevel2() {
        return administrativeAreaLevel2;
    }

    /**
     * Set the user address administrative area level 2.
     * @param pAdministrativeAreaLevel2 - The user address administrative area level 2 to set
     */
    public void setAdministrativeAreaLevel2(final String pAdministrativeAreaLevel2) {
        this.administrativeAreaLevel2 = pAdministrativeAreaLevel2;
    }

    /**
     * Get the user address administrative area level 1.
     * @return The user address administrative area level 1 
     */
    public String getAdministrativeAreaLevel1() {
        return administrativeAreaLevel1;
    }

    /**
     * Set the user address administrative area level 1.
     * @param pAdministrativeAreaLevel1 - The user address administrative area level 1 to set
     */
    public void setAdministrativeAreaLevel1(final String pAdministrativeAreaLevel1) {
        this.administrativeAreaLevel1 = pAdministrativeAreaLevel1;
    }

    /**
     * Get the user address country.
     * @return The user address country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the user address country.
     * @param pCountry - The user address country to set
     */
    public void setCountry(final String pCountry) {
        this.country = pCountry;
    }

    /**
     * Get the user address postal code.
     * @return The user address postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Set the user address postal code.
     * @param pPostalCode - The user address postal code to set.
     */
    public void setPostalCode(final String pPostalCode) {
        this.postalCode = pPostalCode;
    }

    /**
     * Get the user address precise latitude.
     * @return The user address precise latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Set the user address precise latitude.
     * @param pLatitude - The user address precise latitude to set
     */
    public void setLatitude(final Double pLatitude) {
        this.latitude = pLatitude;
    }

    /**
     * Get the user address precise longitude.
     * @return The user address precise longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Set the user address precise longitude.
     * @param pLongitude - The user address precise longitude to set
     */
    public void setLongitude(final Double pLongitude) {
        this.longitude = pLongitude;
    }
}
