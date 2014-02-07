package org.mdubois.freeveggie.bo;

import javax.persistence.*;
import static org.mdubois.freeveggie.bo.RefRegionBO.QueryNamedConstant.*;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_REGION")
@NamedQueries(value = {
    @NamedQuery(name = GET_REGION_BY_NAME_NAME, query = GET_REGION_BY_NAME_QUERY),
    @NamedQuery(name = GET_REGION_BY_STATE_NAME, query = GET_REGION_BY_STATE_QUERY),
    @NamedQuery(name = GET_REGION_BY_COUNTRY_NAME, query = GET_REGION_BY_COUNTRY_QUERY)
})
@Cacheable(value = true)
@SuppressWarnings("serial")
public class RefRegionBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 15L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "rre_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "s_rre_id")
    @SequenceGenerator(name = "s_rre_id", sequenceName = "s_rre_id",
    initialValue = 1)
    private Integer id;
    /**
     * The region name.
     */
    @Column(name = "rre_name", nullable = false)
    private String name;
    /**
     * The country.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "rre_rst_id")
    private RefStateBO state;

    /**
     * Get the state
     * @return The state
     */
    public RefStateBO getState() {
        return state;
    }

    /**
     * Set the state.
     * @param state - The state to set
     */
    public void setState(RefStateBO state) {
        this.state = state;
    }

    /**
     * Get the database id.
     * @return
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param id - The database id to set
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
     * @param name - The region name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public static final class QueryNamedConstant {

        /*
         * The name of getRegionByName query.
         */
        public static final String GET_REGION_BY_NAME_NAME = "RefRegionBO.getRegionByName";
        /*
         * The getRegionByName query.
         */
        public static final String GET_REGION_BY_NAME_QUERY = "SELECT e FROM RefRegionBO e WHERE UPPER(e.name) LIKE :name ORDER BY e.name ASC";
        /*
         * The nams of getRegionByState query.
         */
        public static final String GET_REGION_BY_STATE_NAME = "RefRegionBO.getRegionByState";
        /*
         * The getRegionByState query.
         */
        public static final String GET_REGION_BY_STATE_QUERY = "SELECT e FROM RefRegionBO e WHERE e.state.id = :state ORDER BY e.state.name ASC";
        /*
         * The name of getRegionByCountry query.
         */
        public static final String GET_REGION_BY_COUNTRY_NAME = "RefRegionBO.getRegionByCountry";
        /*
         * The getRegionByCountry query.
         */
        public static final String GET_REGION_BY_COUNTRY_QUERY = "SELECT e FROM RefRegionBO e WHERE e.state.country.id = :country ORDER BY e.state.country.name ASC";


        /*
         * Default constructor.
         */
        private QueryNamedConstant() {
        }
    }
}
