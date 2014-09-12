package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.CreateGardenForm;
import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;

import play.data.Form;
import play.libs.F.Function;
import play.libs.WS;
import play.mvc.Result;

@Authenticated
public class GardenController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/garden";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getGardenById(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getGardenByUser(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/user/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result addGarden() throws JsonGenerationException, JsonMappingException, IOException {
		Form<CreateGardenForm> createGardenFormPost = form(CreateGardenForm.class).bindFromRequest();
		if (createGardenFormPost.hasErrors()) {
			return badRequest(createGardenFormPost.errorsAsJson());
		}
		CreateGardenForm createProductForm = createGardenFormPost.get();
		final GardenMsg gardenMsg = createProductForm.getCorrespondingGardenMsg();
		gardenMsg.setOwner(new PartialUserMsg());
		gardenMsg.getOwner().setId(contextId());

		String feedUrl = AddressController.SERVICE_PATH + String.format("/user/%1$s", contextId());
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		return async(holder.get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) throws JsonParseException, JsonMappingException, IOException {

				if (response.getStatus() == 200 || response.getStatus() == 204) {
					ObjectMapper objMapper = new ObjectMapper();
					AddressMsg addressMsg = objMapper.readValue(response.getBody(), AddressMsg.class);
					gardenMsg.setAddress(addressMsg);
					return postAsyncFreeveggieRestService(SERVICE_PATH, gardenMsg);
				} else {
					// Error 500
					flash("errorMessage", "An error occur, we are sorry, please try again later");
					return redirect("/error");
				}
			}

		}));

	}

	public static Result deleteGarden(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		return deleteAsyncFreeveggieRestService(feedUrl);
	}
}
