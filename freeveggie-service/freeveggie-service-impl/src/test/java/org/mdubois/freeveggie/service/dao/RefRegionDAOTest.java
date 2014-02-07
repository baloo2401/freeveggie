package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.dao.impl.RefCountryDAO;
import org.mdubois.freeveggie.dao.impl.RefRegionDAO;
import org.mdubois.freeveggie.dao.impl.RefStateDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RefRegionOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class RefRegionDAOTest extends ReadOnlyDAOTest<RefRegionBO ,Integer> {

    private RefRegionDAO refRegionDAO;
    private RefCountryDAO refCountryDAO;
    private RefStateDAO refStateDAO;

    private Integer france;
    private Integer ileDeFrance;

    private Pagination pagination;

    @Before
    public void setUp() {

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);
        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);
        refStateDAO = new RefStateDAO();
        refStateDAO.setEntityManager(em);

        france = 76;
        ileDeFrance = 1;

        pagination = new Pagination(1, 1);
    }

    @Test
    public void getRefRegionById(){
        RefRegionBO region = refRegionDAO.get(2);
        assertEquals("SEINE ET MARNE", region.getName());
    }

    @Test
    public void getRefRegionByName(){
        List<RefRegionBO> frenchRegions = refRegionDAO.getRefRegionsByName("V", null);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size() >0);
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertTrue(region.getName().startsWith("V"));
        }
        frenchRegions = refRegionDAO.getRefRegionsByName("v", null);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size() >0);
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertTrue(region.getName().startsWith("V"));
        }
        frenchRegions = refRegionDAO.getRefRegionsByName("v", pagination);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size()==pagination.getNbPerPage());
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertTrue(region.getName().startsWith("V"));
        }
    }

    @Test
    public void getRefRegionByState(){
        List<RefRegionBO> frenchRegions = refRegionDAO.getRefRegionsByState(ileDeFrance, null);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size() >0);
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertEquals(ileDeFrance, region.getState().getId());
        }
        frenchRegions = refRegionDAO.getRefRegionsByState(ileDeFrance, pagination);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size()==pagination.getNbPerPage());
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertEquals(ileDeFrance, region.getState().getId());
        }
    }

    @Test
    public void getRefRegionByCountry(){
        List<RefRegionBO> frenchRegions = refRegionDAO.getRefRegionsByCountry(france, null);
        assertTrue(frenchRegions!=null);
        assertTrue(frenchRegions.size() >0);
        AssertBusinnesObject.assertNoDuplicate(frenchRegions);
        for(RefRegionBO region : frenchRegions){
            assertEquals("FRANCE", region.getState().getCountry().getName());
        }
    }

    @Override
    public IReadOnlyDAO<RefRegionBO, Integer> getDao() {
        return refRegionDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RefRegionOrderColumn.class;
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
