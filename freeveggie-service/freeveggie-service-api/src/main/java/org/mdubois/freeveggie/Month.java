package org.mdubois.freeveggie;

import org.mdubois.freeveggie.framework.msg.MessageEnum;

/**
 * The list of month
 * @author Mickael Dubois
 */
public enum Month implements MessageEnum {
    /**
     * January
     */
    JANUARY(1),
    /**
     * February
     */
    FEBRUARY(2),
    /**
     * March
     */
    MARCH(3),
    /**
     * April
     */
    APRIL(4),
    /**
     * May
     */
    MAY(5),
    /**
     * June
     */
    JUNE(6),
    /**
     * July
     */
    JULY(7),
    /**
     * August
     */
    AUGUST(8),
    /**
     * September
     */
    SEPTEMBER(9),
    /**
     * October
     */
    OCTOBER(10),
    /**
     * November
     */
    NOVEMBER(11),
    /**
     * December
     */
    DECEMBER(12),
    /**
     * Unknown value
     */
    UNKNOWN(99);

    /**
     * January
     */
    private static final int JANUARY_MONTH = 1;
    /**
     * February
     */
    private static final int FEBRUARY_MONTH = 2;
    /**
     * March
     */
    private static final int MARCH_MONTH = 3;
    /**
     * April
     */
    private static final int APRIL_MONTH = 4;
    /**
     * May
     */
    private static final int MAY_MONTH = 5;
    /**
     * June
     */
    private static final int JUNE_MONTH = 6;
    /**
     * July
     */
    private static final int JULY_MONTH = 7;
    /**
     * August
     */
    private static final int AUGUST_MONTH = 8;
    /**
     * September
     */
    private static final int SEPTEMBER_MONTH = 9;
    /**
     * October
     */
    private static final int OCTOBER_MONTH = 10;
    /**
     * November
     */
    private static final int NOVEMBER_MONTH = 11;
    /**
     * December
     */
    private static final int DECEMBER_MONTH = 12;

    /**
     * The month number.
     */
    private Integer monthNumber;

    /**
     * A constructor.
     * @param pMonthNumber - The month number
     */
    Month(final Integer pMonthNumber){
        this.monthNumber = pMonthNumber;
    }

    /**
     * Get the month number.
     * @return The month number
     */
    public Integer getMonthNumber() {
        return monthNumber;
    }

    public static Month fromInt(int monthNumber) {
        switch (monthNumber) {
            case JANUARY_MONTH:
                return JANUARY;
            case FEBRUARY_MONTH:
                return FEBRUARY;
            case MARCH_MONTH:
                return MARCH;
            case APRIL_MONTH:
                return APRIL;
            case MAY_MONTH:
                return MAY;
            case JUNE_MONTH:
                return JUNE;
            case JULY_MONTH:
                return JULY;
            case AUGUST_MONTH:
                return AUGUST;
            case SEPTEMBER_MONTH:
                return SEPTEMBER;
            case OCTOBER_MONTH:
                return OCTOBER;
            case NOVEMBER_MONTH:
                return NOVEMBER;
            case DECEMBER_MONTH:
                return DECEMBER;
            default:
                return UNKNOWN;
        }
    }

    @Override
    public Integer toInt() {
        return getMonthNumber();
    }
}
