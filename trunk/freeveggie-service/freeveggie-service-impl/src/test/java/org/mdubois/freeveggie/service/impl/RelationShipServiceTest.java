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
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.dao.api.IRelationShipDAO;
import org.mdubois.freeveggie.dao.api.IUserPartialDAO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.framework.exception.BusinessException;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.service.api.IRelationShipService;
import org.mdubois.freeveggie.service.matcher.RelationShipMatcher;
import org.mdubois.freeveggie.service.matcher.TechnicalInformationMatcher;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class RelationShipServiceTest {

    @Mocked
    private Converter<RelationShipMsg, RelationShipBO> relationshipBOConverter;
    @Mocked
    private Converter<RelationShipMsg, RelationShipBO> mockRelationshipBOConverter;
    @Mocked
    private IUserPartialDAO userPartialDAO;
    @Mocked
    private IRelationShipDAO relationShipDAO;
    @Mocked
    private BusinessObjectConverter<RelationShipBO, RelationShipMsg> relationshipMsgToBOConverter;

    @Test(expected = BusinessException.class)
    public void createInexistingSender() throws BusinessException {
        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, userPartialDAO);
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(null);

            }
        };

        Long id = relationShipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createInexistingRecipient() throws BusinessException {
        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, userPartialDAO);
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                userPartialDAO.get(13L);
                returns(null);

                RelationShipBO relationShipBO = new RelationShipBO();
            }
        };

        Long id = relationShipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createExistingRelationship() throws BusinessException {
        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, userPartialDAO);
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                List<RelationShipBO> relationships = new ArrayList<RelationShipBO>();

                RelationShipBO relationShipBO = new RelationShipBO();
                relationShipBO.setSender(sender);
                relationShipBO.setRecipient(recipient);

                relationships.add(relationShipBO);
                relationShipDAO.getRelationShip(sender.getId(), null);
                returns(relationships);

            }
        };

        Long id = relationShipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test(expected = BusinessException.class)
    public void createExistingRelationshipBis() throws BusinessException {
        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, userPartialDAO);
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                List<RelationShipBO> relationships = new ArrayList<RelationShipBO>();

                RelationShipBO relationShipBO = new RelationShipBO();
                relationShipBO.setSender(recipient);
                relationShipBO.setRecipient(sender);

                relationships.add(relationShipBO);
                relationShipDAO.getRelationShip(sender.getId(), null);
                returns(relationships);

            }
        };

        Long id = relationShipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test
    public void create() throws BusinessException {
        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, userPartialDAO);
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipMsgToBOConverter);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                userPartialDAO.get(12L);
                returns(sender);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);
                userPartialDAO.get(13L);
                returns(recipient);

                relationShipDAO.getRelationShip(sender.getId(), null);
                returns(null);

                RelationShipBO relationShipBO = new RelationShipBO();

                relationshipMsgToBOConverter.createNew(relationshipMsg);
                returns(relationShipBO);

                relationShipDAO.save(relationShipBO);
                returns(123L);
            }
        };

        Long id = relationShipService.create(relationshipMsg);

        Assert.assertTrue("Ma", 123L == id);
    }

    @Test
    public void validate() throws BusinessException {

        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);

                RelationShipBO relationShipBO = new RelationShipBO();
                relationShipBO.setSender(sender);
                relationShipBO.setRecipient(recipient);
                relationShipBO.setCreationDate(new Date());
                relationShipBO.setId(111111L);
                relationShipBO.setRequest("request");
                relationShipBO.setStatus(RelationshipStatus.PENDING);
                relationShipBO.setType(RelationshipType.FRIEND);

                relationShipDAO.get(1223L);
                returns(relationShipBO);

                relationShipBO.setStatus(RelationshipStatus.VALIDED);
                relationShipBO.setAnswer("answer");

                relationShipDAO.update(with(relationShipBO, new RelationShipMatcher(relationShipBO)));
            }
        };

        boolean returns = relationShipService.validate(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void validateWithoutExistingRelationship() throws BusinessException {

        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);

                relationShipDAO.get(1223L);
                returns(null);

            }
        };

        boolean returns = relationShipService.validate(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test
    public void refuse() throws BusinessException {

        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);

                PartialUserBO sender = new PartialUserBO();
                sender.setId(12L);

                PartialUserBO recipient = new PartialUserBO();
                recipient.setId(13L);

                RelationShipBO relationShipBO = new RelationShipBO();
                relationShipBO.setSender(sender);
                relationShipBO.setRecipient(recipient);
                relationShipBO.setCreationDate(new Date());
                relationShipBO.setId(111111L);
                relationShipBO.setRequest("request");
                relationShipBO.setStatus(RelationshipStatus.PENDING);
                relationShipBO.setType(RelationshipType.FRIEND);

                relationShipDAO.get(1223L);
                returns(relationShipBO);

                relationShipBO.setStatus(RelationshipStatus.VALIDED);
                relationShipBO.setAnswer("answer");

                relationShipDAO.update(with(relationShipBO, new RelationShipMatcher(relationShipBO)));
            }
        };

        boolean returns = relationShipService.refuse(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void refuseWithoutExistingRelationship() throws BusinessException {

        final IRelationShipService relationShipService = new RelationShipService();
        PartialUserMsg recipient = new PartialUserMsg();
        recipient.setId(13L);

        PartialUserMsg sender = new PartialUserMsg();
        sender.setId(12L);

        final RelationShipMsg relationshipMsg = new RelationShipMsg();
        relationshipMsg.setRecipient(recipient);
        relationshipMsg.setSender(sender);
        relationshipMsg.setRequest("request");
        relationshipMsg.setType(RelationshipType.FRIEND);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);

                relationShipDAO.get(1223L);
                returns(null);

            }
        };

        boolean returns = relationShipService.refuse(1223L, "answer");

        Assert.assertTrue(returns);
    }

    @Test(expected = BusinessException.class)
    public void getGardenByIdExc() throws BusinessException {

        final Long relationShipId = 1L;

        final IRelationShipService relationShipService = new RelationShipService();

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);

                relationShipDAO.get(relationShipId);
                times = 1;
                returns(null);
            }
        };

        relationShipService.getRelationShipById(1L);
    }

    @Test
    public void getGardenById() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationShipService relationShipService = new RelationShipService();
        final RelationShipBO relationshipExpected = new RelationShipBO();
        relationshipExpected.setId(relationshipId);

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, mockRelationshipBOConverter);

                relationShipDAO.get(relationshipId);
                returns(relationshipExpected);

                mockRelationshipBOConverter.convert(relationshipExpected);
            }
        };

        relationShipService.getRelationShipById(1L);
    }

    @Test
    public void getRelationShip() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationShipService relationShipService = new RelationShipService();
        final List<RelationShipBO> relationShipBOs = new ArrayList<RelationShipBO>();
        final RelationShipBO relationshipExpected = new RelationShipBO();
        relationshipExpected.setId(relationshipId);
        relationShipBOs.add(relationshipExpected);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, mockRelationshipBOConverter);

                TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> techInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();
                List<RelationshipStatus> relationshipStatus = new ArrayList<RelationshipStatus>();
                relationshipStatus.add(RelationshipStatus.PENDING);
                relationshipStatus.add(RelationshipStatus.VALIDED);
                QueryCriteria<RelationShipCriteriaColumn> gardenCriteriaPendingAndValidated = new QueryCriteria<RelationShipCriteriaColumn>(RelationShipCriteriaColumn.STATUS, CriteriaOperation.IN, relationshipStatus);

                techInfo.addCriteria(gardenCriteriaPendingAndValidated);

                relationShipDAO.getRelationShip(user, withInstanceLike((techInfo)));
                returns(relationShipBOs);

                mockRelationshipBOConverter.convert(relationShipBOs);
            }
        };

        relationShipService.getRelationShip(user, null);
    }

    @Test
    public void getRelationShipWithStatus() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationShipService relationShipService = new RelationShipService();
        final List<RelationShipBO> relationShipBOs = new ArrayList<RelationShipBO>();
        final RelationShipBO relationshipExpected = new RelationShipBO();
        relationshipExpected.setId(relationshipId);
        relationShipBOs.add(relationshipExpected);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, mockRelationshipBOConverter);

                TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> techInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();
                QueryCriteria<RelationShipCriteriaColumn> gardenCriteria = new QueryCriteria<RelationShipCriteriaColumn>(RelationShipCriteriaColumn.STATUS, CriteriaOperation.EQUAL, RelationshipStatus.REFUSED);
                techInfo.addCriteria(gardenCriteria);

                relationShipDAO.getRelationShip(user, with(techInfo, new TechnicalInformationMatcher(techInfo)));
                returns(relationShipBOs);

                mockRelationshipBOConverter.convert(relationShipBOs);
            }
        };
        TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> techInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();
        QueryCriteria<RelationShipCriteriaColumn> gardenCriteria = new QueryCriteria<RelationShipCriteriaColumn>(RelationShipCriteriaColumn.STATUS, CriteriaOperation.EQUAL, RelationshipStatus.REFUSED);
        techInfo.addCriteria(gardenCriteria);
        relationShipService.getRelationShip(user, techInfo);
    }

    @Test
    public void getRelationShipNullResult() throws BusinessException {

        final Long relationshipId = 1L;
        final IRelationShipService relationShipService = new RelationShipService();
        final RelationShipBO relationshipExpected = new RelationShipBO();
        relationshipExpected.setId(relationshipId);
        final Long user = 12390L;

        new Expectations() {

            {
                Deencapsulation.setField(relationShipService, relationShipDAO);
                Deencapsulation.setField(relationShipService, relationshipBOConverter);

                TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn> techInfo = new TechnicalInformation<RelationShipCriteriaColumn, RelationShipOrderColumn>();
                List<RelationshipStatus> relationshipStatus = new ArrayList<RelationshipStatus>();
                relationshipStatus.add(RelationshipStatus.PENDING);
                relationshipStatus.add(RelationshipStatus.VALIDED);
                QueryCriteria<RelationShipCriteriaColumn> gardenCriteriaPendingAndValidated = new QueryCriteria<RelationShipCriteriaColumn>(RelationShipCriteriaColumn.STATUS, CriteriaOperation.IN, relationshipStatus);

                techInfo.addCriteria(gardenCriteriaPendingAndValidated);

                relationShipDAO.getRelationShip(user, withInstanceLike(techInfo));
                returns(null);

                relationshipBOConverter.convert((List) null);
                returns(null);
            }
        };

        Assert.assertNull(relationShipService.getRelationShip(user, null));
    }
}
