package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;



/**
 * The exchange type of a {@link ProductBO}.
 * @author Mickael Dubois
 */
public enum ExchangeType implements MessageEnum{

    /**
     * The {@link ProductBO} is given for anything more than another product
     */
    SHARES(1),
    /**
     * The {@link ProductBO} is given for free
     */
    GIVES(2),
    /**
     * The {@link ProductBO} is to sell
     */
    SELLS(3),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database value.
     */
    private int value;
    /**
     * The {@link ProductBO} is given for anything more than another product
     */
    private static final int SHARES_EXCHANGE_TYPE = 1;
    /**
     * The {@link ProductBO} is given for free
     */
    private static final int GIVES_EXCHANGE_TYPE = 2;
    /**
     * The {@link ProductBO} is to sell
     */
    private static final int SELLS_EXCHANGE_TYPE = 3;
    /**
     * A constructor.
     * @param pValue - The database value
     */
    ExchangeType(int pValue) {
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
     * Convert a Integer to a {@link ExchangeType}
     * @param value
     * @return
     */
    public static ExchangeType fromInt(int value) {
        switch (value) {
            case SHARES_EXCHANGE_TYPE:
                return SHARES;
            case GIVES_EXCHANGE_TYPE:
                return GIVES;
            case SELLS_EXCHANGE_TYPE:
                return SELLS;
            default:
                return UNKNOWN;
        }
    }
}