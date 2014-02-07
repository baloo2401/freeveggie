package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.RefRegionBO;
import org.mdubois.freeveggie.bo.RefStateBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefRegionMsg;
import org.mdubois.freeveggie.service.msg.RefStateMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefRegionBOToMsgConverter extends AbstractConverter<RefRegionMsg, RefRegionBO> implements Converter<RefRegionMsg, RefRegionBO> {

    @Inject
    private Converter<RefStateMsg, RefStateBO> refStateBOConverter;

    @Override
    public RefRegionMsg doConvert(RefRegionBO pRefRegionBO) {
        RefRegionMsg msg = new RefRegionMsg();
        msg.setId(pRefRegionBO.getId());
        msg.setName(pRefRegionBO.getName());
        if(pRefRegionBO.getState() != null){
            msg.setState(refStateBOConverter.convert(pRefRegionBO.getState()));
        }
        return msg;
    }
}
