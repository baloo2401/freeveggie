package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The status of a {@link RelationShipBO}
 *
 * @author Mickael Dubois
 */
public enum RelationshipStatus implements MessageEnum {

    /**
     * The {@link RelationShipBO} have been send and are waiting for an answer.
     */
    PENDING(2),
    /**
     * The {@link RelationShipBO} have been validated.
     */
    VALIDED(3),
    /**
     * The {@link RelationShipBO} have been refused.
     */
    REFUSED(4),
    /**
     * Unknown value
     */
    UNKNOWN(99);
    /**
     * The database id.
     */
    private int value;

    /**
     * The {@link RelationShipBO} have been send and are waiting for an answer.
     */
    private static final int PENDING_RELATIONSHIP_STATUS = 2;
    /**
     * The {@link RelationShipBO} have been validated.
     */
    private static final int VALIDED_RELATIONSHIP_STATUS = 3;
    /**
     * The {@link RelationShipBO} have been refused.
     */
    private static final int REFUSED_RELATIONSHIP_STATUS = 4;

    /**
     * A constructor.
     *
     * @param pValue - The database id
     */
    RelationshipStatus(int pValue) {
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
     * {@inheritDoc }
     */
    @Override
    public Integer toInt() {
        return getValue();
    }

    /**
     * Convert a Integer to a {@link RelationshipStatus}
     *
     * @param value
     * @return
     */
    public static RelationshipStatus fromInt(int value) {
        switch (value) {
            case PENDING_RELATIONSHIP_STATUS:
                return PENDING;
            case VALIDED_RELATIONSHIP_STATUS:
                return VALIDED;
            case REFUSED_RELATIONSHIP_STATUS:
                return REFUSED;
            default:
                return UNKNOWN;
        }
    }
}