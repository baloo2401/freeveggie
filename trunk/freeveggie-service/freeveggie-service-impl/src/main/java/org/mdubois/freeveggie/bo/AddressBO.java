package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import static org.mdubois.freeveggie.bo.AddressBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
// </editor-fold>

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_ADDRESS")
@SuppressWarnings("serial")
@NamedQueries(value = {
    @NamedQuery(name = GET_ADDRESS_BY_COUNTRY, query = GET_ADDRESS_BY_COUNTRY_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_USER, query = GET_ADDRESS_BY_USER_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_COUNTRY_AND_CITY_NAME, query = GET_ADDRESS_BY_COUNTRY_AND_CITY_NAME_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_COUNTRY_AND_ZIP_CODE, query = GET_ADDRESS_BY_COUNTRY_AND_ZIP_CODE_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_CITY, query = GET_ADDRESS_BY_CITY_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_REGION, query = GET_ADDRESS_BY_REGION_QUERY),
    @NamedQuery(name = GET_ADDRESS_BY_STATE, query = GET_ADDRESS_BY_STATE_QUERY)
})
public class AddressBO extends BusinessObject<Long> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 2L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "adr_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_adr_seq")
    @SequenceGenerator(name = "s_adr_seq", sequenceName = "s_adr_seq",
    initialValue = 1)
    private Long id;
    /**
     * The name of the address.
     */
    @Column(name = "adr_name", nullable = false)
    private String name;
    /**
     * The street number.
     */
    @Column(name = "adr_street_number", nullable = false)
    private String streetNumber;
    /**
     * First line for the address
     */
    @Column(name = "adr_street_name", nullable = false)
    private String streetName;

    /**
     * The address city.
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "adr_rci_id")
    private RefCityBO city;

    /**
     * The user city name.
     */
    @Column(name = "adr_locality")
    private String locality;

    /**
     * The user administrative area level 2.
     */
    @Column(name = "adr_adm_area_l2")
    private String administrativeAreaLevel2;

    /**
     * The user administrative area level 1.
     */
    @Column(name = "adr_adm_area_l1")
    private String administrativeAreaLevel1;

    /**
     * The user country.
     */
    @Column(name = "adr_country")
    private String country;

    /**
     * The user postal code.
     */
    @Column(name = "adr_postal_code")
    private String postalCode;

    /**
     * The address latitude.
     */
    @Column(name = "adr_latitude")
    private Double latitude;

    /**
     * The address longitude.
     */
    @Column(name = "adr_longitude")
    private Double longitude;

    /**
     * Get the address first line.
     * @return The address first line
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Set the address first line.
     * @param pStreetName The address line to set
     */
    public void setStreetName(String pStreetName) {
        this.streetName = pStreetName;
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
     * @param pId The database id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }

    /**
     * Get the address name.
     * @return The address name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the address name.
     * @param pName The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    /**
     * The address street number.
     * @return The street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Set the address street number
     * @param pStreetNumber The street number to set
     */
    public void setStreetNumber(String pStreetNumber) {
        this.streetNumber = pStreetNumber;
    }

    /**
     * Get the city.
     * @return The city
     */
    public RefCityBO getCity() {
        return city;
    }

    /**
     * Set the city.
     * @param city - The city to set
     */
    public void setCity(RefCityBO city) {
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
    public void setLocality(String pLocality) {
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
    public void setAdministrativeAreaLevel2(String pAdministrativeAreaLevel2) {
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
    public void setAdministrativeAreaLevel1(String pAdministrativeAreaLevel1) {
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
    public void setCountry(String pCountry) {
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
    public void setPostalCode(String pPostalCode) {
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
    public void setLatitude(Double pLatitude) {
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
    public void setLongitude(Double pLongitude) {
        this.longitude = pLongitude;
    }

    public static final class QueryNamedConstant {

        /*
         * The name of GetProductByName query.
         */
        public static final String GET_ADDRESS_BY_USER = "AddressBO.getAddressByUser";
        /*
         * The GetProductByName query.
         */
        public static final String GET_ADDRESS_BY_USER_QUERY = "SELECT e FROM AddressBO e, UserBO u WHERE u.homeAddress.id = e.id AND u.id = :userId";
        /*
         * The name of GetProductByName query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY = "AddressBO.getAddressCountry";
        /*
         * The GetProductByName query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY_QUERY = "SELECT e FROM AddressBO e WHERE e.city.region.state.country.id = :country";
        /**
         * The name of getAddressCountryAndCityName query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY_AND_CITY_NAME = "AddressBO.getAddressCountryAndCityName";
        /**
         * The getAddressCountryAndCityName query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY_AND_CITY_NAME_QUERY = "SELECT e FROM AddressBO e WHERE e.city.region.state.country.id = :country AND e.city.name= :city";
        /**
         * The name of getAddressCountryAndZipCode query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY_AND_ZIP_CODE = "AddressBO.getAddressCountryAndZipCode";
        /**
         * The getAddressCountryAndZipCode query.
         */
        public static final String GET_ADDRESS_BY_COUNTRY_AND_ZIP_CODE_QUERY = "SELECT e FROM AddressBO e WHERE e.city.region.state.country.id = :country AND e.city.zipCode = :zipCode";
         /**
         * The name of getAddressCity query.
         */
        public static final String GET_ADDRESS_BY_CITY = "AddressBO.getAddressCity";
        /**
         * The getAddressCity query.
         */
        public static final String GET_ADDRESS_BY_CITY_QUERY = "SELECT e FROM AddressBO e WHERE e.city.id = :refCity";
        /**
         * The name of getAddressRegion query.
         */
        public static final String GET_ADDRESS_BY_REGION = "AddressBO.getAddressRegion";
        /**
         * The getAddressRegion query.
         */
        public static final String GET_ADDRESS_BY_REGION_QUERY = "SELECT e FROM AddressBO e WHERE e.city.region.id  = :region";
        /**
         * The name of getAddressState query.
         */
        public static final String GET_ADDRESS_BY_STATE = "AddressBO.getAddressState";
        /**
         * The getAddressState query.
         */
        public static final String GET_ADDRESS_BY_STATE_QUERY = "SELECT e FROM AddressBO e WHERE e.city.region.state.id = :state";
        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
