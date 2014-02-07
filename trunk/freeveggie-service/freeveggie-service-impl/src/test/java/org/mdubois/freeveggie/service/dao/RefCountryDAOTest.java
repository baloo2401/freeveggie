package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.dao.impl.RefCountryDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RefCountryOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois.
 */
public class RefCountryDAOTest extends ReadOnlyDAOTest<RefCountryBO ,Integer> {

    private RefCountryDAO refCountryDAO = null;

    @Before
    public void setUp() {

        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);
    }

    /**
     * Test the getRefCountryByIsoCodeA2 of the {@link RefCountryDAO} class.
     */
    @Test
    public void testGetRefCountryByIsoCodeA2() {
        RefCountryBO countryBO = refCountryDAO.getRefCountryByIsoCodeA2("FR");
        assertEquals("FRANCE", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA2("Fr");
        assertEquals("FRANCE", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA2("fR");
        assertEquals("FRANCE", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA2("fr");
        assertEquals("FRANCE", countryBO.getName());
    }

    /**
     * Test the getRefCountryByIsoCodeA3 of the {@link RefCountryDAO} class.
     */
    @Test
    public void testGetRefCountryByIsoCodeA3() {
        RefCountryBO countryBO = refCountryDAO.getRefCountryByIsoCodeA3("GUF");
        assertEquals("FRENCH GUIANA", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA3("gUF");
        assertEquals("FRENCH GUIANA", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA3("GUf");
        assertEquals("FRENCH GUIANA", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeA3("guF");
        assertEquals("FRENCH GUIANA", countryBO.getName());
    }

    /**
     * Test the getRefCountryByIsoCodeA3 of the {@link RefCountryDAO} class.
     */
    @Test
    public void getRefCountryByIsoCodeNumber() {
        RefCountryBO countryBO = refCountryDAO.getRefCountryByIsoCodeNumber(254);
        assertEquals("FRENCH GUIANA", countryBO.getName());
        countryBO = refCountryDAO.getRefCountryByIsoCodeNumber(125678);
        assertEquals(null, countryBO);
    }

    /**
     * Test the getRefCountryByName of the {@link RefCountryDAO} class.
     */
    @Test
    public void testGetRefCountryByName() {
        List<RefCountryBO> countries = refCountryDAO.getRefCountriesByName("GERMANY",null);
        assertTrue(countries.size() == 1);
        assertEquals(countries.get(0).getCodeIsoNumber(), new Integer(276));
        countries = refCountryDAO.getRefCountriesByName("   GERMANY",null);
        assertTrue(countries.size() == 1);
        countries = refCountryDAO.getRefCountriesByName("   GERMANY     ",null);
        assertTrue(countries.size() == 1);
        countries = refCountryDAO.getRefCountriesByName("   GERMANY  ",null);
        assertTrue(countries.size() == 1);
        countries = refCountryDAO.getRefCountriesByName("GERMANy",null);
        assertTrue(countries.size() == 1);
        assertEquals(countries.get(0).getCodeIsoNumber(), new Integer(276));
        countries = refCountryDAO.getRefCountriesByName("germany",null);
        assertTrue(countries.size() == 1);
        assertEquals(countries.get(0).getCodeIsoNumber(), new Integer(276));
        countries = refCountryDAO.getRefCountriesByName("a",null);
        assertTrue(countries.size() == 16);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("  a",null);
        assertTrue(countries.size() == 16);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("a   ",null);
        assertTrue(countries.size() == 16);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("   a   ",null);
        assertTrue(countries.size() == 16);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("geany",null);
        assertTrue(countries.isEmpty());
    }

    /**
     * Test the getRefCountryByName of the {@link RefCountryDAO} class.
     */
    @Test
    public void testGetRefCountryByNameAndPagination() {
        Pagination pagination = new Pagination(10, 1);
        List<RefCountryBO> countries = refCountryDAO.getRefCountriesByName("GERMANY", pagination);
        assertTrue(countries.size() == 1);
        assertEquals(countries.get(0).getCodeIsoNumber(), new Integer(276));
        countries = refCountryDAO.getRefCountriesByName("a", pagination);
        assertTrue(countries.size() == 10);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("   a", pagination);
        assertTrue(countries.size() == 10);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("a    ", pagination);
        assertTrue(countries.size() == 10);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("   a   ", pagination);
        assertTrue(countries.size() == 10);
        AssertBusinnesObject.assertNoDuplicate(countries);
        pagination = new Pagination(10, 2);
        countries = refCountryDAO.getRefCountriesByName("a", pagination);
        assertTrue(countries.size() == 6);
        AssertBusinnesObject.assertNoDuplicate(countries);
        countries = refCountryDAO.getRefCountriesByName("geany", pagination);
        assertTrue(countries.isEmpty());
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RefCountryOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public IReadOnlyDAO<RefCountryBO, Integer> getDao() {
        return refCountryDAO;
    }

    @Override
    public Integer getId(){
        return 1;
    }
}
