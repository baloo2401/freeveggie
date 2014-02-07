package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The status of the evaluation.
 * @author Mickael Dubois
 */
public enum Status implements MessageEnum{

    /**
     * The object is created.
     */
    CREATED(1),
    /**
     * The object have been deleted buy the user.
     */
    DELETED(2),
    /**
     * The object have been archived by the system.
     */
    ARCHIVED(3),
    /**
     * The object have been blacklisted by the system
     */
    BLACKLISTED(4),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database value.
     */
    private int value;

    /**
     * The object is created.
     */
    private static final int CREATED_STATUS = 1;
    /**
     * The object have been deleted buy the user.
     */
    private static final int DELETED_STATUS = 2;
    /**
     * The object have been archived by the system.
     */
    private static final int ARCHIVED_STATUS = 3;
    /**
     * The object have been blacklisted by the system
     */
    private static final int BLACKLISTED_STATUS = 4;

    /**
     * A constructor.
     * @param pValue - The database value
     */
    Status(int pValue) {
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
     * {@inheritDoc }
     */
    @Override
    public Integer toInt() {
        return getValue();
    }

    /**
     * Convert a Integer to a {@link EvaluationStatus}
     * @param value
     * @return
     */
    public static Status fromInt(int value) {
        switch (value) {
            case CREATED_STATUS:
                return CREATED;
            case DELETED_STATUS:
                return DELETED;
            case ARCHIVED_STATUS:
                return ARCHIVED;
            case BLACKLISTED_STATUS:
                return BLACKLISTED;
            default:
                return UNKNOWN;
        }
    }
}
