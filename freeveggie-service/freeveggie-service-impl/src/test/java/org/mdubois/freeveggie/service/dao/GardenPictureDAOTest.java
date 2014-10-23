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
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenPictureBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.dao.impl.GardenDAO;
import org.mdubois.freeveggie.dao.impl.GardenPictureDAO;
import org.mdubois.freeveggie.dao.impl.UserPartialDAO;
import org.mdubois.freeveggie.framework.dao.IReadWriteDAO;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;

/**
 *
 * @author mickael
 */
public class GardenPictureDAOTest extends ReadWriteDAOTest<GardenPictureBO, Long> {

    private static final long MICKY_TOMATO_ID = 1L;
    private static final long THIERRY_ID = 3L;

    private GardenPictureDAO gardenPictureDAO = null;
    private UserPartialDAO userDAO = null;
    private PartialUserBO thierryUserPartialBO = null;
    private GardenDAO gardenDAO = null;
    private GardenBO mickaelTomato = null;

    @Before
    public void setUp() {
        gardenPictureDAO = new GardenPictureDAO();
        gardenPictureDAO.setEntityManager(em);

        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        mickaelTomato = gardenDAO.get(MICKY_TOMATO_ID);
        thierryUserPartialBO = userDAO.get(THIERRY_ID);
    }

    @Test
    public void getGardenPictureByGarden() {
        List<GardenPictureBO> gardenPicturesBO = gardenPictureDAO.getGardenPictureByGarden(1L);
        Assert.assertTrue(gardenPicturesBO != null);
        Assert.assertTrue("Dataset need more data to run test", gardenPicturesBO.size() > 0);
        AssertBusinnesObject.assertNoDuplicate(gardenPicturesBO);
        for (GardenPictureBO gardenPictureBO : gardenPicturesBO) {
            Assert.assertEquals(mickaelTomato, gardenPictureBO.getGarden());
        }

    }

    @Test
    public void getGardenPictureByIdFetchImage() {
        GardenPictureBO gardenPictureBO = gardenPictureDAO.getGardenPictureByIdFetchImage(1L);
        Assert.assertTrue(gardenPictureBO != null);
        Assert.assertTrue(gardenPictureBO.getPicture() != null);
    }

    @Override
    public GardenPictureBO createEntity() {
        GardenPictureBO gardenPictureBO = new GardenPictureBO();
        gardenPictureBO.setCreationDate(new Date());
        gardenPictureBO.setMimeType("jpeg");
        gardenPictureBO.setPicture("".getBytes());
        gardenPictureBO.setGarden(mickaelTomato);

        return gardenPictureBO;
    }

    @Override
    public IReadWriteDAO<GardenPictureBO, Long> getDao() {
        return gardenPictureDAO;
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
