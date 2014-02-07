package org.mdubois.freeveggie.bo;

/**
 *
 * @author mdubois
 */
public class MostSharedProductBO {

    private Double quantityShared;

    private ProductBO product;

    public MostSharedProductBO() {
    }

    public MostSharedProductBO(final Double pQuantityShared, final ProductBO pProduct) {
        this.quantityShared = pQuantityShared;
        this.product = pProduct;
    }

    public Double getQuantityShared() {
        return quantityShared;
    }

    public void setQuantityShared(final Double pQuantityShared) {
        this.quantityShared = pQuantityShared;
    }

    public ProductBO getProduct() {
        return product;
    }

    public void setProduct(final ProductBO pProduct) {
        this.product = pProduct;
    }
}
