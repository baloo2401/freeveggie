package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefCityMsg;
import org.mdubois.freeveggie.service.msg.RefRegionMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefCityBOToMsgConverter extends AbstractConverter<RefCityMsg, RefCityBO> implements Converter<RefCityMsg, RefCityBO> {

    @Inject
    private Converter<RefRegionMsg, RefRegionBO> refRegionBOConverter;

    @Override
    public RefCityMsg doConvert(RefCityBO pRefCityBO) {
        RefCityMsg msg = new RefCityMsg();
        msg.setId(pRefCityBO.getId());
        msg.setName(pRefCityBO.getName());
        if(pRefCityBO.getRegion() != null){
            msg.setRegion(refRegionBOConverter.convert(pRefCityBO.getRegion()));
        }
        msg.setZipCode(Long.valueOf(pRefCityBO.getZipCode().trim()));
        return msg;
    }
}
