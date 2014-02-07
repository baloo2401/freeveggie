package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.Month;
import org.mdubois.freeveggie.bo.RefMonthBO;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefMonthBOToMsgConverterTest extends AbstractConverterTest<Month, RefMonthBO> {

    @Override
    public Month getExpectedMessage() {
        Month msg = Month.FEBRUARY;
        return msg;
    }

    @Override
    public RefMonthBO getBusinessObject() {
        RefMonthBO bo = new RefMonthBO();
        bo.setId(2);
        bo.setMonth("janvier");
        return bo;
    }

    @Override
    public RefMonthBOToEnumConverter getConverter() {
        return new RefMonthBOToEnumConverter();
    }
}
