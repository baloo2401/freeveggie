package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.GardenDAO;
import org.mdubois.freeveggie.dao.impl.GardenLikeDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class GardenLikeDAOTest extends ReadWriteDAOTest<GardenLikeBO ,Long> {
    /**
     * The id of the garden used in the test.
     */
    private static final long GARDEN_TEST_ID = 3L;

    /**
     * The id of the user used in the test
     */
    private static final long USER_TEST_ID = 2L;

    /**
     * {@link GardenLikeDAO}
     */
    private GardenLikeDAO gardenLikeDAO;

    /**
     * {@link UserPartialDAO}
     */
    private UserPartialDAO userDAO;

    /**
     * {@link GardenDAO}
     */
    private GardenDAO gardenDAO;

    /**
     * {@link PartialUserBO}
     */
    private PartialUserBO user;

    /**
     * {@link GardenBO}
     */
    private GardenBO garden;

    @Before
    public void setUp() {

        gardenLikeDAO = new GardenLikeDAO();
        gardenLikeDAO.setEntityManager(em);
        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);
        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        user = userDAO.get(USER_TEST_ID);
        garden = gardenDAO.get(GARDEN_TEST_ID);
    }


    @Test
    public void getGardenLikeByWriter(){
        List<GardenLikeBO> gardenLikeList = gardenLikeDAO.getGardenLikeByWriter(USER_TEST_ID, null);
        assertTrue("Dao should not return null", gardenLikeList != null);
        assertTrue("Dataset need more data to run test", !gardenLikeList.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(gardenLikeList);
        for (GardenLikeBO gardenLikeBO : gardenLikeList) {
            assertEquals(user, gardenLikeBO.getWriter());
        }
    }

    @Test
    public void getGardenLikeByWriterWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenLikeByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenLikeByWriterWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenLikeByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenLikeByWriterWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenLikeByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenLikeByGarden(){
        List<GardenLikeBO> gardenLikeList = gardenLikeDAO.getGardenLikeByGarden(GARDEN_TEST_ID, null);
        assertTrue("Dao should not return null", gardenLikeList != null);
        assertTrue("Dataset need more data to run test", !gardenLikeList.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(gardenLikeList);
        for (GardenLikeBO gardenLikeBO : gardenLikeList) {
            assertEquals(garden, gardenLikeBO.getGarden());
        }
    }

    @Test
    public void getGardenLikeByGardenWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenLikeByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenLikeByGardenWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenLikeByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenLikeByGardenWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenLikeByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenLikeUserAndByGarden(){
        GardenLikeBO gardenLike = gardenLikeDAO.getGardenLikeByUserAndGarden(USER_TEST_ID, GARDEN_TEST_ID);
        assertTrue("Dao should not return null", gardenLike != null);
        assertTrue("Dataset need more data to run test", gardenLike != null);
        assertEquals(garden, gardenLike.getGarden());
        assertEquals(user, gardenLike.getWriter());
    }

    @Override
    public GardenLikeBO createEntity() {
        GardenLikeBO gardenLikeBO = new GardenLikeBO();
        gardenLikeBO.setCreationDate(new Date());
        gardenLikeBO.setStatus(EvaluationStatus.SETTED);
        gardenLikeBO.setGarden(gardenDAO.get(1L));
        gardenLikeBO.setWriter(userDAO.get(2L));
        return gardenLikeBO;
    }

    @Override
    public IReadWriteDAO<GardenLikeBO, Long> getDao() {
        return gardenLikeDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return GardenLikeOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return GardenLikeCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
