package org.mdubois.freeveggie.controllers;

import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.RecentSearchModel;
import org.mdubois.freeveggie.models.RecentSearchModel.RecentSearch;

import play.cache.Cache;
import play.mvc.Result;

@Authenticated
public class SearchController extends FreeveggieController {

	/**
	 * Service endpoint.
	 */
	public static final String SERVICE_PATH = getFreeveggieEndPointURL();

	public static Result searchGardens(final Double latitudeUp, final Double latitudeDown, final Double longitudeUp,
			final Double longitudeDown, final Integer productRefId) {
	    RecentSearchModel recentSearchModel = (RecentSearchModel) Cache.get("recentSearchModel");
	    if(recentSearchModel == null){
	        recentSearchModel = new RecentSearchModel();
	        Cache.set("recentSearchModel", recentSearchModel);
	    }
	    RecentSearch recentSearch = recentSearchModel.new RecentSearch(); 
	    recentSearch.setProductRefId(productRefId);
	    recentSearchModel.addRecentSearch(recentSearch);
	    
		String feedUrl = SERVICE_PATH
				+ String.format("/garden/search/%1$s/%2$s/%3$s/%4$s/refproduct/%5$s", latitudeUp, latitudeDown,
						longitudeUp, longitudeDown, productRefId);
		return getAsyncFreeveggieRestService(feedUrl);
	}

	public static Result getGarden() {
		return ok();
	}

	public static Result getProduct() {
		return ok();
	}
}
