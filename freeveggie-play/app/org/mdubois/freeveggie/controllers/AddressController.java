package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import java.io.IOException;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.AddressForm;
import org.mdubois.freeveggie.service.msg.AddressMsg;



import play.data.Form;
import play.mvc.Result;

@Authenticated
public class AddressController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/address";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getAddressById(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getUserHomeAddress() throws JsonGenerationException, JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/user/%1$s", context());
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getAddressByGarden(final Long pGardenId) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/garden/%1$s", pGardenId);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result updateUserHomeAddress() {
		final Form<AddressForm> addressForm = form(AddressForm.class).bindFromRequest();
		if (addressForm.hasErrors()) {
			return badRequest(addressForm.errorsAsJson());
		} else {
			AddressMsg addressMsg = addressForm.get().getCorrespondingAddressMsg();
			return putAsyncFreeveggieRestService(SERVICE_PATH, addressMsg);
		}
	}
}
