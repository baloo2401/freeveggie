/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.service.msg;

import org.junit.Test;
import org.mdubois.freeveggie.framework.exception.MessageValidationException;

/**
 *
 * @author mickael
 */
public class ChangePasswordMsgTest {

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate1() throws MessageValidationException {
        ChangePasswordMsg instance = new ChangePasswordMsg();
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate2() throws MessageValidationException {
        ChangePasswordMsg instance = new ChangePasswordMsg();
        instance.setNewPassword("pqsss");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test(expected = MessageValidationException.class)
    public void testValidate3() throws MessageValidationException {
        ChangePasswordMsg instance = new ChangePasswordMsg();
        instance.setNewPassword("pqsss");
        instance.setOldPassword("pqsss");
        instance.validate();
    }

    /**
     * Test of message validation.
     */
    @Test
    public void testValidate4() throws MessageValidationException {
        ChangePasswordMsg instance = new ChangePasswordMsg();
        instance.setNewPassword("pqsss");
        instance.setOldPassword("pqsss");
        instance.setUserId(123L);
        instance.validate();
    }
}
