package org.mdubois.freeveggie.framework.security.utils;

import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.security.UserRole;
import org.mdubois.freeveggie.framework.utils.UserUtils;

/**
 *
 * @author Mickael Dubois
 */
public class UserUtilsTest {


    /**
     * Test of isUserInRole method, of class UserUtils.
     */
    @Test
    public void testIsUserUser() {
        UserRole pUserRole = UserRole.USER;
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.USER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.MANAGER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.ADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SUPERADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SYSTEM));
    }

    /**
     * Test of isUserInRole method, of class UserUtils.
     */
    @Test
    public void testIsUserManager() {
        UserRole pUserRole = UserRole.MANAGER;
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.USER));
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.MANAGER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.ADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SUPERADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SYSTEM));
    }

    /**
     * Test of isUserInRole method, of class UserUtils.
     */
    @Test
    public void testIsUserAdmin() {
        UserRole pUserRole = UserRole.ADMIN;
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.USER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.MANAGER));
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.ADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SUPERADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SYSTEM));
    }



    /**
     * Test of isUserInRole method, of class UserUtils.
     */
    @Test
    public void testIsUserSuperAdmin() {
        UserRole pUserRole = UserRole.SUPERADMIN;
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.USER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.MANAGER));
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.ADMIN));
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.SUPERADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SYSTEM));
    }


    /**
     * Test of isUserInRole method, of class UserUtils.
     */
    @Test
    public void testIsUserSystem() {
        UserRole pUserRole = UserRole.SYSTEM;
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.USER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.MANAGER));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.ADMIN));
        Assert.assertFalse(UserUtils.isUserInRole(pUserRole, UserRole.SUPERADMIN));
        Assert.assertTrue(UserUtils.isUserInRole(pUserRole, UserRole.SYSTEM));
    }
}
