package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import org.mdubois.freeveggie.models.CreateAccountForm;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;

import play.data.Form;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Result;

public class AccountController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/security";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result createAccount() {
		final Form<CreateAccountForm> createAccountStepOneForm = form(CreateAccountForm.class).bindFromRequest();
		if (createAccountStepOneForm.hasErrors()) {
			return badRequest(createAccountStepOneForm.errorsAsJson());
		} else {
			String feedUrlIsExistingLogin = SERVICE_PATH + String.format("/isExistingLogin");
			WS.WSRequestHolder holder = WS.url(feedUrlIsExistingLogin);
			holder.setContentType("application/json");
			holder.setQueryParameter("login", createAccountStepOneForm.get().username);
			Response response = holder.get().get();
			if (response.getBody().equalsIgnoreCase("true")) {
				return badRequest("{\"username\":\"This username is already used\"}");
			}

			String feedUrlIsExistingEmail = SERVICE_PATH + String.format("/isExistingEmail");
			holder = WS.url(feedUrlIsExistingEmail);
			holder.setContentType("application/json");
			holder.setQueryParameter("email", createAccountStepOneForm.get().email);
			response = holder.get().get();
			if (response.getBody().equalsIgnoreCase("true")) {
				return badRequest("{\"email\":\"This email is already used\"}");
			}

			final CreateAccountMsg CreateAccountMsg = createAccountStepOneForm.get().getCreateAccountMsg();

			return postAsyncFreeveggieRestService(UserController.SERVICE_PATH, CreateAccountMsg, "/validateaccount");
		}
	}

	/**
	 * The is call to validate a user account using the link given in the email
	 * validation we send him when he create his account.
	 * 
	 * @return The validation message page.
	 */
	public static Result confirmAccount(String pCode) {
		final String feedUrl = UserController.SERVICE_PATH + "/validate";
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setContentType("application/json");
		holder.setQueryParameter("code", pCode);
		Response response = holder.get().get();
		if (response.getBody().equalsIgnoreCase("true")) {
			return ok("{\"result\":\"Success\"}");
		} else {
			return badRequest("{\"result\":\"Failed\"}");
		}
	}

}
