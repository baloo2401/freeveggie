package org.mdubois.freeveggie.bo;

/**
 *
 * @author mdubois
 */
public class BestRatedProductBO {

    private Double averageNote;

    private ProductBO product;

    public BestRatedProductBO() {
    }

    public BestRatedProductBO(final Double pAverageNote, final ProductBO pProduct) {
        this.averageNote = pAverageNote;
        this.product = pProduct;
    }

    public Double getAverageNote() {
        return averageNote;
    }

    public void setAverageNote(final Double pAverageNote) {
        this.averageNote = pAverageNote;
    }

    public ProductBO getProduct() {
        return product;
    }

    public void setProduct(final ProductBO pProduct) {
        this.product = pProduct;
    }
}
