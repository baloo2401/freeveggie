package org.mdubois.freeveggie.framework.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.mdubois.freeveggie.framework.service.Pagination;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class DataAccessObject {

    /**
     * {@link EntityManager}
     */
    @PersistenceContext(unitName="FREEVEGGIE_PU")
    protected EntityManager entityManager;
    
    /**
     * {@link EntityManager} for the no sql database
     */
//    @PersistenceContext(unitName="FREEVEGGIE_PU_NOSQL")
//    protected EntityManager noSQLentityManager;

    /**
     * The query implementation class that our project use.
     */
    protected static final Class<org.hibernate.Query> QUERY_IMPLEMENTATION = org.hibernate.Query.class;

    /**
     * Set the relational database entity manager.
     * @param pEntityManager - The relational database entity manager to set
     */
    public void setEntityManager(EntityManager pEntityManager) {
        this.entityManager = pEntityManager;
    }

    /**
     * Set the no SQL database entity manage.
     * @param noSQLentityManager - The no SQL database entity manager to set
     */
//    public void setNoSQLentityManager(EntityManager noSQLentityManager) {
//        this.noSQLentityManager = noSQLentityManager;
//    }

    /**
     * Add the pagination information to the query.
     *
     * @param query - The query to modify.
     * @param pPagination - The pagination to set.
     */
    protected void addPagination(Query query, final Pagination pPagination) {
        assert query != null : "Type query can not be null";
        if (pPagination != null) {
            query.setMaxResults(pPagination.getNbPerPage()).setFirstResult((pPagination.getPageNumber() - 1) * pPagination.getNbPerPage());
        }
    }
}
