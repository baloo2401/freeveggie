package org.mdubois.freeveggie.framework.service;

// <editor-fold defaultstate="collapsed" desc="Imports">
import org.apache.commons.lang.builder.ToStringBuilder;
// </editor-fold>

/**
 * This class is made to execute a request and have the reponse paginated.
 * @author Mickael Dubois
 */
public class Pagination {

    /**
     * The page number we want as a result.
     */
    private int pageNumber;
    /**
     * The number of result by page.
     */
    private int nbPerPage;

    public Pagination(){
    	
    }
    /**
     * Constructor.
     * @param pNbPerPage - The number of result per page
     * @param pPageNumber - The page number we want to see
     */
    public Pagination(final int pNbPerPage, final int pPageNumber){
        this.nbPerPage = pNbPerPage;
        this.pageNumber = pPageNumber;
    }

    /**
     * Get the number of page per result.
     * @return - The number of result.
     */
    public int getNbPerPage() {
        return nbPerPage;
    }

    /**
     * Get the page number.
     * @return - The page number
     */
    public int getPageNumber() {
        return pageNumber;
    }
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
