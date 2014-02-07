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
import org.mdubois.freeveggie.EvaluationNote;
import org.mdubois.freeveggie.EvaluationStatus;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.bo.GardenCommentBO;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IGardenCommentDAO;
import org.mdubois.freeveggie.dao.api.IGardenDAO;
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
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.service.matcher.GardenCommentBOMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.GardenCommentMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
// </editor-fold>

/**
 *
 * @author francishuynh
 */
@RunWith(JMockit.class)
public class GardenCommentServiceTest {

    /**
     * {@link Criteria}
     */
    private static QueryCriteria<GardenCommentCriteriaColumn> criteriaStatusEqualSetted = new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.SETTED);

    // <editor-fold defaultstate="collapsed" desc="Test add Comment">
    /**
     * Test that if the user in parameter does not exist we have an
     * businessException.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentNoUser() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        pGardenCommentMsg.setComment("Comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(1L);
        pGardenCommentMsg.setGarden(garden);
        pGardenCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(122L);
        pGardenCommentMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;

            {
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);

                mockUserPartialDAO.get(122L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.comment(pGardenCommentMsg);
    }

    /**
     * Test that if the garden in parameter does not exist we have an
     * businessException.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentNoGarden() throws BusinessException {

        final GardenService gardenService = new GardenService();


        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        pGardenCommentMsg.setComment("Comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(1L);
        pGardenCommentMsg.setGarden(garden);
        pGardenCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(122L);
        pGardenCommentMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;

            {
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);

                mockUserPartialDAO.get(122L);
                repeats(1);
                returns(new PartialUserBO());

                mockGardenDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.comment(pGardenCommentMsg);
    }

    /**
     * Test that a user can't comment on he's own garden.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentFromTheOwner() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        pGardenCommentMsg.setComment("Comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(1L);
        pGardenCommentMsg.setGarden(garden);
        pGardenCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(2L);
        pGardenCommentMsg.setWriter(writer);

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                gardenBO.setOwner(userBO);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);

            }
        };

        gardenService.comment(pGardenCommentMsg);
    }

    /**
     * Test that nobody can comment twice a garden.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void commentTwice() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        pGardenCommentMsg.setComment("Comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(1L);
        pGardenCommentMsg.setGarden(garden);
        pGardenCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(2L);
        pGardenCommentMsg.setWriter(writer);

        final Date now = new Date();

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);


                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setId(10L);
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setGarden(gardenBO);
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                gardenCommentBO.setWriter(userBO);
                gardenCommentBO.setCreationDate(now);
                mockGardenCommentDAO.getGardenCommentByUserAndGarden(userBO.getId(), gardenBO.getId());
                returns(gardenCommentBO);
            }
        };

        gardenService.comment(pGardenCommentMsg);
    }

    /**
     * Test comment works.
     *
     * @throws BusinessException
     */
    @Test
    public void comment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final GardenCommentMsg pGardenCommentMsg = new GardenCommentMsg();
        pGardenCommentMsg.setComment("Comment");
        GardenMsg garden = new GardenMsg();
        garden.setId(1L);
        pGardenCommentMsg.setGarden(garden);
        pGardenCommentMsg.setNote(EvaluationNote.BAD);
        PartialUserMsg writer = new PartialUserMsg();
        writer.setId(2L);
        pGardenCommentMsg.setWriter(writer);

        final Date now = new Date();

        new Expectations() {

            @Mocked
            private IUserPartialDAO mockUserPartialDAO;
            @Mocked
            private IGardenDAO mockGardenDAO;
            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private BusinessObjectConverter<GardenCommentBO,GardenCommentMsg> mockGardenCommentMsgConverter;

            {
                Deencapsulation.setField(gardenService, "userPartialDAO", mockUserPartialDAO);
                Deencapsulation.setField(gardenService, "gardenDAO", mockGardenDAO);
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentMsgConverter", mockGardenCommentMsgConverter);

                PartialUserBO userBO = new PartialUserBO();
                userBO.setId(2L);
                mockUserPartialDAO.get(2L);
                repeats(1);
                returns(userBO);

                GardenBO gardenBO = new GardenBO();
                gardenBO.setId(1L);
                mockGardenDAO.get(1L);
                repeats(1);
                returns(gardenBO);


                mockGardenCommentDAO.getGardenCommentByUserAndGarden(userBO.getId(), gardenBO.getId());
                returns(null);

                mockGardenCommentMsgConverter.createNew(pGardenCommentMsg);
                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setGarden(gardenBO);
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                gardenCommentBO.setWriter(userBO);
                gardenCommentBO.setCreationDate(now);
                returns(gardenCommentBO);
                mockGardenCommentDAO.save(gardenCommentBO);
            }
        };

        gardenService.comment(pGardenCommentMsg);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Remove Comment">
    /**
     * Test that if we cant remove an unexiting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeCommentNoComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.removeComment(pGardenCommentId);
    }

    /**
     * Test that we can't remove an archive comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void removeCommentArchived() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.removeComment(pGardenCommentId);
    }

    /**
     * That if we try to remove an already removed comment there is no pb and no
     * call to save.
     *
     * @throws BusinessException
     */
    @Test
    public void removeCommentAlreadyRemoved() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.removeComment(pGardenCommentId);
    }

    /**
     * Test that remove works.
     *
     * @throws BusinessException
     */
    @Test
    public void removeComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setId(1L);
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                gardenCommentBO.setWriter(writer);
                gardenCommentBO.setGarden(garden);
                gardenCommentBO.setCreationDate(now);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);

                GardenCommentBO gardenCommentBOExpected = new GardenCommentBO();
                gardenCommentBOExpected.setId(1L);
                gardenCommentBOExpected.setComment("Comment");
                gardenCommentBOExpected.setNote(EvaluationNote.BAD);
                gardenCommentBOExpected.setStatus(EvaluationStatus.REMOVED);
                gardenCommentBOExpected.setWriter(writer);
                gardenCommentBOExpected.setGarden(garden);
                gardenCommentBOExpected.setCreationDate(now);

                mockGardenCommentDAO.update(with(new GardenCommentBOMatcher(gardenCommentBOExpected)));
            }
        };

        gardenService.removeComment(pGardenCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Reactivate Comment">
    /**
     * Test that if we cant reactive an unexiting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateCommentNoComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.reactivateComment(pGardenCommentId);
    }

    /**
     * Control that if we can't reactived an archive comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void reactivateCommentArchived() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.reactivateComment(pGardenCommentId);
    }

    /**
     * Control that we can reactivated an already activate comment.
     *
     * @throws BusinessException
     */
    @Test
    public void reactivateCommentAlreadyActivated() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.reactivateComment(pGardenCommentId);
    }

    /**
     * control that reactivate works.
     *
     * @throws BusinessException
     */
    @Test
    public void reactivateComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setId(1L);
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.REMOVED);
                gardenCommentBO.setWriter(writer);
                gardenCommentBO.setGarden(garden);
                gardenCommentBO.setCreationDate(now);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);

                GardenCommentBO gardenCommentBOExpected = new GardenCommentBO();
                gardenCommentBOExpected.setId(1L);
                gardenCommentBOExpected.setComment("Comment");
                gardenCommentBOExpected.setNote(EvaluationNote.BAD);
                gardenCommentBOExpected.setStatus(EvaluationStatus.SETTED);
                gardenCommentBOExpected.setWriter(writer);
                gardenCommentBOExpected.setGarden(garden);
                gardenCommentBOExpected.setCreationDate(now);

                mockGardenCommentDAO.update(with(new GardenCommentBOMatcher(gardenCommentBOExpected)));
            }
        };

        gardenService.reactivateComment(pGardenCommentId);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Archive Comment">
    /**
     * Control that we can't archive a unexisting comment
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveNoComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.archiveComment(pGardenCommentId);
    }

    /**
     * Control that we can't archive a deleted comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void archiveCommentDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.archiveComment(pGardenCommentId);
    }

    /**
     * Control that we can archive an already archive comment without using
     * save.
     *
     * @throws BusinessException
     */
    @Test
    public void archiveCommentAlreadyArchive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.archiveComment(pGardenCommentId);
    }

    /**
     * Control that archive works.
     *
     * @throws BusinessException
     */
    @Test
    public void archive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {


                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setId(1L);
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                gardenCommentBO.setWriter(writer);
                gardenCommentBO.setGarden(garden);
                gardenCommentBO.setCreationDate(now);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);

                GardenCommentBO gardenCommentBOExpected = new GardenCommentBO();
                gardenCommentBOExpected.setId(1L);
                gardenCommentBOExpected.setComment("Comment");
                gardenCommentBOExpected.setNote(EvaluationNote.BAD);
                gardenCommentBOExpected.setStatus(EvaluationStatus.ARCHIVED);
                gardenCommentBOExpected.setWriter(writer);
                gardenCommentBOExpected.setGarden(garden);
                gardenCommentBOExpected.setCreationDate(now);

                mockGardenCommentDAO.update(with(new GardenCommentBOMatcher(gardenCommentBOExpected)));
            }
        };

        gardenService.archiveComment(pGardenCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Unarchive Comment">
    /**
     * Control that we can't unarchive an unexisting comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveNoComment() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(null);
            }
        };

        gardenService.unarchiveComment(pGardenCommentId);
    }

    /**
     * Control that we can unarchive a deleted comment.
     *
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void unarchiveCommentDeleted() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.REMOVED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.unarchiveComment(pGardenCommentId);
    }

    /**
     * Control that we don't call save when we unarchive an unarchived comment.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchiveCommentAlreadyUnarchive() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setStatus(EvaluationStatus.SETTED);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);
            }
        };

        gardenService.unarchiveComment(pGardenCommentId);
    }

    /**
     * Control that unarchive works.
     *
     * @throws BusinessException
     */
    @Test
    public void unarchive() throws BusinessException {
        final GardenService gardenService = new GardenService();

        final Long pGardenCommentId = 1L;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);

                PartialUserBO writer = new PartialUserBO();
                writer.setId(1L);
                GardenBO garden = new GardenBO();
                garden.setId(1L);

                Date now = new Date();

                GardenCommentBO gardenCommentBO = new GardenCommentBO();
                gardenCommentBO.setId(1L);
                gardenCommentBO.setComment("Comment");
                gardenCommentBO.setNote(EvaluationNote.BAD);
                gardenCommentBO.setStatus(EvaluationStatus.ARCHIVED);
                gardenCommentBO.setWriter(writer);
                gardenCommentBO.setGarden(garden);
                gardenCommentBO.setCreationDate(now);
                mockGardenCommentDAO.get(1L);
                repeats(1);
                returns(gardenCommentBO);

                GardenCommentBO gardenCommentBOExpected = new GardenCommentBO();
                gardenCommentBOExpected.setId(1L);
                gardenCommentBOExpected.setComment("Comment");
                gardenCommentBOExpected.setNote(EvaluationNote.BAD);
                gardenCommentBOExpected.setStatus(EvaluationStatus.SETTED);
                gardenCommentBOExpected.setWriter(writer);
                gardenCommentBOExpected.setGarden(garden);
                gardenCommentBOExpected.setCreationDate(now);

                mockGardenCommentDAO.update(with(new GardenCommentBOMatcher(gardenCommentBOExpected)));
            }
        };

        gardenService.unarchiveComment(pGardenCommentId);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test on getGardenCommentByGarden">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getGardenCommentGardenNotExit() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> gardenCommentBOConverter;
            @Mocked
            private IGardenCommentDAO gardenCommentDAO;
            {

                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", gardenCommentBOConverter);
                Deencapsulation.setField(gardenService, "gardenCommentDAO", gardenCommentDAO);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                gardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                gardenCommentBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(gardenService.getGardenComment(pGardenId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithNullTechnicalInformation() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithEmptyCriteria() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {


                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithCriteriaWithStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);




                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithCriteriaButNotStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);


                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithPagination() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWithOrder() throws BusinessException {

        final GardenService gardenService = new GardenService();

        final Long pGardenId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<GardenCommentOrderColumn>(GardenCommentOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByGarden(pGardenId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);

            }
        };

        gardenService.getGardenComment(pGardenId, pTechnicalInformation);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Test on getGardenCommentByWriter">
    /**
     * This test control that, if the user id given in parameter is not
     * corresponding to a valid user database id, the service throw a business
     * exception
     *
     * @throws BusinessException Expected
     */
    @Test
    public void getGardenCommentWriteUserNotExit() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

             @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> gardenCommentBOConverter;
            @Mocked
            private IGardenCommentDAO gardenCommentDAO;
            {

                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", gardenCommentBOConverter);
                Deencapsulation.setField(gardenService, "gardenCommentDAO", gardenCommentDAO);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                gardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(null);

                gardenCommentBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation));
    }

    /**
     * This test that if the service is call with a null technical information
     * the underlying DAO is call with a criteria having the status equal to
     * setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithNullTechnicalInformation() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = null;

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with an empty criteria the
     * underlying DAO is call with a criteria having the status equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithEmptyCriteria() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();

        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> lTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
                List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
                lTechnicalInformation.setCriterias(criterias);
                lTechnicalInformation.getCriterias().add(criteriaStatusEqualSetted);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(lTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithCriteriaWithStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.STATUS, CriteriaOperation.EQUAL, EvaluationStatus.ARCHIVED));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that if the service is call with a criteria that dont have a
     * status, the underlying DAO is call with a criteria having the status
     * equal to setted.
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithCriteriaButNotStatus() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the pagination is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithPagination() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {

                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }

    /**
     * This test that the result order is propage to the underlying DAO
     *
     * @throws BusinessException
     */
    @Test
    public void getGardenCommentWriteWithOrder() throws BusinessException {
        final GardenService gardenService = new GardenService();


        final Long pUserWriterId = 1L;
        final TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn> pTechnicalInformation = new TechnicalInformation<GardenCommentCriteriaColumn, GardenCommentOrderColumn>();
        List<QueryCriteria<GardenCommentCriteriaColumn>> criterias = new ArrayList<QueryCriteria<GardenCommentCriteriaColumn>>();
        final Date now = new Date();
        criterias.add(new QueryCriteria<GardenCommentCriteriaColumn>(GardenCommentCriteriaColumn.CREATION_DATE, CriteriaOperation.EQUAL, now));
        pTechnicalInformation.setCriterias(criterias);
        pTechnicalInformation.setPagination(new Pagination(100, 1));
        pTechnicalInformation.setOrder(new ResultOrder<GardenCommentOrderColumn>(GardenCommentOrderColumn.CREATION_DATE, OrderWay.DESC));
        new Expectations() {

            @Mocked
            private IGardenCommentDAO mockGardenCommentDAO;
            @Mocked
            private Converter<GardenCommentMsg, GardenCommentBO> mockGardenCommentBOConverter;

            {
                Deencapsulation.setField(gardenService, "gardenCommentDAO", mockGardenCommentDAO);
                Deencapsulation.setField(gardenService, "gardenCommentBOConverter", mockGardenCommentBOConverter);

                List<GardenCommentBO> gardenCommentBOs = new ArrayList<GardenCommentBO>(1);
                gardenCommentBOs.add(new GardenCommentBO());
                mockGardenCommentDAO.getGardenCommentByWriter(pUserWriterId, with(new TechnicalInformationMatcher(pTechnicalInformation)));
                returns(gardenCommentBOs);
                mockGardenCommentBOConverter.convert(gardenCommentBOs);
            }
        };

        gardenService.getGardenCommentWrite(pUserWriterId, pTechnicalInformation);
    }
    // </editor-fold>
}
