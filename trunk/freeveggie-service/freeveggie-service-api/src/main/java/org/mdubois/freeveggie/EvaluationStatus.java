package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The status of the evaluation.
 * @author Mickael Dubois
 */
public enum EvaluationStatus implements MessageEnum{

    /**
     * The evaluation is set
     */
    SETTED(1),
    /**
     * The evaluation have been removed
     */
    REMOVED(2),
    /**
     * The evaluation have been removed
     */
    ARCHIVED(3),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database value.
     */
    private int value;

    /**
     * The evaluation is set
     */
    private static final int SETTED_EVALUATION_STATUS = 1;
    /**
     * The evaluation have been removed
     */
    private static final int REMOVED_EVALUATION_STATUS = 2;
    /**
     * The evaluation have been removed
     */
    private static final int ARCHIVED_EVALUATION_STATUS = 3;

    /**
     * A constructor.
     * @param pValue - The database value
     */
    EvaluationStatus(int pValue) {
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
    public static EvaluationStatus fromInt(int value) {
        switch (value) {
            case SETTED_EVALUATION_STATUS:
                return SETTED;
            case REMOVED_EVALUATION_STATUS:
                return REMOVED;
            case ARCHIVED_EVALUATION_STATUS:
                return ARCHIVED;
            default:
                return UNKNOWN;
        }
    }

}
