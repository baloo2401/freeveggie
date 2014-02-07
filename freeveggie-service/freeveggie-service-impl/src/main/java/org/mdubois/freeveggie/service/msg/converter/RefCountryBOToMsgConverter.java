package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefCountryBOToMsgConverter extends AbstractConverter<RefCountryMsg, RefCountryBO> implements Converter<RefCountryMsg, RefCountryBO> {

    @Override
    public RefCountryMsg doConvert(RefCountryBO pRefCountryBO) {
        RefCountryMsg msg = new RefCountryMsg();
        msg.setCodeIsoA2(pRefCountryBO.getCodeIsoA2());
        msg.setCodeIsoA3(pRefCountryBO.getCodeIsoA3());
        msg.setCodeIsoNumber(pRefCountryBO.getCodeIsoNumber());
        msg.setId(pRefCountryBO.getId());
        msg.setName(pRefCountryBO.getName());
        return msg;
    }
}
