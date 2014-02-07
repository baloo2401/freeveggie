package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mdubois.freeveggie.models.ChangePasswordForm;
import org.mdubois.freeveggie.models.LogingForm;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

import play.data.Form;
import play.libs.F.Function;
import play.libs.WS;
import play.mvc.Result;
import views.html.login;

public class SecurityController extends FreeveggieController {

	private static final DateFormat SDF = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	private static final String SERVICE_PATH = getFreeveggieEndPointURL() + "/security";

	/**
	 * Display a blank form.
	 */
	public static Result blank() {
		if (context() != null) {
			return redirect("/search");
		}
		return ok(login.render());
	}

	public static Result index() {
		return redirect("/login");
	}

	public static Result disconnect() {
		session().clear();
		return redirect("login");
	}

	public static Result connect() {
		final Form<LogingForm> loginFormPost = form(LogingForm.class).bindFromRequest();
		if (loginFormPost.hasErrors()) {
			return badRequest("Username and/or password not valid!");
		}
		final LogingForm loginForm = loginFormPost.get();

		WS.WSRequestHolder holder = WS.url(SERVICE_PATH + "/controlPassword");
		holder.setQueryParameter("login", loginForm.username);
		holder.setQueryParameter("password", loginForm.password);
		return async(holder.get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) {
				if (response.getStatus() == 200) {
					try {

						ObjectMapper objMapper = new ObjectMapper();
						String controlPasswordServiceCallRsponse = response.getBody();

						UserMsg userMsg = objMapper.readValue(controlPasswordServiceCallRsponse, UserMsg.class);
						session("connected", userMsg.getId().toString());
						session("connectedName", userMsg.getFirstname() + " " + userMsg.getLastname());
						session("lastConnexion", SDF.format(userMsg.getLastConnexion()));
						session("lastActionTime", new Long(new Date().getTime()).toString());
						return redirect("/search");
					} catch (IOException e) {
						return redirect("/error");
					}
				} else if (response.getStatus() == 400) {
					return badRequest(response.getBody());
				} else {
					// Error 500
					flash("errorMessage", "An error occur, we are sorry, please try again later");
					return redirect("/error");
				}
			}
		}).recover(manageCallException()));
	}

	public static Result changePassword() throws JsonGenerationException, JsonMappingException, IOException {
		Form<ChangePasswordForm> changePasswordForm = form(ChangePasswordForm.class).bindFromRequest();
		if (changePasswordForm.hasErrors()) {
			return badRequest(changePasswordForm.errorsAsJson());
		} else {
			ChangePasswordMsg changePasswordMsg = changePasswordForm.get().getCorrespondingChangePasswordMsg();
			changePasswordMsg.setUserId(contextId());
			return postAsyncFreeveggieRestService(SERVICE_PATH + "/changePassword", changePasswordMsg);
		}
	}
}
