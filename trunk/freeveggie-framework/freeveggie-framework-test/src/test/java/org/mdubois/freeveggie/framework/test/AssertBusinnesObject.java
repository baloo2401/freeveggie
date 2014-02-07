package org.mdubois.freeveggie.framework.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.mdubois.freeveggie.framework.bo.BusinessObject;

/**
 *
 * @author Mickael Dubois
 */
public class AssertBusinnesObject {
    private AssertBusinnesObject(){}

    public static void assertNoDuplicate(final List<? extends BusinessObject<? extends Serializable>> bussinnesObjectList){
        if(bussinnesObjectList!=null){
            List<BusinessObject<? extends Serializable>> copy = new ArrayList<BusinessObject<? extends Serializable>>();
            for (BusinessObject<? extends Serializable> businessObject  : bussinnesObjectList) {
                if(!copy.contains(businessObject)){
                    copy.add(businessObject);
                }else {
                    Assert.fail(businessObject + " occure more than one ine the list");
                }
            }
        }
    }
}
