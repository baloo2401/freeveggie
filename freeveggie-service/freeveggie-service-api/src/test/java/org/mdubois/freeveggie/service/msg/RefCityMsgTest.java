package org.mdubois.freeveggie.service.msg;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class RefCityMsgTest extends MessageTest<RefCityMsg> {

    public RefCityMsgTest() {
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(RefRegionMsg.class, new RefRegionMsg());
        return toReturn;
    }
}
