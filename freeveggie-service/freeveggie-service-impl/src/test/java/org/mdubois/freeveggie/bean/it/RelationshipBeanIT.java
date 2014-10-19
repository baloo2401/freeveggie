package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bean.IRelationshipBean;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author mdubois
 */
public class RelationshipBeanIT extends AbstractBeanIntegrationTest {

    private IRelationshipBean relationshipBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/RelationshipBeanLocal");
        relationshipBean = (IRelationshipBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRelationship() throws Exception {
        ContextMsg pContextMsg = null;
        Long pUserId = null;
        List expResult = null;
        List result = relationshipBean.getRelationship(pContextMsg, pUserId, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRelationship2() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 2L;
        List result = relationshipBean.getRelationship(pContextMsg, pUserId, null);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    @Ignore
    public void testCreateRefuseValidate() throws Exception {
        //Delete relationship before test
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_relationship WHERE rlt_usr_id_sender = 1 OR rlt_usr_id_recipient = 1";
        Statement stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);

        ContextMsg pContextMsgUser2 = new ContextMsg();
        pContextMsgUser2.setUser(new UserContext());
        pContextMsgUser2.getUser().setId(2L);

        List relationships = relationshipBean.getRelationship(pContextMsg, 1L, null);
        Assert.assertNotNull(relationships);
        Assert.assertTrue(relationships.isEmpty());

        RelationshipMsg pRelationshipMsg = new RelationshipMsg();
        pRelationshipMsg.setRequest("request message");
        pRelationshipMsg.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(1L);
        pRelationshipMsg.setSender(sender);
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(2L);
        pRelationshipMsg.setRecipient(recipient);

        //Create a request
        Long result = relationshipBean.create(pContextMsg, pRelationshipMsg);

        //Refuse the request
        relationshipBean.refuse(pContextMsgUser2, result, "no");

        //Get the request
        relationships = relationshipBean.getRelationship(pContextMsg, 1L, null);
        assertEquals(1, relationships.size());

        //Delete relationship after test
        stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        //Get the relationships
        relationships = relationshipBean.getRelationship(pContextMsg, 1L, null);
        assertNull(relationships);

        //Create the relationship
        result = relationshipBean.create(pContextMsg, pRelationshipMsg);

        //Validate it
        relationshipBean.validate(pContextMsgUser2, result, "sure");

        //Get the request
        relationships = relationshipBean.getRelationship(pContextMsg, 1L, null);
        assertEquals(1, relationships.size());

        //Delete relationship after test
        stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
