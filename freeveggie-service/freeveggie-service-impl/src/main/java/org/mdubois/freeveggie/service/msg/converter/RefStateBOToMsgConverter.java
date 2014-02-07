package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefStateBOToMsgConverter extends AbstractConverter<RefStateMsg, RefStateBO> implements Converter<RefStateMsg, RefStateBO> {

    @Inject
    private Converter<RefCountryMsg, RefCountryBO> refCountryBOConverter;

    @Override
    public RefStateMsg doConvert(RefStateBO pRefStateBO) {
        RefStateMsg msg = new RefStateMsg();
        msg.setId(pRefStateBO.getId());
        msg.setName(pRefStateBO.getName());
        if(pRefStateBO.getCountry() != null){
            msg.setCountry(refCountryBOConverter.convert(pRefStateBO.getCountry()));
        }
        return msg;
    }
}
