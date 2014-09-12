package org.mdubois.freeveggie.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.RequestStatus;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.framework.play.FreeveggieMapper;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;



import play.libs.F.Function;
import play.libs.WS;
import play.mvc.Result;

@Authenticated
public class RelationshiptRequestController extends FreeveggieController {

	/**
	 * Name resource service.
	 */
	private static final String WEB_SERVICE_PATH = "/productrequest";

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

	public static Result getProductRequestById(final Long productRequestId) {
		String feedUrl = SERVICE_PATH + String.format("/%1$s/%2$s", 1, productRequestId);
		return async(WS.url(feedUrl).get().map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response response) {
				return ok(response.getBody());
			}
		}));
	}

	public static Result getProductsRequestReceived(final String pRestriction, final String pOrder, final String pWay,
			final Integer pPage) {

		String feedUrl = SERVICE_PATH + String.format("/receive/%1$s", context());
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		if (!"".equals(pRestriction)) {
			TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
			RequestStatus requestStatus = RequestStatus.valueOf(pRestriction);
			QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
					ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL, requestStatus);
			pTech.addCriteria(statusCriteria);
			holder.setQueryParameter("technicalInformation", FreeveggieMapper.writeAsString(pTech));
		}
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

	public static Result getProductsRequestSend(final String pRestriction, final String pOrder, final String pWay,
			final Integer pPage) {
		String feedUrl = SERVICE_PATH + String.format("/send/%1$s", context());
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setHeader("userId", context());

		if (!"".equals(pRestriction)) {
			TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn> pTech = new TechnicalInformation<ProductRequestCriteriaColumn, ProductRequestOrderColumn>();
			RequestStatus requestStatus = RequestStatus.valueOf(pRestriction);
			QueryCriteria<ProductRequestCriteriaColumn> statusCriteria = new QueryCriteria<ProductRequestCriteriaColumn>(
					ProductRequestCriteriaColumn.STATUS, CriteriaOperation.EQUAL, requestStatus);
			pTech.addCriteria(statusCriteria);
			holder.setQueryParameter("technicalInformation", FreeveggieMapper.writeAsString(pTech));
		}
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

	public static Result getProductsRequestByGarden(final Long id) throws JsonGenerationException,
			JsonMappingException, IOException {
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
