package org.mdubois.freeveggie.models;

import java.util.LinkedList;
import java.util.Queue;

import play.cache.Cached;

@Cached(key = "recentSearchModel")
public class RecentSearchModel {

    private static final int MAX_NUMBER_OF_RECENT_SEARCH = 3;

    /**
     * The list of the recent search
     */
    private Queue<RecentSearchModel.RecentSearch> recentSearchs;

    public Queue<RecentSearch> getRecentSearchs() {
        if (recentSearchs == null) {
            recentSearchs = new LinkedList<RecentSearchModel.RecentSearch>();
        }
        return recentSearchs;
    }

    public void addRecentSearch(RecentSearch pRecentSearch) {
        if (recentSearchs == null) {
            recentSearchs = new LinkedList<RecentSearchModel.RecentSearch>();
        }
        if (recentSearchs.size() == MAX_NUMBER_OF_RECENT_SEARCH) {
            recentSearchs.poll();
        }
        recentSearchs.add(pRecentSearch);
    }

    public class RecentSearch {
        /**
         * The product ref id of the search.
         */
        private Integer productRefId;

        /**
         * The product ref name.
         */
        private String productRefName;

        /**
         * The latitude.
         */
        private Long latitude;

        /**
         * The longitude.
         */
        private Long longitude;

        /**
         * The readable position
         */
        private String locality;

        /**
         * @return the productRefId
         */
        public Integer getProductRefId() {
            return productRefId;
        }

        /**
         * @param productRefId the productRefId to set
         */
        public void setProductRefId(Integer productRefId) {
            this.productRefId = productRefId;
        }

        /**
         * @return the productRefName
         */
        public String getProductRefName() {
            return productRefName;
        }

        /**
         * @param productRefName the productRefName to set
         */
        public void setProductRefName(String productRefName) {
            this.productRefName = productRefName;
        }

        /**
         * @return the latitude
         */
        public Long getLatitude() {
            return latitude;
        }

        /**
         * @param latitude the latitude to set
         */
        public void setLatitude(Long latitude) {
            this.latitude = latitude;
        }

        /**
         * @return the longitude
         */
        public Long getLongitude() {
            return longitude;
        }

        /**
         * @param longitude the longitude to set
         */
        public void setLongitude(Long longitude) {
            this.longitude = longitude;
        }

        /**
         * @return the locality
         */
        public String getLocality() {
            return locality;
        }

        /**
         * @param locality the locality to set
         */
        public void setLocality(String locality) {
            this.locality = locality;
        }

    }

}
