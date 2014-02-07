package org.mdubois.freeveggie.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.Authenticated;

import play.mvc.Result;

@Authenticated
public class ProductLikeController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/productlike";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getProductLikeById(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getProductLikeByUser(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/user/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getProductLikeByProduct(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result postProductLikeByProduct(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		return postAsyncFreeveggieRestService(feedUrl, null);
	}

	public static Result deleteProductLikeByProduct(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		return deleteAsyncFreeveggieRestService(feedUrl);
	}
}
