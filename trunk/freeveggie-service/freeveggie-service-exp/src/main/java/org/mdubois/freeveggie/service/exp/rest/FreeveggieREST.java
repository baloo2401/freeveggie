package org.mdubois.freeveggie.service.exp.rest;

import org.mdubois.freeveggie.framework.security.ContextMsg;
import org.mdubois.freeveggie.framework.security.UserContext;

public class FreeveggieREST {

	protected static ContextMsg createContext(final Long pUserId){
		ContextMsg contextMsg = new ContextMsg();
		contextMsg.setUser(new UserContext());
		contextMsg.getUser().setId(pUserId);
		return contextMsg;
	}
}
