package org.mdubois.freeveggie.service.msg.converter;

import org.mdubois.freeveggie.bo.RefCityBO;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;

/**
 *
 * @author Mickael Dubois
 */
public class SubscriptionBOToMsgConverterTest extends AbstractConverterTest<SubscriptionMsg, SubscriptionBO> {

    private RefCityBO refCityBO = new RefCityBO();

    @Override
    public SubscriptionMsg getExpectedMessage() {
        SubscriptionMsg expResult = new SubscriptionMsg();
        expResult.setFreeveggieAgreement(Boolean.FALSE);
        expResult.setFreeveggieNewsletter(Boolean.FALSE);
        expResult.setId(10L);
        expResult.setShareGardenInformation(Boolean.TRUE);
        expResult.setSharePersonalInformation(Boolean.TRUE);

        return expResult;
    }

    @Override
    public SubscriptionBO getBusinessObject() {
        SubscriptionBO pSubscriptionBO = new SubscriptionBO();
        pSubscriptionBO.setFreeveggieAgreement(Boolean.FALSE);
        pSubscriptionBO.setFreeveggieNewsletter(Boolean.FALSE);
        pSubscriptionBO.setId(10L);
        pSubscriptionBO.setShareGardenInformation(Boolean.TRUE);
        pSubscriptionBO.setSharePersonalInformation(Boolean.TRUE);

        return pSubscriptionBO;
    }

    @Override
    public Converter<SubscriptionMsg, SubscriptionBO> getConverter() {
        return new SubscriptionBOToMsgConverter();
    }
}
