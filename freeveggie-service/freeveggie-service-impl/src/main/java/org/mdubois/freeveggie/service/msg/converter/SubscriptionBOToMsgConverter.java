package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class SubscriptionBOToMsgConverter extends AbstractConverter<SubscriptionMsg, SubscriptionBO> implements Converter<SubscriptionMsg, SubscriptionBO> {

    @Override
    public SubscriptionMsg doConvert(SubscriptionBO pSubscriptionBO) {
        SubscriptionMsg msg = new SubscriptionMsg();
        msg.setFreeveggieAgreement(pSubscriptionBO.getFreeveggieAgreement());
        msg.setFreeveggieNewsletter(pSubscriptionBO.getFreeveggieNewsletter());
        msg.setId(pSubscriptionBO.getId());
        msg.setShareGardenInformation(pSubscriptionBO.getShareGardenInformation());
        msg.setSharePersonalInformation(pSubscriptionBO.getSharePersonalInformation());
        return msg;
    }
}
