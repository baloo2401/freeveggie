package org.mdubois.freeveggie.framework.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 * @param <T> - The entity to have a data access on
 * @param <PK> - The primary key of the business object
 */
public abstract class ReadOnlyDAO<T extends BusinessObject<PK>, PK extends Serializable> extends DataAccessObject implements IReadOnlyDAO<T, PK> {

    /**
     * The persistence class.
     */
    private final Class<T> persistentClass;

    /**
     * Constructor.
     */
    public ReadOnlyDAO() {
        this.persistentClass = getType();
    }

    /**
     * Get the persistence class.
     *
     * @return - The persistence class
     */
    protected abstract Class<T> getType();

    @Override
    public T get(PK pId) {
        assert pId != null : "id can not be null";
        return entityManager.find(persistentClass, pId);
    }

    @Override
    public List<T> getAll(final List<QueryCriteria<? extends CriteriaColumn>> pCriterias, final ResultOrder<? extends OrderColumn> pResultSearchOrder, final Pagination pPagination) {
        TypedQuery<T> typeQuery = getSelectQuery(pCriterias, pResultSearchOrder);
        addPagination(typeQuery, pPagination);
        return typeQuery.getResultList();
    }

    @Override
    public List<T> getAll(final boolean withDefaultOrder, final Pagination pPagination) {
        TypedQuery<T> typedQuery = getSelectAllQuery(withDefaultOrder);
        addPagination(typedQuery, pPagination);
        return typedQuery.getResultList();
    }

    /**
     * Build the get select all query.
     *
     * @param withDefaultOrder - Does the query need to add the default order
     * @return The select all query
     */
    protected TypedQuery<T> getSelectAllQuery(final boolean withDefaultOrder) {

        if (withDefaultOrder && getDefaultOrder() != null) {
            return getSelectQuery(null, getDefaultOrder());
        } else {
            return getSelectQuery(null, null);
        }
    }

