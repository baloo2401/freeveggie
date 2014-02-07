package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.ProfilForm;
import org.mdubois.freeveggie.service.msg.ProfileMsg;



import play.data.Form;
import play.mvc.Result;

@Authenticated
public class ProfilController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/profil";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getUserProfil() {
		final String userId = session().get("connected");
		final String feedUrl = SERVICE_PATH + String.format("/%1$s", userId);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result updateUserProfil() {
		final Form<ProfilForm> profilForm = form(ProfilForm.class).bindFromRequest();
		if (profilForm.hasErrors()) {
			return badRequest(profilForm.errorsAsJson());
		} else {
			ProfileMsg profilMsg = profilForm.get().getCorrespondingProfilMsg();
			profilMsg.setId(contextId());
			return putAsyncFreeveggieRestService(SERVICE_PATH, profilMsg);
		}
	}

}
