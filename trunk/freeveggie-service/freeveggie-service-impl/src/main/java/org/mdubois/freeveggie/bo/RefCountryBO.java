package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import static org.mdubois.freeveggie.bo.RefCountryBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;
// </editor-fold>

/**
 * The reference fruit and vegetable.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_COUNTRY")
@NamedQueries(value = {
    @NamedQuery(name = GET_COUNTRY_BY_ISO_CODE_A2_NAME, query = GET_COUNTRY_BY_ISO_CODE_A2_QUERY),
    @NamedQuery(name = GET_COUNTRY_BY_ISO_CODE_A3_NAME, query = GET_COUNTRY_BY_ISO_CODE_A3_QUERY),
    @NamedQuery(name = GET_COUNTRY_BY_ISO_CODE_NUMBER_NAME, query = GET_COUNTRY_BY_ISO_CODE_NUMBER_QUERY),
    @NamedQuery(name = GET_COUNTRY_BY_NAME_NAME, query = GET_COUNTRY_BY_NAME_QUERY)})
@Cacheable(value = true)
@SuppressWarnings("serial")
public class RefCountryBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 12L;
    /**
     * The database id.
     */
    @Id
    @Column(name = "rcn_id")
    private Integer id;
    /**
     * The name of the country (in English)
     */
    @Column(name = "rcn_country_name", nullable = false)
    private String name;
    /**
     * The official iso code country a2.
     */
    @Column(name = "rcn_code_iso_a2", nullable = false)
    private String codeIsoA2;
    /**
     * The official iso code country a3.
     */
    @Column(name = "rcn_code_iso_a3", nullable = false)
    private String codeIsoA3;
    /**
     * The official iso code country number.
     */
    @Column(name = "rcn_code_iso_number", nullable = false)
    private Integer codeIsoNumber;

    /**
     * Get the database id.
     * @return The id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param pId The id to set.
     */
    public void setId(Integer pId) {
        this.id = pId;
    }

    /**
     * Get the iso a2 code of the country
     * @return The iso a2 code
     */
    public String getCodeIsoA2() {
        return codeIsoA2;
    }

    /**
     * Set the iso a2 code of the country
     * @param pCodeIsoA2 - The iso a2 code to set
     */
    public void setCodeIsoA2(String pCodeIsoA2) {
        this.codeIsoA2 = pCodeIsoA2;
    }

    /**
     * Get the iso a3 code of the country
     * @return The iso a3 code
     */
    public String getCodeIsoA3() {
        return codeIsoA3;
    }

    /**
     * Set the iso a3 code of the country
     * @param pCodeIsoA3 - The iso a3 code to set
     */
    public void setCodeIsoA3(String pCodeIsoA3) {
        this.codeIsoA3 = pCodeIsoA3;
    }

    /**
     * Get the iso code number of the country
     * @return The iso code number
     */
    public Integer getCodeIsoNumber() {
        return codeIsoNumber;
    }

    /**
     * Set the iso code number of the country
     * @param pCodeIsoNumber - The iso code number
     */
    public void setCodeIsoNumber(Integer pCodeIsoNumber) {
        this.codeIsoNumber = pCodeIsoNumber;
    }

    /**
     * Get the country name
     * @return The country name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the country name.
     * @param pName - The name to set
     */
    public void setName(String pName) {
        this.name = pName;
    }

    // <editor-fold defaultstate="collapsed" desc="Name of named query">
    public static final class QueryNamedConstant {

        /**
         * The name of the getCountryByIsoCodeA2 query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_A2_NAME = "RefCountryBO.getCountryByIsoCodeA2";
        /**
         * The getCountryByIsoCodeA2 query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_A2_QUERY = "SELECT e FROM RefCountryBO e WHERE e.codeIsoA2 = :codeIsoA2 ORDER BY e.name ASC";
        /**
         * The name of the getCountryByIsoCodeA3 query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_A3_NAME = "RefCountryBO.getCountryByIsoCodeA3";
        /**
         * The getCountryByIsoCodeA3 query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_A3_QUERY = "SELECT e FROM RefCountryBO e WHERE e.codeIsoA3 = :codeIsoA3 ORDER BY e.name ASC";
        /**
         * The name of the getCountryByIsoCodeNumber query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_NUMBER_NAME = "RefCountryBO.getCountryByIsoCodeNumber";
        /**
         * The getCountryByIsoCodeA3 query.
         */
        public static final String GET_COUNTRY_BY_ISO_CODE_NUMBER_QUERY = "SELECT e FROM RefCountryBO e WHERE e.codeIsoNumber = :codeIsoNumber ORDER BY e.name ASC";
        /**
         * The name of the getCountryByName query.
         */
        public static final String GET_COUNTRY_BY_NAME_NAME = "RefCountryBO.getCountryByName";
        /**
         * The getCountryByIsoCodeA3 query.
         */
        public static final String GET_COUNTRY_BY_NAME_QUERY = "SELECT e FROM RefCountryBO e WHERE UPPER(e.name) LIKE :name  ORDER BY e.name ASC";

        private QueryNamedConstant() {
        }
    }
    // </editor-fold>
}
