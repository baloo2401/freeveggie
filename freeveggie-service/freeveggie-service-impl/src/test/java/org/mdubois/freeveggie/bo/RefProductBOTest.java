package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class RefProductBOTest extends BusinessObjectTest<RefProductBO>{

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(ProductType.class, ProductType.FRUIT);
        return toReturn;
    }
}
