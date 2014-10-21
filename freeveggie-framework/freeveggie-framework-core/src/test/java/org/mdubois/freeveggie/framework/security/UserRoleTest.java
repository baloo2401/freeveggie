/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mdubois.freeveggie.framework.security;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author mickael
 */
public class UserRoleTest {

    @Test
    public void testValidateFromInt() {
        Assert.assertEquals(UserRole.USER, UserRole.fromInt(1));
        Assert.assertEquals(UserRole.MANAGER, UserRole.fromInt(2));
        Assert.assertEquals(UserRole.ADMIN, UserRole.fromInt(3));
        Assert.assertEquals(UserRole.SUPERADMIN, UserRole.fromInt(4));
        Assert.assertEquals(UserRole.SYSTEM, UserRole.fromInt(5));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(6));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(7));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(8));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(9));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(10));
    }

    @Test
    public void testValidateToInt() {
        Assert.assertEquals(new Integer(1), UserRole.USER.toInt());
        Assert.assertEquals(new Integer(2), UserRole.MANAGER.toInt());
        Assert.assertEquals(new Integer(3), UserRole.ADMIN.toInt());
        Assert.assertEquals(new Integer(4), UserRole.SUPERADMIN.toInt());
        Assert.assertEquals(new Integer(5), UserRole.SYSTEM.toInt());
        Assert.assertEquals(new Integer(99), UserRole.UNKNOWN.toInt());
    }
}
