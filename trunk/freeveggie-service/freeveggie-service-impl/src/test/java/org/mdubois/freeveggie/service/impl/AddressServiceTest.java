package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.dao.api.IAddressDAO;
import org.mdubois.freeveggie.dao.api.IRefCityDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.service.api.IAddressService;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class AddressServiceTest {

    @Test
    public void create() throws BusinessException {
        final IAddressService addressService = new AddressService();
        final Long newAddressId = 12765L;
        final AddressMsg addressMsg = new AddressMsg();
        final RefCityMsg refCityMsg = new RefCityMsg();
        final Integer refCityId = 123;
        refCityMsg.setId(refCityId);
        addressMsg.setCity(refCityMsg);

        new Expectations() {

            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private IRefCityDAO refCityDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> converter;

            {
                Deencapsulation.setField(addressService, addressDAO);
                Deencapsulation.setField(addressService, refCityDAO);
                Deencapsulation.setField(addressService, converter);

                RefCityBO refCityBO = new RefCityBO();

                refCityDAO.get(refCityId);
                returns(refCityBO);

                AddressBO addressBO = new AddressBO();
                converter.createNew(addressMsg);
                returns(addressBO);

                addressDAO.save(addressBO);
                returns(newAddressId);

            }
        };

        Long id = addressService.create(addressMsg);
        Assert.assertEquals(newAddressId, id);
    }

    @Test(expected=BusinessException.class)
    public void createNoCity() throws BusinessException {
        final IAddressService addressService = new AddressService();
        final AddressMsg addressMsg = new AddressMsg();
        final RefCityMsg refCityMsg = new RefCityMsg();
        final Integer refCityId = 123;
        refCityMsg.setId(refCityId);
        addressMsg.setCity(refCityMsg);

        new Expectations() {

            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private IRefCityDAO refCityDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> converter;

            {
                Deencapsulation.setField(addressService, addressDAO);
                Deencapsulation.setField(addressService, refCityDAO);
                Deencapsulation.setField(addressService, converter);

                RefCityBO refCityBO = new RefCityBO();

                refCityDAO.get(refCityId);
                returns(null);

            }
        };

        Long id = addressService.create(addressMsg);
    }

    @Test
    public void update() throws BusinessException {
        final IAddressService addressService = new AddressService();
        final Long oldAddressId = 12765L;
        final AddressMsg addressMsg = new AddressMsg();
        final RefCityMsg refCityMsg = new RefCityMsg();
        final Integer refCityId = 123;
        refCityMsg.setId(refCityId);
        addressMsg.setCity(refCityMsg);

        new Expectations() {

            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private IRefCityDAO refCityDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> converter;

            {
                Deencapsulation.setField(addressService, addressDAO);
                Deencapsulation.setField(addressService, refCityDAO);
                Deencapsulation.setField(addressService, converter);

                AddressBO addressBO = new AddressBO();
                addressDAO.get(oldAddressId);
                returns(addressBO);


                converter.update(addressBO, addressMsg);

                addressDAO.update(addressBO);

            }
        };

        addressService.update(oldAddressId, addressMsg);
    }

    @Test(expected=BusinessException.class)
    public void updateNoAddress() throws BusinessException {
        final IAddressService addressService = new AddressService();
        final Long oldAddressId = 12765L;
        final AddressMsg addressMsg = new AddressMsg();
        final RefCityMsg refCityMsg = new RefCityMsg();
        final Integer refCityId = 123;
        refCityMsg.setId(refCityId);
        addressMsg.setCity(refCityMsg);

        new Expectations() {

            @Mocked
            private IAddressDAO addressDAO;
            @Mocked
            private IRefCityDAO refCityDAO;
            @Mocked
            private BusinessObjectConverter<AddressBO, AddressMsg> converter;

            {
                Deencapsulation.setField(addressService, addressDAO);
                Deencapsulation.setField(addressService, refCityDAO);
                Deencapsulation.setField(addressService, converter);

                AddressBO addressBO = new AddressBO();
                addressDAO.get(oldAddressId);
                returns(null);

            }
        };

        addressService.update(oldAddressId, addressMsg);
    }
}
