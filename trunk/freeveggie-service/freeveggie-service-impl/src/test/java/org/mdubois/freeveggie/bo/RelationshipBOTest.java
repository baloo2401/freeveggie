package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class RelationshipBOTest extends BusinessObjectTest<RelationshipBO> {

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(RelationshipType.class, RelationshipType.FREEVEGGIE_FRIEND);
        toReturn.put(PartialUserBO.class, new PartialUserBO());
        toReturn.put(RelationshipStatus.class, RelationshipStatus.PENDING);
        return toReturn;
    }
}
