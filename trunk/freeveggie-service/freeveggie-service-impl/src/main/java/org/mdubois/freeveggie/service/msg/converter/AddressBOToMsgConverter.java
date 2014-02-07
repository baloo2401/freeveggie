package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.AddressBO;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class AddressBOToMsgConverter extends AbstractConverter<AddressMsg, AddressBO> implements Converter<AddressMsg, AddressBO> {

    @Inject
    private Converter<RefCityMsg, RefCityBO> refCityBOToMsgConverter;

    @Override
    public AddressMsg doConvert(AddressBO pAddressBO) {
        AddressMsg msg = new AddressMsg();
        msg.setId(pAddressBO.getId());
        msg.setName(pAddressBO.getName());
        msg.setStreetNumber(pAddressBO.getStreetNumber());
        msg.setStreetName(pAddressBO.getStreetName());
        msg.setAdministrativeAreaLevel1(pAddressBO.getAdministrativeAreaLevel1());
        msg.setAdministrativeAreaLevel2(pAddressBO.getAdministrativeAreaLevel2());
        msg.setCountry(pAddressBO.getCountry());
        msg.setLatitude(pAddressBO.getLatitude());
        msg.setLongitude(pAddressBO.getLongitude());
        msg.setName(pAddressBO.getName());
        msg.setPostalCode(pAddressBO.getPostalCode());
        msg.setLocality(pAddressBO.getLocality());
        if(pAddressBO.getCity() != null){
            msg.setCity(refCityBOToMsgConverter.convert(pAddressBO.getCity()));
        }
        return msg;
    }
}
