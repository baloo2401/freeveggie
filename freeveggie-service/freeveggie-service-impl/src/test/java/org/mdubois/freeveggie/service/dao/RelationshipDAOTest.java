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
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.dao.impl.RelationshipDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
// </editor-fold>

/**
 *
 * @author tiry
 */
public class RelationshipDAOTest extends ReadWriteDAOTest<RelationshipBO ,Long> {

    private RelationshipDAO relationshipDAO;
    private UserPartialDAO userDAO;

    private Long user;

    @Before
    public void setUp() {

        relationshipDAO = new RelationshipDAO();
        relationshipDAO.setEntityManager(em);
        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        user = 7L;
    }

    @Test
    public void getRelationship(){
        List<RelationshipBO> relationsShips = relationshipDAO.getRelationship(user, null);
        assertTrue("Dao should not return null", relationsShips != null);
        assertTrue("Dataset need more data to run test", !relationsShips.isEmpty());
        AssertBusinnesObject.assertNoDuplicate(relationsShips);
        for (RelationshipBO relationship : relationsShips) {
            assertTrue(relationship.getRecipient().getId().equals(user) || relationship.getSender().getId().equals(user));
        }
    }


    @Test
    public void getRelationshipWithCriteria() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testCriteria("getRelationship", user);
    }

    @Test
    public void getRelationshipWithOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrder("getRelationship", user);
    }

    @Test
    public void getRelationshipWithCriteriaAndOrder() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        testOrderAndCriteria("getRelationship", user);
    }

    @Override
    public RelationshipBO createEntity() {
        RelationshipBO relationshipBO = new RelationshipBO();
        relationshipBO.setRecipient(userDAO.get(5L));
        relationshipBO.setCreationDate(new Date());
        relationshipBO.setAnswer("Yes");
        relationshipBO.setRequest("please");
        relationshipBO.setSender(userDAO.get(4L));
        relationshipBO.setStatus(RelationshipStatus.PENDING);
        relationshipBO.setType(RelationshipType.FRIEND);
        return relationshipBO;
    }

    @Override
    public IReadWriteDAO<RelationshipBO, Long> getDao() {
        return relationshipDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return RelationshipOrderColumn.class;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return RelationshipCriteriaColumn.class;
    }

    @Override
    public Long getId(){
        return 1L;
    }
}
