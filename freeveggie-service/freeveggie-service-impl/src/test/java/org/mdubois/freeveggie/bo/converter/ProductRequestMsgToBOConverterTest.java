package org.mdubois.freeveggie.bo.converter;

import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.bo.ProductRequestBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.utils.SystemTime;
import org.mdubois.freeveggie.service.msg.ProductRequestMsg;

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ProductRequestMsgToBOConverterTest extends BusinessObjectConverterTest<ProductRequestBO, ProductRequestMsg> {

    private static final Date NOW = new Date();
    private final ProductRequestMsgToBOConverter converter = new ProductRequestMsgToBOConverter();

    @Override
    public ProductRequestBO getNewBusinessObject() {
        ProductRequestBO productRequestBO = new ProductRequestBO();
        productRequestBO.setPickingDate(NOW);
        productRequestBO.setMessage("message");
        productRequestBO.setCreationDate(NOW);
        productRequestBO.setQuantity(1.0F);
        productRequestBO.setStatus(RequestStatus.PENDING);
        return productRequestBO;


    }

    @Override
    public ProductRequestMsg getNewMessage() {
        ProductRequestMsg productRequestMsg = new ProductRequestMsg();
        productRequestMsg.setPickingDate(NOW);
        productRequestMsg.setMessage("message");
        productRequestMsg.setQuantity(1.0F);
        return productRequestMsg;
    }

    @Override
    public BusinessObjectConverter<ProductRequestBO, ProductRequestMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations() {

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
