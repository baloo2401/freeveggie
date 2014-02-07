package org.mdubois.freeveggie.bo;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.mdubois.freeveggie.framework.bo.RefBusinessObject;

/**
 * The reference month of the year.
 * @author Mickael Dubois
 */
@Entity
@Table(name = "T_REF_MONTH")
@Cacheable(value=true)
@SuppressWarnings("serial")
public class RefMonthBO extends RefBusinessObject<Integer> {

    /*
     * Default serialVersionUID.
     */
    private static final long serialVersionUID = 13L;

    /**
     * The database id.
     */
    @Id
    @Column(name = "rmn_id")
    private Integer id;
    /**
     * The mine type of the image
     */
    @Column(name = "rmn_name")
    private String month;

    /**
     * Get the databse id.
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
     * Get the month name.
     * @return The month name
     */
    public String getMonth() {
        return month;
    }

    /**
     * Set hte month name.
     * @param pMonth - The name to set
     */
    public void setMonth(String pMonth) {
        this.month = pMonth;
    }


}
