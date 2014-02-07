package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * A {@link UserBO} status
 * @author Mickael Dubois
 */
public enum UserStatus implements MessageEnum{

    /**
     * A user just created.
     */
    NEW(1),
    /**
     * A user who valid his email.
     */
    VALIDED(2),
    /**
     * A blacklisted user.
     */
    BLACKLISTED(3),
    /**
     * A deleted user.
     */
    DELETED(4),
    /**
     * A user archived.
     */
    ARCHIVED(5),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database id.
     */
    private int value;

    /**
     * A user just created.
     */
    private static final int NEW_USER_STATUS = 1;
    /**
     * A user who valid his email.
     */
    private static final int VALIDED_USER_STATUS = 2;
    /**
     * A blacklisted user.
     */
    private static final int BLACKLISTED_USER_STATUS = 3;
    /**
     * A deleted user.
     */
    private static final int DELETED_USER_STATUS = 4;
    /**
     * A user archived.
     */
    private static final int ARCHIVED_USER_STATUS = 5;
    /**
     * A constructor.
     * @param pValue - The database id
     */
    UserStatus(int pValue) {
        this.value = pValue;
    }

    /**
     * Get the database value.
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
        return getValue();
    }

    /**
     * Convert a Integer to a {@link UserStatus}
     * @param value
     * @return
     */
    public static UserStatus fromInt(int value) {
        switch (value) {
            case NEW_USER_STATUS:
                return NEW;
            case VALIDED_USER_STATUS:
                return VALIDED;
            case BLACKLISTED_USER_STATUS:
                return BLACKLISTED;
            case DELETED_USER_STATUS:
                return DELETED;
            case ARCHIVED_USER_STATUS:
                return ARCHIVED;
            default:
                return UNKNOWN;
        }
    }

}