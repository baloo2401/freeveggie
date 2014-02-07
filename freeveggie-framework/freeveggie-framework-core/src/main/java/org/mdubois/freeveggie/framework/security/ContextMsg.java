package org.mdubois.freeveggie.framework.security;

// <editor-fold defaultstate="collapsed" desc="Imports">
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
     * @return The user context
     */
    public final UserContext getUser() {
        return user;
    }

    /**
     * Set the user context.
     * @param pUserId The user context to set
     */
    public final void setUser(final UserContext pUser) {
        this.user = pUser;
    }
    // </editor-fold>

}
