package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class PartialUserDAOTest extends ReadOnlyDAOTest<PartialUserBO, Long> {

    private UserPartialDAO partialUserDAO;

    @Before
    public void setUp() {

        partialUserDAO = new UserPartialDAO();
        partialUserDAO.setEntityManager(em);
    }

    @Test
    public void testGetUserPartialByEmail(){
        PartialUserBO partialUserBO = partialUserDAO.get(1L);
        PartialUserBO partialUserBOTested = partialUserDAO.getUserFromEmail("mickael@edubois.org");
        Assert.assertEquals(partialUserBO, partialUserBOTested);
    }

    @Test
    public void testGetUserPartialByEmailNotExiting(){
        PartialUserBO partialUserBO = partialUserDAO.get(1L);
        PartialUserBO partialUserBOTested = partialUserDAO.getUserFromEmail("ois.org");
        Assert.assertEquals(null, partialUserBOTested);
    }

    @Override
    public IReadOnlyDAO<PartialUserBO, Long> getDao() {
        return partialUserDAO;
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
    public Long getId() {
        return 1L;
    }
}
