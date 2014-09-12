package org.mdubois.freeveggie.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.Authenticated;



import play.libs.F.Function;
import play.libs.WS;
import play.mvc.Result;

@Authenticated
public class GardenCommentController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/gardencomment";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getGardenCommentById(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		return async(holder.get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) {

				if (response.getStatus() == 200 || response.getStatus() == 204) {
					return ok(response.getBody());
				} else {
					// Error 500
					flash("errorMessage", "An error occur, we are sorry, please try again later");
					return redirect("/error");
				}
			}
		}));
	}

	public static Result getGardenCommentByUser(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/user/%1$s", id);
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		return async(holder.get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) {

				if (response.getStatus() == 200 || response.getStatus() == 204) {
					return ok(response.getBody());
				} else {
					// Error 500
					flash("errorMessage", "An error occur, we are sorry, please try again later");
					return redirect("/error");
				}
			}
		}));
	}

	public static Result getGardenCommentByGarden(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/garden/%1$s", id);
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		return async(holder.get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) {

				if (response.getStatus() == 200 || response.getStatus() == 204) {
					return ok(response.getBody());
				} else {
					// Error 500
					flash("errorMessage", "An error occur, we are sorry, please try again later");
					return redirect("/error");
				}
			}
		}));
	}
}
