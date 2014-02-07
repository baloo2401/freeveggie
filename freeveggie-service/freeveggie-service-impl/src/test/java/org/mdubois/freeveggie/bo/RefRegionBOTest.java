package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class RefRegionBOTest extends BusinessObjectTest<RefRegionBO>{

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(AddressBO.class, new AddressBO());
        toReturn.put(PartialUserBO.class, new PartialUserBO());
        toReturn.put(Status.class, Status.CREATED);
        return toReturn;
    }
}
