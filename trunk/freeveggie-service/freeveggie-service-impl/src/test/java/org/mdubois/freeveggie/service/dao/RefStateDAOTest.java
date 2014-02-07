package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.dao.impl.RefCountryDAO;
import org.mdubois.freeveggie.dao.impl.RefRegionDAO;
import org.mdubois.freeveggie.dao.impl.RefStateDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RefStateOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class RefStateDAOTest extends ReadOnlyDAOTest<RefStateBO ,Integer> {

    private RefStateDAO refStateDAO;

    private RefRegionDAO refRegionDAO;

    private RefCountryDAO refCountryDAO;

    private Integer france;

    @Before
    public void setUp() {

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);
        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);
        refStateDAO = new RefStateDAO();
        refStateDAO.setEntityManager(em);

        france = 76;
    }

    @Test
    public void testGetRefRegionsByCountry(){
        List<RefStateBO> states = refStateDAO.getRefStatesByCountry(france, null);
        Assert.assertTrue("Bad dataset or test.",states!=null && !states.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(states);
        for (RefStateBO state : states) {
            Assert.assertTrue(state.getCountry().getId().equals(france));
        }
    }

    @Test
    public void testGetRefStatesByName(){
        String beginWith = "ile";
        List<RefStateBO> states = refStateDAO.getRefStatesByName(beginWith, null);
        AssertBusinnesObject.assertNoDuplicate(states);
        Assert.assertTrue("Bad dataset or test.",states!=null && !states.isEmpty());
        for (RefStateBO state : states) {
            state.getName().toUpperCase().startsWith(beginWith.toUpperCase());
        }
        beginWith = "ILE";
        states = refStateDAO.getRefStatesByName(beginWith, null);
        Assert.assertTrue("Bad dataset or test.",states!=null && !states.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(states);
        for (RefStateBO state : states) {
            state.getName().toUpperCase().startsWith(beginWith.toUpperCase());
        }
        Pagination pagination = new Pagination(1,1);
        states = refStateDAO.getRefStatesByName(beginWith, pagination);
        Assert.assertTrue("Bad dataset or test.",states!=null && !states.isEmpty());
        Assert.assertTrue(states.size()==1);
        for (RefStateBO state : states) {
            state.getName().toUpperCase().startsWith(beginWith.toUpperCase());
        }
    }

    @Override
    public IReadOnlyDAO<RefStateBO, Integer> getDao() {
        return refStateDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RefStateOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public Integer getId(){
        return 1;
    }
}
