package org.mdubois.freeveggie.service.impl;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenLikeBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
import org.mdubois.freeveggie.dao.api.IGardenLikeDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.service.matcher.GardenLikeBOMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.GardenLikeMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
@SuppressWarnings("unchecked")
@RunWith(JMockit.class)
public class GardenLikeServiceTest {

    /**
     * {@link Criteria}
     */
    private static QueryCriteria<GardenLikeCriteriaColumn> criteriaStatusEqualSetted = new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.SETTED);

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    // <editor-fold defaultstate="collapsed" desc="Unlike test">
    /**
     * Test we get an exception when we try to unlike that doesn't exist.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unlikeNotExit() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);

                mockGardenLikeDAO.get(pLikeId);
                repeats(1);
                returns(null);
            }
        };

        gardenService.unlike(pLikeId);
    }

    /**
     * Test unlike works
     *
     * @throws BusinessException
     */
    @Test
    public void unlikeStatusSetted() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                mockGardenLikeDAO.get(pLikeId);
                gardenLikeBO.setId(pLikeId);
                gardenLikeBO.setCreationDate(new Date());
                gardenLikeBO.setGarden(gardenBO);
                gardenLikeBO.setWriter(userBO);
                gardenLikeBO.setStatus(EvaluationStatus.SETTED);
                repeats(1);
                returns(gardenLikeBO);

                GardenLikeBO newGardenLikeBO = new GardenLikeBO();
                newGardenLikeBO.setId(pLikeId);
                newGardenLikeBO.setCreationDate(new Date());
                newGardenLikeBO.setGarden(gardenBO);
                newGardenLikeBO.setWriter(userBO);
                newGardenLikeBO.setStatus(EvaluationStatus.REMOVED);

                mockGardenLikeDAO.save(with(new GardenLikeBOMatcher(newGardenLikeBO)));
            }
        };

        gardenService.unlike(pLikeId);
    }

    /**
     * Test that we have an exception if we try to unlike a removed one.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unlikeStatusArchived() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pLikeId = 1L;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(1L);

                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                mockGardenLikeDAO.get(pLikeId);
                gardenLikeBO.setId(pLikeId);
                gardenLikeBO.setCreationDate(new Date());
                gardenLikeBO.setGarden(gardenBO);
                gardenLikeBO.setWriter(userBO);
                gardenLikeBO.setStatus(EvaluationStatus.ARCHIVED);
                repeats(1);
                returns(gardenLikeBO);
                mockGardenLikeBOConverter.convert(gardenLikeBO);
            }
        };

        gardenService.unlike(pLikeId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Like">
    /**
     * Test that that you can like with an unknown user.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeUnknownUser() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pWriterId = 1L;
        final Long pGardenId = 1L;


        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pGardenLikeMsg.setWriter(writer);
        GardenMsg garden = new GardenMsg();
        garden.setId(pGardenId);
        pGardenLikeMsg.setGarden(garden);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;

            {

                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);

                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(null);
            }
        };

        gardenService.like(pGardenLikeMsg);
    }

    /**
     * Test that that you can like with an unexisting garden.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeUnknownGarden() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pWriterId = 1L;
        final Long pGardenId = 1L;


        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        GardenMsg garden = new GardenMsg();
        garden.setId(pGardenId);
        pGardenLikeMsg.setGarden(garden);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pGardenLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;

            {

                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                PartialUserBO userBO = new PartialUserBO();
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                mockGardenDAO.get(pGardenId);
                returns(null);
            }
        };

        gardenService.like(pGardenLikeMsg);
    }

    /**
     * Conrole that we can like twice the same garden.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeOnExistingLike() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pWriterId = 1L;
        final Long pGardenId = 1L;


        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        GardenMsg garden = new GardenMsg();
        garden.setId(pGardenId);
        pGardenLikeMsg.setGarden(garden);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pGardenLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(pGardenId);
                mockGardenDAO.get(pGardenId);
                returns(gardenBO);

                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                mockGardenLikeDAO.getGardenLikeByUserAndGarden(pWriterId, pGardenId);
                gardenLikeBO.setId(1L);
                gardenLikeBO.setStatus(EvaluationStatus.ARCHIVED);
                gardenLikeBO.setWriter(userBO);
                gardenLikeBO.setGarden(gardenBO);
                returns(gardenLikeBO);
                mockGardenLikeBOConverter.convert(gardenLikeBO);
            }
        };

        gardenService.like(pGardenLikeMsg);
    }

    /**
     * Control that we can like it's own garden
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void likeOnOwnGarden() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pWriterId = 1L;
        final Long pGardenId = 1L;


        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        GardenMsg garden = new GardenMsg();
        garden.setId(pGardenId);
        pGardenLikeMsg.setGarden(garden);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pGardenLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            {

                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(pGardenId);
                gardenBO.setOwner(userBO);
                mockGardenDAO.get(pGardenId);
                returns(gardenBO);

            }
        };

        gardenService.like(pGardenLikeMsg);
    }

    /**
     * Control that like works
     *
     * @throws BusinessException
     */
    @Test
    public void like() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pWriterId = 1L;
        final Long pGardenId = 1L;


        final GardenLikeMsg pGardenLikeMsg = new GardenLikeMsg();
        GardenMsg garden = new GardenMsg();
        garden.setId(pGardenId);
        pGardenLikeMsg.setGarden(garden);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(pWriterId);
        pGardenLikeMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;
            @Mocked
            private BusinessObjectConverter<GardenLikeBO,GardenLikeMsg> mockGardenLikeMsgConverter;
            {

                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeMsgConverter", mockGardenLikeMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(pWriterId);
                mockUserPartialDAO.get(pWriterId);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(pGardenId);
                mockGardenDAO.get(pGardenId);
                returns(gardenBO);

                mockGardenLikeDAO.getGardenLikeByUserAndGarden(pWriterId, pGardenId);
                returns(null);

                GardenLikeBO newGardenLikeBO = new GardenLikeBO();
                newGardenLikeBO.setStatus(EvaluationStatus.SETTED);
                newGardenLikeBO.setWriter(userBO);
                newGardenLikeBO.setGarden(gardenBO);


                //TODO MDU : control cration date

                mockGardenLikeMsgConverter.createNew(pGardenLikeMsg);
                returns(newGardenLikeBO);

                mockGardenLikeDAO.save(newGardenLikeBO);
            }
        };

        gardenService.like(pGardenLikeMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get Garden Like test">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getGardenLikeUserNotExit() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO gardenLikeDAO;
            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> gardenLikeBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", gardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", gardenLikeBOConverter);

                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                gardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                repeats(1);
                returns(null);

                gardenLikeBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(gardenService.getGardenLike(pGardenId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithNullTechnicalInformation() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithEmptyCriteria() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithCriteriaWithStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithCriteriaButNotStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {
                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithPagination() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;
            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);


            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWithOrder() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<GardenLikeOrderColumn>(GardenLikeOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLike(pGardenId, pTechnicalInformation);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Get garden like write test">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getGardenLikeWriteUserNotExist() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;
            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> gardenLikeBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", gardenLikeBOConverter);


                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                repeats(1);
                returns(null);

                gardenLikeBOConverter.convert((List)null);
                returns(null);

            }
        };

        Assert.assertNull(gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithNullTechnicalInformation() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithEmptyCriteria() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();

        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
                List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithCriteriaWithStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithCriteriaButNotStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithPagination() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenLikeWriteWithOrder() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenLikeCriteriaColumn, GardenLikeOrderColumn>();
        List<QueryCriteria<GardenLikeCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenLikeCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenLikeCriteriaColumn>(GardenLikeCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<GardenLikeOrderColumn>(GardenLikeOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IGardenLikeDAO mockGardenLikeDAO;

            @Mocked
            private Converter<GardenLikeMsg, GardenLikeBO> mockGardenLikeBOConverter;
            {

                Deencapsulation.setField(gardenService, "gardenLikeDAO", mockGardenLikeDAO);
                Deencapsulation.setField(gardenService, "gardenLikeBOConverter", mockGardenLikeBOConverter);

                List<GardenLikeBO> gardenLikeBOs = new ArrayList<GardenLikeBO>();
                GardenLikeBO gardenLikeBO = new GardenLikeBO();
                gardenLikeBOs.add(gardenLikeBO);
                mockGardenLikeDAO.getGardenLikeByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenLikeBOs);
                mockGardenLikeBOConverter.convert(gardenLikeBOs);
            }
        };

        gardenService.getGardenLikeWrite(pUserWriterId, pTechnicalInformation);
    }
    // </editor-fold>
}
