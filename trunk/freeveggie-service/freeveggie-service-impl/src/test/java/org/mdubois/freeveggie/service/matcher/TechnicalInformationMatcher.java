package org.mdubois.freeveggie.service.matcher;

import java.util.Collection;
import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;

/**
 *
 * @author Mickael Dubois
 */
@SuppressWarnings("unchecked")
public class TechnicalInformationMatcher extends BaseMatcher<TechnicalInformation> {

    private TechnicalInformation technicalInformation;

    public TechnicalInformationMatcher(TechnicalInformation pTechnicalInformation) {
        this.technicalInformation = pTechnicalInformation;
    }

    @Override
    public boolean matches(Object item) {
        if (item instanceof TechnicalInformation) {
            TechnicalInformation lTechnicalInformation = (TechnicalInformation) item;
            if (technicalInformation != null) {
                if (testPagination(lTechnicalInformation)) {
                    if (testOrder(lTechnicalInformation)) {
                        if (testCriteria(lTechnicalInformation)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
    }

    private boolean testPagination(TechnicalInformation pTechnicalInformation) {

        if (technicalInformation.getPagination() != null && pTechnicalInformation.getPagination() != null) {
            Pagination pPagination = pTechnicalInformation.getPagination();
            Pagination cPagination = technicalInformation.getPagination();
            if (pPagination.getNbPerPage() == cPagination.getNbPerPage() && pPagination.getPageNumber() == pPagination.getPageNumber()) {
                return true;
            }
        } else if (technicalInformation.getPagination() == null && pTechnicalInformation.getPagination() == null) {
            return true;
        }
        return false;
    }

    private boolean testOrder(TechnicalInformation pTechnicalInformation) {
        if (technicalInformation.getOrder() != null && pTechnicalInformation.getOrder() != null) {
            ResultOrder pResultSearchOrder = pTechnicalInformation.getOrder();
            ResultOrder cResultSearchOrder = technicalInformation.getOrder();
            if (pResultSearchOrder.getInstruction().equals(cResultSearchOrder.getInstruction())) {
                return true;
            }
        } else if (technicalInformation.getOrder() == null && pTechnicalInformation.getOrder() == null) {
            return true;
        }
        return false;
    }

    private boolean testCriteria(TechnicalInformation pTechnicalInformation) {
        if (technicalInformation.getCriterias() != null && pTechnicalInformation.getCriterias() != null) {
            List<QueryCriteria> pCriterias = pTechnicalInformation.getCriterias();
            List<QueryCriteria> cCriterias = technicalInformation.getCriterias();
            boolean findCriteria = false;
            for (QueryCriteria pCriteria : pCriterias) {
                for (QueryCriteria cCriteria : cCriterias) {
                    if (cCriteria.getInstruction().equals(pCriteria.getInstruction()) && cCriteria.getValue().equals(pCriteria.getValue())) {
                        findCriteria = true;
                        break;
                    } else if (cCriteria.getValue() instanceof Collection && pCriteria.getValue() instanceof Collection) {
                        Collection c1 = (Collection) cCriteria.getValue();
                        Collection c2 = (Collection) pCriteria.getValue();
                        if (c1.containsAll(c2)) {
                            findCriteria = true;
                            break;
                        }
                    }
                }
                if (!findCriteria) {
                    return false;
                }
                findCriteria = false;
            }
            return true;
        } else if (technicalInformation.getCriterias() == null && pTechnicalInformation.getCriterias() == null) {
            return true;
        }
        return false;
    }
}
