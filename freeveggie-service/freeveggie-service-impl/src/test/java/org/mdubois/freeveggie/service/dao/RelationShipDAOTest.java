package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.RelationShipDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class RelationShipDAOTest extends ReadWriteDAOTest<RelationShipBO ,Long> {

    private RelationShipDAO relationShipDAO;
    private UserPartialDAO userDAO;

    private Long user;

    @Before
    public void setUp() {

        relationShipDAO = new RelationShipDAO();
        relationShipDAO.setEntityManager(em);
        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        user = 7L;
    }

    @Test
    public void getRelationShip(){
        List<RelationShipBO> relationsShips = relationShipDAO.getRelationShip(user, null);
        assertTrue("Dao should not return null", relationsShips != null);
        assertTrue("Dataset need more data to run test", !relationsShips.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(relationsShips);
        for (RelationShipBO relationShip : relationsShips) {
            assertTrue(relationShip.getRecipient().getId().equals(user) || relationShip.getSender().getId().equals(user));
        }
    }


    @Test
    public void getRelationShipWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getRelationShip", user);
    }

    @Test
    public void getRelationShipWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getRelationShip", user);
    }

    @Test
    public void getRelationShipWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getRelationShip", user);
    }

    @Override
    public RelationShipBO createEntity() {
        RelationShipBO relationShipBO = new RelationShipBO();
        relationShipBO.setRecipient(userDAO.get(5L));
        relationShipBO.setCreationDate(new Date());
        relationShipBO.setAnswer("Yes");
        relationShipBO.setRequest("please");
        relationShipBO.setSender(userDAO.get(4L));
        relationShipBO.setStatus(RelationshipStatus.PENDING);
        relationShipBO.setType(RelationshipType.FRIEND);
        return relationShipBO;
    }

    @Override
    public IReadWriteDAO<RelationShipBO, Long> getDao() {
        return relationShipDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RelationShipOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return RelationShipCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
