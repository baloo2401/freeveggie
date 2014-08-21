package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IAddressBean;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class AddressBeanIT extends AbstractBeanIntegrationTest {

    /**
     * {@link IAddressBeanLocal}
     */
    private IAddressBean addressBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        addressBean = (IAddressBean) container.getContext().lookup("java:global/classes/AddressBeanLocal");
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        final Long addressId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(addressId);
        pAddressMsg.setLatitude(0.0);
        pAddressMsg.setLongitude(0.0);
        pAddressMsg.setName("change address name");
        pAddressMsg.setStreetName("rue du test it");
        pAddressMsg.setStreetNumber("1");
        RefCityMsg refCity = new RefCityMsg();
        refCity.setId(26);
        pAddressMsg.setCity(refCity);

        Long result = addressBean.create(pAddressMsg);

        AddressMsg findAddress = addressBean.getAddressById(pContextMsg, result);

        addressBean.update(pContextMsg, result, findAddress);

        findAddress = addressBean.getAddressById(pContextMsg, result);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testCreate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        final Long addressId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(addressId);
        pAddressMsg.setLatitude(0.0);
        pAddressMsg.setLongitude(0.0);
        pAddressMsg.setName("change address name");
        pAddressMsg.setStreetName("rue du test it");
        pAddressMsg.setStreetNumber("1");
        RefCityMsg refCity = new RefCityMsg();
        refCity.setId(26);
        pAddressMsg.setCity(refCity);

        Long result = addressBean.create(pAddressMsg);

        AddressMsg findAddress = addressBean.getAddressById(pContextMsg, result);
    }
}
