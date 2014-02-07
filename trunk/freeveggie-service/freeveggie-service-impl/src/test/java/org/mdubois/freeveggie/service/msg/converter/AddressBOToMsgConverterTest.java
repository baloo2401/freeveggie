package org.mdubois.freeveggie.service.msg.converter;

import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.AddressMsg;

/**
 *
 * @author Mickael Dubois
 */
public class AddressBOToMsgConverterTest extends AbstractConverterTest<AddressMsg, AddressBO> {

//    private RefCityMsg refCityMsg = new RefCityMsg();

    @Override
    public AddressMsg getExpectedMessage() {
        AddressMsg expResult = new AddressMsg();
        expResult.setStreetName("address line 1");
//        expResult.setCity(refCityMsg);
        expResult.setId(1L);
        expResult.setName("name");
        expResult.setStreetNumber("11");
        expResult.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        expResult.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        expResult.setCountry("pAddressBO.getCountry()");
        expResult.setLatitude(1.0);
        expResult.setLongitude(1.0);
        expResult.setName("pAddressBO.getName()");
        expResult.setPostalCode("pAddressBO.getPostalCode()");
        expResult.setLocality("locality");
        return expResult;
    }

    @Override
    public AddressBO getBusinessObject() {
        AddressBO pAddressBO = new AddressBO();
        pAddressBO.setStreetName("address line 1");
//        pAddressBO.setCity(refCityMsg);
        pAddressBO.setId(1L);
        pAddressBO.setName("name");
        pAddressBO.setStreetNumber("11");
        pAddressBO.setAdministrativeAreaLevel1("pAddressBO.getAdministrativeAreaLevel1()");
        pAddressBO.setAdministrativeAreaLevel2("pAddressBO.getAdministrativeAreaLevel2()");
        pAddressBO.setCountry("pAddressBO.getCountry()");
        pAddressBO.setLatitude(1.0);
        pAddressBO.setLongitude(1.0);
        pAddressBO.setName("pAddressBO.getName()");
        pAddressBO.setPostalCode("pAddressBO.getPostalCode()");
        pAddressBO.setLocality("locality");
        return pAddressBO;
    }

    @Override
    public Converter<AddressMsg, AddressBO> getConverter() {
        return new AddressBOToMsgConverter();
    }
}
