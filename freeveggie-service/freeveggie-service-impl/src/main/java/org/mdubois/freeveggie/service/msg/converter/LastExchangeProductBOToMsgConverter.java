package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class LastExchangeProductBOToMsgConverter extends AbstractConverter<LastExchangeProductMsg, LastExchangeProductBO> implements Converter<LastExchangeProductMsg, LastExchangeProductBO> {

    @Inject
    private Converter<ProductMsg, ProductBO> productBOToMsgConverter;

    @Override
    public LastExchangeProductMsg doConvert(LastExchangeProductBO pSource) {
        LastExchangeProductMsg lastExchangeProductMsg = new LastExchangeProductMsg();
        lastExchangeProductMsg.setLastExchangeDate(pSource.getLastExchangeDate());
        if(pSource.getProduct()!= null) {
            lastExchangeProductMsg.setProduct(productBOToMsgConverter.convert(pSource.getProduct()));
        }

        return lastExchangeProductMsg;
    }


}
