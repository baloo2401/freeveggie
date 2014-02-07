package org.mdubois.freeveggie.models;

import play.data.validation.Constraints.Required;

public class AnswerRequestForm {

	/**
	 * The status of the request.
	 */
	@Required
	public String status;

	/**
	 * The message of the answer.
	 */
	@Required
	public String message;

}
