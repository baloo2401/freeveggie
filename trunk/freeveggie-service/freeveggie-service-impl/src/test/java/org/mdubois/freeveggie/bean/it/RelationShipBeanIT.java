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
import org.mdubois.freeveggie.bean.IRelationShipBean;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class RelationShipBeanIT extends AbstractBeanIntegrationTest {

    private IRelationShipBean relationShipBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/RelationShipBeanLocal");
        relationShipBean = (IRelationShipBean) bean;
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test(expected = MessageValidationException.class)
    public void testGetRelationShip() throws Exception {
        ContextMsg pContextMsg = null;
        Long pUserId = null;
        List expResult = null;
        List result = relationShipBean.getRelationShip(pContextMsg, pUserId, null);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRefCitiesByCountry method, of class ReferenceBean.
     */
    @Test
    public void testGetRelationShip2() throws Exception {
        ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(1L);
        Long pUserId = 2L;
        List result = relationShipBean.getRelationShip(pContextMsg, pUserId, null);
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

        List relationships = relationShipBean.getRelationShip(pContextMsg, 1L, null);
        Assert.assertNotNull(relationships);
        Assert.assertTrue(relationships.isEmpty());

        RelationShipMsg pRelationshipMsg = new RelationShipMsg();
        pRelationshipMsg.setRequest("request message");
        pRelationshipMsg.setType(RelationshipType.FRIEND);
        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(1L);
        pRelationshipMsg.setSender(sender);
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(2L);
        pRelationshipMsg.setRecipient(recipient);

        //Create a request
        Long result = relationShipBean.create(pContextMsg, pRelationshipMsg);

        //Refuse the request
        relationShipBean.refuse(pContextMsgUser2, result, "no");

        //Get the request
        relationships = relationShipBean.getRelationShip(pContextMsg, 1L, null);
        assertEquals(1, relationships.size());

        //Delete relationship after test
        stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();

        //Get the relationships
        relationships = relationShipBean.getRelationShip(pContextMsg, 1L, null);
        assertNull(relationships);

        //Create the relationship
        result = relationShipBean.create(pContextMsg, pRelationshipMsg);

        //Validate it
        relationShipBean.validate(pContextMsgUser2, result, "sure");

        //Get the request
        relationships = relationShipBean.getRelationShip(pContextMsg, 1L, null);
        assertEquals(1, relationships.size());

        //Delete relationship after test
        stmt = freeveggieDatasource.getConnection().createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
