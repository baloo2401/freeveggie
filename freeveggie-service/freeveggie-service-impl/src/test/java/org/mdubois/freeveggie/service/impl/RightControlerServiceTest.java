package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.bo.ProductCommentBO;
import org.mdubois.freeveggie.bo.ProductLikeBO;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.dao.api.IGardenCommentDAO;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.api.IGardenLikeDAO;
import org.mdubois.freeveggie.dao.api.IProductCommentDAO;
import org.mdubois.freeveggie.dao.api.IProductDAO;
import org.mdubois.freeveggie.dao.api.IProductLikeDAO;
import org.mdubois.freeveggie.dao.api.IRelationshipDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.utils.UserUtils;
import org.mdubois.freeveggie.service.api.IRightControlerService;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class RightControlerServiceTest {

    @Mocked
    private UserUtils userUtils;
    @Mocked
    private IUserPartialDAO userPartialDAO;
    @Mocked
    private IRelationshipDAO relationshipDAO;
    @Mocked
    private IGardenLikeDAO gardenLikeDAO;
    @Mocked
    private IGardenCommentDAO gardenCommentDAO;
    @Mocked
    private IGardenDAO gardenDAO;
    @Mocked
    private IProductCommentDAO productCommentDAO;
    @Mocked
    private IProductDAO productDAO;
    @Mocked
    private IProductLikeDAO productLikeDAO;

    @Test(expected = BusinessException.class)
    public void testIsUserInRoleUnknowUser() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, userPartialDAO);

                userPartialDAO.get(userId);
                returns(null);
            }
        };

        rightControlerService.isUserInRole(userId, UserRole.USER);
    }

    @Test
    public void testIsUserInRoleNotAdmin() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, userPartialDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setRole(UserRole.USER);

                userPartialDAO.get(userId);
                returns(userBO);

                UserUtils.isUserInRole(UserRole.USER, UserRole.ADMIN);
                returns(false);
            }
        };

        Assert.assertFalse(rightControlerService.isUserInRole(userId, UserRole.ADMIN));
    }

    @Test
    public void testIsUserInRole() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, userPartialDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setRole(UserRole.SUPERADMIN);

                userPartialDAO.get(userId);
                returns(userBO);

                UserUtils.isUserInRole(UserRole.SUPERADMIN, UserRole.USER);
                returns(true);
            }
        };

        Assert.assertTrue(rightControlerService.isUserInRole(userId, UserRole.USER));
    }

    @Test
    public void testIsUserOwnerProductNoProduct() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productDAO);

                productDAO.get(productId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerProduct(userId, productId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProductNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setOwner(userBO);

                ProductBO productBO = new ProductBO();
                productBO.setGarden(gardenBO);

                productDAO.get(productId);
                returns(productBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProduct(userId, productId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProduct() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setOwner(userBO);

                ProductBO productBO = new ProductBO();
                productBO.setGarden(gardenBO);

                productDAO.get(productId);
                returns(productBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProduct(userId, productId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerProductCommentNoComment() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productCommentId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productCommentDAO);

                productCommentDAO.get(productCommentId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductComment(userId, productCommentId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProductCommentNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productCommentId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setWriter(userBO);

                productCommentDAO.get(productCommentId);
                returns(productCommentBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductComment(userId, productCommentId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProductComment() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productCommentId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                ProductCommentBO productCommentBO = new ProductCommentBO();
                productCommentBO.setWriter(userBO);

                productCommentDAO.get(productCommentId);
                returns(productCommentBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductComment(userId, productCommentId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerProductLikeNoLike() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productLikeDAO);

                productLikeDAO.get(productLikeId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductLike(userId, productLikeId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProductLikeNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productLikeDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBO.setWriter(userBO);

                productLikeDAO.get(productLikeId);
                returns(productLikeBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductLike(userId, productLikeId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerProductLike() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long productLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, productLikeDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                ProductLikeBO productLikeBO = new ProductLikeBO();
                productLikeBO.setWriter(userBO);

                productLikeDAO.get(productLikeId);
                returns(productLikeBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerProductLike(userId, productLikeId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerGardenNoGarden() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenDAO);

                gardenDAO.get(gardenId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerGarden(userId, gardenId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGardenNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setOwner(userBO);

                gardenDAO.get(gardenId);
                returns(gardenBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGarden(userId, gardenId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGarden() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setOwner(userBO);

                gardenDAO.get(gardenId);
                returns(gardenBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGarden(userId, gardenId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerGardenCommentNoComment() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenCommentId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenCommentDAO);

                gardenCommentDAO.get(gardenCommentId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenComment(userId, gardenCommentId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGardenCommentNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenCommentId = 1233L;

        new Expectations() {

            private IGardenDAO gardenDAO;

            private IProductCommentDAO productCommentDAO;

            private IProductDAO productDAO;

            {
                Deencapsulation.setField(rightControlerService, gardenCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setWriter(userBO);

                gardenCommentDAO.get(gardenCommentId);
                returns(gardenCommentBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenComment(userId, gardenCommentId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGardenComment() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenCommentId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setWriter(userBO);

                gardenCommentDAO.get(gardenCommentId);
                returns(gardenCommentBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenComment(userId, gardenCommentId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerGardenLikeNoLike() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenLikeDAO);

                gardenLikeDAO.get(gardenLikeId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenLike(userId, gardenLikeId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGardenLikeNotOwner() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenLikeDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBO.setWriter(userBO);

                gardenLikeDAO.get(gardenLikeId);
                returns(gardenLikeBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenLike(userId, gardenLikeId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerGardenLike() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long userId = 123L;
        final Long gardenLikeId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, gardenLikeDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(userId);

                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBO.setWriter(userBO);

                gardenLikeDAO.get(gardenLikeId);
                returns(gardenLikeBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerGardenLike(userId, gardenLikeId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerRelationship() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long pUserId = 123L;
        final Long pRelationshipId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, relationshipDAO);

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setId(pRelationshipId);
                PartialUserBO partiUserBO = new PartialUserBO();
                partiUserBO.setId(pUserId);

                relationshipBO.setRecipient(partiUserBO);

                relationshipDAO.get(pRelationshipId);
                returns(relationshipBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerRelationship(pUserId, pRelationshipId);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testIsUserOwnerRelationship2() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long pUserId = 123L;
        final Long pRelationshipId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, relationshipDAO);

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setId(pRelationshipId);
                PartialUserBO partiUserBO = new PartialUserBO();
                partiUserBO.setId(1234L);

                relationshipBO.setRecipient(partiUserBO);

                relationshipDAO.get(pRelationshipId);
                returns(relationshipBO);
            }
        };

        boolean result = rightControlerService.isUserOwnerRelationship(pUserId, pRelationshipId);
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsUserOwnerRelationship3() throws BusinessException {
        final IRightControlerService rightControlerService = new RightControlerService();

        final Long pUserId = 123L;
        final Long pRelationshipId = 1233L;

        new Expectations() {

            {
                Deencapsulation.setField(rightControlerService, relationshipDAO);

                relationshipDAO.get(pRelationshipId);
                returns(null);
            }
        };

        boolean result = rightControlerService.isUserOwnerRelationship(pUserId, pRelationshipId);
        Assert.assertEquals(false, result);
    }

}
