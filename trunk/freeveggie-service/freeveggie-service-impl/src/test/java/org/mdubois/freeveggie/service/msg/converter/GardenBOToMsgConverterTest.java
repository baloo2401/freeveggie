package org.mdubois.freeveggie.service.msg.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.ArrayList;
import java.util.Date;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Test;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.*;
import org.mdubois.freeveggie.framework.msg.converter.Converter;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.unitils.reflectionassert.ReflectionAssert;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenBOToMsgConverterTest extends AbstractConverterTest<GardenMsg, GardenBO> {

    private static final Date NOW = new Date();

    @Test
    public void testConvertFull() {

        final GardenBO gardenBO = getBusinessObject();
        GardenMsg expectedResult = getExpectedMessage();
        final GardenBOToMsgConverter converter = getConverter();
        final PartialUserBO partialUserBO = new PartialUserBO();
        final ArrayList<GardenCommentBO> gardenComments = new ArrayList<GardenCommentBO>();
        gardenComments.add(new GardenCommentBO());
        final AddressBO addressBO = new AddressBO();
        final ArrayList<ProductBO> products = new ArrayList<ProductBO>();
        products.add(new ProductBO());
        final ArrayList<GardenLikeBO> gardenLikes = new ArrayList<GardenLikeBO>();
        gardenLikes.add(new GardenLikeBO());

        gardenBO.setOwner(partialUserBO);
        gardenBO.setComments(gardenComments);
        gardenBO.setLikes(gardenLikes);
        gardenBO.setProducts(products);
        gardenBO.setAddress(addressBO);

        new Expectations() {

            @Mocked
            private Converter<AddressMsg, AddressBO> mockAddressBOToMsgConverter;

            @Mocked
            private Converter<PartialUserMsg, PartialUserBO> mockPartialUserBOToMsgConverter;

            {
                Deencapsulation.setField(converter,"addressBOToMsgConverter", mockAddressBOToMsgConverter);
                Deencapsulation.setField(converter,"partialUserBOToMsgConverter", mockPartialUserBOToMsgConverter);

                mockAddressBOToMsgConverter.convert(gardenBO.getAddress());
                returns(null);

                mockPartialUserBOToMsgConverter.convert(gardenBO.getOwner());
                returns(null);

            }
        };

        GardenMsg result = converter.convert(gardenBO);

        ReflectionAssert.assertLenientEquals(expectedResult, result);
    }

    @Override
    public GardenMsg getExpectedMessage() {

        GardenMsg expectedResult = new GardenMsg();
        expectedResult.setId(11L);
        expectedResult.setCreationDate(NOW);
        expectedResult.setDescription("Description");
        expectedResult.setName("name");
        expectedResult.setStatus(Status.CREATED);

        return expectedResult;
    }

    @Override
    public GardenBO getBusinessObject() {
        GardenBO gardenBO = new GardenBO();
        gardenBO.setId(11L);
        gardenBO.setCreationDate(NOW);
        gardenBO.setDescription("Description");
        gardenBO.setName("name");
        gardenBO.setStatus(Status.CREATED);
        return gardenBO;
    }

    @Override
    public GardenBOToMsgConverter getConverter() {
        return new GardenBOToMsgConverter();
    }

}
