package org.mdubois.freeveggie.framework.service.criteria;

/**
 *
 * @author Mickael Dubois
 */
public enum CriteriaOperation {
    /**
     *
     */
    MIN(">"),
    /**
     *
     */
    MAX(">"),

    /**
     *
     */
    EQUAL("="),
    /**
     *
     */
    IN("IN");

    private String operation;

    private CriteriaOperation(String pOperation) {
        this.operation = pOperation;
    }

    public String getOperation() {
        return operation;
    }
}
