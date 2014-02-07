package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 *
 * @author mdubois
 */
@XmlRootElement
public class MostSharedProductMsg extends Message {

    private Double quantityShared;

    private ProductMsg product;

    public Double getQuantityShared() {
        return quantityShared;
    }

    public void setQuantityShared(final Double pQuantityShared) {
        this.quantityShared = pQuantityShared;
    }

    public ProductMsg getProduct() {
        return product;
    }

    public void setProduct(final ProductMsg pProduct) {
        this.product = pProduct;
    }
}
