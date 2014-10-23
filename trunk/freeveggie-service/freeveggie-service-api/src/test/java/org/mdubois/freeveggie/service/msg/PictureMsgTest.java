package org.mdubois.freeveggie.service.msg;

import java.util.Map;
import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;
import org.mdubois.freeveggie.framework.test.MessageTest;

/**
 *
 * @author mdubois
 */
public class PictureMsgTest extends MessageTest<PictureMsg> {

    public PictureMsgTest() {
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        PictureMsg instance = new PictureMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        PictureMsg instance = new PictureMsg();
        instance.setObjId(10L);
        instance.setMimeType("12");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        PictureMsg instance = new PictureMsg();
        instance.setObjId(10L);
        instance.setPicture("address".getBytes());
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate5() throws MessageValidationException {
        PictureMsg instance = new PictureMsg();
        instance.setMimeType("12");
        instance.setPicture("address".getBytes());
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate() throws MessageValidationException {
        PictureMsg instance = new PictureMsg();
        instance.setObjId(10L);
        instance.setMimeType("12");
        instance.setPicture("address".getBytes());
        instance.validate();
    }

    protected Map<Class, Object> getMessageAndBusinessObjectUsed() {
        return null;
    }
}
