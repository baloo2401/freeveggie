package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.ProductType;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.RefProductDAO;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
public class RefProductDAOTest extends ReadOnlyDAOTest<RefProductBO, Integer> {

    private RefProductDAO refProductDAO = null;
    private static final Month JANUARY = Month.JANUARY;
    private static final Month DECEMBER = Month.DECEMBER;
    private List<Integer> reapMonths = new ArrayList<Integer>();
    private List<Integer> seedMonths = new ArrayList<Integer>();

    @Before
    public void setUp() {
        refProductDAO = new RefProductDAO();
        refProductDAO.setEntityManager(em);

        reapMonths.add(JANUARY.toInt());
        reapMonths.add(DECEMBER.toInt());
        seedMonths.add(JANUARY.toInt());
        seedMonths.add(DECEMBER.toInt());
    }

    @Test
    public void getProductsByType() {
        List<RefProductBO> refPrdList = refProductDAO.getRefProducts(ProductType.FRUIT.toInt(), null);
        Assert.assertTrue(refPrdList.size() > 5);
        AssertBusinnesObject.assertNoDuplicate(refPrdList);
        refPrdList = refProductDAO.getRefProducts(ProductType.VEGETABLE.toInt(), null);
        Assert.assertTrue(refPrdList.size() > 3);
        AssertBusinnesObject.assertNoDuplicate(refPrdList);
    }

    @Test
    public void getProductByName() {
        List<RefProductBO> refPrdList = refProductDAO.getRefProductsByName("Tomato", null);
        Assert.assertEquals(new Integer(1), new Integer(refPrdList.size()));
        refPrdList = refProductDAO.getRefProductsByName("Tom", null);
        Assert.assertEquals(new Integer(1), new Integer(refPrdList.size()));
        refPrdList = refProductDAO.getRefProductsByName("toM", null);
        Assert.assertEquals(new Integer(1), new Integer(refPrdList.size()));
        refPrdList = refProductDAO.getRefProductsByName("prout", null);
        Assert.assertTrue(refPrdList.isEmpty());
    }

    @Test
    public void getProductByReapSeason() {
        List<RefProductBO> refPrdList = refProductDAO.getRefProductsByReapSeason(reapMonths, null);
        Assert.assertTrue(refPrdList.size() > 5);
        AssertBusinnesObject.assertNoDuplicate(refPrdList);
    }

    @Test
    public void getProductBySeedSeason() {
        List<RefProductBO> refPrdList = refProductDAO.getRefProductsBySeedSeason(seedMonths, null);
        Assert.assertTrue(refPrdList.size() > 2);
        AssertBusinnesObject.assertNoDuplicate(refPrdList);
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RefProductOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return RefProductCriteriaColumn.class;
    }

    @Override
    public IReadOnlyDAO<RefProductBO, Integer> getDao() {
        return refProductDAO;
    }

    @Override
    public Integer getId() {
        return 1;
    }
}
