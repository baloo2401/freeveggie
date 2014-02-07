package org.mdubois.freeveggie.service.msg;

import javax.xml.bind.annotation.XmlRootElement;
import org.mdubois.freeveggie.framework.msg.Message;

/**
 *
 * @author mdubois
 */
@XmlRootElement
public class BestRatedProductMsg extends Message {

    private Double averageNote;

    private ProductMsg product;

    public Double getAverageNote() {
        return averageNote;
    }

    public void setAverageNote(final Double pAverageNote) {
        this.averageNote = pAverageNote;
    }

    public ProductMsg getProduct() {
        return product;
    }

    public void setProduct(final ProductMsg pProduct) {
        this.product = pProduct;
    }
}
