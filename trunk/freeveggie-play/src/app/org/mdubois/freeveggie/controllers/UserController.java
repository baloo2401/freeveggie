package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.GeneralInformationForm;
import org.mdubois.freeveggie.service.msg.UserMsg;

import play.data.Form;
import play.libs.WS;
import play.mvc.Result;

@Authenticated
public class UserController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/user";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static final WS.WSRequestHolder WS_REQUEST_HOLDER = WS.url(SERVICE_PATH);

	static {
		WS_REQUEST_HOLDER.setContentType("application/json");
	}

	public static Result getUser() {
		final String userId = session().get("connected");
		final String feedUrl = SERVICE_PATH + String.format("/%1$s", userId);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result updateGeneralInformation() {
		final Form<GeneralInformationForm> generalInformationForm = form(GeneralInformationForm.class)
				.bindFromRequest();
		if (generalInformationForm.hasErrors()) {
			return badRequest(generalInformationForm.errorsAsJson());
		} else {
			UserMsg userMsg = generalInformationForm.get().getCorrespondingUserMsg();
			userMsg.setId(contextId());
			// TODO Maybe change user name in session but only if the call is
			// validated
			session("connectedName", userMsg.getFirstname() + " " + userMsg.getLastname());
			return putAsyncFreeveggieRestService(SERVICE_PATH, userMsg);
		}
	}

}
