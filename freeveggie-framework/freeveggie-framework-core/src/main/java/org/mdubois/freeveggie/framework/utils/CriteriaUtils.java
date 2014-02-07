package org.mdubois.freeveggie.framework.utils;

import java.util.List;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;

/**
 *
 * @author mdubois
 */
public final class CriteriaUtils {

    private CriteriaUtils(){

    }

    public static boolean isCriteriaPresent(final List<? extends QueryCriteria> criterias, final CriteriaColumn pCriteriaColumn) {
        boolean criteriaFind = false;
        if (criterias != null) {
            for (QueryCriteria criteria : criterias) {
                if (criteria.getCriteria().equals(pCriteriaColumn)) {
                    criteriaFind = true;
                    break;
                }
            }
        }
        return criteriaFind;
    }
}
