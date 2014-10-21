package org.mdubois.freeveggie.framework.security;

import org.mdubois.freeveggie.framework.msg.ExtendedSerializable;

/**
 *
 * @author Mickael Dubois
 */
public class UserContext extends ExtendedSerializable {

    /**
     * The user id.
     */
    private Long id;

    /**
     * {@link UserRole}
     */
    private UserRole role;

    /**
     * Get the user id.
     *
     * @return The user id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the user id
     *
     * @param id - The user id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get user role.
     *
     * @return - The user role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Set the user role.
     *
     * @param role - The user role to set
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

}
