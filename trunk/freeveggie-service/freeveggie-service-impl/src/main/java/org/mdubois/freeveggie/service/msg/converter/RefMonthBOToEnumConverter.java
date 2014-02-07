package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.*;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefMonthBOToEnumConverter extends AbstractConverter<Month, RefMonthBO> implements Converter<Month, RefMonthBO> {

    @Override
    public Month doConvert(RefMonthBO pRefMonthBO) {
        Month msg = Month.fromInt(pRefMonthBO.getId());
        return msg;
    }
}
