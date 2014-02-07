package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.ProductOrderColumn;
// </editor-fold>


/**
 *
 * @author tiry
 */
public class ProductDAOTest extends ReadWriteDAOTest<ProductBO ,Long> {
    private static final int BOIS_COLOMBES_ID = 2;
    private static final int FRANCE_ID = 76;
    private static final int HAUT_SEINE_ID = 5;
    private static final int ILE_DE_FRANCE_ID = 1;
    private static final int REF_PRODUCT_TEST_ID = 1;
    private static final long USER_TEST_ID = 3L;

    private ProductDAO productDAO;
    private GardenDAO gardenDAO;
    private RefProductDAO refProductDAO;
    private RefCityDAO refCityDAO;
    private RefCountryDAO refCountryDAO = null;
    private RefStateDAO refStateDAO = null;
    private RefRegionDAO refRegionDAO = null;
    private UserPartialDAO userDAO;

    private RefCountryBO france;
    private RefRegionBO hautSaine;
    private RefStateBO ileDeFrance;
    private RefCityBO boisColombes;
    private RefProductBO refProduct;
    private PartialUserBO titi;

    @Before
    public void setUp() {
       productDAO = new ProductDAO();
       productDAO.setEntityManager(em);
       gardenDAO = new GardenDAO();
       gardenDAO.setEntityManager(em);
       refProductDAO = new RefProductDAO();
       refProductDAO.setEntityManager(em);
       refCityDAO = new RefCityDAO();
       refCityDAO.setEntityManager(em);
       refCountryDAO = new RefCountryDAO();
       refCountryDAO.setEntityManager(em);
       refStateDAO = new RefStateDAO();
       refStateDAO.setEntityManager(em);
       refRegionDAO = new RefRegionDAO();
       refRegionDAO.setEntityManager(em);
       userDAO = new UserPartialDAO();
       userDAO.setEntityManager(em);

       boisColombes = refCityDAO.get(BOIS_COLOMBES_ID);
       france = refCountryDAO.get(FRANCE_ID);
       hautSaine = refRegionDAO.get(HAUT_SEINE_ID);
       ileDeFrance = refStateDAO.get(ILE_DE_FRANCE_ID);
       refProduct = refProductDAO.get(REF_PRODUCT_TEST_ID);
       titi = userDAO.get(USER_TEST_ID);
    }

    @Test
    public void getProductByOwner(){
        List<ProductBO> userProducts = productDAO.getProductByUser(USER_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", userProducts != null);
        Assert.assertTrue("Dataset need more data to run test", userProducts.size()>0);
        AssertBusinnesObject.assertNoDuplicate(userProducts);
        for (ProductBO productBO : userProducts) {
            Assert.assertEquals(titi, productBO.getGarden().getOwner());
        }
    }

    @Test
    public void getProductByOwnerWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductByUser", USER_TEST_ID);
    }

    @Test
    public void getProductByOwnerWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductByUser", USER_TEST_ID);
    }

    @Test
    public void getProductByOwnerWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductByUser", USER_TEST_ID);
    }

    @Test
    public void getProductByCity(){
        List<ProductBO> cityProducts = productDAO.getProductByCityAndRefProduct(BOIS_COLOMBES_ID, REF_PRODUCT_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", cityProducts != null);
        Assert.assertTrue("Dataset need more data to run test", cityProducts.size()>0);
        AssertBusinnesObject.assertNoDuplicate(cityProducts);
        for (ProductBO productBO : cityProducts) {
            Assert.assertEquals(refProduct, productBO.getReferenceProduct());
            Assert.assertEquals(boisColombes, productBO.getGarden().getAddress().getCity());
        }
    }

    @Test
    public void getProductByCityWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductByCityAndRefProduct", BOIS_COLOMBES_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByCityWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductByCityAndRefProduct", BOIS_COLOMBES_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByCityWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductByCityAndRefProduct", BOIS_COLOMBES_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByRegion(){
        List<ProductBO> cityProducts = productDAO.getProductByRegionAndRefProduct(HAUT_SEINE_ID, REF_PRODUCT_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", cityProducts != null);
        Assert.assertTrue("Dataset need more data to run test", cityProducts.size()>0);
        AssertBusinnesObject.assertNoDuplicate(cityProducts);
        for (ProductBO productBO : cityProducts) {
            Assert.assertEquals(refProduct, productBO.getReferenceProduct());
            Assert.assertEquals(hautSaine, productBO.getGarden().getAddress().getCity().getRegion());
        }
    }

    @Test
    public void getProductByRegionWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductByRegionAndRefProduct", HAUT_SEINE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByRegionWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductByRegionAndRefProduct", HAUT_SEINE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByRegionWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductByRegionAndRefProduct", HAUT_SEINE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByState(){
        List<ProductBO> cityProducts = productDAO.getProductByStateAndRefProduct(ILE_DE_FRANCE_ID, REF_PRODUCT_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", cityProducts != null);
        Assert.assertTrue("Dataset need more data to run test", cityProducts.size()>0);
        AssertBusinnesObject.assertNoDuplicate(cityProducts);
        for (ProductBO productBO : cityProducts) {
            Assert.assertEquals(refProduct, productBO.getReferenceProduct());
            Assert.assertEquals(ileDeFrance, productBO.getGarden().getAddress().getCity().getRegion().getState());
        }
    }

    @Test
    public void getProductByStateWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductByStateAndRefProduct", ILE_DE_FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByStateWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductByStateAndRefProduct", ILE_DE_FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByStateWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductByStateAndRefProduct", ILE_DE_FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByCountry(){
        List<ProductBO> cityProducts = productDAO.getProductByCountryAndRefProduct(FRANCE_ID, REF_PRODUCT_TEST_ID, null);
        Assert.assertTrue("Dao should not return null", cityProducts != null);
        Assert.assertTrue("Dataset need more data to run test", cityProducts.size()>0);
        AssertBusinnesObject.assertNoDuplicate(cityProducts);
        for (ProductBO productBO : cityProducts) {
            Assert.assertEquals(refProduct, productBO.getReferenceProduct());
            Assert.assertEquals(france, productBO.getGarden().getAddress().getCity().getRegion().getState().getCountry());
        }
    }

    @Test
    public void getProductByCountryWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductByCountryAndRefProduct", FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByCountryWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductByCountryAndRefProduct", FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Test
    public void getProductByCountryWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductByCountryAndRefProduct", FRANCE_ID, REF_PRODUCT_TEST_ID);
    }

    @Override
    public ProductBO createEntity() {
        ProductBO product = new ProductBO();
        product.setName("titi avocadoes");
        product.setReferenceProduct(refProductDAO.get(11));
        product.setGarden(gardenDAO.get(3L));
        product.setExchangeType(ExchangeType.SHARES);
        product.setCultureMode(CultureMode.GARDEN);
        product.setCultureType(CultureType.REGULAR);
        product.setStatus(Status.CREATED);
        product.setQuantity(3.0F);
        return product;
    }


    @Override
    public IReadWriteDAO<ProductBO, Long> getDao() {
        return productDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return ProductOrderColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return ProductCriteriaColumn.class;
    }
}
