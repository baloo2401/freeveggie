package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.AddressMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class AddressMsgToBOConverter implements BusinessObjectConverter<AddressBO, AddressMsg> {

    @Override
    public AddressBO createNew(AddressMsg pAddressMsg) {
        AddressBO addressBO = new AddressBO();
        addressBO.setStreetName(pAddressMsg.getStreetName());
        addressBO.setAdministrativeAreaLevel1(pAddressMsg.getAdministrativeAreaLevel1());
        addressBO.setAdministrativeAreaLevel2(pAddressMsg.getAdministrativeAreaLevel2());
        addressBO.setLocality(pAddressMsg.getLocality());
        addressBO.setCountry(pAddressMsg.getCountry());
        addressBO.setLatitude(pAddressMsg.getLatitude());
        addressBO.setLongitude(pAddressMsg.getLongitude());
        addressBO.setName(pAddressMsg.getName());
        addressBO.setPostalCode(pAddressMsg.getPostalCode());
        //Should be set by ther service
        //addressBO.setCity(pAddressMsg.getCity()); have to be set by the service
        addressBO.setName(pAddressMsg.getName());
        addressBO.setStreetNumber(pAddressMsg.getStreetNumber());
        return addressBO;
    }

    @Override
    public void update(AddressBO pAddressBO, AddressMsg pAddressMsg) {
        pAddressBO.setStreetName(pAddressMsg.getStreetName());
        pAddressBO.setAdministrativeAreaLevel1(pAddressMsg.getAdministrativeAreaLevel1());
        pAddressBO.setAdministrativeAreaLevel2(pAddressMsg.getAdministrativeAreaLevel2());
        pAddressBO.setLocality(pAddressMsg.getLocality());
        pAddressBO.setCountry(pAddressMsg.getCountry());
        pAddressBO.setLatitude(pAddressMsg.getLatitude());
        pAddressBO.setLongitude(pAddressMsg.getLongitude());
        pAddressBO.setName(pAddressMsg.getName());
        pAddressBO.setPostalCode(pAddressMsg.getPostalCode());
        //Should be set by ther service
        //pAddressBO.setCity(pAddressMsg.getCity()); have to be set by the service
        pAddressBO.setName(pAddressMsg.getName());
        pAddressBO.setStreetNumber(pAddressMsg.getStreetNumber());
    }
}
