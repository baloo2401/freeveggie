package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class BestRatedProductBOToMsgConverter extends AbstractConverter<BestRatedProductMsg, BestRatedProductBO> implements Converter<BestRatedProductMsg, BestRatedProductBO> {

    @Inject
    private Converter<ProductMsg, ProductBO> productBOToMsgConverter;

    @Override
    public BestRatedProductMsg doConvert(BestRatedProductBO pSource) {
        BestRatedProductMsg bestRatedProductMsg = new BestRatedProductMsg();
        bestRatedProductMsg.setAverageNote(pSource.getAverageNote());
        if(pSource.getProduct()!= null) {
            bestRatedProductMsg.setProduct(productBOToMsgConverter.convert(pSource.getProduct()));
        }

        return bestRatedProductMsg;
    }


}
