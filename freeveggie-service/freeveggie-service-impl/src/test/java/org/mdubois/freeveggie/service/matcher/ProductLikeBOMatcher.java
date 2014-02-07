/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.bo.ProductLikeBO;

/**
 *
 * @author francishuynh
 */
public class ProductLikeBOMatcher extends BaseMatcher<ProductLikeBO> {

    private ProductLikeBO productLikeBO;

     public ProductLikeBOMatcher(ProductLikeBO pProductLikeBO) {
        this.productLikeBO = pProductLikeBO;
    }

    @Override
    public boolean matches(Object item) {
       if (item instanceof ProductLikeBO) {
            ProductLikeBO productLikeBOToTest = (ProductLikeBO) item;
            if (productLikeBOToTest.getProduct().getId().equals(productLikeBO.getProduct().getId())) {
                if (productLikeBOToTest.getWriter().getId().equals(productLikeBO.getWriter().getId())) {
                    if (productLikeBOToTest.getStatus().equals(productLikeBO.getStatus())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
