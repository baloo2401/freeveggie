package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.GardenDAO;
import org.mdubois.freeveggie.dao.impl.ProductDAO;
import org.mdubois.freeveggie.dao.impl.ProductRequestDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductRequestDAOTest extends ReadWriteDAOTest<ProductRequestBO, Long> {
    public static final long GARDEN_TEST_IT = 1L;
    public static final long PRODUCT_TEST_IT = 1L;
    public static final long USER_TEST_IT = 1L;

    private ProductRequestDAO productRequestDAO;

    private ProductDAO productDAO;

    private GardenDAO gardenDAO;

    private UserPartialDAO userDAO;

    private ProductBO productBO;

    private GardenBO gardenBO;

    private PartialUserBO userBO;

    @Before
    public void setUp(){
        productRequestDAO = new ProductRequestDAO();
        productRequestDAO.setEntityManager(em);

        productDAO = new ProductDAO();
        productDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        productBO = productDAO.get(PRODUCT_TEST_IT);
        gardenBO = gardenDAO.get(GARDEN_TEST_IT);
        userBO = userDAO.get(USER_TEST_IT);
    }

    @Test
    public void getProductRequestByProduct(){

        List<ProductRequestBO> productsRequest = productRequestDAO.getProductRequestByProduct(PRODUCT_TEST_IT,null);
        //Control that we have at least on product request to control
        Assert.assertTrue("Dataset need more data to run test", productsRequest.size()>0);
        AssertBusinnesObject.assertNoDuplicate(productsRequest);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertEquals(productBO, productRequestBO.getProduct());
        }
    }

    @Test
    public void getProductRequestByProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductRequestByProduct", PRODUCT_TEST_IT);
    }

    @Test
    public void getProductRequestByProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductRequestByProduct", PRODUCT_TEST_IT);
    }

    @Test
    public void getProductRequestByProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductRequestByProduct", PRODUCT_TEST_IT);
    }

    @Test
    public void getProductRequestByGarden(){
        //Control that we have at least on product request to control
        List<ProductRequestBO> productsRequest = productRequestDAO.getProductRequestByGarden(GARDEN_TEST_IT,null);
        Assert.assertTrue(productsRequest.size() >= 2);
        AssertBusinnesObject.assertNoDuplicate(productsRequest);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getProduct().getGarden().getId().equals(gardenBO.getId()));
        }
        TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        Pagination pagination = new Pagination(1,1);

        techInfo.setPagination(pagination);
        productsRequest = productRequestDAO.getProductRequestByGarden(GARDEN_TEST_IT,techInfo);
        //Control that we have at least on product request to control
        Assert.assertTrue(productsRequest.size() == 1);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getProduct().getGarden().getId().equals(gardenBO.getId()));
        }
    }

    @Test
    public void getProductRequestByGardenWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductRequestByGarden", GARDEN_TEST_IT);
    }

    @Test
    public void getProductRequestByGardenWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductRequestByGarden", GARDEN_TEST_IT);
    }

    @Test
    public void getProductRequestByGardenWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductRequestByGarden", GARDEN_TEST_IT);
    }

    @Test
    public void getProductRequestReceive(){

        List<ProductRequestBO> productsRequest = productRequestDAO.getProductRequestReceived(USER_TEST_IT, null);
        //Control that we have at least on product request to control
        Assert.assertTrue(productsRequest.size() >= 2);
        AssertBusinnesObject.assertNoDuplicate(productsRequest);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getProduct().getGarden().getOwner().getId().equals(userBO.getId()));
        }

        TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        Pagination pagination = new Pagination(1,1);

        techInfo.setPagination(pagination);
        productsRequest = productRequestDAO.getProductRequestReceived(USER_TEST_IT, techInfo);
        //Control that we have at least on product request to control
        Assert.assertTrue(productsRequest.size() == 1);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getProduct().getGarden().getOwner().getId().equals(userBO.getId()));
        }
    }

    @Test
    public void getProductRequestReceiveWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductRequestReceived", USER_TEST_IT);
    }

    @Test
    public void getProductRequestReceiveWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductRequestReceived", USER_TEST_IT);
    }

    @Test
    public void getProductRequestReceiveWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductRequestReceived", USER_TEST_IT);
    }

    @Test
    public void getProductRequestSend(){

        List<ProductRequestBO> productsRequest = productRequestDAO.getProductRequestSend(USER_TEST_IT, null);
        //Control that we have at least on product request to control
        Assert.assertTrue(productsRequest.size() >= 2);
        AssertBusinnesObject.assertNoDuplicate(productsRequest);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getRequester().getId().equals(userBO.getId()));
        }

        TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> techInfo = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();

        Pagination pagination = new Pagination(1,1);
        techInfo.setPagination(pagination);
        productsRequest = productRequestDAO.getProductRequestSend(USER_TEST_IT, techInfo);
        //Control that we have at least on product request to control
        Assert.assertTrue(productsRequest.size() == 1);
        for (ProductRequestBO productRequestBO : productsRequest) {
            Assert.assertTrue(productRequestBO.getRequester().getId().equals(userBO.getId()));
        }
    }

    @Test
    public void getProductRequestSendWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductRequestSend", USER_TEST_IT);
    }

    @Test
    public void getProductRequestSendWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductRequestSend", USER_TEST_IT);
    }

    @Test
    public void getProductRequestSendWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductRequestSend", USER_TEST_IT);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ProductRequestBO createEntity() {
        ProductRequestBO entity = new ProductRequestBO();
        entity.setAnswer("answer");
        entity.setAnswerDate(new Date());
        entity.setCreationDate(new Date());
        entity.setMessage("request");
        entity.setPickingDate(new Date());
        entity.setQuantity(1.0f);
        entity.setStatus(RequestStatus.PENDING);
        entity.setProduct(productDAO.get(1L));
        entity.setRequester(userDAO.get(3L));
        return entity;
    }

    /**
     * {@inheritDoc }
     * @return
     */
    @Override
    public IReadWriteDAO<ProductRequestBO, Long> getDao() {
        return productRequestDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return ProductRequestOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return ProductRequestCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }

}
