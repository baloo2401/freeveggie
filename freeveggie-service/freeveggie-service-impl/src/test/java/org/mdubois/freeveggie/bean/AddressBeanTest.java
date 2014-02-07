package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bean.local.AddressBeanLocal;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.service.api.IAddressService;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.msg.AddressMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class AddressBeanTest {

    // <editor-fold defaultstate="collapsed" desc="Update">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testUpdate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;
        final boolean expResult = true;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.update(pAddressId, pAddressMsg);
            }
        };

        instance.update(pContextMsg, pAddressId, pAddressMsg);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testUpdateAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        instance.update(pContextMsg, pAddressId, pAddressMsg);
    }

    @Test(expected = BusinessException.class)
    public void testUpdateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.update(pAddressId, pAddressMsg);
                throwsException(new BusinessException("BusinessException"));
            }
        };

        instance.update(pContextMsg, pAddressId, pAddressMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testUpdateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.update(pAddressId, pAddressMsg);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };

        instance.update(pContextMsg, pAddressId, pAddressMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Create">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testCreate() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;
        final boolean expResult = true;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;

            {
                Deencapsulation.setField(instance, addresseService);

                addresseService.create(pAddressMsg);
                returns(123L);
            }
        };

        Long result = instance.create(pAddressMsg);
        Assert.assertEquals((Long)123L, result);
    }

    @Test(expected = BusinessException.class)
    public void testCreateThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;

            {
                Deencapsulation.setField(instance, addresseService);

                addresseService.create(pAddressMsg);
                throwsException(new BusinessException("BusinessException"));
            }
        };

        instance.create(pAddressMsg);
    }

    @Test(expected = TechnicalException.class)
    public void testCreateThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final AddressMsg pAddressMsg = new AddressMsg();
        pAddressMsg.setId(userId);
        final IAddressBean instance = new AddressBeanLocal();
        final Long pAddressId = 12L;

        new Expectations() {

            @Mocked
            private IAddressService addresseService;

            {
                Deencapsulation.setField(instance, addresseService);

                addresseService.create(pAddressMsg);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };

        instance.create(pAddressMsg);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="GetAddressById">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetAddressById() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IAddressBean instance = new AddressBeanLocal();
        final AddressMsg expResult = new AddressMsg();

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.getAddressById(userId);
                returns(expResult);
            }
        };

        AddressMsg result = instance.getAddressById(pContextMsg, userId);
        assertEquals(expResult, result);
    }
    
    @Test(expected = AccessNotGrantedException.class)
    public void testGetAddressByIdAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IAddressBean instance = new AddressBeanLocal();
        final AddressMsg expResult = new AddressMsg();

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        AddressMsg result = instance.getAddressById(pContextMsg, userId);
        assertEquals(expResult, result);
    }

    @Test(expected = BusinessException.class)
    public void testGetAddressByIdThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IAddressBean instance = new AddressBeanLocal();

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.getAddressById(userId);
                throwsException(new BusinessException("BusinessException"));
            }
        };

        instance.getAddressById(pContextMsg, userId);
    }

    @Test(expected = TechnicalException.class)
    public void testGetAddressByIdThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(12L);
        final Long userId = 12378L;
        final IAddressBean instance = new AddressBeanLocal();

        new Expectations() {

            @Mocked
            private IAddressService addresseService;
            @Mocked
            private IRightControlerService rightControlerService;

            {
                Deencapsulation.setField(instance, addresseService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                addresseService.getAddressById(userId);
                throwsException(new TechnicalException("TechnicalException"));
            }
        };

        instance.getAddressById(pContextMsg, userId);
    }
    // </editor-fold>
}
