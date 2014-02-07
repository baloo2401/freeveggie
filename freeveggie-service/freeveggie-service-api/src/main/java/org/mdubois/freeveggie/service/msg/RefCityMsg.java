package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 * The representation of an address.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefCityMsg extends Message {


    /**
     * The database id.
     */
    private Integer id;
    /**
     * The city name.
     */
    private String name;
    /**
     * The city zip code.
     */
    private Long zipCode;
    /**
     * The country.
     */
    private RefRegionMsg region;

    /**
     * Get the database id.
     * @return The database id
     */
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
    public Long getZipCode() {
        return zipCode;
    }

    /**
     * Set the zip code.
     * @param zipCode The zip code to set.
     */
    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Get the region.
     * @return The region
     */
    public RefRegionMsg getRegion() {
        return region;
    }

    /**
     * Set the region.
     * @param region - The region to set
     */
    public void setRegion(RefRegionMsg region) {
        this.region = region;
    }

}
