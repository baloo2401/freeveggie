package org.mdubois.freeveggie.service.msg;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
// </editor-fold>

/**
 * The reference fruit and vegetable.
 * @author Mickael Dubois
 */
@XmlRootElement
public class RefCountryMsg extends Message {

    /**
     * The database id.
     */
    private Integer id;
    /**
     * The name of the country (in English)
     */
    private String name;
    /**
     * The official iso code country a2.
     */
    private String codeIsoA2;
    /**
     * The official iso code country a3.
     */
    private String codeIsoA3;
    /**
     * The official iso code country number.
     */
    private Integer codeIsoNumber;

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

}
