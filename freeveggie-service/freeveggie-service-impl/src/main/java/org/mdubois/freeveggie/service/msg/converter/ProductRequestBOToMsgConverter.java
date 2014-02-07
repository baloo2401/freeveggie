package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import javax.inject.Inject;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;

/**
 *
 * @author Mickael Dubois
 */
public class ProductRequestBOToMsgConverter extends AbstractConverter<ProductRequestMsg, ProductRequestBO> {

    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;
    /**
     * {@link Converter<ProductMsg, ProductBO>}
     */
    @Inject
    private Converter<ProductMsg, ProductBO> productBOToMsgConverter;

    @Override
    public ProductRequestMsg doConvert(ProductRequestBO pSource) {
        ProductRequestMsg msg = new ProductRequestMsg();
        msg.setId(pSource.getId());
        msg.setAnswer(pSource.getAnswer());
        if(pSource.getAnswer()!=null){
            msg.setAnswerDate(new Date(pSource.getAnswerDate().getTime()));
        }
        msg.setCreationDate(new Date(pSource.getCreationDate().getTime()));
        msg.setMessage(pSource.getMessage());
        msg.setPickingDate(new Date(pSource.getPickingDate().getTime()));
        if (pSource.getProduct() != null) {
            msg.setProduct(productBOToMsgConverter.convert(pSource.getProduct()));
        }
        msg.setQuantity(pSource.getQuantity());
        if (pSource.getRequester() != null) {
            msg.setRequester(partialUserBOToMsgConverter.convert(pSource.getRequester()));
        }
        msg.setStatus(pSource.getStatus());
        return msg;
    }
}
