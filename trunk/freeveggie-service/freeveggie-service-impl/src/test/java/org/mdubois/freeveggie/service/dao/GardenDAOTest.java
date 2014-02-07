package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.GardenOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class GardenDAOTest extends ReadWriteDAOTest<GardenBO ,Long> {
    public static final int HAUT_SAINE_ID = 5;
    public static final int ILE_DE_FRANCE_ID = 1;
    private static final int BOIS_COLOMBES_ID = 2;
    private static final int FRANCE_ID = 76;
    private static final int TOMATO_ID = 1;
    private static final long USER_TEST_ID = 3L;

    private GardenDAO gardenDAO;
    private UserPartialDAO userDAO;
    private RefProductDAO refProductDAO;
    private RefCityDAO refCityDAO;
    private AddressDAO addressDAO;

    private PartialUserBO mika;
    private RefProductBO tomato;
    private RefCountryDAO refCountryDAO = null;
    private RefStateDAO refStateDAO = null;
    private RefRegionDAO refRegionDAO = null;
    private RefCountryBO france;
    private RefCityBO boisColombesCity;
    private RefRegionBO hautSaine;
    private RefStateBO ileDeFrance;

    @Before
    public void setUp() {
        addressDAO = new AddressDAO();
        addressDAO.setEntityManager(em);

        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        refCountryDAO = new RefCountryDAO();
        refCountryDAO.setEntityManager(em);

        refCityDAO = new RefCityDAO();
        refCityDAO.setEntityManager(em);

        refStateDAO = new RefStateDAO();
        refStateDAO.setEntityManager(em);

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);

        refProductDAO = new RefProductDAO();
        refProductDAO.setEntityManager(em);

        france = refCountryDAO.get(FRANCE_ID);
        boisColombesCity = refCityDAO.get(BOIS_COLOMBES_ID);
        hautSaine = refRegionDAO.get(HAUT_SAINE_ID);
        ileDeFrance = refStateDAO.get(ILE_DE_FRANCE_ID);
        tomato = refProductDAO.get(TOMATO_ID);
        mika = userDAO.get(USER_TEST_ID);
    }

    @Test
    public void testGetGardenByProduct(){
        List<GardenBO> gardens = gardenDAO.getGardenByProduct(TOMATO_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test",  gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        boolean productFind = false;
        for (GardenBO gardenBO : gardens) {
            for (ProductBO productBO : gardenBO.getProducts()) {
                if(productBO.getReferenceProduct().equals(tomato)){
                    productFind = true;
                }
            }
            Assert.assertTrue("Garden doesn't have the ref product " + tomato.getName(),productFind);
            productFind = false;
        }
    }


    @Test
    public void getGardenByProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByProduct", TOMATO_ID);
    }

    @Test
    public void getGardenByProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByProduct", TOMATO_ID);
    }

    @Test
    public void getGardenByProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByProduct", TOMATO_ID);
    }

    @Test
    public void testGetGardenByUser(){
        List<GardenBO> gardens = gardenDAO.getGardenByUser(USER_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test", gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        for (GardenBO gardenBO : gardens) {
            Assert.assertEquals(gardenBO.getOwner(), mika);
        }
    }

    @Test
    public void getGardenByUserWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByUser", USER_TEST_ID);
    }

    @Test
    public void getGardenByUserWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByUser", USER_TEST_ID);
    }

    @Test
    public void getGardenByUserWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByUser", USER_TEST_ID);
    }

    @Test
    public void testGetGardenByCityAndProduct(){

        List<GardenBO> gardens = gardenDAO.getGardenByCityAndProduct(BOIS_COLOMBES_ID, TOMATO_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test",  gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        boolean productFind = false;
        for (GardenBO gardenBO : gardens) {
            for (ProductBO productBO : gardenBO.getProducts()) {
                if(productBO.getReferenceProduct().equals(tomato)){
                    productFind = true;
                }
            }
            Assert.assertTrue("Garden doesn't have the ref product " + tomato.getName(),productFind);
            productFind = false;
        }
    }


    @Test
    public void getGardenByCityAndProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByCityAndProduct", BOIS_COLOMBES_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByCityAndProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByCityAndProduct", BOIS_COLOMBES_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByCityAndProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByCityAndProduct", BOIS_COLOMBES_ID, TOMATO_ID);
    }

    @Test
    public void testGetGardenByRegionAndProduct(){

        List<GardenBO> gardens = gardenDAO.getGardenByRegionAndProduct(HAUT_SAINE_ID, TOMATO_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test",  gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        boolean productFind = false;
        for (GardenBO gardenBO : gardens) {
            for (ProductBO productBO : gardenBO.getProducts()) {
                if(productBO.getReferenceProduct().equals(tomato)){
                    productFind = true;
                }
            }
            Assert.assertTrue("Garden doesn't have the ref product " + tomato.getName(),productFind);
            productFind = false;
        }
    }


    @Test
    public void getGardenByRegionAndProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByRegionAndProduct", HAUT_SAINE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByRegionAndProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByRegionAndProduct", HAUT_SAINE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByRegionAndProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByRegionAndProduct", HAUT_SAINE_ID, TOMATO_ID);
    }

    @Test
    public void testGetGardenByStateAndProduct(){

        List<GardenBO> gardens = gardenDAO.getGardenByStateAndProduct(ILE_DE_FRANCE_ID, TOMATO_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test",  gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        boolean productFind = false;
        for (GardenBO gardenBO : gardens) {
            for (ProductBO productBO : gardenBO.getProducts()) {
                if(productBO.getReferenceProduct().equals(tomato)){
                    productFind = true;
                }
            }
            Assert.assertTrue("Garden doesn't have the ref product " + tomato.getName(),productFind);
            productFind = false;
        }
    }


    @Test
    public void getGardenByStateAndProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByStateAndProduct", ILE_DE_FRANCE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByStateAndProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByStateAndProduct", ILE_DE_FRANCE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByStateAndProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByStateAndProduct", ILE_DE_FRANCE_ID, TOMATO_ID);
    }

    @Test
    public void testGetGardenByCountryAndProduct(){

        List<GardenBO> gardens = gardenDAO.getGardenByCountryAndProduct(FRANCE_ID, TOMATO_ID, null);
        Assert.assertTrue("Dao should not return null", gardens != null);
        Assert.assertTrue("Dataset need more data to run test",  gardens.size() >0);
        AssertBusinnesObject.assertNoDuplicate(gardens);
        boolean productFind = false;
        for (GardenBO gardenBO : gardens) {
            for (ProductBO productBO : gardenBO.getProducts()) {
                if(productBO.getReferenceProduct().equals(tomato)){
                    productFind = true;
                }
            }
            Assert.assertTrue("Garden doesn't have the ref product " + tomato.getName(),productFind);
            productFind = false;
        }
    }


    @Test
    public void getGardenByCountryAndProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenByCountryAndProduct", FRANCE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByCountryAndProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenByCountryAndProduct", FRANCE_ID, TOMATO_ID);
    }

    @Test
    public void getGardenByCountryAndProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenByCountryAndProduct", FRANCE_ID, TOMATO_ID);
    }

    @Override
    public GardenBO createEntity() {
        GardenBO gardenBO = new GardenBO();
        gardenBO.setAddress(addressDAO.get(1L));
        gardenBO.setCreationDate(new Date());
        gardenBO.setDescription("Description");
        gardenBO.setName("New Home");
        gardenBO.setOwner(userDAO.get(1L));
        gardenBO.setStatus(Status.CREATED);
        return gardenBO;
    }

    @Override
    public IReadWriteDAO<GardenBO, Long> getDao() {
        return gardenDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return GardenOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return GardenCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
