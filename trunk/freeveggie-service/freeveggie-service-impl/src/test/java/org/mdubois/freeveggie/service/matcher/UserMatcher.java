package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.UserBO;

/**
 *
 * @author Mickael Dubois
 */
public class UserMatcher extends BaseMatcher<UserBO> {

    private UserBO userBO;
    
    public UserMatcher(UserBO pUserBO) {
        this.userBO = pUserBO;
    }

    @Override
    public boolean matches(Object item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void describeTo(Description description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
