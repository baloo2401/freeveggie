package org.mdubois.freeveggie.framework.utils;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.mdubois.freeveggie.framework.security.UserRole;
// </editor-fold>

/**
 * Utility class for user rights.
 * @author Mickael Dubois
 */
public final class UserUtils {

    /**
     * Constructor.
     */
    private UserUtils() {
    }

    /**
     * Control that the user as the role ask.
     * @param pRoleUser The user to control
     * @param pRoleExcpected The role to control
     * @return <code>true</code> if the user has the role ask <code>false</code>
     * otherwise
     */
    public static boolean isUserInRole(final UserRole pRoleUser, final UserRole pRoleExcpected) {
        boolean isUserInRole;
        switch (pRoleExcpected) {
            case USER:
                isUserInRole = isUser(pRoleUser);
                break;
            case MANAGER:
                isUserInRole = isManager(pRoleUser);
                break;
            case ADMIN:
                isUserInRole = isAdmin(pRoleUser);
                break;
            case SUPERADMIN:
                isUserInRole = isSuperAdmin(pRoleUser);
                break;
            case SYSTEM:
                isUserInRole = isSystem(pRoleUser);
                break;
            default:
                isUserInRole = false;
                break;
        }
        return isUserInRole;
    }

    /**
     * Control if the user role can be apply to a user.
     * @param pRoleUser - The user role
     * @return <code>true</code> if it is a user or a manager <code>false</code> otherwise
     */
    private static boolean isUser(final UserRole pRoleUser) {
        return UserRole.USER.equals(pRoleUser) || UserRole.MANAGER.equals(pRoleUser);
    }

    /**
     * Control if the manager role can be apply to a user.
     * @param pRoleUser - The user role
     * @return <code>true</code> if it is a manager <code>false</code> otherwise
     */
    private static boolean isManager(final UserRole pRoleUser) {
        return UserRole.MANAGER.equals(pRoleUser);
    }

    /**
     * Control if the user role can be apply to a administrator.
     * @param pRoleUser - The user role
     * @return <code>true</code> if it is a administrator or a super administrator <code>false</code> otherwise
     */
    private static boolean isAdmin(final UserRole pRoleUser) {
        return UserRole.ADMIN.equals(pRoleUser) || UserRole.SUPERADMIN.equals(pRoleUser);
    }

    /**
     * Control if the user role can be apply to a super administrator.
     * @param pRoleUser - The user role
     * @return <code>true</code> if it is a super administrator <code>false</code>
     * otherwise
     */
    private static boolean isSuperAdmin(final UserRole pRoleUser) {
        return UserRole.SUPERADMIN.equals(pRoleUser);
    }

    /**
     * Control if the user role can be apply to a system user.
     * @param pRoleUser - The user role
     * @return <code>true</code> if it is a system user <code>false</code>
     * otherwise
     */
    private static boolean isSystem(final UserRole pRoleUser) {
        return UserRole.SYSTEM.equals(pRoleUser);
    }
}
