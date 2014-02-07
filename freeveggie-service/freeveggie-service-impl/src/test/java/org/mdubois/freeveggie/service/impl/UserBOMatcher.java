package org.mdubois.freeveggie.service.impl;

import org.mdubois.freeveggie.service.matcher.BusinessObjectMatcher;
import org.mdubois.freeveggie.bo.UserBO;

/**
 *
 * @author Mickael Dubois
 */
public class UserBOMatcher extends BusinessObjectMatcher<UserBO> {

    private UserBO userBO;

    public UserBOMatcher(UserBO userBO) {
        super(userBO);
        this.userBO = userBO;
    }

    @Override
    public boolean matches(Object current) {
        if (super.matches(current)) {
            if (current instanceof UserBO) {
                UserBO obj = (UserBO) current;
                if (userBO.getRole() != null) {
                    if (userBO.getRole().equals(obj.getRole())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {

                    if (userBO.getStatus() != null) {
                        if (userBO.getStatus().equals(obj.getStatus())) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return true;
                    }
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
