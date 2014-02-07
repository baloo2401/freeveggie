package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IReferenceBean;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.msg.RefCountryMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class ReferenceBeanIT extends AbstractBeanIntegrationTest {

    private IReferenceBean referenceBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/ReferenceBeanLocal");
        referenceBean = (IReferenceBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCitiesByCountry() throws Exception {
        referenceBean.getRefCitiesByCountry(null, null);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCitiesByCountry2() throws Exception {
        //France
        Integer pRefCountryId = 76;
        List result = referenceBean.getRefCitiesByCountry(pRefCountryId, null);
        Assert.assertTrue(result.size()>36700);
        result = referenceBean.getRefCitiesByCountry(pRefCountryId, new Pagination(10, 1));
        assertEquals(10, result.size());
    }

    /**
     * Test of getRefCitiesByState method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCitiesByState() throws Exception {
        referenceBean.getRefCitiesByCountry(null, null);
    }

    /**
     * Test of getRefCitiesByState method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCitiesByState2() throws Exception {
        //Franche compté
        Integer pRefstateId = 10;
        List result = referenceBean.getRefCitiesByState(pRefstateId, null);
        assertEquals(1785, result.size());
        result = referenceBean.getRefCitiesByState(pRefstateId, new Pagination(20, 2));
        assertEquals(20, result.size());
    }

    /**
     * Test of getRefCitiesByRegion method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCitiesByRegion() throws Exception {
        referenceBean.getRefCitiesByRegion(null, null);
    }

    /**
     * Test of getRefCitiesByRegion method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCitiesByRegion2() throws Exception {
        //Doubs
        Integer pRefRegionId = 35;
        List result = referenceBean.getRefCitiesByRegion(pRefRegionId, null);
        assertEquals(594, result.size());
        result = referenceBean.getRefCitiesByRegion(pRefRegionId, new Pagination(15, 3));
        assertEquals(15, result.size());
    }


    /**
     * Test of getRefRegionsByState method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefRegionsByState() throws Exception {
        referenceBean.getRefRegionsByState(null, null);
    }

    /**
     * Test of getRefRegionsByState method, of class ReferenceBean.
     */
    @Test
    public void testGetRefRegionsByState2() throws Exception {
        //Franche comté
        Integer pRefStateId = 10;
        List result = referenceBean.getRefRegionsByState(pRefStateId, null);
        assertEquals(4, result.size());
        result = referenceBean.getRefRegionsByState(pRefStateId, new Pagination(2, 1));
        assertEquals(2, result.size());
    }

    /**
     * Test of getRefRegionsByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefRegionsByCountry() throws Exception {
        referenceBean.getRefRegionsByCountry(null, null);
    }

    /**
     * Test of getRefRegionsByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRefRegionsByCountry2() throws Exception {
        //France
        Integer pRefStateId = 76;
        List result = referenceBean.getRefRegionsByCountry(pRefStateId, null);
        assertEquals(101, result.size());
        result = referenceBean.getRefRegionsByCountry(pRefStateId, new Pagination(100, 1));
        assertEquals(100, result.size());
    }

    /**
     * Test of getRefStatesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefStatesByCountry() throws Exception {
        referenceBean.getRefStatesByCountry(null, null);
    }

    /**
     * Test of getRefStatesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRefStatesByCountry2() throws Exception {
        //France
        Integer pRefCountryId = 76;
        List result = referenceBean.getRefStatesByCountry(pRefCountryId, null);
        assertEquals(27, result.size());
        result = referenceBean.getRefStatesByCountry(pRefCountryId, new Pagination(10, 1));
        assertEquals(10, result.size());
    }

    /**
     * Test of getRefCountriesByName method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCountriesByName() throws Exception {
        referenceBean.getRefCountriesByName(null, null);
    }

    /**
     * Test of getRefCountriesByName method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCountriesByName2() throws Exception {
        //France
        String pRefCountryName = "FR";
        List result = referenceBean.getRefCountriesByName(pRefCountryName, null);
        assertEquals(4, result.size());
        result = referenceBean.getRefCountriesByName(pRefCountryName, new Pagination(2, 1));
        assertEquals(2, result.size());
    }

    /**
     * Test of getRefCitiesByName method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCitiesByName() throws Exception {
        referenceBean.getRefCitiesByName(null, null);
    }

    /**
     * Test of getRefCitiesByName method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCitiesByName2() throws Exception {
        //France
        String pRefCityName = "Alla";
        List result = referenceBean.getRefCitiesByName(pRefCityName, null);
        assertEquals(17, result.size());
        result = referenceBean.getRefCitiesByName(pRefCityName, new Pagination(2, 1));
        assertEquals(2, result.size());
    }

    /**
     * Test of getRefRegionsByName method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefRegionsByName() throws Exception {
        referenceBean.getRefRegionsByName(null, null);
    }

    /**
     * Test of getRefRegionsByName method, of class ReferenceBean.
     */
    @Test
    public void testGetRefRegionsByName2() throws Exception {
        String pRefRegionName = "A";
        List result = referenceBean.getRefRegionsByName(pRefRegionName, null);
        assertEquals(11, result.size());
        result = referenceBean.getRefRegionsByName(pRefRegionName, new Pagination(2, 1));
        assertEquals(2, result.size());
    }

    /**
     * Test of getRefStatesByName method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefStatesByName() throws Exception {
        referenceBean.getRefStatesByName(null, null);
    }

    /**
     * Test of getRefStatesByName method, of class ReferenceBean.
     */
    @Test
    public void testGetRefStatesByName2() throws Exception {
        String pRefStateName = "A";
        List result = referenceBean.getRefStatesByName(pRefStateName, null);
        assertEquals(3, result.size());
        result = referenceBean.getRefStatesByName(pRefStateName, new Pagination(2, 1));
        assertEquals(2, result.size());
    }

    /**
     * Test of getRefCountryByIsoCodeA2 method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCountryByIsoCodeA2() throws Exception {
        referenceBean.getRefCountryByIsoCodeA2(null);
    }

    /**
     * Test of getRefRegionsByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCountryByIsoCodeA22() throws Exception {
        //ANGOLA
        String pRefCountryIsoCodeA2 = "AO";
        RefCountryMsg result = referenceBean.getRefCountryByIsoCodeA2(pRefCountryIsoCodeA2);
        Assert.assertEquals("ANGOLA", result.getName());
    }

    /**
     * Test of getRefCountryByIsoCodeA2 method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRefCountryByIsoCodeA3() throws Exception {
        referenceBean.getRefCountryByIsoCodeA3(null);
    }

    /**
     * Test of getRefRegionsByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRefCountryByIsoCodeA32() throws Exception {
        //ANGUILLA
        String pRefCountryIsoCodeA2 = "AIA";
        RefCountryMsg result = referenceBean.getRefCountryByIsoCodeA3(pRefCountryIsoCodeA2);
        Assert.assertEquals("ANGUILLA", result.getName());
    }
}
