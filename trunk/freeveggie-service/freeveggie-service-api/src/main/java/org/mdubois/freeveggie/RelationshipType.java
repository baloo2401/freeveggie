package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
* Type of {@link RelationshipBO}.
* @author Mickael Dubois
*/
public enum RelationshipType implements MessageEnum{

    /**
     * They are friend only via freeveggie.
     */
    FREEVEGGIE_FRIEND(1),
    /**
     * They are friend in real life.
     */
    FRIEND(2),
    /**
     * The are neighbor
     */
    NEIGHBOR(3),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database id.
     */
    private int value;

    /**
     * They are friend only via freeveggie.
     */
    private static final int FREEVEGGIE_FRIEND_RELATION_SHIP_TYPE = 1;
    /**
     * They are friend in real life.
     */
    private static final int FRIEND_RELATION_SHIP_TYPE = 2;
    /**
     * The are neighbor
     */
    private static final int NEIGHBOR_RELATION_SHIP_TYPE = 3;

    /**
     * A constructor.
     * @param pValue - The database id
     */
    RelationshipType(int pValue) {
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
     * Convert a Integer to a {@link RelationshipType}
     * @param value
     * @return
     */
    public static RelationshipType fromInt(int value) {
        switch (value) {
            case FREEVEGGIE_FRIEND_RELATION_SHIP_TYPE:
                return FREEVEGGIE_FRIEND;
            case FRIEND_RELATION_SHIP_TYPE:
                return FRIEND;
            case NEIGHBOR_RELATION_SHIP_TYPE:
                return NEIGHBOR;
            default:
                return UNKNOWN;
        }
    }

}