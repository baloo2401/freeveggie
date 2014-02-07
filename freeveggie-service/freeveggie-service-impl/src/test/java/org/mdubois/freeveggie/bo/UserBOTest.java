package org.mdubois.freeveggie.bo;

import java.util.HashMap;
import java.util.Map;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.UserStatus;
import org.mdubois.freeveggie.framework.test.BusinessObjectTest;

/**
 *
 * @author mdubois
 */
public class UserBOTest extends BusinessObjectTest<UserBO>{

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        Map<Class, Object> toReturn = new HashMap<Class, Object>();
        toReturn.put(AddressBO.class, new AddressBO());
        toReturn.put(ProfileBO.class, new ProfileBO());
        toReturn.put(SubscriptionBO.class, new SubscriptionBO());
        toReturn.put(UserStatus.class, UserStatus.NEW);
        toReturn.put(UserRole.class, UserRole.USER);
        return toReturn;
    }
}
