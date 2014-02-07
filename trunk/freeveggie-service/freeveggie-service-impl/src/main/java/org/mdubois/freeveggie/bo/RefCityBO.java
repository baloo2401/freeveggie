package org.mdubois.freeveggie.bo;

import javax.persistence.*;
import static org.mdubois.freeveggie.bo.RefCityBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_CITY")
@NamedQueries(value = {
    @NamedQuery(name = GET_CITY_BY_NAME_NAME, query = GET_CITY_BY_NAME_QUERY),
    @NamedQuery(name = GET_CITY_BY_ZIP_CODE_NAME, query = GET_CITY_BY_ZIP_CODE_QUERY),
    @NamedQuery(name = GET_CITY_BY_REGION_NAME, query = GET_CITY_BY_REGION_QUERY),
    @NamedQuery(name = GET_CITY_BY_STATE_NAME, query = GET_CITY_BY_STATE_QUERY),
    @NamedQuery(name = GET_CITY_BY_COUNTRY_NAME, query = GET_CITY_BY_COUNTRY_QUERY)})
@Cacheable(value = true)
@SuppressWarnings("serial")
public class RefCityBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 11L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "rci_id")
    private Integer id;
    /**
     * The city name.
     */
    @Column(name = "rci_name", nullable = false)
    private String name;
    /**
     * The city zip code.
     */
    @Column(name = "rci_zip_code", nullable = false)
    private String zipCode;
    /**
     * The country.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "rci_rre_id")
    private RefRegionBO region;

    /**
     * Get the database id.
     * @return The database id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param id The id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the city name.
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the city name.
     * @param name - The city name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the city zip code.
     * @return The zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Set the zip code.
     * @param zipCode The zip code to set.
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the region.
     * @return The region
     */
    public RefRegionBO getRegion() {
        return region;
    }

    /**
     * Set the region.
     * @param region - The region to set
     */
    public void setRegion(RefRegionBO region) {
        this.region = region;
    }

    // <editor-fold defaultstate="collapsed" desc="Name of named query">
    public static final class QueryNamedConstant {

        /**
         * The name of the getCityByName query
         */
        public static final String GET_CITY_BY_NAME_NAME = "RefCityBO.getCityByName";

        /**
         * The getCityByName query.
         */
        public static final String GET_CITY_BY_NAME_QUERY = "SELECT e FROM RefCityBO e WHERE UPPER(e.name) LIKE :name ORDER BY e.name ASC";

        /**
         * The name of the getCityByName query
         */
        public static final String GET_CITY_BY_ZIP_CODE_NAME = "RefCityBO.getCityByZipCode";

        /**
         * The getCityByName query.
         */
        public static final String GET_CITY_BY_ZIP_CODE_QUERY = "SELECT e FROM RefCityBO e WHERE e.zipCode LIKE :zipCode ORDER BY e.zipCode ASC";

        /**
         * The name of getCityByRegion query
         */
        public static final String GET_CITY_BY_REGION_NAME = "RefCityBO.getCityByRegion";

        /**
         * The GetCityByRegion query
         */
        public static final String GET_CITY_BY_REGION_QUERY = "SELECT e FROM RefCityBO e WHERE e.region.id = :region ORDER BY e.region.name ASC";

        /*
         * The name of the GetCityByState query
         */
        public static final String GET_CITY_BY_STATE_NAME = "RefCityBO.getCityByState";

        /*
         * The GetCityByState query
         */
        public static final String GET_CITY_BY_STATE_QUERY = "SELECT e FROM RefCityBO e WHERE e.region.state.id = :state ORDER BY e.region.state.name ASC";

        /*
         * The name of GetCityByCountry query
         */
        public static final String GET_CITY_BY_COUNTRY_NAME = "RefCityBO.getCityByCountry";

        /*
         * The GetCityByCountry query
         */
        public static final String GET_CITY_BY_COUNTRY_QUERY = "SELECT e FROM RefCityBO e WHERE e.region.state.country.id = :country ORDER BY e.region.state.country.name ASC";

        /**
         * Constructor.
         */
        private QueryNamedConstant() {
        }
    }
    // </editor-fold>
}
