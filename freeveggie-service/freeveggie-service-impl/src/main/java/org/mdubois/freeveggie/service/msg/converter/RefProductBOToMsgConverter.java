package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.bo.RefMonthBO;
import org.mdubois.freeveggie.bo.RefProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.RefProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefProductBOToMsgConverter extends AbstractConverter<RefProductMsg, RefProductBO> implements Converter<RefProductMsg, RefProductBO> {

    @Inject
    private Converter<Month, RefMonthBO> refMonthBOConverter;

    @Override
    public RefProductMsg doConvert(RefProductBO pRefCountryBO) {
        RefProductMsg msg = new RefProductMsg();
        msg.setCarbohydrate(pRefCountryBO.getCarbohydrate());
        msg.setId(pRefCountryBO.getId());
        msg.setLipid(pRefCountryBO.getLipid());
        msg.setName(pRefCountryBO.getName());
        msg.setPictureFilename(pRefCountryBO.getPictureFilename());
        msg.setProtein(pRefCountryBO.getProtein());
        msg.setType(pRefCountryBO.getType());
        if(pRefCountryBO.getReapMonths() != null && !pRefCountryBO.getReapMonths().isEmpty()){
            msg.setReapMonths(refMonthBOConverter.convert(pRefCountryBO.getReapMonths()));
        }
        if(pRefCountryBO.getSeedMonths() != null && !pRefCountryBO.getSeedMonths().isEmpty()){
            msg.setSeedMonths(refMonthBOConverter.convert(pRefCountryBO.getSeedMonths()));
        }
        return msg;
    }
}
