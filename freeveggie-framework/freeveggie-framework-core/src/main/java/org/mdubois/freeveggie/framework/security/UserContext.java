package org.mdubois.freeveggie.framework.security;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 *
 * @author Mickael Dubois
 */
public class UserContext implements Serializable {

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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
