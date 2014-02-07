package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.framework.play.FreeveggieMapper;
import org.mdubois.freeveggie.framework.play.QueryParameter;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.models.AnswerRequestForm;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;

import play.mvc.Result;

@Authenticated
public class ProductRequestController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/productrequest";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getProductRequestById() {
		String feedUrl = SERVICE_PATH + String.format("/%1$s", request().getQueryString("id"));
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getProductsRequestReceived(final String pRestriction, final String pOrder, final String pWay,
			final Integer pPage) {

		String feedUrl = SERVICE_PATH + String.format("/receive/%1$s", context());
		if (!"ALL".equals(pRestriction) && !"".equals(pRestriction)) {
			TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
			RequestStatus requestStatus = RequestStatus.valueOf(pRestriction);
			QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
					ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL, requestStatus);
			pTech.addCriteria(statusCriteria);

			QueryParameter parameter = new QueryParameter("technicalInformation", FreeveggieMapper.writeAsString(pTech));

			return getAsyncFreeveggieRestService(feedUrl, parameter);
		} else {
			return getAsyncFreeveggieRestService(feedUrl);
		}

	}

	public static Result getProductsRequestSend(final String pRestriction, final String pOrder, final String pWay,
			final Integer pPage) {
		String feedUrl = SERVICE_PATH + String.format("/send/%1$s", context());

		if (!"ALL".equals(pRestriction) && !"".equals(pRestriction)) {
			TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
			RequestStatus requestStatus = RequestStatus.valueOf(pRestriction);
			QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
					ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL, requestStatus);
			pTech.addCriteria(statusCriteria);
			QueryParameter parameter = new QueryParameter("technicalInformation", FreeveggieMapper.writeAsString(pTech));

			return getAsyncFreeveggieRestService(feedUrl, parameter);
		} else {
			return getAsyncFreeveggieRestService(feedUrl);
		}
	}

	public static Result getProductsRequestByGarden(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
		String feedUrl = SERVICE_PATH + String.format("/garden/%1$s", id);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result anwserRequest(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
		AnswerRequestForm answerRequestForm = form(AnswerRequestForm.class).bindFromRequest().get();

		String feedUrl = SERVICE_PATH + String.format("/%1$s/%2$s", answerRequestForm.status, id);
		System.out.println(feedUrl);
		return postAsyncFreeveggieRestService(feedUrl, answerRequestForm.message);
	}
}
