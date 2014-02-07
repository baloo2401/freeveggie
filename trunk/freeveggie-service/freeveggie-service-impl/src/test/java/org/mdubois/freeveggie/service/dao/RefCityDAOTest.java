package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.dao.impl.RefCityDAO;
import org.mdubois.freeveggie.dao.impl.RefCountryDAO;
import org.mdubois.freeveggie.dao.impl.RefRegionDAO;
import org.mdubois.freeveggie.dao.impl.RefStateDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RefCityOrderColumn;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
public class RefCityDAOTest extends ReadOnlyDAOTest<RefCityBO, Integer> {

    private RefCityDAO refCity = null;
    private RefRegionDAO refRegion = null;
    private RefStateDAO refState = null;
    private RefCountryDAO refCountry = null;
    private Integer DOUBS = null;
    private Integer FRANCHE_COMTE = null;
    private Integer CENTRE = null;
    private Integer FRANCE = null;
    private Integer ZIMBABWE = null;
    private Integer SEINE_ET_MARNE = null;

    @Before
    public void setup() {

        refCity = new RefCityDAO();
        refCity.setEntityManager(em);

        refRegion = new RefRegionDAO();
        refRegion.setEntityManager(em);

        refState = new RefStateDAO();
        refState.setEntityManager(em);

        refCountry = new RefCountryDAO();
        refCountry.setEntityManager(em);

        DOUBS = 9;
        SEINE_ET_MARNE = 2;
        FRANCHE_COMTE = 2;
        CENTRE = 3;
        FRANCE = 76;
        ZIMBABWE = 251;

    }

    @Test
    public void testGetRefCitiesByName() {
        List<RefCityBO> refCitiesList = refCity.getRefCitiesByName("VELOTTE", null);
        Assert.assertTrue(refCitiesList.size() > 1);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertTrue(refCityBO.getName().toUpperCase().startsWith("VELOTTE"));
        }

        Pagination pagination = new Pagination(1,1);
        refCitiesList = refCity.getRefCitiesByName("VElottE", pagination);
        Assert.assertTrue(refCitiesList.size() == 1);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertTrue(refCityBO.getName().toUpperCase().startsWith("VELOTTE"));
        }
        refCitiesList = refCity.getRefCitiesByName("VELOTTe", null);
        Assert.assertTrue(refCitiesList.size() > 1);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertTrue(refCityBO.getName().toUpperCase().startsWith("VELOTTE"));
        }
        refCitiesList = refCity.getRefCitiesByName("123", null);
        Assert.assertTrue(refCitiesList.isEmpty());
        refCitiesList = refCity.getRefCitiesByName("vEl", null);
        Assert.assertTrue(refCitiesList.size() > 1);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertTrue(refCityBO.getName().toUpperCase().startsWith("VELOTTE"));
        }
    }

    @Test
    public void testGetRefCitiesByRegion() {
        List<RefCityBO> refCitiesList = refCity.getRefCitiesByRegion(DOUBS, null);
        Assert.assertTrue(refCitiesList.size() > 3);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertEquals("DOUBS", refCityBO.getRegion().getName());
        }
        refCitiesList = refCity.getRefCitiesByRegion(SEINE_ET_MARNE, null);
        Assert.assertTrue(refCitiesList.isEmpty());
    }

    @Test
    public void testGetRefCitiesByState() {
        List<RefCityBO> refCitiesList = refCity.getRefCitiesByState(FRANCHE_COMTE, null);
        Assert.assertTrue(refCitiesList.size() > 3);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertEquals("FRANCHE COMPTE", refCityBO.getRegion().getState().getName());
        }
        refCitiesList = refCity.getRefCitiesByState(CENTRE, null);
        Assert.assertTrue(refCitiesList.isEmpty());
    }

    @Test
    public void testGetRefCitiesByCountry() {
        List<RefCityBO> refCitiesList = refCity.getRefCitiesByCountry(FRANCE, null);
        Assert.assertTrue(refCitiesList.size() > 8);
        AssertBusinnesObject.assertNoDuplicate(refCitiesList);
        for (RefCityBO refCityBO : refCitiesList) {
            Assert.assertEquals("FRANCE", refCityBO.getRegion().getState().getCountry().getName());
        }
        refCitiesList = refCity.getRefCitiesByCountry(ZIMBABWE, null);
        Assert.assertTrue(refCitiesList.isEmpty());
    }

    @Override
    public IReadOnlyDAO<RefCityBO, Integer> getDao() {
        return refCity;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RefCityOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public Integer getId() {
        return 1;
    }
}
