package org.mdubois.freeveggie.service.exp.rest;

import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IAddressBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.IdMsg;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 *
 * @author Mickael
 */
public class AddresstRESTTest {

    private final AddressREST service = new AddressREST();

    @Mocked
    private IAddressBean addressBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, addressBean);
    }

    @Test
    public void testAddAddress() throws BusinessException {
        final AddressMsg pAddressMsg = null;
        new Expectations() {
            {
                addressBean.create(pAddressMsg);
                returns(null);
            }
        };
        service.addAddress(pAddressMsg);
    }

    @Test
    public void testAddAddressWithData() throws BusinessException {
        final AddressMsg pAddressMsg = new AddressMsg();
        final Long pContextUserId = 10L;
        final IdMsg expected = new IdMsg(1235L);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                addressBean.create(pAddressMsg);
                returns(1235L);
            }
        };
        Assert.assertEquals(expected, service.addAddress(pAddressMsg));
    }

    @Test(expected = BusinessWebException.class)
    public void testAddAddressThrowException() throws BusinessException {
        final AddressMsg pAddressMsg = new AddressMsg();
        final Long pContextUserId = 10L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                addressBean.create(pAddressMsg);
                result = new BusinessException("");
            }
        };
        service.addAddress(pAddressMsg);
    }

    @Test
    @Ignore
    public void testUpdateAddress() throws BusinessException {
        final AddressMsg pAddressMsg = null;
        final Long addressId = null;
        final Long pContextUserId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                addressBean.update(pContextMsg, addressId, pAddressMsg);
                returns(false);
            }
        };
        service.updateAddress(pAddressMsg, pContextUserId);
    }

    @Test
    public void testUpdateAddressWithData() throws BusinessException {
        final AddressMsg pAddressMsg = new AddressMsg();
        final Long pContextUserId = 10L;
        final Long addressId = 123L;
        pAddressMsg.setId(addressId);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                addressBean.update(pContextMsg, addressId, pAddressMsg);
                returns(true);
            }
        };
        service.updateAddress(pAddressMsg, pContextUserId);
    }

    @Test(expected = BusinessWebException.class)
    public void testUpdateAddressThrowException() throws BusinessException {
        final AddressMsg pAddressMsg = new AddressMsg();
        final Long pContextUserId = 10L;
        final Long addressId = 123L;
        pAddressMsg.setId(addressId);
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                addressBean.update(pContextMsg, addressId, pAddressMsg);
                result = new BusinessException("");
            }
        };
        service.updateAddress(pAddressMsg, pContextUserId);
    }

    @Test
    public void testGetAddressById() throws BusinessException {

        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                addressBean.getAddressById(pContextMsg, null);
                returns(null);
            }
        };
        junit.framework.Assert.assertNull(service.getAddressById(null, null));
    }

    @Test
    public void testGetAddressByIdWithReturn() throws BusinessException {

        final long userId = 10L;
        final Long addressId = 11L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                addressBean.getAddressById(pContextMsg, addressId);
                returns(null);
            }
        };
        junit.framework.Assert.assertNull(service.getAddressById(userId, addressId));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetAddressByIdThrowException() throws BusinessException {

        final long userId = 10L;
        final Long addressId = 11L;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(userId);
                pContextMsg.setUser(userContext);
                addressBean.getAddressById(pContextMsg, addressId);
                result = new BusinessException("");
            }
        };
        junit.framework.Assert.assertNull(service.getAddressById(userId, addressId));
    }

}
