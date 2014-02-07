package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The culture mode of a {@link ProductBO}.
 * @author Mickale Dubois
 */
public enum CultureMode implements MessageEnum{

    /**
     * The {@link ProductBO} is growing on a garden.
     */
    GARDEN(1),
    /**
     * The {@link ProductBO} is growing on a balcony.
     */
    BALCONY(2),
    /**
     * The {@link ProductBO} is growing on a terrace.
     */
    TERRACE(3),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database id for garden culture mode.
     */
    private static final int GARDEN_CULTURE_MODE_ID = 1;
    /**
     * The database id for balcony culture mode.
     */
    private static final int BALCONY_CULTURE_MODE_ID = 2;
    /**
     * The database id for terrace culture mode.
     */
    private static final int TERRACE_CULTURE_MODE_ID = 3;
    /**
     * The database value.
     */
    private int value;

    /**
     * A constructor.
     * @param pValue - The database value
     */
    CultureMode(int pValue) {
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
     * Convert a Integer to a {@link CultureMode}
     * @param value
     * @return
     */
    public static CultureMode fromInt(int value) {
        switch (value) {
            case GARDEN_CULTURE_MODE_ID:
                return GARDEN;
            case BALCONY_CULTURE_MODE_ID:
                return BALCONY;
            case TERRACE_CULTURE_MODE_ID:
                return TERRACE;
            default:
                return UNKNOWN;
        }
    }

}