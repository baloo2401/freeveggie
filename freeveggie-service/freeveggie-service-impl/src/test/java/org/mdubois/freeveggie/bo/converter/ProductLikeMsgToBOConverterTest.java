package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.framework.utils.SystemTime;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ProductLikeMsgToBOConverterTest extends BusinessObjectConverterTest<ProductLikeBO,ProductLikeMsg>{

    private PartialUserMsg userMsg = new PartialUserMsg();
    private ProductMsg productMsg = new ProductMsg();

    private final ProductLikeMsgToBOConverter converter = new ProductLikeMsgToBOConverter();

    private static final Date NOW = new Date();

    @Override
    public ProductLikeBO getNewBusinessObject() {
        ProductLikeBO  bo = new ProductLikeBO();
        bo.setCreationDate(NOW);
        bo.setStatus(EvaluationStatus.SETTED);
        return bo;


    }

    @Override
    public ProductLikeMsg getNewMessage() {
        ProductLikeMsg  msg = new ProductLikeMsg();
        msg.setWriter(userMsg);
        msg.setProduct(productMsg);
        return msg;
    }

    @Override
    public BusinessObjectConverter<ProductLikeBO,ProductLikeMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations(){
            @Mocked
            @SuppressWarnings("unused")
            private final SystemTime systemTime = null;
            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }


}
