package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.ProductCommentDAO;
import org.mdubois.freeveggie.dao.impl.ProductDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
// </editor-fold>

/**
 *
 * @author Thierry Luu Huu
 */
public class ProductCommentDAOTest extends ReadWriteDAOTest<ProductCommentBO, Long> {
    private static final long PRODUCT_TEST_ID = 1L;
    private static final long USER_TEST_ID = 1L;

    private ProductCommentDAO productCommentDAO;
    private ProductDAO productDAO;
    private UserPartialDAO userDAO;
    private ProductBO productBO;
    private PartialUserBO userBO;

    @Before
    public void setUp() {

        productCommentDAO = new ProductCommentDAO();
        productCommentDAO.setEntityManager(em);

        productDAO = new ProductDAO();
        productDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        productBO = productDAO.get(PRODUCT_TEST_ID);
        userBO = userDAO.get(USER_TEST_ID);
    }

    @Test
    public void getGardenCommentByProduct() {
        List<ProductCommentBO> productCommentBOs = productCommentDAO.getProductCommentByProduct(PRODUCT_TEST_ID, null);
        assertTrue("Dao should not return null", productCommentBOs != null);
        assertTrue("Dataset need more data to run test", productCommentBOs.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(productCommentBOs);
        for (ProductCommentBO productCommentBO : productCommentBOs) {
            assertEquals(productCommentBO.getProduct(), productBO);
        }
    }

    @Test
    public void getGardenCommentByProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testCriteria("getProductCommentByProduct", PRODUCT_TEST_ID);
    }

    @Test
    public void getGardenCommentByProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testOrder("getProductCommentByProduct", PRODUCT_TEST_ID);
    }

    @Test
    public void getGardenCommentByProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testOrderAndCriteria("getProductCommentByProduct", PRODUCT_TEST_ID);
    }

    @Test
    public void getGardenCommentByUser() {
        List<ProductCommentBO> productCommentBOs = productCommentDAO.getProductCommentByWriter(USER_TEST_ID, null);
        assertTrue("Dao should not return null", productCommentBOs != null);
        assertTrue("Dataset need more data to run test", productCommentBOs.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(productCommentBOs);
        for (ProductCommentBO productCommentBO : productCommentBOs) {
            assertEquals(productCommentBO.getWriter(), userBO);
        }
    }

    @Test
    public void getGardenCommentWriteWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testCriteria("getProductCommentByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenCommentWriteWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testOrder("getProductCommentByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenCommentWriteWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        testOrderAndCriteria("getProductCommentByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenCommentByUserAndProduct() {
        ProductCommentBO productCommentBO = productCommentDAO.getProductCommentByUserAndProduct(USER_TEST_ID, PRODUCT_TEST_ID);
        assertTrue("Dao should not return null", productCommentBO != null);
        assertEquals(productCommentBO.getWriter(), userBO);
        assertEquals(productCommentBO.getProduct(), productBO);
    }

    @Override
    public ProductCommentBO createEntity() {
        ProductCommentBO productCommentBO = new ProductCommentBO();
        productCommentBO.setComment("comment");
        productCommentBO.setCreationDate(new Date());
        productCommentBO.setNote(EvaluationNote.BAD);
        productCommentBO.setProduct(productDAO.get(1L));
        productCommentBO.setStatus(EvaluationStatus.SETTED);
        productCommentBO.setWriter(userDAO.get(1L));
        return productCommentBO;
    }

    @Override
    public IReadWriteDAO<ProductCommentBO, Long> getDao() {
        return productCommentDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return ProductCommentOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return ProductCommentCriteriaColumn.class;
    }

    @Override
    public Long getId() {
        return 1L;
    }
}
