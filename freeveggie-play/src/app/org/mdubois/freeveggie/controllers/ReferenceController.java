package org.mdubois.freeveggie.controllers;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.QueryParameter;

import play.cache.Cached;
import play.mvc.Result;

public class ReferenceController extends FreeveggieController {

    /**
     * Name resource service.
     */
    private static final String CURRENT_WEB_SERVICE_PATH = "/reference";

    /**
     * Service endpoint.
     */
    public static final String SERVICE_PATH = getFreeveggieEndPointURL() + CURRENT_WEB_SERVICE_PATH;

    @Cached(key = "ReferenceController.getRefProduct")
    public static Result getRefProduct() {
        String feedUrl = SERVICE_PATH + "/product";
        return getAsyncFreeveggieRestService(feedUrl);
    }

    @Cached(key = "ReferenceController.getRefCountry")
    public static Result getRefCountry() throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + "/country";
        QueryParameter queryParameter = new QueryParameter("countryName", request().getQueryString("countryName"));
        return getAsyncFreeveggieRestService(feedUrl, queryParameter);
    }

    @Cached(key = "ReferenceController.getRefCity")
    public static Result getRefCity() throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + "/city";
        QueryParameter queryParameter = new QueryParameter("cityName", request().getQueryString("cityName"));
        return getAsyncFreeveggieRestService(feedUrl, queryParameter);
    }

    @Cached(key = "ReferenceController.getRefCityByZipCode")
    public static Result getRefCityByZipCode() throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + "/zipCode";
        QueryParameter queryParameter = new QueryParameter("zipCode", request().getQueryString("zipCode"));
        return getAsyncFreeveggieRestService(feedUrl, queryParameter);
    }
}
