package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.AddressOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class AddressDAOTest extends ReadWriteDAOTest<AddressBO, Long> {

    public static final int ILE_DE_FRANCE_TEST_IT = 1;
    public static final int PARIS_CITY_TEST_IT = 1;
    public static final int PARIS_TEST_IT = 1;
    private static final int FRANCE_TEST_IT = 76;
    private AddressDAO addressDAO = null;
    private RefCountryDAO refCountryDAO = null;
    private RefRegionDAO refRegionDAO = null;
    private RefStateDAO refStateDAO = null;
    private RefCityDAO refCityDAO = null;
    private RefCityBO paris;
    private String PARIS_STRING = "Paris";
    private String PARIS_UPPER_STRING = PARIS_STRING.toUpperCase();

    public AddressDAOTest() {
    }

    @Before
    public void setUp() {
        addressDAO = new AddressDAO();
        addressDAO.setEntityManager(em);

        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);

        refCityDAO = new RefCityDAO();
        refCityDAO.setEntityManager(em);

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);

        refStateDAO = new RefStateDAO();
        refStateDAO.setEntityManager(em);

        paris = refCityDAO.get(PARIS_CITY_TEST_IT);
    }

    /**
     * Test of {@link AddressDAO#getAddressByCountry(org.mdubois.freeveggie.bo.RefCountryBO, org.mdubois.freeveggie.framework.dao.Pagination)
     */
    @Test
    public void getAddressByCountry() {
        List<AddressBO> resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
        }

        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
        }
    }

    @Test
    public void getAddressByCountryAndCity() {
        List<AddressBO> resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, PARIS_STRING, null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals(PARIS_UPPER_STRING, address.getCity().getName());
        }

        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, "Paris", pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals(PARIS_UPPER_STRING, address.getCity().getName());
        }
    }

    @Test
    public void getAddressByCountryAndZipCode() {
        List<AddressBO> resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, paris.getName(), null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals(paris.getZipCode(), address.getCity().getZipCode());
        }

        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByCountry(FRANCE_TEST_IT, paris.getName(), pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals(paris.getZipCode(), address.getCity().getZipCode());
        }
    }

    @Test
    public void getAddressByRegion() {
        List<AddressBO> resultList = addressDAO.getAddressByRegion(PARIS_TEST_IT, null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("PARIS", address.getCity().getRegion().getName());
        }
        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByRegion(PARIS_TEST_IT, pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("PARIS", address.getCity().getRegion().getName());
        }
    }

    @Test
    public void getAddressByState() {
        List<AddressBO> resultList = addressDAO.getAddressByState(ILE_DE_FRANCE_TEST_IT, null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("ILE DE FRANCE", address.getCity().getRegion().getState().getName());
        }
        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByState(ILE_DE_FRANCE_TEST_IT, pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("ILE DE FRANCE", address.getCity().getRegion().getState().getName());
        }
    }

    @Test
    public void getAddressByCity() {
        List<AddressBO> resultList = addressDAO.getAddressByCity(PARIS_CITY_TEST_IT, null);
        assertTrue("Dao should not return null", resultList != null);
        assertTrue("Dataset need more data to run test", resultList.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(resultList);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals("PARIS", address.getCity().getName());
        }
        Pagination pagination = new Pagination(1, 1);

        resultList = addressDAO.getAddressByCity(PARIS_CITY_TEST_IT, pagination);
        assertEquals(resultList.size(), 1);
        for (AddressBO address : resultList) {
            assertEquals("FRANCE", address.getCity().getRegion().getState().getCountry().getName());
            assertEquals("PARIS", address.getCity().getName());
        }
    }

    @Override
    public AddressBO createEntity() {
        AddressBO addressBO = new AddressBO();
        addressBO.setStreetName("rue louis legrand");
        addressBO.setCity(paris);
        addressBO.setName("Test Adresse");
        addressBO.setStreetNumber("25");
        addressBO.setStreetName("pAddressBO.getStreetName()");
        addressBO.setLocality("pAddressBO.getLocality()");
        addressBO.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        addressBO.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        addressBO.setCountry("pAddressBO.getCountry()");
        addressBO.setLatitude(1.0);
        addressBO.setLongitude(1.0);
        addressBO.setPostalCode("pAddressBO.getPostalCode()");
        return addressBO;
    }

    @Override
    public IReadWriteDAO<AddressBO, Long> getDao() {
        return addressDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return AddressOrderColumn.class;
    }

    @Override
    public Long getId() {
        return 1L;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }
}
