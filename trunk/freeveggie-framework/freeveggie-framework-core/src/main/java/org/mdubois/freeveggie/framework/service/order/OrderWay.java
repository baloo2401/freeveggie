package org.mdubois.freeveggie.framework.service.order;

/**
 * This class is use to define the order way in a reseach query.
 * @author Mickael Dubois
 */
public enum OrderWay {

    /**
     * From the higer to the smaller
     */
    ASC("ASC"),
    /**
     * From the smaller to the higher
     */
    DESC("DESC");
    private String way;

    OrderWay(final String pWay) {
        this.way = pWay;
    }

    /**
     * Get the order way.
     * @return - The order way
     */
    public String getWay() {
        return way;
    }
}
