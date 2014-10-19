package org.mdubois.freeveggie.service.exp.rest;

import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IGardenBean;
import org.mdubois.freeveggie.bean.IProductBean;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.exp.exception.BusinessWebException;
import org.mdubois.freeveggie.service.msg.PictureMsg;

/**
 *
 * @author Mickael
 */
public class PicturetRESTTest {

    private final PictureREST service = new PictureREST();

    @Mocked
    private IProductBean productBean;
    @Mocked
    private IGardenBean gardenBean;

    @Before
    public void setUp() {
        Deencapsulation.setField(service, productBean);
        Deencapsulation.setField(service, gardenBean);
    }

    @Test
    public void testGetProductPictureById() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getPictureByProduct(pContextMsg, pSentId);
                returns(null);
            }
        };
        Assert.assertNull(service.getProductPictures(pContextUserId, pSentId));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetProductPictureByIdThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.getPictureByProduct(pContextMsg, pSentId);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getProductPictures(pContextUserId, pSentId));
    }

    @Test
    public void testGetProductPictureByIdFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pSentId = 101L;

        final List<PictureMsg> expected = new ArrayList<PictureMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                productBean.getPictureByProduct(pContextMsg, pSentId);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getProductPictures(pContextUserId, pSentId));
    }

    @Test
    public void testAddProductPicture() throws BusinessException {
        final Long pContextUserId = null;
        final Long pPictureId = null;
        final PictureMsg pPictureMsg = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                productBean.addPicture(pContextMsg, pPictureMsg);
            }
        };
        service.addProductPicture(pPictureId, pContextUserId, pPictureMsg);
    }

    @Test
    public void testAddProductPictureFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pPictureId = 15L;
        final PictureMsg pPictureMsg = new PictureMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.addPicture(pContextMsg, pPictureMsg);
            }
        };
        service.addProductPicture(pContextUserId, pPictureId, pPictureMsg);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddProductPictureException() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pPictureId = 15L;
        final PictureMsg pPictureMsg = new PictureMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                productBean.addPicture(pContextMsg, pPictureMsg);
                result = new BusinessException("");
            }
        };
        service.addProductPicture(pContextUserId, pPictureId, pPictureMsg);
    }

    @Test
    public void testGetGardenPictureById() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getPictureByGarden(pContextMsg, pSentId);
                returns(null);
            }
        };
        Assert.assertNull(service.getGardenPictures(pContextUserId, pSentId));
    }

    @Test(expected = BusinessWebException.class)
    public void testGetGardenPictureByIdThrowException() throws BusinessException {
        final Long pContextUserId = null;
        final Long pSentId = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.getPictureByGarden(pContextMsg, pSentId);
                result = new BusinessException("");
            }
        };
        Assert.assertNull(service.getGardenPictures(pContextUserId, pSentId));
    }

    @Test
    public void testGetGardenPictureByIdFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pSentId = 101L;

        final List<PictureMsg> expected = new ArrayList<PictureMsg>();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);

                gardenBean.getPictureByGarden(pContextMsg, pSentId);
                returns(expected);
            }
        };
        Assert.assertEquals(expected, service.getGardenPictures(pContextUserId, pSentId));
    }

    @Test
    public void testAddGardenPicture() throws BusinessException {
        final Long pContextUserId = null;
        final Long pPictureId = null;
        final PictureMsg pPictureMsg = null;
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                pContextMsg.setUser(userContext);
                gardenBean.addPicture(pContextMsg, pPictureMsg);
            }
        };
        service.addGardenPicture(pPictureId, pContextUserId, pPictureMsg);
    }

    @Test
    public void testAddGardenPictureFull() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pPictureId = 15L;
        final PictureMsg pPictureMsg = new PictureMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.addPicture(pContextMsg, pPictureMsg);
            }
        };
        service.addGardenPicture(pContextUserId, pPictureId, pPictureMsg);
    }

    @Test(expected = BusinessWebException.class)
    public void testAddGardenPictureException() throws BusinessException {
        final Long pContextUserId = 10L;
        final Long pPictureId = 15L;
        final PictureMsg pPictureMsg = new PictureMsg();
        new Expectations() {
            {
                ContextMsg pContextMsg = new ContextMsg();
                UserContext userContext = new UserContext();
                userContext.setId(pContextUserId);
                pContextMsg.setUser(userContext);
                gardenBean.addPicture(pContextMsg, pPictureMsg);
                result = new BusinessException("");
            }
        };
        service.addGardenPicture(pContextUserId, pPictureId, pPictureMsg);
    }
}
