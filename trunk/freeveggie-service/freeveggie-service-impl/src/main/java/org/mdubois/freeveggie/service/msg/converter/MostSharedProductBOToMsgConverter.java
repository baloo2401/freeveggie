package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class MostSharedProductBOToMsgConverter extends AbstractConverter<MostSharedProductMsg, MostSharedProductBO> implements Converter<MostSharedProductMsg, MostSharedProductBO> {

    @Inject
    private Converter<ProductMsg, ProductBO> productBOToMsgConverter;

    @Override
    public MostSharedProductMsg doConvert(MostSharedProductBO pSource) {
        MostSharedProductMsg mostShardProductMsg = new MostSharedProductMsg();
        mostShardProductMsg.setQuantityShared(pSource.getQuantityShared());
        if(pSource.getProduct()!= null) {
            mostShardProductMsg.setProduct(productBOToMsgConverter.convert(pSource.getProduct()));
        }

        return mostShardProductMsg;
    }


}
