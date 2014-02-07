package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import mockit.Expectations;
import org.mdubois.freeveggie.bo.SubscriptionBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class SubscriptionMsgToBOConverterTest extends BusinessObjectConverterTest<SubscriptionBO,SubscriptionMsg>{


    private final SubscriptionMsgToBOConverter converter = new SubscriptionMsgToBOConverter();

    @Override
    public SubscriptionBO getNewBusinessObject() {
        SubscriptionBO  bo = new SubscriptionBO();
        bo.setFreeveggieAgreement(true);
        bo.setFreeveggieNewsletter(true);
        bo.setShareGardenInformation(true);
        bo.setSharePersonalInformation(true);
        return bo;


    }

    @Override
    public SubscriptionMsg getNewMessage() {
        SubscriptionMsg  msg = new SubscriptionMsg();
        msg.setFreeveggieAgreement(true);
        msg.setFreeveggieNewsletter(true);
        msg.setShareGardenInformation(true);
        msg.setSharePersonalInformation(true);
        return msg;
    }

    @Override
    public BusinessObjectConverter<SubscriptionBO,SubscriptionMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            {

            }
        };
    }


}
