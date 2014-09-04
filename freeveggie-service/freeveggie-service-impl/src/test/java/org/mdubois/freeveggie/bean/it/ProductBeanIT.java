package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.framework.exception.AuthenticationException;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class ProductBeanIT extends AbstractBeanIntegrationTest {

    private IProductBean productBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/ProductBeanLocal");
        productBean = (IProductBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetProductByCity() throws Exception {
        productBean.getProductByCity(null, null, null, null);

    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = AuthenticationException.class)
    public void testGetProductByCity1() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(129L);
        Integer pRefProductId = 1;
        Integer pRefCityId = 15582;
        List result = productBean.getProductByCity(pContextMsg, pRefCityId, pRefProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductByCity2() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefProductId = 1;
        Integer pRefCityId = 15582;
        List result = productBean.getProductByCity(pContextMsg, pRefCityId, pRefProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductByRegion() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefProductId = 1;
        Integer pRefRegionId = 42;
        List result = productBean.getProductByRegion(pContextMsg, pRefRegionId, pRefProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductByState() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefProductId = 1;
        Integer pRefStateId = 12;
        List result = productBean.getProductByState(pContextMsg, pRefStateId, pRefProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductByCountry() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Integer pRefProductId = 1;
        Integer pRefCountryId = 76;
        List result = productBean.getProductByCountry(pContextMsg, pRefCountryId, pRefProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductByUser() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 1L;
        List result = productBean.getProductByUser(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductById() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pProductId = 1L;
        ProductMsg result = productBean.getProductById(pContextMsg, pProductId);
        Assert.assertEquals((Long) 1L, result.getId());
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductComment() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pProductId = 1L;
        List result = productBean.getProductComment(pContextMsg, pProductId, null);
        Assert.assertEquals(2, result.size());
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetProductCommentWrite() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 1L;
        List result = productBean.getProductCommentWrite(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size() > 0);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    @Ignore
    public void testGetProductLike() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pProductId = 2L;
        List result = productBean.getProductLike(pContextMsg, pProductId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    @Ignore
    public void testGetProductLikeWrite() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(2L);
        Long pUserId = 4L;
        List result = productBean.getProductLikeWrite(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size() >= 1);
    }/*
     * Test of getUserById method, of class ReferenceBean.
     */


    @Test
    public void testBlacklist() throws Exception {
        //Set the product test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_product SET prd_sts_id = 1 WHERE prd_id = 4";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsgSystem = new ContextMsg();
        pContextMsgSystem.setUser(new UserContext());
        Long pUserId = 9L;
        Long pProductId = 4L;
        pContextMsgSystem.getUser().setId(pUserId);
        productBean.blacklist(pContextMsgSystem, pProductId);

        ContextMsg pContextMsgUser = new ContextMsg();
        pContextMsgUser.setUser(new UserContext());
        Long pUserIdUser = 1L;
        pContextMsgUser.getUser().setId(pUserIdUser);

        ProductMsg productBlacklist = productBean.getProductById(pContextMsgUser, pProductId);
        Assert.assertEquals(Status.BLACKLISTED, productBlacklist.getStatus());
        productBean.unblacklist(pContextMsgSystem, pProductId);
        productBlacklist = productBean.getProductById(pContextMsgUser, pProductId);
        Assert.assertEquals(Status.CREATED, productBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testDelete() throws Exception {
        //Set the product test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_product SET prd_sts_id = 1 WHERE prd_id = 5";
        Connection con = freeveggieDatasource.getConnection();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(5L);
        Long pProductId = 5L;
        productBean.remove(pContextMsg, pProductId);
        ProductMsg productBlacklist = productBean.getProductById(pContextMsg, pProductId);
        Assert.assertEquals(Status.DELETED, productBlacklist.getStatus());
        productBean.reactivate(pContextMsg, pProductId);
        productBlacklist = productBean.getProductById(pContextMsg, pProductId);
        Assert.assertEquals(Status.CREATED, productBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testArchive() throws Exception {
        //Set the garden test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "UPDATE t_product SET prd_sts_id = 1 WHERE prd_id = 6";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsgSystem = new ContextMsg();
        pContextMsgSystem.setUser(new UserContext());
        pContextMsgSystem.getUser().setId(9L);
        Long pProductId = 6L;
        productBean.archive(pContextMsgSystem, pProductId);
        
        ContextMsg pContextMsgUser = new ContextMsg();
        pContextMsgUser.setUser(new UserContext());
        Long pUserIdUser = 6L;
        pContextMsgUser.getUser().setId(pUserIdUser);
        
        ProductMsg productBlacklist = productBean.getProductById(pContextMsgUser, pProductId);
        Assert.assertEquals(Status.ARCHIVED, productBlacklist.getStatus());
        productBean.unarchive(pContextMsgUser, pProductId);
        productBlacklist = productBean.getProductById(pContextMsgUser, pProductId);
        Assert.assertEquals(Status.CREATED, productBlacklist.getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testComment() throws Exception {
        //Set the product test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_comment_product WHERE cpr_prd_id = 2";
        Connection con = freeveggieDatasource.getConnection();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        ProductCommentMsg productComment = new ProductCommentMsg();
        ProductMsg productMsg = new ProductMsg();
        Long productId = 2L;
        productMsg.setId(productId);
        productComment.setProduct(productMsg);
        PartialUserMsg writerMsg = new PartialUserMsg();
        writerMsg.setId(1L);
        productComment.setWriter(writerMsg);
        productComment.setComment("commentdeplusde10caracteres");
        productComment.setNote(EvaluationNote.BAD);

        Long productCommentId = productBean.comment(pContextMsg, productComment);
        List<ProductCommentMsg>productBlacklist = productBean.getProductComment(pContextMsg, productId, null);
        Assert.assertEquals(1, productBlacklist.size());

        ContextMsg pContextSystem = new ContextMsg();
        pContextSystem.setUser(new UserContext());
        pContextSystem.getUser().setId(9L);

        productBean.archiveComment(pContextSystem, productCommentId);
        productBlacklist = productBean.getProductComment(pContextMsg, productId, null);
        Assert.assertNotNull(productBlacklist.isEmpty());
        Assert.assertTrue(productBlacklist.isEmpty());

        TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn> toReturn = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        QueryCriteria<ProductCommentCriteriaColumn> CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_ARCHIVED = new QueryCriteria<ProductCommentCriteriaColumn>(
                ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.ARCHIVED);
        toReturn.addCriteria(CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_ARCHIVED);
        
        productBlacklist = productBean.getProductComment(pContextMsg, productId, toReturn);
        Assert.assertEquals(1, productBlacklist.size());
        Assert.assertEquals(EvaluationStatus.ARCHIVED, productBlacklist.get(0).getStatus());
        
        productBean.unarchiveComment(pContextMsg, productCommentId);
        productBlacklist = productBean.getProductComment(pContextMsg, productId, null);
        Assert.assertEquals(1, productBlacklist.size());
        Assert.assertEquals(EvaluationStatus.SETTED, productBlacklist.get(0).getStatus());
        
        toReturn = new TechnicalInformation<ProductCommentCriteriaColumn, ProductCommentOrderColumn>();
        QueryCriteria<ProductCommentCriteriaColumn> CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_REMOVED = new QueryCriteria<ProductCommentCriteriaColumn>(
                ProductCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.REMOVED);
        toReturn.addCriteria(CRITERIA_PRODUCT_COMMENT_STATUS_EQUAL_REMOVED);
        
        
        productBean.removeComment(pContextMsg, productCommentId);
        productBlacklist = productBean.getProductComment(pContextMsg, productId, toReturn);
        Assert.assertEquals(1, productBlacklist.size());
        Assert.assertEquals(EvaluationStatus.REMOVED, productBlacklist.get(0).getStatus());
        productBean.reactivateComment(pContextMsg, productCommentId);
        productBlacklist = productBean.getProductComment(pContextMsg, productId, null);
        Assert.assertEquals(1, productBlacklist.size());
        Assert.assertEquals(EvaluationStatus.SETTED, productBlacklist.get(0).getStatus());
    }

    /*
     * Test of getUserById method, of class ReferenceBean.
     */
    @Test
    public void testLike() throws Exception {
        //Set the product test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_like_product WHERE lpr_prd_id = 1";
        Connection con = freeveggieDatasource.getConnection();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(2L);
        ProductLikeMsg productLike = new ProductLikeMsg();
        ProductMsg productMsg = new ProductMsg();
        Long productId = 1L;
        productMsg.setId(productId);
        productLike.setProduct(productMsg);
        PartialUserMsg writerMsg = new PartialUserMsg();
        writerMsg.setId(2L);
        productLike.setWriter(writerMsg);
        Long productLikeId = productBean.like(pContextMsg, productLike);
        List<ProductLikeMsg> productLikes = productBean.getProductLike(pContextMsg, productId, null);
        Assert.assertEquals(1, productLikes.size());
        productBean.unlike(pContextMsg, productLikeId);

        productLikes = productBean.getProductLike(pContextMsg, productId, null);
        Assert.assertNotNull(productLikes.isEmpty());
        Assert.assertTrue(productLikes.isEmpty());

        TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn> toReturn = new TechnicalInformation<ProductLikeCriteriaColumn, ProductLikeOrderColumn>();
        QueryCriteria<ProductLikeCriteriaColumn> CRITERIA_PRODUCT_LIKE_STATUS_EQUAL_SETTED = new QueryCriteria<ProductLikeCriteriaColumn>(
                ProductLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL,
                EvaluationStatus.REMOVED);
        toReturn.addCriteria(CRITERIA_PRODUCT_LIKE_STATUS_EQUAL_SETTED);
        productLikes = productBean.getProductLike(pContextMsg, productId, toReturn);
        Assert.assertEquals(1, productLikes.size());
        Assert.assertEquals(EvaluationStatus.REMOVED, productLikes.get(0).getStatus());
    }
}