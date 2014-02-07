package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;


/**
 * The culture mode of a {@link ProductBO}.
 * @author Mickael Dubois
 */
public enum CultureType implements MessageEnum{

    /**
     * The {@link ProductBO} is growing using regular product
     */
    REGULAR(1),
    /**
     * The {@link ProductBO} is growing using product methods
     */
    BIO(2),
    /**
     * The {@link ProductBO} is growing using GMO
     */
    GMO(3),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database id for regular culture type.
     */
    private static final int REGULAR_CULTURE_TYPE = 1;
    /**
     * The database id for bio culture type.
     */
    private static final int BIO_CULTURE_TYPE = 2;
    /**
     * The database id for genetic modify culture culture type.
     */
    private static final int GMO_CULTURE_TYPE = 3;

    /**
     * The database value.
     */
    private int value;

    /**
     * A constructor.
     * @param pValue - The database value
     */
    CultureType(int pValue) {
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
     * Convert a Integer to a {@link CultureType}
     * @param value
     * @return
     */
    public static CultureType fromInt(int value) {
        switch (value) {
            case REGULAR_CULTURE_TYPE:
                return REGULAR;
            case BIO_CULTURE_TYPE:
                return BIO;
            case GMO_CULTURE_TYPE:
                return GMO;
            default:
                return UNKNOWN;
        }
    }

}