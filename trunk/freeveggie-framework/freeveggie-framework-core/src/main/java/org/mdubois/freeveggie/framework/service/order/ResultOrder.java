package org.mdubois.freeveggie.framework.service.order;

import org.mdubois.freeveggie.framework.msg.ExtendedSerializable;

/**
 * This is the abstract class for request order.
 *
 * @param <T>
 * @author Mickael Dubois
 */
public class ResultOrder<T extends OrderColumn> extends ExtendedSerializable {

    /**
     * The search way.
     */
    private OrderWay way;

    /**
     * The database ordered column.
     */
    private T orderColumn;

    public ResultOrder() {
    }

    /**
     * A constructor.
     *
     * @param pOrderColumn - The order column to set
     * @param pOrderWay - The order way to set
     */
    public ResultOrder(final T pOrderColumn, final OrderWay pOrderWay) {
        this.orderColumn = pOrderColumn;
        this.way = pOrderWay;
    }

    /**
     * Get the ordering column.
     *
     * @return - The ordering column
     */
    public OrderColumn getOrderColumn() {
        return orderColumn;
    }

    /**
     * Get the order way. By default the order is DESC
     *
     * @return The order way
     */
    public OrderWay getWay() {
        return way;
    }

    /**
     * Return the HQL criteria that represent the order.
     *
     * @return An HQL order by clause
     */
    public String getInstruction() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getOrderColumn().getOrderedColumn());
        stringBuilder.append(" ");
        stringBuilder.append(this.getWay().getWay());
        stringBuilder.append(" ");
        return stringBuilder.toString();
    }

}
