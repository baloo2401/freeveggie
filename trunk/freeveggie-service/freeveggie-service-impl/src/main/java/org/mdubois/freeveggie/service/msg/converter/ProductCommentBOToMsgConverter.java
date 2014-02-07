package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import javax.inject.Inject;
import org.mdubois.freeveggie.framework.msg.converter.AbstractConverter;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductCommentBOToMsgConverter extends AbstractConverter<ProductCommentMsg, ProductCommentBO> implements Converter<ProductCommentMsg, ProductCommentBO> {

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
    public ProductCommentMsg doConvert(ProductCommentBO pProductCommentBO) {
        ProductCommentMsg msg = new ProductCommentMsg();
        msg.setId(pProductCommentBO.getId());
        msg.setCreationDate(pProductCommentBO.getCreationDate());
        msg.setStatus(pProductCommentBO.getStatus());
        if (pProductCommentBO.getProduct() != null) {
            msg.setProduct(productBOToMsgConverter.convert(pProductCommentBO.getProduct()));
        }
        if (pProductCommentBO.getWriter() != null) {
            msg.setWriter(partialUserBOToMsgConverter.convert(pProductCommentBO.getWriter()));
        }
        msg.setComment(pProductCommentBO.getComment());
        msg.setNote(pProductCommentBO.getNote());
        return msg;
    }
}
