package org.mdubois.freeveggie.framework.security;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * A user {@link UserBO}.
 *
 * @author Mickael Dubois
 */
public enum UserRole implements MessageEnum {

    /**
     * A regular user.
     */
    USER(1),
    /**
     * A manager.
     */
    MANAGER(2),
    /**
     * An administrator.
     */
    ADMIN(3),
    /**
     * A super administrator.
     */
    SUPERADMIN(4),
    /**
     * A system user.
     */
    SYSTEM(5),
    /**
     * Unknown value
     */
    UNKNOWN(99);
    /**
     * The database id.
     */
    private int value;
    /**
     * A regular user.
     */
    private static final int USER_ROLE = 1;
    /**
     * A manager.
     */
    private static final int MANAGER_ROLE = 2;
    /**
     * An administrator.
     */
    private static final int ADMIN_ROLE = 3;
    /**
     * A super administrator.
     */
    private static final int SUPERADMIN_ROLE = 4;
    /**
     * A system user.
     */
    private static final int SYSTEM_ROLE = 5;

    /**
     * A constructor.
     *
     * @param pValue - The database id
     */
    UserRole(int pValue) {
        this.value = pValue;
    }

    /**
     * Get the database value.
     *
     * @return The database value
     */
    public int getValue() {
        return this.value;
    }

    /**
     *
     * {@inheritDoc }
     */
    @Override
    public Integer toInt() {
        return value;
    }

    /**
     * Convert a Integer to a {@link UserRole}
     *
     * @param value
     * @return
     */
    public static UserRole fromInt(int value) {
        switch (value) {
            case USER_ROLE:
                return USER;
            case MANAGER_ROLE:
                return MANAGER;
            case ADMIN_ROLE:
                return ADMIN;
            case SUPERADMIN_ROLE:
                return SUPERADMIN;
            case SYSTEM_ROLE:
                return SYSTEM;
            default:
                return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return "UserRole{" + "value=" + value + '}';
    }
}