package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 * The reference month of the year.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefMonthMsg extends Message {

    /**
     * The database id.
     */
    private Integer id;
    /**
     * The mine type of the image
     */
    private String month;

    /**
     * Get the database id.
     * @return The id
     */
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
     * Set the month name.
     * @param pMonth - The name to set
     */
    public void setMonth(String pMonth) {
        this.month = pMonth;
    }


}
