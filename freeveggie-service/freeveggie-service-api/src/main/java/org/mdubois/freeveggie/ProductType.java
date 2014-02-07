package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
* The type of {@link ProductBO}.
* @author Mickael Dubois
*/
public enum ProductType implements MessageEnum{

    /**
     * The {@link ProductBO} is a fruit
     */
    FRUIT(1),
    /**
     * The {@link ProductBO} is a vegetable
     */
    VEGETABLE(2),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * The database value.
     */
    private int value;

    /**
     * The {@link ProductBO} is a fruit
     */
    private static final int FRUIT_PRODUCT_TYPE = 1;
    /**
     * The {@link ProductBO} is a vegetable
     */
    private static final int VEGETABLE_PRODUCT_TYPE = 2;


    /**
     * A constructor.
     * @param pValue - The database value
     */
    ProductType(int pValue) {
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
    public static ProductType fromInt(int value) {
        switch (value) {
            case FRUIT_PRODUCT_TYPE:
                return FRUIT;
            case VEGETABLE_PRODUCT_TYPE:
                return VEGETABLE;
            default:
                return UNKNOWN;
        }
    }

}