package org.mdubois.freeveggie.framework.service.criteria;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.mdubois.freeveggie.framework.msg.ExtendedSerializable;
import org.mdubois.freeveggie.framework.msg.MessageEnum;
// </editor-fold>

/**
 * The representation of criteria that can be apply on a define BusinessObject
 *
 * @param <T> The based criteria object
 * @author Mickael Dubois
 */
public class QueryCriteria<T extends CriteriaColumn> extends ExtendedSerializable {

    /**
     * The database criteria column.
     */
    private T criteria;
    /**
     * The criteria operation.
     */
    private CriteriaOperation operation;
    /**
     * The criteria value.
     */
    private Object value;

    public QueryCriteria() {
    }

    /**
     * Constructor.
     *
     * @param pCriteriaColumn - The criteria column to set
     * @param pOperation - The operation to set
     */
    public QueryCriteria(final T pCriteriaColumn, final CriteriaOperation pOperation) {
        this.criteria = pCriteriaColumn;
        this.operation = pOperation;
    }

    /**
     * Constructor.
     *
     * @param pCriteriaColumn - The criteria column to set
     * @param pOperation - The operation to set
     * @param pValue - The criteria value to set
     */
    public QueryCriteria(final T pCriteriaColumn, final CriteriaOperation pOperation, final Object pValue) {
        this.criteria = pCriteriaColumn;
        this.operation = pOperation;
        this.value = pValue;
    }

    /**
     * Get the criteria column.
     *
     * @return The criteria column
     */
    public CriteriaColumn getCriteria() {
        return criteria;
    }

    /**
     * Get the criteria operation.
     *
     * @return The criteria operation
     */
    public CriteriaOperation getOperation() {
        return operation;
    }

    /**
     * Get the criteria value.
     *
     * @return The criteria value
     */
    public Object getValue() {
        if (value instanceof MessageEnum) {
            MessageEnum valueEnum = (MessageEnum) value;
            return valueEnum.toInt();
        } else if (value instanceof Collection) {
            Object[] c = ((Collection) value).toArray(new Object[0]);
            //if the list is a list of enum
            if (c[0] instanceof MessageEnum) {
                List<Integer> toReturn = new ArrayList<Integer>();
                for (int i = 0; i < c.length; i++) {
                    MessageEnum valueEnum = (MessageEnum) c[i];
                    toReturn.add(valueEnum.toInt());
                }
                return toReturn;
            } else {
                return value;
            }
        } else {
            return value;
        }
    }

    /**
     * Set the criteria value.
     *
     * @param value - The criteria to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Return the HQL criteria that represent the criteria.
     *
     * @return An HQL where clause
     */
    public String getInstruction() {
        StringBuilder instruction = new StringBuilder();
        instruction.append(getCriteria().getCriteriaColumn());
        instruction.append(" ");
        instruction.append(getOperation().getOperation());
        instruction.append(" ");
        return instruction.toString();
    }

}
