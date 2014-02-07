package org.mdubois.freeveggie.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductLikeMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;

import play.mvc.Result;

@Authenticated
public class ProductCommentController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/productcomment";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getProductCommentById(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getProductCommentByUser(final Long id) throws JsonGenerationException, JsonMappingException,
			IOException {
		String feedUrl = SERVICE_PATH + String.format("/user/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getProductCommentByProduct(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result postProductCommentByProduct(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		ProductLikeMsg productLikeMsg = new ProductLikeMsg();
		ProductMsg productMsg = new ProductMsg();
		productMsg.setId(id);
		productLikeMsg.setProduct(productMsg);
		PartialUserMsg userPartial = new PartialUserMsg();
		userPartial.setId(contextId());
		productLikeMsg.setWriter(userPartial);
		return postAsyncFreeveggieRestService(feedUrl, productLikeMsg);
	}

	public static Result deleteProductCommentByProduct(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/product/%1$s", id);
		return deleteAsyncFreeveggieRestService(feedUrl);
	}
}
