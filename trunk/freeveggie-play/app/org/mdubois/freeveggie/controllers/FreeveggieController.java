package org.mdubois.freeveggie.controllers;

import org.mdubois.freeveggie.framework.play.JSONUtils;
import org.mdubois.freeveggie.framework.play.QueryParameter;

import play.Logger;
import play.Play;
import play.api.templates.Html;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.Controller;
import play.mvc.Result;

public abstract class FreeveggieController extends Controller {

	private static final String PROTOCOLE = Play.application().configuration().getString("service.protocol");
	private static final String HOST = Play.application().configuration().getString("service.host");
	private static final String PORT = Play.application().configuration().getString("service.port");
	private static final String CONTEXT = Play.application().configuration().getString("service.context");

	private static final String SERVICE_ADDRESS_LOCATION = PROTOCOLE + "://" + HOST + ":" + PORT + CONTEXT;

	protected static String getFreeveggieEndPointURL() {
		return SERVICE_ADDRESS_LOCATION;
	}

	protected static String context() {
		return session().get("connected");
	}

	protected static Long contextId() {
		return Long.valueOf(session().get("connected"));
	}

	protected static String toJson(Object obj) {
		if (obj != null) {
			return Json.stringify(Json.toJson(obj));
		} else {
			return null;
		}
	}

	private static Function<WS.Response, Result> getFreeveggieRestService(final Html pageToRender) {
		return new Function<WS.Response, Result>() {

			@Override
			public Result apply(Response pReponse) throws Throwable {
				if (Logger.isDebugEnabled()) {
					Logger.debug("Return code (" + pReponse.getStatus() + ") : " + pReponse.getBody());
				}
				if (pReponse.getStatus() >= 200 && pReponse.getStatus() <= 299) {
					if (pageToRender == null) {
						return ok(pReponse.getBody());
					} else {
						return ok(pageToRender);
					}
				} else if (pReponse.getStatus() == 400 && pReponse.getStatus() <= 499) {
					if (Logger.isInfoEnabled()) {
						Logger.info("An error occur while calling freeveggie the back end service return ("
								+ pReponse.getStatus() + ") : " + pReponse.getBody());
					}
					return badRequest(JSONUtils.createJSONError(pReponse));
				} else {
					Logger.error("An error occur while calling freeveggie the back end service return ("
							+ pReponse.getStatus() + ") : " + pReponse.getBody());
					return internalServerError(JSONUtils.createJSONError(pReponse,
							"The server is encountering some issues please try again later"));
				}
			}

		};
	}

	public static AsyncResult getAsyncFreeveggieRestService(final String pULR) {
		WS.WSRequestHolder holder = WS.url(pULR);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return async(holder.get().map(getFreeveggieRestService((Html) null)).recover(manageCallException()));
	}

	public static AsyncResult deleteAsyncFreeveggieRestService(final String pULR) {
		WS.WSRequestHolder holder = WS.url(pULR);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return async(holder.delete().map(getFreeveggieRestService((Html) null)).recover(manageCallException()));
	}

	public static Promise<Result> getFreeveggieRestService(final String pULR) {
		WS.WSRequestHolder holder = WS.url(pULR);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return holder.get().map(getFreeveggieRestService((Html) null)).recover(manageCallException());
	}

	public static AsyncResult getAsyncFreeveggieRestService(final String pULR, final QueryParameter... pParameters) {
		WS.WSRequestHolder holder = WS.url(pULR);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		if (pParameters != null) {
			for (QueryParameter queryParameter : pParameters) {
				holder.setQueryParameter(queryParameter.getKey(), queryParameter.getValue());
			}
		}
		return async(holder.get().map(getFreeveggieRestService((Html) null)).recover(manageCallException()));
	}

	public static AsyncResult getAsyncFreeveggieRestService(final String pULR, final Html pageToRender) {
		WS.WSRequestHolder holder = WS.url(pULR);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return async(holder.get().map(getFreeveggieRestService(pageToRender)).recover(manageCallException()));
	}

	public static Function<Throwable, Result> manageCallException() {
		return new Function<Throwable, Result>() {
			@Override
			public Result apply(Throwable pThrowable) throws Throwable {
				Logger.error("Unable to call freeveggie back end service", pThrowable);
				return internalServerError("The server is encountering some issues please try again later");
			}
		};
	}

	public static Result postAsyncFreeveggieRestService(final String feedUrl, final Object message,
			final String redirectPage) {
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return async(holder.post(toJson(message)).map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response pReponse) {
				if (pReponse.getStatus() >= 200 && pReponse.getStatus() <= 299) {
					if (redirectPage != null) {
						return redirect(redirectPage);
					} else {
						return ok(pReponse.getBody());
					}
				} else if (pReponse.getStatus() >= 400 && pReponse.getStatus() <= 499) {
					if (Logger.isInfoEnabled()) {
						Logger.info("An error occur while calling freeveggie the back end service return ("
								+ pReponse.getStatus() + ") : " + pReponse.getBody());
					}
					return badRequest(JSONUtils.createJSONError(pReponse));
				} else {
					Logger.error("An error occur while calling freeveggie the back end service return ("
							+ pReponse.getStatus() + ") : " + pReponse.getBody());
					return internalServerError(JSONUtils.createJSONError(pReponse,
							"The server is encountering some issues please try again later"));
				}
			}
		}));
	}

	public static Result postAsyncFreeveggieRestService(final String feedUrl, final Object message) {
		return postAsyncFreeveggieRestService(feedUrl, message, null);
	}

	public static Result putAsyncFreeveggieRestService(final String feedUrl, final Object message) {
		WS.WSRequestHolder holder = WS.url(feedUrl);
		holder.setContentType("application/json");
		holder.setHeader("userId", context());
		return async(holder.put(toJson(message)).map(new Function<WS.Response, Result>() {
			@Override
			public Result apply(WS.Response pReponse) {
				if (pReponse.getStatus() >= 200 && pReponse.getStatus() <= 299) {
					return ok(pReponse.getBody());
				} else if (pReponse.getStatus() >= 400 && pReponse.getStatus() <= 499) {
					if (Logger.isInfoEnabled()) {
						Logger.info("An error occur while calling freeveggie the back end service return ("
								+ pReponse.getStatus() + ") : " + pReponse.getBody());
					}
					return badRequest(JSONUtils.createJSONError(pReponse));
				} else {
					Logger.error("An error occur while calling freeveggie the back end service return ("
							+ pReponse.getStatus() + ") : " + pReponse.getBody());
					return internalServerError(JSONUtils.createJSONError(pReponse,
							"The server is encountering some issues please try again later"));
				}
			}
		}));
	}
}
