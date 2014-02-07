package org.mdubois.freeveggie.bo;

import java.util.Date;

/**
 *
 * @author mdubois
 */
public class LastExchangeProductBO {

    private Date lastExchangeDate;

    private ProductBO product;

    public LastExchangeProductBO() {
    }

    public LastExchangeProductBO(final Date pLastExchangeDate, final ProductBO pProduct) {
        this.lastExchangeDate = pLastExchangeDate;
        this.product = pProduct;
    }

    public Date getLastExchangeDate() {
        return lastExchangeDate;
    }

    public void setLastExchangeDate(final Date pLastExchangeDate) {
        this.lastExchangeDate = pLastExchangeDate;
    }

    public ProductBO getProduct() {
        return product;
    }

    public void setProduct(final ProductBO pProduct) {
        this.product = pProduct;
    }
}
