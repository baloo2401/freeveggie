package org.mdubois.freeveggie.framework.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.List;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
// </editor-fold>

/**
 * The interface of the read only data access object.
 *
 * @author Mickael Dubois
 * @param <ENTITY> - The business object which this class have access to
 * @param <PK> - The primary key of the business object which this class have access to.
 */
public interface IReadOnlyDAO<ENTITY extends BusinessObject<PK>, PK extends Serializable> {

    /**
     * Get an entity from the database using the id.
     * @param id - The given id
     * @return The entity
     */
    ENTITY get(PK id);

    /**
     * Get all paginated entity sorted by default if asked.
     * @param withDefaultOrder - Define if you want the result order by the default order
     * @param pPagination - The pagination of the result
     * @return List of entity
     */
    List<ENTITY> getAll(final boolean withDefaultOrder, final Pagination pPagination);

    /**
     * Get all information that apply to the criteria.
     * @param pCriterias - The {@link Criteria} to respect
     * @param pResultSearchOrder - The {@link ResultSearchOrder} to respect
     * @param pPagination - The pagination of the result
     * @return List of entity
     */
    List<ENTITY> getAll(final List<QueryCriteria<? extends CriteriaColumn>> pCriterias, final ResultOrder<? extends OrderColumn> pResultSearchOrder, final Pagination pPagination);

    /**
     * Get the default order column for the entity.
     * @return The default order column.
     */
    ResultOrder<? extends OrderColumn> getDefaultOrder();
}