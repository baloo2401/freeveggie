package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefRegionMsg extends Message {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 15L;

    /**
     * The database id.
     */
    private Integer id;
    /**
     * The region name.
     */
    private String name;
    /**
     * The country.
     */
    private RefStateMsg state;

    /**
     * Get the state
     * @return The state
     */
    public RefStateMsg getState() {
        return state;
    }

    /**
     * Set the state.
     * @param state - The state to set
     */
    public void setState(RefStateMsg state) {
        this.state = state;
    }

    /**
     * Get the database id.
     * @return
     */
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
}
