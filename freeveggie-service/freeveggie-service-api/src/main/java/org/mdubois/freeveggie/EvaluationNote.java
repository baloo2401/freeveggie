package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The value of the note.
 * @author Mickael Dubois
 */
public enum EvaluationNote implements MessageEnum{

    /**
     * The note is very bad
     */
    VERYBAD(1),
    /**
     * The note is bad
     */
    BAD(2),
    /**
     * The note is normal
     */
    NORMAL(3),
    /**
     * The note is good
     */
    GOOD(4),
    /**
     * The note is very very good
     */
    VERYGOOD(5),
    /**
     * Unknown value
     */
    UNKNOWN(99);

     /**
     * The note is very bad
     */
    private static final int VERYBAD_EVALUATION_NOTE = 1;
    /**
     * The note is bad
     */
    private static final int BAD_EVALUATION_NOTE = 2;
    /**
     * The note is normal
     */
    private static final int NORMAL_EVALUATION_NOTE = 3;
    /**
     * The note is good
     */
    private static final int GOOD_EVALUATION_NOTE = 4;
    /**
     * The note is very very good
     */
    private static final int VERYGOOD_EVALUATION_NOTE = 5;


    /**
     * The database value.
     */
    private int value;

    /**
     * A constructor.
     * @param pValue - The database value
     */
    EvaluationNote(int pValue) {
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
     * Convert a Integer to a {@link EvaluationNote}
     * @param value
     * @return
     */
    public static EvaluationNote fromInt(int value) {
        switch (value) {
            case VERYBAD_EVALUATION_NOTE:
                return VERYBAD;
            case BAD_EVALUATION_NOTE:
                return BAD;
            case NORMAL_EVALUATION_NOTE:
                return NORMAL;
            case GOOD_EVALUATION_NOTE:
                return GOOD;
            case VERYGOOD_EVALUATION_NOTE:
                return VERYGOOD;
            default:
                return UNKNOWN;
        }
    }

}
