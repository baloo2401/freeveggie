package org.mdubois.freeveggie.bo.converter;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.util.Date;
import mockit.Expectations;
import mockit.Mocked;
import org.mdubois.freeveggie.Status;
import org.mdubois.freeveggie.bo.GardenBO;
import org.mdubois.freeveggie.framework.bo.converter.BusinessObjectConverter;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.framework.utils.SystemTime;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class GardenMsgToBOConverterTest extends BusinessObjectConverterTest<GardenBO, GardenMsg> {

    private final GardenMsgToBOConverter converter = new GardenMsgToBOConverter();
    private static final Date NOW = new Date();

    @Override
    public GardenBO getNewBusinessObject() {
        GardenBO bo = new GardenBO();
        bo.setDescription("description");
        bo.setCreationDate(NOW);
        bo.setName("name");
        bo.setStatus(Status.CREATED);
        return bo;


    }

    @Override
    public GardenMsg getNewMessage() {
        GardenMsg msg = new GardenMsg();
        msg.setDescription("description");
        msg.setName("name");
        return msg;
    }

    @Override
    public BusinessObjectConverter<GardenBO, GardenMsg> getConverter() {
        return converter;
    }

    @Override
    public Expectations getConvertCallExpectaion() {
        return new Expectations() {

            @Mocked
            @SuppressWarnings("unused")
            private final SystemTime systemTime = null;

            {
                SystemTime.asDate();
                returns(NOW);
            }
        };
    }
}
