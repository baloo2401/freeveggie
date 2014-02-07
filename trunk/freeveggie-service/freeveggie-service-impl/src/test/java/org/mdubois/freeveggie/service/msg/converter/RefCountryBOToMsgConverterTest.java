package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultcountry="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.RefCountryBO;
import org.mdubois.freeveggie.service.msg.RefCountryMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class RefCountryBOToMsgConverterTest extends AbstractConverterTest<RefCountryMsg, RefCountryBO> {

    @Override
    public RefCountryMsg getExpectedMessage() {
        RefCountryMsg msg = new RefCountryMsg();
        msg.setId(23);
        msg.setName("name");
        msg.setCodeIsoA2("a2");
        msg.setCodeIsoA3("aaa");
        msg.setCodeIsoNumber(123);
        return msg;
    }

    @Override
    public RefCountryBO getBusinessObject() {
        RefCountryBO bo = new RefCountryBO();
        bo.setId(23);
        bo.setName("name");
        bo.setCodeIsoA2("a2");
        bo.setCodeIsoA3("aaa");
        bo.setCodeIsoNumber(123);
        return bo;
    }

    @Override
    public RefCountryBOToMsgConverter getConverter() {
        return new RefCountryBOToMsgConverter();
    }
}
