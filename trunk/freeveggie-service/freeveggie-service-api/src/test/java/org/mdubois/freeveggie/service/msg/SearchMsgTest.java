package org.mdubois.freeveggie.service.msg;

import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class SearchMsgTest extends MessageTest<SearchMsg> {

    public SearchMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        SearchMsg instance = new SearchMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        SearchMsg instance = new SearchMsg();
        instance.setLatitudeUp(1.0);
        instance.setLongitudeDown(1.0);
        instance.setLongitudeUp(1.0);
        instance.setRefProductId(1);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        SearchMsg instance = new SearchMsg();
        instance.setLatitudeDown(1.0);
        instance.setLongitudeDown(1.0);
        instance.setLongitudeUp(1.0);
        instance.setRefProductId(1);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        SearchMsg instance = new SearchMsg();
        instance.setLatitudeDown(1.0);
        instance.setLatitudeUp(1.0);
        instance.setLongitudeDown(1.0);
        instance.setRefProductId(1);
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        SearchMsg instance = new SearchMsg();
        instance.setLatitudeDown(1.0);
        instance.setLatitudeUp(1.0);
        instance.setLongitudeDown(1.0);
        instance.setLongitudeUp(1.0);
        instance.setRefProductId(1);
        instance.validate();
    }

    @Override
    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        return null;
    }
}
