package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationshipBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationshipMsg;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class RelationshipBOToMsgConverterTest extends AbstractConverterTest<RelationshipMsg, RelationshipBO> {

    @Mocked
    private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final RelationshipBO relationshipBO = getBusinessObject();
        RelationshipMsg expectedResult = getExpectedMessage();
        final RelationshipBOToMsgConverter converter = getConverter();
        final PartialUserBO partialUserBO = new PartialUserBO();

        relationshipBO.setRecipient(partialUserBO);
        relationshipBO.setSender(partialUserBO);

        new Expectations() {

            {
                Deencapsulation.setField(converter, "partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockPartialUserBOToMsgConverter.convert(relationshipBO.getSender());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(relationshipBO.getRecipient());
                returns(null);

            }
        };

        RelationshipMsg result = converter.convert(relationshipBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public RelationshipMsg getExpectedMessage() {
        RelationshipMsg expResult = new RelationshipMsg();
        expResult.setId(10L);
        expResult.setAnswer("answer");
        expResult.setCreationDate(NOW);
        expResult.setRequest("request");
        expResult.setStatus(RelationshipStatus.PENDING);
        expResult.setType(RelationshipType.FRIEND);

        return expResult;
    }

    @Override
    public RelationshipBO getBusinessObject() {
        RelationshipBO pRelationshipBO = new RelationshipBO();
        pRelationshipBO.setId(10L);
        pRelationshipBO.setAnswer("answer");
        pRelationshipBO.setCreationDate(NOW);
        pRelationshipBO.setRequest("request");
        pRelationshipBO.setStatus(RelationshipStatus.PENDING);
        pRelationshipBO.setType(RelationshipType.FRIEND);

        return pRelationshipBO;
    }

    @Override
    public RelationshipBOToMsgConverter getConverter() {
        return new RelationshipBOToMsgConverter();
    }
}
