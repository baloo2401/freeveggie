package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.ProductDAO;
import org.mdubois.freeveggie.dao.impl.ProductLikeDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
public class ProductLikeDAOTest extends ReadWriteDAOTest<ProductLikeBO, Long> {
    private static final long LETTUCE_ID = 2L;
    private static final long THIERRY_ID = 3L;

    private ProductLikeDAO productLikeDAO = null;
    private UserPartialDAO userDAO = null;
    private PartialUserBO thierryUserPartialBO = null;
    private ProductDAO productDAO = null;
    private ProductBO lettuce = null;

    @Before
    public void setUp() {
        productLikeDAO = new ProductLikeDAO();
        productLikeDAO.setEntityManager(em);

        productDAO = new ProductDAO();
        productDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        lettuce = productDAO.get(LETTUCE_ID);
        thierryUserPartialBO = userDAO.get(THIERRY_ID);
    }

    @Test
    public void getProductLikeByProduct() {
        List<ProductLikeBO> productLikeList = productLikeDAO.getProductLikeByProduct(LETTUCE_ID, null);
        Assert.assertTrue(productLikeList != null);
        Assert.assertTrue("Dataset need more data to run test", productLikeList.size()>0);
        AssertBusinnesObject.assertNoDuplicate(productLikeList);
        for (ProductLikeBO productLikeBO : productLikeList) {
            Assert.assertEquals(lettuce, productLikeBO.getProduct());
        }

    }

    @Test
    public void getProductLikeByProductWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductLikeByProduct", LETTUCE_ID);
    }

    @Test
    public void getProductLikeByProductWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductLikeByProduct", LETTUCE_ID);
    }

    @Test
    public void getProductLikeByProductWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductLikeByProduct", LETTUCE_ID);
    }

    @Test
    public void getProductLikeByUserPartialBO() {
        List<ProductLikeBO> productLikeList = productLikeDAO.getProductLikeByWriter(THIERRY_ID, null);
        Assert.assertTrue(productLikeList != null);
        Assert.assertTrue("Dataset need more data to run test", productLikeList.size()>0);
        AssertBusinnesObject.assertNoDuplicate(productLikeList);
        for (ProductLikeBO productLikeBO : productLikeList) {
            Assert.assertEquals(thierryUserPartialBO, productLikeBO.getWriter());
        }
    }

    @Test
    public void getProductLikeWriteWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getProductLikeByWriter", THIERRY_ID);
    }

    @Test
    public void getProductLikeWriteWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getProductLikeByWriter", THIERRY_ID);
    }

    @Test
    public void getProductLikeWriteWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getProductLikeByWriter", THIERRY_ID);
    }

    @Test
    public void getProductLikeByUserPartialBOAndProduct() {
        ProductLikeBO productLike = productLikeDAO.getProductLikeByUserAndProduct(THIERRY_ID, LETTUCE_ID);
        Assert.assertTrue(productLike != null);
        Assert.assertEquals(thierryUserPartialBO, productLike.getWriter());
        Assert.assertEquals(lettuce, productLike.getProduct());
    }

    @Override
    public ProductLikeBO createEntity() {
        ProductLikeBO productLikeBO = new ProductLikeBO();
        productLikeBO.setCreationDate(new Date());
        productLikeBO.setProduct(productDAO.get(1L));
        productLikeBO.setStatus(EvaluationStatus.SETTED);
        productLikeBO.setWriter(userDAO.get(1L));
        return productLikeBO;
    }

    @Override
    public IReadWriteDAO<ProductLikeBO, Long> getDao() {
        return productLikeDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return ProductLikeOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return ProductLikeCriteriaColumn.class;
    }

    @Override
    public Long getId() {
        return 1L;
    }
}
