/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.dao;

import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductPictureBO;
import org.mdubois.freeveggie.dao.impl.ProductDAO;
import org.mdubois.freeveggie.dao.impl.ProductPictureDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;

/**
 *
 * @author mickael
 */
public class ProductPictureDAOTest extends ReadWriteDAOTest<ProductPictureBO, Long> {

    private static final long MICKY_TOMATO_ID = 1L;
    private static final long THIERRY_ID = 3L;

    private ProductPictureDAO productPictureDAO = null;
    private UserPartialDAO userDAO = null;
    private PartialUserBO thierryUserPartialBO = null;
    private ProductDAO productDAO = null;
    private ProductBO mickaelTomato = null;

    @Before
    public void setUp() {
        productPictureDAO = new ProductPictureDAO();
        productPictureDAO.setEntityManager(em);

        productDAO = new ProductDAO();
        productDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        mickaelTomato = productDAO.get(MICKY_TOMATO_ID);
        thierryUserPartialBO = userDAO.get(THIERRY_ID);
    }

    @Test
    public void getProductPictureByProduct() {
        List<ProductPictureBO> productPicturesBO = productPictureDAO.getProductPictureByProduct(1L);
        Assert.assertTrue(productPicturesBO != null);
        Assert.assertTrue("Dataset need more data to run test", productPicturesBO.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(productPicturesBO);
        for (ProductPictureBO productPictureBO : productPicturesBO) {
            Assert.assertEquals(mickaelTomato, productPictureBO.getProduct());
        }

    }

    @Test
    public void getProductPictureByIdFetchImage() {
        ProductPictureBO productPictureBO = productPictureDAO.getProductPictureByIdFetchImage(1L);
        Assert.assertTrue(productPictureBO != null);
        Assert.assertTrue(productPictureBO.getPicture() != null);
    }

    @Override
    public ProductPictureBO createEntity() {
        ProductPictureBO productPictureBO = new ProductPictureBO();
        productPictureBO.setCreationDate(new Date());
        productPictureBO.setMimeType("jpeg");
        productPictureBO.setPicture("".getBytes());
        productPictureBO.setProduct(mickaelTomato);

        return productPictureBO;
    }

    @Override
    public IReadWriteDAO<ProductPictureBO, Long> getDao() {
        return productPictureDAO;
    }

    @Override
    public Class<? extends OrderColumn> getOrderColumn() {
        return null;
    }

    @Override
    public Class<? extends CriteriaColumn> getCriteriaColumn() {
        return null;
    }

    @Override
    public Long getId() {
        return 1L;
    }
}
