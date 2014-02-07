package org.mdubois.freeveggie.framework.msg;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mickael
 */
@XmlRootElement
public class IdMsg extends Message{
    
    private Long id;

    public IdMsg(){
        
    }
    
    public IdMsg(final Long pId){
        this.id = pId;
    }
    public Long getId() {
        return id;
    }

    public void setId(final Long pId) {
        this.id = pId;
    }
}
