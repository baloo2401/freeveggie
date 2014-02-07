package org.mdubois.freeveggie.framework.msg;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mickael
 */
@XmlRootElement
public class BooleanMsg extends Message{
    
    private Boolean result;
    
    public BooleanMsg(){
        
    }
    
    public BooleanMsg(final Boolean pResult){
        this.result = pResult;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(final Boolean pResult) {
        this.result = pResult;
    }
    
    
}
