package org.mdubois.freeveggie.service.msg;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;

/**
 *
 * @author Mickael
 */
@XmlRootElement
public class PictureMsg extends Message {
    /**
     * The database id
     */
    private Long id;
    /**
     * The object id not the database id.
     */
    @Required
    private Long objId;
    
    /**
     * The picture mime type.
     */
    @Required
    private String mimeType; 
    
    /**
     * The picture byte.
     */
    @Required
    private byte[] picture;
    
    /**
     * The picture saved date.
     */
    private Date creationDate;

    /**
     * The object id (product or garden id).
     * @return - The object id
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * Set the object id.
     * @param objId - The object id to set
     */
    public void setObjId(Long objId) {
        this.objId = objId;
    }

    
    /**
     * Get the database id.
     * @return - The database id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the database id.
     * @param id - The database id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the mime type.
     * @return - The mime type
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Set the mime type.
     * @param mimeType - The mime type to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * Get the picture
     * @return - The picture
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Set the picture.
     * @param picture - The picture to set
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * Get the creation date.
     * @return - The creation date to set
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Set the creation date.
     * @param creationDate - The creation date to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
