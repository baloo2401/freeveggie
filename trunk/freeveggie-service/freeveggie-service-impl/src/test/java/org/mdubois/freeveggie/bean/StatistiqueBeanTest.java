package org.mdubois.freeveggie.bean;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.List;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.bean.local.StatistiqueBeanLocal;
import org.mdubois.freeveggie.framework.exception.AccessNotGrantedException;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.exception.TechnicalException;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.api.IRightControlerService;
import org.mdubois.freeveggie.service.api.IStatistiqueService;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class StatistiqueBeanTest {

    @Mocked
    private IStatistiqueService statistiqueService;
    @Mocked
    private IRightControlerService rightControlerService;

    // <editor-fold defaultstate="collapsed" desc="getBestRatedProductByCity">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByCity(pRefCityId, pPagination);
                returns(expResult);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetBestRatedProductByCityAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = BusinessException.class)
    public void testGetBestRatedProductByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByCity(pRefCityId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getBestRatedProductByCity(pContextMsg, pRefCityId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetBestRatedProductByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByCity(pRefCityId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getBestRatedProductByCity(pContextMsg, pRefCityId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getBestRatedProductByRegion">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByRegion(pRefRegionId, pPagination);
                returns(expResult);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByRegion(pContextMsg, pRefRegionId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetBestRatedProductByRegionAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetBestRatedProductByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByRegion(pRefRegionId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getBestRatedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetBestRatedProductByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByRegion(pRefRegionId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getBestRatedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getBestRatedProductByUser">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByUser(pRefUserId, pPagination);
                returns(expResult);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByUser(pContextMsg, pRefUserId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetBestRatedProductByUserAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetBestRatedProductByUserThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByUser(pRefUserId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getBestRatedProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetBestRatedProductByUserThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByUser(pRefUserId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getBestRatedProductByUser(pContextMsg, pRefUserId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getBestRatedProductByGarden">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<BestRatedProductMsg> expResult = new ArrayList<BestRatedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByGarden(pRefGardenId, pPagination);
                returns(expResult);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByGarden(pContextMsg, pRefGardenId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetBestRatedProductByGardenAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<BestRatedProductMsg> result = instance.getBestRatedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetBestRatedProductByGardenThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByGarden(pRefGardenId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getBestRatedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetBestRatedProductByGardenThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getBestRatedProductByGarden(pRefGardenId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getBestRatedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getMostSharedProductByCity">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByCity(pRefCityId, pPagination);
                returns(expResult);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetMostSharedProductByCityAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = BusinessException.class)
    public void testGetMostSharedProductByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByCity(pRefCityId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getMostSharedProductByCity(pContextMsg, pRefCityId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetMostSharedProductByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByCity(pRefCityId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getMostSharedProductByCity(pContextMsg, pRefCityId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getMostSharedProductByRegion">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByRegion(pRefRegionId, pPagination);
                returns(expResult);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByRegion(pContextMsg, pRefRegionId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetMostSharedProductByRegionAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetMostSharedProductByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByRegion(pRefRegionId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getMostSharedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetMostSharedProductByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByRegion(pRefRegionId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getMostSharedProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getMostSharedProductByUser">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByUser(pRefUserId, pPagination);
                returns(expResult);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByUser(pContextMsg, pRefUserId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetMostSharedProductByUserAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetMostSharedProductByUserThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByUser(pRefUserId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getMostSharedProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetMostSharedProductByUserThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByUser(pRefUserId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getMostSharedProductByUser(pContextMsg, pRefUserId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getMostSharedProductByGarden">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<MostSharedProductMsg> expResult = new ArrayList<MostSharedProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByGarden(pRefGardenId, pPagination);
                returns(expResult);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByGarden(pContextMsg, pRefGardenId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetMostSharedProductByGardenAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<MostSharedProductMsg> result = instance.getMostSharedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetMostSharedProductByGardenThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByGarden(pRefGardenId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getMostSharedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetMostSharedProductByGardenThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getMostSharedProductByGarden(pRefGardenId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getMostSharedProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getLastExchangeProductByCity">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByCity(pRefCityId, pPagination);
                returns(expResult);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetLastExchangeProductByCityAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByCity(pContextMsg, pRefCityId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = BusinessException.class)
    public void testGetLastExchangeProductByCityThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByCity(pRefCityId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getLastExchangeProductByCity(pContextMsg, pRefCityId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetLastExchangeProductByCityThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefCityId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByCity(pRefCityId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getLastExchangeProductByCity(pContextMsg, pRefCityId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getLastExchangeProductByRegion">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByRegion(pRefRegionId, pPagination);
                returns(expResult);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByRegion(pContextMsg, pRefRegionId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetLastExchangeProductByRegionAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetLastExchangeProductByRegionThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByRegion(pRefRegionId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getLastExchangeProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetLastExchangeProductByRegionThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Integer pRefRegionId = 12;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByRegion(pRefRegionId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getLastExchangeProductByRegion(pContextMsg, pRefRegionId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getLastExchangeProductByUser">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByUser(pRefUserId, pPagination);
                returns(expResult);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByUser(pContextMsg, pRefUserId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetLastExchangeProductByUserAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetLastExchangeProductByUserThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByUser(pRefUserId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getLastExchangeProductByUser(pContextMsg, pRefUserId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetLastExchangeProductByUserThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefUserId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByUser(pRefUserId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getLastExchangeProductByUser(pContextMsg, pRefUserId, pPagination);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getLastExchangeProductByGarden">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);
        final List<LastExchangeProductMsg> expResult = new ArrayList<LastExchangeProductMsg>();

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByGarden(pRefGardenId, pPagination);
                returns(expResult);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByGarden(pContextMsg, pRefGardenId, pPagination);
        Assert.assertEquals(expResult, result);
    }

    @Test(expected = AccessNotGrantedException.class)
    public void testGetLastExchangeProductByGardenAccessNotGranted() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(false);
            }
        };

        List<LastExchangeProductMsg> result = instance.getLastExchangeProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = BusinessException.class)
    public void testGetLastExchangeProductByGardenThrowBusinessException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByGarden(pRefGardenId, pPagination);
                result = new BusinessException("BusinessException");
            }
        };

        instance.getLastExchangeProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }

    @Test(expected = TechnicalException.class)
    public void testGetLastExchangeProductByGardenThrowTechnicalException() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 12378L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final IStatistiqueBean instance = new StatistiqueBeanLocal();
        final Long pRefGardenId = 12L;
        final Pagination pPagination = new Pagination(1, 2);

        new Expectations() {

            {
                Deencapsulation.setField(instance, statistiqueService);
                Deencapsulation.setField(instance, rightControlerService);

                rightControlerService.isUserInRole(anyLong, UserRole.USER);
                returns(true);

                statistiqueService.getLastExchangeProductByGarden(pRefGardenId, pPagination);
                result = new TechnicalException("TechnicalException");
            }
        };

        instance.getLastExchangeProductByGarden(pContextMsg, pRefGardenId, pPagination);
    }
    // </editor-fold>

}
