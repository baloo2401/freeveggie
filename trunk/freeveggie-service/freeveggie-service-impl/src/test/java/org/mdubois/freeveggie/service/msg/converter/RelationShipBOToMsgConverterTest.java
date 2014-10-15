package org.mdubois.freeveggie.service.msg.converter;

import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.RelationshipStatus;
import org.mdubois.freeveggie.RelationshipType;
import org.mdubois.freeveggie.bo.PartialUserBO;
import org.mdubois.freeveggie.bo.RelationShipBO;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.RelationShipMsg;
import org.unitils.reflectionassert.ReflectionAssert;

/**
 *
 * @author Mickael Dubois
 */
public class RelationShipBOToMsgConverterTest extends AbstractConverterTest<RelationShipMsg, RelationShipBO> {

    @Mocked
    private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final RelationShipBO relationShipBO = getBusinessObject();
        RelationShipMsg expectedResult = getExpectedMessage();
        final RelationShipBOToMsgConverter converter = getConverter();
        final PartialUserBO partialUserBO = new PartialUserBO();

        relationShipBO.setRecipient(partialUserBO);
        relationShipBO.setSender(partialUserBO);

        new Expectations() {

            {
                Deencapsulation.setField(converter, "partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockPartialUserBOToMsgConverter.convert(relationShipBO.getSender());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(relationShipBO.getRecipient());
                returns(null);

            }
        };

        RelationShipMsg result = converter.convert(relationShipBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public RelationShipMsg getExpectedMessage() {
        RelationShipMsg expResult = new RelationShipMsg();
        expResult.setId(10L);
        expResult.setAnswer("answer");
        expResult.setCreationDate(NOW);
        expResult.setRequest("request");
        expResult.setStatus(RelationshipStatus.PENDING);
        expResult.setType(RelationshipType.FRIEND);

        return expResult;
    }

    @Override
    public RelationShipBO getBusinessObject() {
        RelationShipBO pRelationShipBO = new RelationShipBO();
        pRelationShipBO.setId(10L);
        pRelationShipBO.setAnswer("answer");
        pRelationShipBO.setCreationDate(NOW);
        pRelationShipBO.setRequest("request");
        pRelationShipBO.setStatus(RelationshipStatus.PENDING);
        pRelationShipBO.setType(RelationshipType.FRIEND);

        return pRelationShipBO;
    }

    @Override
    public RelationShipBOToMsgConverter getConverter() {
        return new RelationShipBOToMsgConverter();
    }
}
