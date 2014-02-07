package org.mdubois.freeveggie.framework.service;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
// </editor-fold>

/**
 *
 * @param <T>
 * @author Mickael Dubois
 */
public class TechnicalInformation<T extends CriteriaColumn, V extends OrderColumn> {
    /**
     *
     */
    private List<QueryCriteria<T>> criterias;
    /**
     *
     */
    private Pagination pagination;
    /**
     *
     */
    private ResultOrder<V> order;

    public List<QueryCriteria<T>> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<QueryCriteria<T>> pCriterias) {
        this.criterias = pCriterias;
    }

    public ResultOrder<V> getOrder() {
        return order;
    }

    public void setOrder(ResultOrder<V> pOrder) {
        this.order = pOrder;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pPagination) {
        this.pagination = pPagination;
    }

    public void addCriteria(QueryCriteria<T> criteria){
    	if(criterias == null){
    		criterias = new ArrayList<QueryCriteria<T>>();
    	}
    	criterias.add(criteria);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
