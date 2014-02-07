package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class SubscriptionMsgToBOConverter  implements BusinessObjectConverter<SubscriptionBO,SubscriptionMsg> {

    @Override
    public SubscriptionBO createNew(SubscriptionMsg pSubscriptionMsg) {
        SubscriptionBO subscriptionBO = new SubscriptionBO();
        subscriptionBO.setFreeveggieAgreement(pSubscriptionMsg.getFreeveggieAgreement());
        subscriptionBO.setFreeveggieNewsletter(pSubscriptionMsg.getFreeveggieNewsletter());
        subscriptionBO.setShareGardenInformation(pSubscriptionMsg.getShareGardenInformation());
        subscriptionBO.setSharePersonalInformation(pSubscriptionMsg.getSharePersonalInformation());
        return subscriptionBO;
    }

    @Override
    public void update(SubscriptionBO pSubscriptionBO, SubscriptionMsg pSubscriptionMsg) {
        pSubscriptionBO.setFreeveggieAgreement(pSubscriptionMsg.getFreeveggieAgreement());
        pSubscriptionBO.setFreeveggieNewsletter(pSubscriptionMsg.getFreeveggieNewsletter());
        pSubscriptionBO.setShareGardenInformation(pSubscriptionMsg.getShareGardenInformation());
        pSubscriptionBO.setSharePersonalInformation(pSubscriptionMsg.getSharePersonalInformation());
    }

}
