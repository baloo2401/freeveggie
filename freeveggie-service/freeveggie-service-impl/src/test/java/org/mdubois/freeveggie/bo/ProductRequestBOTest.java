package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class ProductRequestBOTest extends BusinessObjectTest<ProductRequestBO> {

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(ProductBO.class, new ProductBO());
        toReturn.put(PartialUserBO.class, new PartialUserBO());
        toReturn.put(RequestStatus.class, RequestStatus.ACCEPTED);
        return toReturn;
    }
}
