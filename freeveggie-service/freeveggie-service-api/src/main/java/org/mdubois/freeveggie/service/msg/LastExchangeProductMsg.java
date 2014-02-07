package org.mdubois.freeveggie.service.msg;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 *
 * @author mdubois
 */
@XmlRootElement
public class LastExchangeProductMsg extends Message {

    private Date lastExchangeDate;

    private ProductMsg product;

    public Date getLastExchangeDate() {
        return lastExchangeDate;
    }

    public void setLastExchangeDate(final Date pLastExchangeDate) {
        this.lastExchangeDate = pLastExchangeDate;
    }

    public ProductMsg getProduct() {
        return product;
    }

    public void setProduct(final ProductMsg pProduct) {
        this.product = pProduct;
    }
}
