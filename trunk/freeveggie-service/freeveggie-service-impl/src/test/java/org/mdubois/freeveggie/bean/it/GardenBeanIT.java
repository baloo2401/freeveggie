package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AuthenticationException;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class GardenBeanIT extends AbstractBeanIntegrationTest {

    private IGardenBean gardenBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/GardenBeanLocal");
        gardenBean = (IGardenBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetGardenByCity() throws Exception {
        gardenBean.getGardenByCity(null, null, null, null);

    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = AuthenticationException.class)
    public void testGetGardenByCity1() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(129L);
        Integer pRefGardenId = 1;
        Integer pRefCityId = 15582;
        List result = gardenBean.getGardenByCity(pContextMsg, pRefCityId, pRefGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenByCity2() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefGardenId = 1;
        Integer pRefCityId = 15582;
        List result = gardenBean.getGardenByCity(pContextMsg, pRefCityId, pRefGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenByRegion() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefGardenId = 1;
        Integer pRefRegionId = 42;
        List result = gardenBean.getGardenByRegion(pContextMsg, pRefRegionId, pRefGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenByState() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefGardenId = 1;
        Integer pRefStateId = 12;
        List result = gardenBean.getGardenByState(pContextMsg, pRefStateId, pRefGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenByCountry() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefGardenId = 1;
        Integer pRefCountryId = 76;
        List result = gardenBean.getGardenByCountry(pContextMsg, pRefCountryId, pRefGardenId, null);
        Assert.assertTrue(result.size()>1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenByUser() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 1L;
        List result = gardenBean.getGardenByUser(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenById() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pGardenId = 1L;
        GardenMsg result = gardenBean.getGardenById(pContextMsg, pGardenId);
        Assert.assertEquals((Long) 1L, result.getId());
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenComment() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pGardenId = 1L;
        List result = gardenBean.getGardenComment(pContextMsg, pGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenCommentWrite() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 1L;
        List result = gardenBean.getGardenCommentWrite(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenLike() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pGardenId = 2L;
        List result = gardenBean.getGardenLike(pContextMsg, pGardenId, null);
        Assert.assertTrue(result.size()>=1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetGardenLikeWrite() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 4L;
        List result = gardenBean.getGardenLikeWrite(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size()>=1);
    }/*
     * Test of getUserById method, of class ReferenceBean.
     */


    @Test
    public void testBlacklist() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_garden SET grd_sts_id = 1 WHERE grd_id = 4";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsgSystem = new ContextMsg();
        pContextMsgSystem.setUser(new UserContext());
        Long pUserId = 9L;
        Long pGardenId = 4L;
        pContextMsgSystem.getUser().setId(pUserId);
        gardenBean.blacklist(pContextMsgSystem, pGardenId);
        
        ContextMsg pContextMsgUser = new ContextMsg();
        pContextMsgUser.setUser(new UserContext());
        Long pUserIdUser = 1L;
        pContextMsgUser.getUser().setId(pUserIdUser);
        
        GardenMsg gardenBlacklist = gardenBean.getGardenById(pContextMsgUser, pGardenId);
        Assert.assertEquals(Status.BLACKLISTED, gardenBlacklist.getStatus());
        gardenBean.unblacklist(pContextMsgSystem, pGardenId);
        gardenBlacklist = gardenBean.getGardenById(pContextMsgUser, pGardenId);
        Assert.assertEquals(Status.CREATED, gardenBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testDelete() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_garden SET grd_sts_id = 1 WHERE grd_id = 5";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        Long pUserId = 5L;
        pContextMsg.getUser().setId(pUserId);
        Long pGardenId = 5L;
        gardenBean.remove(pContextMsg, pGardenId);
        GardenMsg gardenBlacklist = gardenBean.getGardenById(pContextMsg, pGardenId);
        Assert.assertEquals(Status.DELETED, gardenBlacklist.getStatus());
        gardenBean.reactivate(pContextMsg, pGardenId);
        gardenBlacklist = gardenBean.getGardenById(pContextMsg, pGardenId);
        Assert.assertEquals(Status.CREATED, gardenBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testArchive() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_garden SET grd_sts_id = 1 WHERE grd_id = 6";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsgSystem = new ContextMsg();
        pContextMsgSystem.setUser(new UserContext());
        pContextMsgSystem.getUser().setId(9L);
        Long pGardenId = 6L;
        gardenBean.archive(pContextMsgSystem, pGardenId);
        
        ContextMsg pContextMsgUser = new ContextMsg();
        pContextMsgUser.setUser(new UserContext());
        Long pUserIdUser = 6L;
        pContextMsgUser.getUser().setId(pUserIdUser);
        
        GardenMsg gardenBlacklist = gardenBean.getGardenById(pContextMsgUser, pGardenId);
        Assert.assertEquals(Status.ARCHIVED, gardenBlacklist.getStatus());
        gardenBean.unarchive(pContextMsgUser, pGardenId);
        gardenBlacklist = gardenBean.getGardenById(pContextMsgUser, pGardenId);
        Assert.assertEquals(Status.CREATED, gardenBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testComment() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_comment_garden WHERE cgr_grd_id = 2";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        GardenCommentMsg gardenComment = new GardenCommentMsg();
        GardenMsg gardenMsg = new GardenMsg();
        Long gardenId = 2L;
        gardenMsg.setId(gardenId);
        gardenComment.setGarden(gardenMsg);
        PartialUserMsg writerMsg = new PartialUserMsg();
        writerMsg.setId(1L);
        gardenComment.setWriter(writerMsg);
        gardenComment.setComment("commentdeplusde10caracteres");
        gardenComment.setNote(EvaluationNote.BAD);

        Long gardenCommentId = gardenBean.comment(pContextMsg, gardenComment);
        List<GardenCommentMsg>gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, null);
        Assert.assertEquals(1, gardenBlacklist.size());

        ContextMsg pContextSystem = new ContextMsg();
        pContextSystem.setUser(new UserContext());
        pContextSystem.getUser().setId(9L);

        gardenBean.archiveComment(pContextSystem, gardenCommentId);
        gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, null);
        Assert.assertNull(gardenBlacklist);

        TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> toReturn = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        QueryCriteria<GardenCommentCriteriaColumn> CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_ARCHIVED = new QueryCriteria<GardenCommentCriteriaColumn>(
                GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.ARCHIVED);
        toReturn.addCriteria(CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_ARCHIVED);
        
        gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, toReturn);
        Assert.assertEquals(1, gardenBlacklist.size());
        Assert.assertEquals(EvaluationStatus.ARCHIVED, gardenBlacklist.get(0).getStatus());
        
        gardenBean.unarchiveComment(pContextMsg, gardenCommentId);
        gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, null);
        Assert.assertEquals(1, gardenBlacklist.size());
        Assert.assertEquals(EvaluationStatus.SETTED, gardenBlacklist.get(0).getStatus());
        
        toReturn = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        QueryCriteria<GardenCommentCriteriaColumn> CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_REMOVED = new QueryCriteria<GardenCommentCriteriaColumn>(
                GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.REMOVED);
        toReturn.addCriteria(CRITERIA_GARDEN_COMMENT_STATUS_EQUAL_REMOVED);
        
        
        gardenBean.removeComment(pContextMsg, gardenCommentId);
        gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, toReturn);
        Assert.assertEquals(1, gardenBlacklist.size());
        Assert.assertEquals(EvaluationStatus.REMOVED, gardenBlacklist.get(0).getStatus());
        gardenBean.reactivateComment(pContextMsg, gardenCommentId);
        gardenBlacklist = gardenBean.getGardenComment(pContextMsg, gardenId, null);
        Assert.assertEquals(1, gardenBlacklist.size());
        Assert.assertEquals(EvaluationStatus.SETTED, gardenBlacklist.get(0).getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testLike() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_like_garden WHERE lgr_grd_id = 1";
        Connection con = freeveggieDatasource.getConnection();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(2L);
        GardenLikeMsg gardenLike = new GardenLikeMsg();
        GardenMsg gardenMsg = new GardenMsg();
        Long gardenId = 1L;
        gardenMsg.setId(gardenId);
        gardenLike.setGarden(gardenMsg);
        PartialUserMsg writerMsg = new PartialUserMsg();
        writerMsg.setId(2L);
        gardenLike.setWriter(writerMsg);
        Long gardenLikeId = gardenBean.like(pContextMsg, gardenLike);
        List<GardenLikeMsg> gardenBlacklist = gardenBean.getGardenLike(pContextMsg, gardenId, null);
        Assert.assertEquals(1, gardenBlacklist.size());
        gardenBean.unlike(pContextMsg, gardenLikeId);

        gardenBlacklist = gardenBean.getGardenLike(pContextMsg, gardenId, null);
        Assert.assertNull(gardenBlacklist);

        TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> toReturn = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        QueryCriteria<GardenLikeCriteriaColumn> CRITERIA_GARDEN_LIKE_STATUS_EQUAL_SETTED = new QueryCriteria<GardenLikeCriteriaColumn>(
                GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.REMOVED);
        toReturn.addCriteria(CRITERIA_GARDEN_LIKE_STATUS_EQUAL_SETTED);
        gardenBlacklist = gardenBean.getGardenLike(pContextMsg, gardenId, toReturn);
        Assert.assertEquals(1, gardenBlacklist.size());
        Assert.assertEquals(EvaluationStatus.REMOVED, gardenBlacklist.get(0).getStatus());
    }
}