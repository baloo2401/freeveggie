package org.mdubois.freeveggie.models;

import play.data.validation.Constraints.Required;

public class LogingForm {
	/**
	 * The login of the user.
	 */
	@Required
	public String username;

	/**
	 * The password of the user.
	 */
	@Required
	public String password;

	public LogingForm() {
	}

	public LogingForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}
