package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.mdubois.freeveggie.framework.msg.Message;
import org.mdubois.freeveggie.framework.msg.Required;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 */
public class ContextMsg extends Message {

    // <editor-fold defaultstate="collapsed" desc="Class properties">
    /**
     * The user id.
     */
    @Required
    private UserContext user;

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Properties getters/setters">
    /**
     * Get the user context.
     *
     * @return The user context
     */
    public final UserContext getUser() {
        return user;
    }

    /**
     * Set the user context.
     *
     * @param pUserId The user context to set
     */
    public final void setUser(final UserContext pUser) {
        this.user = pUser;
    }
    // </editor-fold>

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
