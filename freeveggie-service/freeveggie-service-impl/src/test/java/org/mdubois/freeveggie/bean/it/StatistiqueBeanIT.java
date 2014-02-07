package org.mdubois.freeveggie.bean.it;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bean.IStatistiqueBean;
import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;
// </editor-fold>

/**
 *
 * @author mdubois
 */
public class StatistiqueBeanIT extends AbstractBeanIntegrationTest {
    /**
     * {@link IStatistiqueBeanLocal}
     */
    private IStatistiqueBean statistiqueBean;

    @Before
    @Override
    public void setUp() throws Throwable {
        super.setUp();
        Object bean = container.getContext().lookup("java:global/classes/StatistiqueBeanLocal");
        statistiqueBean = (IStatistiqueBean) bean;
    }
    
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefCityId = 15582;

        List<BestRatedProductMsg> result = statistiqueBean.getBestRatedProductByCity(pContextMsg, pRefCityId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefRegionId = 42;

        List<BestRatedProductMsg> result = statistiqueBean.getBestRatedProductByRegion(pContextMsg, pRefRegionId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Long pUserId = 1L;

        List<BestRatedProductMsg> result = statistiqueBean.getBestRatedProductByUser(pContextMsg, pUserId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetBestRatedProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long gardenId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(gardenId);
        final Long pGardenId = 1L;

        List<BestRatedProductMsg> result = statistiqueBean.getBestRatedProductByGarden(pContextMsg, pGardenId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefCityId = 15582;

        List<MostSharedProductMsg> result = statistiqueBean.getMostSharedProductByCity(pContextMsg, pRefCityId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefRegionId = 42;
        
        List<MostSharedProductMsg> result = statistiqueBean.getMostSharedProductByRegion(pContextMsg, pRefRegionId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Long pRefUserId = 1L;

        List<MostSharedProductMsg> result = statistiqueBean.getMostSharedProductByUser(pContextMsg, pRefUserId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    // <editor-fold defaultstate="collapsed" desc="getMostSharedProductByGarden">
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetMostSharedProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final Long pRefGardenId = 1L;
        
        List<MostSharedProductMsg> result = statistiqueBean.getMostSharedProductByGarden(pContextMsg, pRefGardenId, null);
        
        Assert.assertTrue(result.size() >= 1);
    }


    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByCity() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefCityId = 15582;

        List<LastExchangeProductMsg> result = statistiqueBean.getLastExchangeProductByCity(pContextMsg, pRefCityId, null);
        Assert.assertTrue(result.size() >= 1);
    }
    
    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByRegion() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 1L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Integer pRefRegionId = 42;

        List<LastExchangeProductMsg> result = statistiqueBean.getLastExchangeProductByRegion(pContextMsg, pRefRegionId, null);
        Assert.assertTrue(result.size() >= 1);
    }

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByUser() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long userId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(userId);
        final UserMsg pUserMsg = new UserMsg();
        pUserMsg.setId(userId);
        final Long pRefUserId = 1L;

        List<LastExchangeProductMsg> result = statistiqueBean.getLastExchangeProductByUser(pContextMsg, pRefUserId, null);
        Assert.assertTrue(result.size() >= 1);
    }
    

    /**
     * Test of create method, of class GardenBean.
     */
    @Test
    public void testGetLastExchangeProductByGarden() throws Exception {
        final ContextMsg pContextMsg = new ContextMsg();
        final Long GardenId = 2L;
        pContextMsg.setUser(new UserContext());
        pContextMsg.getUser().setId(GardenId);
        final UserMsg pGardenMsg = new UserMsg();
        pGardenMsg.setId(GardenId);
        final Long pRefGardenId = 1L;

        List<LastExchangeProductMsg> result = statistiqueBean.getLastExchangeProductByGarden(pContextMsg, pRefGardenId, null);
        Assert.assertTrue(result.size() >= 1);
    }
    
}
