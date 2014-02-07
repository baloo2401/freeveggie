package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;



/**
 * The status of any request.
 * @author Mickael Dubois
 */
public enum RequestStatus implements MessageEnum{
    //TODO : Add archive (DAO, Service, EJB, Service
    /**
     * The request is pending for an answer.
     */
    PENDING(2),
    /**
     * The request have been accepted.
     */
    ACCEPTED(3),
    /**
     * The request have been refused.
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
     * A constructor.
     * @param pValue - The database id
     */
    RequestStatus(int pValue) {
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
     * Convert a Integer to a {@link RequestStatus}
     * @param value
     * @return
     */
    public static RequestStatus fromInt(int value) {
        switch (value) {
            case 2:
                return PENDING;
            case 3:
                return ACCEPTED;
            case 4:
                return REFUSED;
            default:
                return UNKNOWN;
        }
    }
}