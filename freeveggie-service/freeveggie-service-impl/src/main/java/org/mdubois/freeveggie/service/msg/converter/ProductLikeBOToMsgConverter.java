package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductLikeBOToMsgConverter extends AbstractConverter<ProductLikeMsg, ProductLikeBO> implements Converter<ProductLikeMsg, ProductLikeBO> {

    /**
     * {@link Converter<AddressMsg, AddressBO>}
     */
    @Inject
    private Converter<ProductMsg, ProductBO> productBOToMsgConverter;
    /**
     * {@link Converter<PartialUserMsg, PartialUserBO>}
     */
    @Inject
    private Converter<PartialUserMsg, PartialUserBO> partialUserBOToMsgConverter;

    @Override
    public ProductLikeMsg doConvert(ProductLikeBO pProductLikeBO) {
        ProductLikeMsg msg = new ProductLikeMsg();
        msg.setId(pProductLikeBO.getId());
        msg.setCreationDate(pProductLikeBO.getCreationDate());
        msg.setStatus(pProductLikeBO.getStatus());
        if (pProductLikeBO.getProduct() != null) {
            msg.setProduct(productBOToMsgConverter.convert(pProductLikeBO.getProduct()));
        }
        if (pProductLikeBO.getWriter() != null) {
            msg.setWriter(partialUserBOToMsgConverter.convert(pProductLikeBO.getWriter()));
        }
        return msg;
    }
}
