package org.mdubois.freeveggie.bo;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.*;
import static org.mdubois.freeveggie.bo.RefStateBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;
// </editor-fold>

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_STATE")
@Cacheable(value = true)
@SuppressWarnings("serial")
@NamedQueries(value = {
    @NamedQuery(name = GET_STATE_BY_NAME_NAME, query = GET_STATE_BY_NAME_QUERY),
    @NamedQuery(name = GET_STATE_BY_COUNTRY_NAME, query = GET_STATE_BY_COUNTRY_QUERY)
})
public class RefStateBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 16L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "rst_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_rst_id")
    @SequenceGenerator(name = "s_rst_id", sequenceName = "s_rst_id",
    initialValue = 1)
    private Integer id;
    /**
     * The state name.
     */
    @Column(name = "rst_name", nullable = false)
    private String name;
    /**
     * The country.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "rst_rcn_id")
    private RefCountryBO country;

    /**
     * The country of the state.
     * @return The country
     */
    public RefCountryBO getCountry() {
        return country;
    }

    /**
     * Set the country.
     * @param pCountry - The country to set
     */
    public void setCountry(RefCountryBO pCountry) {
        this.country = pCountry;
    }

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

    public static final class QueryNamedConstant {

        /*
         * The name of getStateByName query.
         */
        public static final String GET_STATE_BY_NAME_NAME = "RefStateBO.getStateByName";

        /*
         * The getStateByName query.
         */
        public static final String GET_STATE_BY_NAME_QUERY = "SELECT e FROM RefStateBO e WHERE UPPER(e.name) LIKE :name ORDER BY e.name ASC";

        /*
         * The name of getStateByCountry query.
         */
        public static final String GET_STATE_BY_COUNTRY_NAME = "RefStateBO.getStateByCountry";

        /*
         * The getStateByCountry query.
         */
        public static final String GET_STATE_BY_COUNTRY_QUERY = "SELECT e FROM RefStateBO e WHERE e.country.id = :country ORDER BY e.country.name ASC";

        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
