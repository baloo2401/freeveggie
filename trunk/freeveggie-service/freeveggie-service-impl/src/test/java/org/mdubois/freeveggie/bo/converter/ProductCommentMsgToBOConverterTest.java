package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductCommentMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductCommentMsgToBOConverterTest extends BusinessObjectConverterTest<ProductCommentBO, ProductCommentMsg> {

    @Mocked
    @SuppressWarnings("unused")
    private final SystemTime systemTime = null;
    private PartialUserMsg userMsg = new PartialUserMsg();
    private ProductMsg productMsg = new ProductMsg();

    private final ProductCommentMsgToBOConverter converter = new ProductCommentMsgToBOConverter();

    private static final Date NOW = new Date();

    @Override
    public ProductCommentBO getNewBusinessObject() {
        ProductCommentBO bo = new ProductCommentBO();
        bo.setComment("comment");
        bo.setCreationDate(NOW);
        bo.setNote(EvaluationNote.BAD);
        bo.setStatus(EvaluationStatus.SETTED);
        return bo;

    }

    @Override
    public ProductCommentMsg getNewMessage() {
        ProductCommentMsg msg = new ProductCommentMsg();
        msg.setComment("comment");
        msg.setNote(EvaluationNote.BAD);
        msg.setWriter(userMsg);
        msg.setProduct(productMsg);
        return msg;
    }

    @Override
    public BusinessObjectConverter<ProductCommentBO, ProductCommentMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations() {
            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }

}
