package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.ISubscriptionBean;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class SubscriptionBeanIT extends AbstractBeanIntegrationTest {
    /**
     * {@link ISubscriptionBeanLocal}
     */
    private ISubscriptionBean subscriptionBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/SubscriptionBeanLocal");
        subscriptionBean = (ISubscriptionBean) bean;
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testCreate() throws Exception {
        //Set the product test as a validate status
        DataSource freeveggieDatasource = (DataSource) container.getContext().lookup("jdbc/freeveggie");
        String sql = "DELETE FROM t_user WHERE usr_email_address = 'monEmail@test.fr'";
        Connection con = freeveggieDatasource.getConnection();
        Statement stmt = con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
        stmt.close();
        
        final AddressMsg  addressMsg = new AddressMsg();
        addressMsg.setStreetName("address line 1");
        addressMsg.setStreetName("pAddressBO.getStreetName()");
        addressMsg.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        addressMsg.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        addressMsg.setCountry("pAddressBO.getCountry()");
        addressMsg.setLatitude(1.0);
        addressMsg.setLongitude(1.0);
        addressMsg.setName("pAddressBO.getName()");
        addressMsg.setPostalCode("pAddressBO.getPostalCode()");
//        addressMsg.setCity(REF_CITY_BO);
        addressMsg.setName("name");
        addressMsg.setStreetNumber("10");
        final SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
        subscriptionMsg.setFreeveggieAgreement(Boolean.TRUE);
        subscriptionMsg.setFreeveggieNewsletter(Boolean.TRUE);
        subscriptionMsg.setShareGardenInformation(Boolean.TRUE);
        subscriptionMsg.setSharePersonalInformation(Boolean.TRUE);
        final CreateAccountMsg pUserMsg = new CreateAccountMsg();
        pUserMsg.setEmail("monEmail@test.fr");
        pUserMsg.setFirstname("Mickeael");
        pUserMsg.setLastname("Dubois");
        pUserMsg.setAddressMsg(addressMsg);
        pUserMsg.setPassword("password");
        pUserMsg.setSubscription(subscriptionMsg);
        pUserMsg.setUsername("pourquoipas");

        Long result = subscriptionBean.create(pUserMsg);
        Assert.assertNotNull(result);
    }

    
}
