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
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.criteria.RelationshipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationshipDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.RelationshipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationshipService;
import org.mdubois.freeveggie.service.matcher.RelationshipMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class RelationshipServiceTest {

    @Mocked
    private Converter<RelationshipMsg, RelationshipBO> relationshipBOConverter;
    @Mocked
    private Converter<RelationshipMsg, RelationshipBO> mockRelationshipBOConverter;
    @Mocked
    private IUserPartialDAO userPartialDAO;
    @Mocked
    private IRelationshipDAO relationshipDAO;
    @Mocked
    private BusinessObjectConverter<RelationshipBO, RelationshipMsg> relationshipMsgToBOConverter;

    @Test(expected = BusinessException.class)
    public void createInexistingSender() throws BusinessException {
        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, userPartialDAO);
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(null);

            }
        };

        Long id = relationshipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createInexistingRecipient() throws BusinessException {
        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, userPartialDAO);
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                userPartialDAO.get(13L);
                returns(null);

                RelationshipBO relationshipBO = new RelationshipBO();
            }
        };

        Long id = relationshipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createExistingRelationship() throws BusinessException {
        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, userPartialDAO);
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                List<RelationshipBO> relationships = new ArrayList<RelationshipBO>();

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setSender(sender);
                relationshipBO.setRecipient(recipient);

                relationships.add(relationshipBO);
                relationshipDAO.getRelationship(sender.getId(), null);
                returns(relationships);

            }
        };

        Long id = relationshipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createExistingRelationshipBis() throws BusinessException {
        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, userPartialDAO);
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                List<RelationshipBO> relationships = new ArrayList<RelationshipBO>();

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setSender(recipient);
                relationshipBO.setRecipient(sender);

                relationships.add(relationshipBO);
                relationshipDAO.getRelationship(sender.getId(), null);
                returns(relationships);

            }
        };

        Long id = relationshipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test
    public void create() throws BusinessException {
        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, userPartialDAO);
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                relationshipDAO.getRelationship(sender.getId(), null);
                returns(null);

                RelationshipBO relationshipBO = new RelationshipBO();

                relationshipMsgToBOConverter.createNew(relationshipMsg);
                returns(relationshipBO);

                relationshipDAO.save(relationshipBO);
                returns(123L);
            }
        };

        Long id = relationshipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test
    public void validate() throws BusinessException {

        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setSender(sender);
                relationshipBO.setRecipient(recipient);
                relationshipBO.setCreationDate(new Date());
                relationshipBO.setId(111111L);
                relationshipBO.setRequest("request");
                relationshipBO.setStatus(RelationshipStatus.PENDING);
                relationshipBO.setType(RelationshipType.FRIEND);

                relationshipDAO.get(1223L);
                returns(relationshipBO);

                relationshipBO.setStatus(RelationshipStatus.VALIDED);
                relationshipBO.setAnswer("answer");

                relationshipDAO.update(with(relationshipBO, new RelationshipMatcher(relationshipBO)));
            }
        };

        boolean returns = relationshipService.validate(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void validateWithoutExistingRelationship() throws BusinessException {

        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);

                relationshipDAO.get(1223L);
                returns(null);

            }
        };

        boolean returns = relationshipService.validate(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test
    public void refuse() throws BusinessException {

        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);

                RelationshipBO relationshipBO = new RelationshipBO();
                relationshipBO.setSender(sender);
                relationshipBO.setRecipient(recipient);
                relationshipBO.setCreationDate(new Date());
                relationshipBO.setId(111111L);
                relationshipBO.setRequest("request");
                relationshipBO.setStatus(RelationshipStatus.PENDING);
                relationshipBO.setType(RelationshipType.FRIEND);

                relationshipDAO.get(1223L);
                returns(relationshipBO);

                relationshipBO.setStatus(RelationshipStatus.VALIDED);
                relationshipBO.setAnswer("answer");

                relationshipDAO.update(with(relationshipBO, new RelationshipMatcher(relationshipBO)));
            }
        };

        boolean returns = relationshipService.refuse(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void refuseWithoutExistingRelationship() throws BusinessException {

        final IRelationshipService relationshipService = new RelationshipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationshipMsg relationshipMsg = new RelationshipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);

                relationshipDAO.get(1223L);
                returns(null);

            }
        };

        boolean returns = relationshipService.refuse(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void getGardenByIdExc() throws BusinessException {

        final Long relationshipId = 1L;

        final IRelationshipService relationshipService = new RelationshipService();

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);

                relationshipDAO.get(relationshipId);
                times = 1;
                returns(null);
            }
        };

        relationshipService.getRelationshipById(1L);
    }

    @Test
    public void getGardenById() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationshipService relationshipService = new RelationshipService();
        final RelationshipBO relationshipExpected = new RelationshipBO();
        relationshipExpected.setId(relationshipId);

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, mockRelationshipBOConverter);

                relationshipDAO.get(relationshipId);
                returns(relationshipExpected);

                mockRelationshipBOConverter.convert(relationshipExpected);
            }
        };

        relationshipService.getRelationshipById(1L);
    }

    @Test
    public void getRelationship() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationshipService relationshipService = new RelationshipService();
        final List<RelationshipBO> relationshipBOs = new ArrayList<RelationshipBO>();
        final RelationshipBO relationshipExpected = new RelationshipBO();
        relationshipExpected.setId(relationshipId);
        relationshipBOs.add(relationshipExpected);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, mockRelationshipBOConverter);

                TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                List<RelationshipStatus> relationshipStatus = new ArrayList<RelationshipStatus>();
                relationshipStatus.add(RelationshipStatus.PENDING);
                relationshipStatus.add(RelationshipStatus.VALIDED);
                QueryCriteria<RelationshipCriteriaColumn> gardenCriteriaPendingAndValidated = new QueryCriteria<RelationshipCriteriaColumn>(RelationshipCriteriaColumn.STATUS, CriteriaOperation.IN, relationshipStatus);

                techInfo.addCriteria(gardenCriteriaPendingAndValidated);

                relationshipDAO.getRelationship(user, withInstanceLike((techInfo)));
                returns(relationshipBOs);

                mockRelationshipBOConverter.convert(relationshipBOs);
            }
        };

        relationshipService.getRelationship(user, null);
    }

    @Test
    public void getRelationshipWithStatus() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationshipService relationshipService = new RelationshipService();
        final List<RelationshipBO> relationshipBOs = new ArrayList<RelationshipBO>();
        final RelationshipBO relationshipExpected = new RelationshipBO();
        relationshipExpected.setId(relationshipId);
        relationshipBOs.add(relationshipExpected);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, mockRelationshipBOConverter);

                TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                QueryCriteria<RelationshipCriteriaColumn> gardenCriteria = new QueryCriteria<RelationshipCriteriaColumn>(RelationshipCriteriaColumn.STATUS, CriteriaOperation.EQUAL, RelationshipStatus.REFUSED);
                techInfo.addCriteria(gardenCriteria);

                relationshipDAO.getRelationship(user, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(relationshipBOs);

                mockRelationshipBOConverter.convert(relationshipBOs);
            }
        };
        TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
        QueryCriteria<RelationshipCriteriaColumn> gardenCriteria = new QueryCriteria<RelationshipCriteriaColumn>(RelationshipCriteriaColumn.STATUS, CriteriaOperation.EQUAL, RelationshipStatus.REFUSED);
        techInfo.addCriteria(gardenCriteria);
        relationshipService.getRelationship(user, techInfo);
    }

    @Test
    public void getRelationshipNullResult() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationshipService relationshipService = new RelationshipService();
        final RelationshipBO relationshipExpected = new RelationshipBO();
        relationshipExpected.setId(relationshipId);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationshipService, relationshipDAO);
                Deencapsulation.setField(relationshipService, relationshipBOConverter);

                TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn> techInfo = new TechnicalInformation<RelationshipCriteriaColumn, RelationshipOrderColumn>();
                List<RelationshipStatus> relationshipStatus = new ArrayList<RelationshipStatus>();
                relationshipStatus.add(RelationshipStatus.PENDING);
                relationshipStatus.add(RelationshipStatus.VALIDED);
                QueryCriteria<RelationshipCriteriaColumn> gardenCriteriaPendingAndValidated = new QueryCriteria<RelationshipCriteriaColumn>(RelationshipCriteriaColumn.STATUS, CriteriaOperation.IN, relationshipStatus);

                techInfo.addCriteria(gardenCriteriaPendingAndValidated);

                relationshipDAO.getRelationship(user, withInstanceLike(techInfo));
                returns(null);

                relationshipBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(relationshipService.getRelationship(user, null));
    }
}
