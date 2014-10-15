package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.bo.BestRatedProductBO;
import org.mdubois.freeveggie.bo.LastExchangeProductBO;
import org.mdubois.freeveggie.bo.MostSharedProductBO;
import org.mdubois.freeveggie.bo.ProductBO;
import org.mdubois.freeveggie.dao.api.IStatistiqueDAO;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.service.api.IStatistiqueService;
import org.mdubois.freeveggie.service.msg.BestRatedProductMsg;
import org.mdubois.freeveggie.service.msg.LastExchangeProductMsg;
import org.mdubois.freeveggie.service.msg.MostSharedProductMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class StatistiqueServiceTest {

    @Mocked
    private IStatistiqueDAO statistiqueDAO;
    @Mocked
    private Converter<MostSharedProductMsg, MostSharedProductBO> mostSharedProductBoToMsgConverter;
    @Mocked
    private Converter<BestRatedProductMsg, BestRatedProductBO> bestRatedProductBoToMsgConverter;
    @Mocked
    private Converter<LastExchangeProductMsg, LastExchangeProductBO> lastExchangeProductBoToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void getMostSharedProductByCity() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<MostSharedProductMsg> bestRatedProductMsgs = new ArrayList<MostSharedProductMsg>();
        MostSharedProductMsg bestRatedProductMsg = new MostSharedProductMsg();
        bestRatedProductMsg.setQuantityShared(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                List<MostSharedProductBO> bestRatedProductBOs = new ArrayList<MostSharedProductBO>();
                MostSharedProductBO bestRatedProductBO = new MostSharedProductBO();
                bestRatedProductBO.setQuantityShared(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getMostSharedProductByCity(refCityId, pagination);
                returns(bestRatedProductBOs);

                mostSharedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<MostSharedProductMsg> bestRatedProductResultMsgs = statistiqueService.getMostSharedProductByCity(refCityId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getMostSharedProductByCityNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                statistiqueDAO.getMostSharedProductByCity(refCityId, pagination);
                returns(null);

                mostSharedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<MostSharedProductMsg> bestRatedProductResultMsgs = statistiqueService.getMostSharedProductByCity(refCityId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getMostSharedProductByRegion() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<MostSharedProductMsg> bestRatedProductMsgs = new ArrayList<MostSharedProductMsg>();
        MostSharedProductMsg bestRatedProductMsg = new MostSharedProductMsg();
        bestRatedProductMsg.setQuantityShared(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                List<MostSharedProductBO> bestRatedProductBOs = new ArrayList<MostSharedProductBO>();
                MostSharedProductBO bestRatedProductBO = new MostSharedProductBO();
                bestRatedProductBO.setQuantityShared(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getMostSharedProductByRegion(refRegionId, pagination);
                returns(bestRatedProductBOs);

                mostSharedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<MostSharedProductMsg> bestRatedProductResultMsgs = statistiqueService.getMostSharedProductByRegion(refRegionId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getMostSharedProductByRegionNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                statistiqueDAO.getMostSharedProductByRegion(refRegionId, pagination);
                returns(null);

                mostSharedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(statistiqueService.getMostSharedProductByRegion(refRegionId, pagination));
    }

    @Test
    public void getMostSharedProductByGarden() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<MostSharedProductMsg> bestRatedProductMsgs = new ArrayList<MostSharedProductMsg>();
        MostSharedProductMsg bestRatedProductMsg = new MostSharedProductMsg();
        bestRatedProductMsg.setQuantityShared(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                List<MostSharedProductBO> bestRatedProductBOs = new ArrayList<MostSharedProductBO>();
                MostSharedProductBO bestRatedProductBO = new MostSharedProductBO();
                bestRatedProductBO.setQuantityShared(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getMostSharedProductByGarden(gardenId, pagination);
                returns(bestRatedProductBOs);

                mostSharedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<MostSharedProductMsg> bestRatedProductResultMsgs = statistiqueService.getMostSharedProductByGarden(gardenId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getMostSharedProductByGardenNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                statistiqueDAO.getMostSharedProductByGarden(gardenId, pagination);
                returns(null);

                mostSharedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(statistiqueService.getMostSharedProductByGarden(gardenId, pagination));
    }

    @Test
    public void getMostSharedProductByPartialUser() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<MostSharedProductMsg> bestRatedProductMsgs = new ArrayList<MostSharedProductMsg>();
        MostSharedProductMsg bestRatedProductMsg = new MostSharedProductMsg();
        bestRatedProductMsg.setQuantityShared(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                List<MostSharedProductBO> bestRatedProductBOs = new ArrayList<MostSharedProductBO>();
                MostSharedProductBO bestRatedProductBO = new MostSharedProductBO();
                bestRatedProductBO.setQuantityShared(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getMostSharedProductByUser(partialUserId, pagination);
                returns(bestRatedProductBOs);

                mostSharedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<MostSharedProductMsg> bestRatedProductResultMsgs = statistiqueService.getMostSharedProductByUser(partialUserId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getMostSharedProductByPartialUserNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "mostSharedProductBoToMsgConverter", mostSharedProductBoToMsgConverter);

                statistiqueDAO.getMostSharedProductByUser(partialUserId, pagination);
                returns(null);

                mostSharedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(statistiqueService.getMostSharedProductByUser(partialUserId, pagination));
    }

    @Test
    public void getBestRatedProductByCity() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<BestRatedProductMsg> bestRatedProductMsgs = new ArrayList<BestRatedProductMsg>();
        BestRatedProductMsg bestRatedProductMsg = new BestRatedProductMsg();
        bestRatedProductMsg.setAverageNote(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                List<BestRatedProductBO> bestRatedProductBOs = new ArrayList<BestRatedProductBO>();
                BestRatedProductBO bestRatedProductBO = new BestRatedProductBO();
                bestRatedProductBO.setAverageNote(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getBestRatedProductOffACity(refCityId, pagination);
                returns(bestRatedProductBOs);

                bestRatedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByCity(refCityId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByCityNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                statistiqueDAO.getBestRatedProductOffACity(refCityId, pagination);
                returns(null);

                bestRatedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(statistiqueService.getBestRatedProductByCity(refCityId, pagination));
    }

    @Test
    public void getBestRatedProductByRegion() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<BestRatedProductMsg> bestRatedProductMsgs = new ArrayList<BestRatedProductMsg>();
        BestRatedProductMsg bestRatedProductMsg = new BestRatedProductMsg();
        bestRatedProductMsg.setAverageNote(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                List<BestRatedProductBO> bestRatedProductBOs = new ArrayList<BestRatedProductBO>();
                BestRatedProductBO bestRatedProductBO = new BestRatedProductBO();
                bestRatedProductBO.setAverageNote(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getBestRatedProductOffARegion(refRegionId, pagination);
                returns(bestRatedProductBOs);

                bestRatedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByRegion(refRegionId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByRegionNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                statistiqueDAO.getBestRatedProductOffARegion(refRegionId, pagination);
                returns(null);

                bestRatedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByRegion(refRegionId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByGarden() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<BestRatedProductMsg> bestRatedProductMsgs = new ArrayList<BestRatedProductMsg>();
        BestRatedProductMsg bestRatedProductMsg = new BestRatedProductMsg();
        bestRatedProductMsg.setAverageNote(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                List<BestRatedProductBO> bestRatedProductBOs = new ArrayList<BestRatedProductBO>();
                BestRatedProductBO bestRatedProductBO = new BestRatedProductBO();
                bestRatedProductBO.setAverageNote(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getBestRatedProductOffAGarden(gardenId, pagination);
                returns(bestRatedProductBOs);

                bestRatedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByGarden(gardenId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByGardenNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                statistiqueDAO.getBestRatedProductOffAGarden(gardenId, pagination);
                returns(null);

                bestRatedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByGarden(gardenId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByPartialUser() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<BestRatedProductMsg> bestRatedProductMsgs = new ArrayList<BestRatedProductMsg>();
        BestRatedProductMsg bestRatedProductMsg = new BestRatedProductMsg();
        bestRatedProductMsg.setAverageNote(2.00);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                List<BestRatedProductBO> bestRatedProductBOs = new ArrayList<BestRatedProductBO>();
                BestRatedProductBO bestRatedProductBO = new BestRatedProductBO();
                bestRatedProductBO.setAverageNote(2.00);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getBestRatedProductOffAUser(partialUserId, pagination);
                returns(bestRatedProductBOs);

                bestRatedProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByUser(partialUserId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getBestRatedProductByPartialUserNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "bestRatedProductBoToMsgConverter", bestRatedProductBoToMsgConverter);

                statistiqueDAO.getBestRatedProductOffAUser(partialUserId, pagination);
                returns(null);

                bestRatedProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<BestRatedProductMsg> bestRatedProductResultMsgs = statistiqueService.getBestRatedProductByUser(partialUserId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByCity() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<LastExchangeProductMsg> bestRatedProductMsgs = new ArrayList<LastExchangeProductMsg>();
        LastExchangeProductMsg bestRatedProductMsg = new LastExchangeProductMsg();
        bestRatedProductMsg.setLastExchangeDate(NOW);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                List<LastExchangeProductBO> bestRatedProductBOs = new ArrayList<LastExchangeProductBO>();
                LastExchangeProductBO bestRatedProductBO = new LastExchangeProductBO();
                bestRatedProductBO.setLastExchangeDate(NOW);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getLastExchangeProductByCity(refCityId, pagination);
                returns(bestRatedProductBOs);

                lastExchangeProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByCity(refCityId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByCityNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refCityId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                statistiqueDAO.getLastExchangeProductByCity(refCityId, pagination);
                returns(null);

                lastExchangeProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByCity(refCityId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByRegion() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        final List<LastExchangeProductMsg> bestRatedProductMsgs = new ArrayList<LastExchangeProductMsg>();
        LastExchangeProductMsg bestRatedProductMsg = new LastExchangeProductMsg();
        bestRatedProductMsg.setLastExchangeDate(NOW);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                List<LastExchangeProductBO> bestRatedProductBOs = new ArrayList<LastExchangeProductBO>();
                LastExchangeProductBO bestRatedProductBO = new LastExchangeProductBO();
                bestRatedProductBO.setLastExchangeDate(NOW);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getLastExchangeProductByRegion(refRegionId, pagination);
                returns(bestRatedProductBOs);

                lastExchangeProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByRegion(refRegionId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByRegionNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Integer refRegionId = 12765;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                statistiqueDAO.getLastExchangeProductByRegion(refRegionId, pagination);
                returns(null);

                lastExchangeProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByRegion(refRegionId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByGarden() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<LastExchangeProductMsg> bestRatedProductMsgs = new ArrayList<LastExchangeProductMsg>();
        LastExchangeProductMsg bestRatedProductMsg = new LastExchangeProductMsg();
        bestRatedProductMsg.setLastExchangeDate(NOW);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                List<LastExchangeProductBO> bestRatedProductBOs = new ArrayList<LastExchangeProductBO>();
                LastExchangeProductBO bestRatedProductBO = new LastExchangeProductBO();
                bestRatedProductBO.setLastExchangeDate(NOW);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getLastExchangeProductOffAGarden(gardenId, pagination);
                returns(bestRatedProductBOs);

                lastExchangeProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByGarden(gardenId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByGardenNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long gardenId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                statistiqueDAO.getLastExchangeProductOffAGarden(gardenId, pagination);
                returns(null);

                lastExchangeProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(statistiqueService.getLastExchangeProductByGarden(gardenId, pagination));
    }

    @Test
    public void getLastExchangeProductByPartialUser() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<LastExchangeProductMsg> bestRatedProductMsgs = new ArrayList<LastExchangeProductMsg>();
        LastExchangeProductMsg bestRatedProductMsg = new LastExchangeProductMsg();
        bestRatedProductMsg.setLastExchangeDate(NOW);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                List<LastExchangeProductBO> bestRatedProductBOs = new ArrayList<LastExchangeProductBO>();
                LastExchangeProductBO bestRatedProductBO = new LastExchangeProductBO();
                bestRatedProductBO.setLastExchangeDate(NOW);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getLastExchangeProductOffAUser(partialUserId, pagination);
                returns(bestRatedProductBOs);

                lastExchangeProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByUser(partialUserId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByPartialUserNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long partialUserId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                statistiqueDAO.getLastExchangeProductOffAUser(partialUserId, pagination);
                returns(null);

                lastExchangeProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByUser(partialUserId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByProduct() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long productId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        final List<LastExchangeProductMsg> bestRatedProductMsgs = new ArrayList<LastExchangeProductMsg>();
        LastExchangeProductMsg bestRatedProductMsg = new LastExchangeProductMsg();
        bestRatedProductMsg.setLastExchangeDate(NOW);

        ProductMsg productMsg = new ProductMsg();
        bestRatedProductMsg.setProduct(productMsg);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                List<LastExchangeProductBO> bestRatedProductBOs = new ArrayList<LastExchangeProductBO>();
                LastExchangeProductBO bestRatedProductBO = new LastExchangeProductBO();
                bestRatedProductBO.setLastExchangeDate(NOW);

                ProductBO productBO = new ProductBO();
                bestRatedProductBO.setProduct(productBO);

                statistiqueDAO.getLastExchangeProductOffAProduct(productId, pagination);
                returns(bestRatedProductBOs);

                lastExchangeProductBoToMsgConverter.convert(bestRatedProductBOs);
                returns(bestRatedProductMsgs);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByProduct(productId, pagination);
        Assert.assertEquals(bestRatedProductMsgs, bestRatedProductResultMsgs);
    }

    @Test
    public void getLastExchangeProductByProductNullResult() throws BusinessException {
        final IStatistiqueService statistiqueService = new StatistiqueService();
        final Long productId = 12765L;
        final Pagination pagination = new Pagination(1, 1);

        new Expectations() {

            {
                Deencapsulation.setField(statistiqueService, statistiqueDAO);
                Deencapsulation.setField(statistiqueService, "lastExchangeProductBoToMsgConverter", lastExchangeProductBoToMsgConverter);

                statistiqueDAO.getLastExchangeProductOffAProduct(productId, pagination);
                returns(null);

                lastExchangeProductBoToMsgConverter.convert((List) null);
                returns(null);
            }
        };

        List<LastExchangeProductMsg> bestRatedProductResultMsgs = statistiqueService.getLastExchangeProductByProduct(productId, pagination);
        Assert.assertEquals(null, bestRatedProductResultMsgs);
    }
}
