package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class RefProductMsgTest extends MessageTest<RefProductMsg> {

    public RefProductMsgTest() {
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(ProductType.class, ProductType.FRUIT);
        return toReturn;
    }
}
