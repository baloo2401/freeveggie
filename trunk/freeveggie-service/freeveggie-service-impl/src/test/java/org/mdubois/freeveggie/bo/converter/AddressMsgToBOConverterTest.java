package org.mdubois.freeveggie.bo.converter;

import mockit.Expectations;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class AddressMsgToBOConverterTest extends BusinessObjectConverterTest<AddressBO,AddressMsg>{

//    private static final RefCityBO REF_CITY_BO = new RefCityBO();
    private final AddressMsgToBOConverter converter = new AddressMsgToBOConverter();

    @Override
    public AddressBO getNewBusinessObject() {
        AddressBO  addressBO = new AddressBO();
        addressBO.setStreetName("address line 1");
        addressBO.setName("name");
        addressBO.setStreetNumber("10");
        addressBO.setStreetName("pAddressBO.getStreetName()");
        addressBO.setLocality("pAddressBO.getLocality()");
        addressBO.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        addressBO.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        addressBO.setCountry("pAddressBO.getCountry()");
        addressBO.setLatitude(1.0);
        addressBO.setLongitude(1.0);
        addressBO.setPostalCode("pAddressBO.getPostalCode()");
        return addressBO;


    }

    @Override
    public AddressMsg getNewMessage() {
        AddressMsg  addressMsg = new AddressMsg();
        addressMsg.setStreetName("address line 1");
        addressMsg.setStreetName("pAddressBO.getStreetName()");
        addressMsg.setLocality("pAddressBO.getLocality()");
        addressMsg.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        addressMsg.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        addressMsg.setCountry("pAddressBO.getCountry()");
        addressMsg.setLatitude(1.0);
        addressMsg.setLongitude(1.0);
        addressMsg.setPostalCode("pAddressBO.getPostalCode()");
//        addressMsg.setCity(REF_CITY_BO);
        addressMsg.setName("name");
        addressMsg.setStreetNumber("10");
        return addressMsg;
    }

    @Override
    public BusinessObjectConverter<AddressBO, AddressMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            {

            }
        };
    }


}
