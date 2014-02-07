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
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.GardenCommentDAO;
import org.mdubois.freeveggie.dao.impl.GardenDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
// </editor-fold>

/**
 *
 * @author Thierry Luu Huu
 */
public class GardenCommentDAOTest extends ReadWriteDAOTest<GardenCommentBO ,Long> {
    public static final long USER_TEST_ID = 1L;
    private static final long GARDEN_TEST_ID = 1L;

    private GardenCommentDAO gardenCommentDAO;

    private GardenDAO gardenDAO;

    private UserPartialDAO userDAO;

    @Before
    public void setUp() {

        gardenCommentDAO = new GardenCommentDAO();
        gardenCommentDAO.setEntityManager(em);

        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);
    }

    @Test
    public void getGardenCommentByGarden(){
        GardenBO garden = gardenDAO.get(1L);
        List<GardenCommentBO> gardenComments = gardenCommentDAO.getGardenCommentByGarden(GARDEN_TEST_ID, null);
        assertTrue("Dao should not return null", gardenComments != null);
        assertTrue("Dataset need more data to run test",gardenComments.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(gardenComments);
        for (GardenCommentBO gardenCommentBO : gardenComments) {
            assertEquals(gardenCommentBO.getGarden(), garden);
        }
    }

    @Test
    public void getGardenCommentByGardenWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenCommentByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenCommentByGardenWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenCommentByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenCommentByGardenWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenCommentByGarden", GARDEN_TEST_ID);
    }

    @Test
    public void getGardenCommentByWriter(){
        PartialUserBO user = userDAO.get(1L);
        List<GardenCommentBO> gardenComments = gardenCommentDAO.getGardenCommentByWriter(USER_TEST_ID, null);
        assertTrue("Dao should not return null", gardenComments != null);
        assertTrue("Dataset need more data to run test", gardenComments.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(gardenComments);
        for (GardenCommentBO gardenCommentBO : gardenComments) {
            assertEquals(gardenCommentBO.getWriter(), user);
        }
    }

    @Test
    public void getGardenCommentByWriterWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getGardenCommentByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenCommentByWriterWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getGardenCommentByWriter", USER_TEST_ID);
    }

    @Test
    public void getGardenCommentByUserWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getGardenCommentByWriter", USER_TEST_ID);
    }



    @Test
    public void getGardenCommentByWriterAndGarden(){
        PartialUserBO user = userDAO.get(USER_TEST_ID);
        GardenBO garden = gardenDAO.get(GARDEN_TEST_ID);
        GardenCommentBO gardenComment = gardenCommentDAO.getGardenCommentByUserAndGarden(USER_TEST_ID, GARDEN_TEST_ID);
        assertTrue("Dao should not return null", gardenComment != null);
        assertEquals(gardenComment.getWriter(), user);
        assertEquals(gardenComment.getGarden(), garden);
    }


    @Override
    public GardenCommentBO createEntity() {
        GardenCommentBO gardenCommentBO = new GardenCommentBO();
        gardenCommentBO.setComment("comment");
        gardenCommentBO.setCreationDate(new Date());
        gardenCommentBO.setGarden(gardenDAO.get(1L));
        gardenCommentBO.setNote(EvaluationNote.BAD);
        gardenCommentBO.setStatus(EvaluationStatus.SETTED);
        gardenCommentBO.setWriter(userDAO.get(1L));
        return gardenCommentBO;
    }

    @Override
    public IReadWriteDAO<GardenCommentBO, Long> getDao() {
        return gardenCommentDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return GardenCommentOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return GardenCommentCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }

}