    /**
     * Build the get select all query
     *
     * @param pCriterias - Criteria to add a the request
     * @param pResultSearchOrder - Order to add a the request
     * @return The select query
     */
    protected TypedQuery<T> getSelectQuery(final List<QueryCriteria<? extends CriteriaColumn>> pCriterias, final ResultOrder<? extends OrderColumn> pResultSearchOrder) {
        StringBuilder query = new StringBuilder("SELECT e FROM ");
        query.append(this.persistentClass.getSimpleName());
        if (pCriterias != null && !pCriterias.isEmpty()) {
            query.append(" e WHERE 1=1");
            for (int i = 0; i < pCriterias.size(); i++) {
                query.append(" AND e.");
                query.append(pCriterias.get(i).getInstruction());
                query.append(" :param");
                query.append(i);
            }
        } else {
            query.append(" e ");
        }
        if (pResultSearchOrder != null) {
            query.append(" ORDER BY e.");
            query.append(pResultSearchOrder.getInstruction());
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(query.toString(), persistentClass);

        if (pCriterias != null && !pCriterias.isEmpty()) {
            for (int i = 0; i < pCriterias.size(); i++) {
                typedQuery.setParameter("param" + i, pCriterias.get(i).getValue());
            }
        }

        return typedQuery;
    }

    /**
     * Add a pagination to a query
     *
     * @param typedQuery - The query to modify
     * @param pTechnicalInformation - The pagination to set
     */
    protected void addPagination(TypedQuery<T> typedQuery, final TechnicalInformation<? extends CriteriaColumn, ? extends OrderColumn> pTechnicalInformation) {
        assert typedQuery != null : "Type query can not be null";
        if (pTechnicalInformation != null && pTechnicalInformation.getPagination() != null) {
            Pagination pagination = pTechnicalInformation.getPagination();
            typedQuery.setMaxResults(pagination.getNbPerPage()).setFirstResult((pagination.getPageNumber() - 1) * pagination.getNbPerPage());
        }
    }

    /**
     * Execute the name query with the given parameters and the given
     * pagination.
     *
     * @param pQueryName - The name query to execute
     * @param pParameters - The value of the parameters find in the named query
     * @param pPagination - The pagination to use
     * @return
     */
    protected List<T> findPaginationQuery(final String pQueryName, final Map<String, Object> pParameters, final Pagination pPagination) {

        Query query = entityManager.createNamedQuery(pQueryName);

        //FIXME : This is the only line wich might be change.
        HQLSimpleParser hQLSimpleParser = HQLSimpleParser.parseSimpleHql(query.unwrap(QUERY_IMPLEMENTATION).getQueryString());

        Query queryFromHQLSimpleParser = entityManager.createQuery(hQLSimpleParser.getHQLQuery());

        for (String parameterKey : pParameters.keySet()) {
            queryFromHQLSimpleParser.setParameter(parameterKey, pParameters.get(parameterKey));
        }

        addPagination(queryFromHQLSimpleParser, pPagination);
        return queryFromHQLSimpleParser.getResultList();
    }

    /**
     * Execute the name query with the given parameters
     *
     * @param pQueryName - The name query to execute
     * @param pParameters - The value of the parameters find in the named query
     * @return
     */
    protected T findQuery(final String pQueryName, final Map<String, Object> pParameters) {

        Query query = entityManager.createNamedQuery(pQueryName);

        //FIXME : This is the only line wich might be change.
        HQLSimpleParser hQLSimpleParser = HQLSimpleParser.parseSimpleHql(query.unwrap(QUERY_IMPLEMENTATION).getQueryString());

        Query queryFromHQLSimpleParser = entityManager.createQuery(hQLSimpleParser.getHQLQuery());

        for (String parameterKey : pParameters.keySet()) {
            queryFromHQLSimpleParser.setParameter(parameterKey, pParameters.get(parameterKey));
        }

        try {
            return (T) queryFromHQLSimpleParser.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Execute the name query with the given parameters and the technical
     * informations.
     *
     * @param pQueryName - The name query to execute
     * @param pParameters - The value of the parameters find in the named query
     * @param pTechnicalInformation - The technical informations to use
     * @return
     */
    protected List<T> findPaginationQuery(final String pQueryName, final Map<String, Object> pParameters, final TechnicalInformation<? extends CriteriaColumn, ? extends OrderColumn> pTechnicalInformation) {

        Query query = entityManager.createNamedQuery(pQueryName);

        //FIXME : This is the only line wich might be change. If you succed please delete hibernate maven dependency
        HQLSimpleParser hQLSimpleParser = HQLSimpleParser.parseSimpleHql(query.unwrap(QUERY_IMPLEMENTATION).getQueryString());

        if (pTechnicalInformation != null) {
            if (pTechnicalInformation.getCriterias() != null) {
                if (pTechnicalInformation.getCriterias() != null && !pTechnicalInformation.getCriterias().isEmpty()) {
                    for (int i = 0; i < pTechnicalInformation.getCriterias().size(); i++) {
                        hQLSimpleParser.addWhereClause(pTechnicalInformation.getCriterias().get(i).getInstruction() + " :param" + i);
                    }
                }
            }
            if (pTechnicalInformation.getOrder() != null) {
                hQLSimpleParser.addOrder(pTechnicalInformation.getOrder().getInstruction());
            }
        }
        Query queryFromHQLSimpleParser = entityManager.createQuery(hQLSimpleParser.getHQLQuery());


        if (pTechnicalInformation != null && pTechnicalInformation.getCriterias() != null && !pTechnicalInformation.getCriterias().isEmpty()) {
            for (int i = 0; i < pTechnicalInformation.getCriterias().size(); i++) {
                queryFromHQLSimpleParser.setParameter("param" + i, pTechnicalInformation.getCriterias().get(i).getValue());
            }
        }
        for (String parameterKey : pParameters.keySet()) {
            queryFromHQLSimpleParser.setParameter(parameterKey, pParameters.get(parameterKey));

        }
        if (pTechnicalInformation != null) {
            addPagination(queryFromHQLSimpleParser, pTechnicalInformation.getPagination());

        }
        return queryFromHQLSimpleParser.getResultList();
    }

    @Override
    public ResultOrder<? extends OrderColumn> getDefaultOrder() {
        return null;
    }
}
