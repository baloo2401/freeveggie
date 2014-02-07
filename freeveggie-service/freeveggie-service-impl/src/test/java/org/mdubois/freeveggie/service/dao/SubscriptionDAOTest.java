package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.junit.Before;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.dao.impl.SubscriptionDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class SubscriptionDAOTest extends ReadWriteDAOTest<SubscriptionBO ,Long> {

    private SubscriptionDAO subscriptionDAO;

    @Before
    public void setUp() {

        subscriptionDAO = new SubscriptionDAO();
        subscriptionDAO.setEntityManager(em);
    }



    @Override
    public SubscriptionBO createEntity() {
        return null;
    }

    @Override
    public IReadWriteDAO<SubscriptionBO, Long> getDao() {
        return subscriptionDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return null;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
