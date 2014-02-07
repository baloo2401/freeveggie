package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.dao.impl.*;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.test.StatDaoUnitTest;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class StatistiqueDAOTest extends StatDaoUnitTest {

    private StatistiqueDAO statistiqueDAO = null;

    private UserPartialDAO userDAO;

    private GardenDAO gardenDAO;

    private RefCityDAO refCityDAO;

    private RefRegionDAO refRegionDAO;

    private ProductDAO productDAO;

    private Long userBO;

    private Long productBO;

    private Long gardenBO;

    private Integer refCityBO;

    private Integer refRegionBO;

    private Pagination pagination = new Pagination(1, 1);

    public StatistiqueDAOTest() {
    }

    @Before
    public void setUp() {
        statistiqueDAO = new StatistiqueDAO();
        statistiqueDAO.setEntityManager(em);

        userDAO = new UserPartialDAO();
        userDAO.setEntityManager(em);

        gardenDAO = new GardenDAO();
        gardenDAO.setEntityManager(em);

        refCityDAO = new RefCityDAO();
        refCityDAO.setEntityManager(em);

        refRegionDAO = new RefRegionDAO();
        refRegionDAO.setEntityManager(em);

        productDAO = new ProductDAO();
        productDAO.setEntityManager(em);

        userBO = 4L;
        productBO = 1L;
        gardenBO = 4L;
        refCityBO = 6;
        refRegionBO = 1;

    }

    @Test
    public void testGetBestRatedProductByUser() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffAUser(userBO,null);
        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(4, bestRatedProductBO.size());

        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(bestRatedProductBO.get(1).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(bestRatedProductBO.get(2).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(bestRatedProductBO.get(3).getProduct().getGarden().getOwner().getId(), userBO);

        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(), 5.0);
        Assert.assertEquals(bestRatedProductBO.get(1).getAverageNote(), 4.5);
        Assert.assertEquals(bestRatedProductBO.get(2).getAverageNote(), 3.5);
        Assert.assertEquals(bestRatedProductBO.get(3).getAverageNote(), 2.5);

    }

    @Test
    public void testGetBestRatedProductByGarden() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffAGarden(gardenBO,null);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(2, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getId(),gardenBO);
        Assert.assertEquals(bestRatedProductBO.get(1).getProduct().getGarden().getId(),gardenBO);

        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(), 4.5);
        Assert.assertEquals(bestRatedProductBO.get(1).getAverageNote(), 3.5);

    }

    @Test
    public void testGetBestRatedProductByCity() {
        Integer lRefCityBO = 1;
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffACity(lRefCityBO,null);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(3, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(),lRefCityBO);
        Assert.assertEquals(bestRatedProductBO.get(1).getProduct().getGarden().getAddress().getCity().getId(),lRefCityBO);
        Assert.assertEquals(bestRatedProductBO.get(2).getProduct().getGarden().getAddress().getCity().getId(),lRefCityBO);

        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(),new Double(5));
        Assert.assertEquals(bestRatedProductBO.get(1).getAverageNote(),new Double(4));
        Assert.assertEquals(bestRatedProductBO.get(2).getAverageNote(),new Double(3));

    }

    @Test
    public void testGetBestRatedProductByRegion() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffARegion(refRegionBO,null);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(3, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);
        Assert.assertEquals(bestRatedProductBO.get(1).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);
        Assert.assertEquals(bestRatedProductBO.get(2).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);

        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(),new Double(5));
        Assert.assertEquals(bestRatedProductBO.get(1).getAverageNote(),new Double(4));
        Assert.assertEquals(bestRatedProductBO.get(2).getAverageNote(),new Double(3));

    }

    @Test
    public void testGetLastExchangeProductByUser() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAUser(userBO,null);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(5, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(lastExchangeProductBO.get(1).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(lastExchangeProductBO.get(2).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(lastExchangeProductBO.get(3).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(lastExchangeProductBO.get(4).getProduct().getGarden().getOwner().getId(), userBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 06).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(1).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 05).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(2).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(3).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(4).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 02).getTime());

    }

    @Test
    public void testGetLastExchangeProductByGarden() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAGarden(gardenBO,null);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(3, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getId(), gardenBO);
        Assert.assertEquals(lastExchangeProductBO.get(1).getProduct().getGarden().getId(), gardenBO);
        Assert.assertEquals(lastExchangeProductBO.get(2).getProduct().getGarden().getId(), gardenBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(1).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(2).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 02).getTime());

    }



    @Test
    public void testGetLastExchangeProductByProduct() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAProduct(productBO,null);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(2, lastExchangeProductBO.size());

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(1).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 02).getTime());

    }


    @Test
    public void testGetLastExchangeProductByCity() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductByCity(refCityBO,null);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(4, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(), refCityBO);
        Assert.assertEquals(lastExchangeProductBO.get(1).getProduct().getGarden().getAddress().getCity().getId(), refCityBO);
        Assert.assertEquals(lastExchangeProductBO.get(2).getProduct().getGarden().getAddress().getCity().getId(), refCityBO);
        Assert.assertEquals(lastExchangeProductBO.get(3).getProduct().getGarden().getAddress().getCity().getId(), refCityBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 03).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(1).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 02).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(2).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 01).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(3).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 00).getTime());


    }

    @Test
    public void testGetLastExchangeProductByRegion(){
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductByRegion(refRegionBO,null);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(4, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(), refRegionBO);
        Assert.assertEquals(lastExchangeProductBO.get(1).getProduct().getGarden().getAddress().getCity().getRegion().getId(), refRegionBO);
        Assert.assertEquals(lastExchangeProductBO.get(2).getProduct().getGarden().getAddress().getCity().getRegion().getId(), refRegionBO);
        Assert.assertEquals(lastExchangeProductBO.get(3).getProduct().getGarden().getAddress().getCity().getRegion().getId(), refRegionBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.FEBRUARY, 01, 00, 00, 00).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(1).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 10, 00, 00).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(2).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 10, 00).getTime());
        Assert.assertEquals(lastExchangeProductBO.get(3).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 00).getTime());


    }

    @Test
    public void testGetMostSharedProductByUser() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByUser(userBO,null);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(4, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getOwner().getId(),userBO);
        Assert.assertEquals(mostSharedProductBO.get(1).getProduct().getGarden().getOwner().getId(),userBO);
        Assert.assertEquals(mostSharedProductBO.get(2).getProduct().getGarden().getOwner().getId(),userBO);
        Assert.assertEquals(mostSharedProductBO.get(3).getProduct().getGarden().getOwner().getId(),userBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(202.22222222));
        Assert.assertEquals(mostSharedProductBO.get(1).getQuantityShared(),new Double(2.3333333));
        Assert.assertEquals(mostSharedProductBO.get(2).getQuantityShared(),new Double(1.5555555));
        Assert.assertEquals(mostSharedProductBO.get(3).getQuantityShared(),new Double(0.64444));

    }

    @Test
    public void testGetMostSharedProductByGarden() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByGarden(gardenBO,null);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(2, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getId(),gardenBO);
        Assert.assertEquals(mostSharedProductBO.get(1).getProduct().getGarden().getId(),gardenBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(202.22222222));
        Assert.assertEquals(mostSharedProductBO.get(1).getQuantityShared(),new Double(2.3333333));

    }

    @Test
    public void testGetMostSharedProductByCity() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByCity(refCityBO,null);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(3, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(),refCityBO);
        Assert.assertEquals(mostSharedProductBO.get(1).getProduct().getGarden().getAddress().getCity().getId(),refCityBO);
        Assert.assertEquals(mostSharedProductBO.get(2).getProduct().getGarden().getAddress().getCity().getId(),refCityBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(2.78));
        Assert.assertEquals(mostSharedProductBO.get(1).getQuantityShared(),new Double(1.156776));
        Assert.assertEquals(mostSharedProductBO.get(2).getQuantityShared(),new Double(1));

    }

    @Test
    public void testGetMostSharedProductByRegion() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByRegion(refRegionBO,null);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(3, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);
        Assert.assertEquals(mostSharedProductBO.get(1).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);
        Assert.assertEquals(mostSharedProductBO.get(2).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(2.78));
        Assert.assertEquals(mostSharedProductBO.get(1).getQuantityShared(),new Double(2.5));
        Assert.assertEquals(mostSharedProductBO.get(2).getQuantityShared(),new Double(1));
    }

    @Test
    public void testGetBestRatedProductByUserWithPagination() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffAUser(userBO,pagination);
        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(1, bestRatedProductBO.size());

        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getOwner().getId(), userBO);
        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(), 5.0);

    }

    @Test
    public void testGetBestRatedProductByGardenWithPagination() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffAGarden(gardenBO,pagination);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(1, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getId(),gardenBO);
        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(), 4.5);

    }

    @Test
    public void testGetBestRatedProductByCityWithPagination() {
        Integer lRefCityBO = 1;
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffACity(lRefCityBO,pagination);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(1, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(),lRefCityBO);
        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(),new Double(5));

    }

    @Test
    public void testGetBestRatedProductByRegionWithPagination() {
        List<BestRatedProductBO> bestRatedProductBO = statistiqueDAO.getBestRatedProductOffARegion(refRegionBO,pagination);

        assertTrue(bestRatedProductBO != null);
        Assert.assertEquals(1, bestRatedProductBO.size());
        Assert.assertEquals(bestRatedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);
        Assert.assertEquals(bestRatedProductBO.get(0).getAverageNote(),new Double(5));

    }

    @Test
    public void testGetLastExchangeProductByUserWithPagination() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAUser(userBO,pagination);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(1, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getOwner().getId(), userBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 06).getTime());

    }

    @Test
    public void testGetLastExchangeProductByGardenWithPagination() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAGarden(gardenBO,pagination);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(1, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getId(), gardenBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());

    }



    @Test
    public void testGetLastExchangeProductByProductWithPagination() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductOffAProduct(productBO,pagination);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(1, lastExchangeProductBO.size());

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 04).getTime());

    }


    @Test
    public void testGetLastExchangeProductByCityWithPagination() {
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductByCity(refCityBO,pagination);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(1, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(), refCityBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.JANUARY, 01, 00, 00, 03).getTime());


    }

    @Test
    public void testGetLastExchangeProductByRegionWithPagination(){
        List<LastExchangeProductBO> lastExchangeProductBO = statistiqueDAO.getLastExchangeProductByRegion(refRegionBO,pagination);
        assertTrue(lastExchangeProductBO != null);
        Assert.assertEquals(1, lastExchangeProductBO.size());

        Assert.assertEquals(lastExchangeProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(), refRegionBO);

        int ANNEE = 112;

        Assert.assertEquals(lastExchangeProductBO.get(0).getLastExchangeDate().getTime(), new Date(ANNEE, Calendar.FEBRUARY, 01, 00, 00, 00).getTime());


    }

    @Test
    public void testGetMostSharedProductByUserWithPagination() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByUser(userBO,pagination);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(1, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getOwner().getId(),userBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(202.22222222));

    }

    @Test
    public void testGetMostSharedProductByGardenWithPagination() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByGarden(gardenBO,pagination);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(1, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getId(),gardenBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(202.22222222));
    }

    @Test
    public void testGetMostSharedProductByCityWithPagination() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByCity(refCityBO,pagination);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(1, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getId(),refCityBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(2.78));

    }

    @Test
    public void testGetMostSharedProductByRegionWithPagination() {
        List<MostSharedProductBO> mostSharedProductBO = statistiqueDAO.getMostSharedProductByRegion(refRegionBO,pagination);

        assertTrue(mostSharedProductBO != null);
        Assert.assertEquals(1, mostSharedProductBO.size());
        Assert.assertEquals(mostSharedProductBO.get(0).getProduct().getGarden().getAddress().getCity().getRegion().getId(),refRegionBO);

        Assert.assertEquals(mostSharedProductBO.get(0).getQuantityShared(),new Double(2.78));
    }

}
