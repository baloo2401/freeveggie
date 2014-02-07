package org.mdubois.freeveggie.framework.utils;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;

/**
 *
 * @author mdubois
 */
public class CriteriaUtilsTest {

    private static class BasicCriteriaColumn implements CriteriaColumn {

        public BasicCriteriaColumn() {
        }

        @Override
        public String getCriteriaColumn() {
            return "CriteriaColumn";
        }
    }

    private static class BasicQueryCriteria extends QueryCriteria<BasicCriteriaColumn> {

        public BasicQueryCriteria(BasicCriteriaColumn pCriteriaColumn, CriteriaOperation pOperation) {
            super(pCriteriaColumn, pOperation);
        }

        public BasicQueryCriteria(BasicCriteriaColumn pCriteriaColumn, CriteriaOperation pOperation, Object pValue) {
            super(pCriteriaColumn, pOperation, pValue);
        }

    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
    @Test
    public void testIsCriteriaPresentTrue() {
        List<BasicQueryCriteria> criterias = new ArrayList<BasicQueryCriteria>();
        BasicCriteriaColumn criteriaColumn = new BasicCriteriaColumn();
        BasicQueryCriteria queryCriteria = new BasicQueryCriteria(criteriaColumn, CriteriaOperation.MIN);
        criterias.add(queryCriteria);
        boolean expResult = true;
        boolean result = CriteriaUtils.isCriteriaPresent(criterias, criteriaColumn);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
    @Test
    public void testIsCriteriaPresentFalse() {
        List<BasicQueryCriteria> criterias = new ArrayList<BasicQueryCriteria>();
        BasicCriteriaColumn criteriaColumn = new BasicCriteriaColumn();
        BasicCriteriaColumn criteriaColumnBis = new BasicCriteriaColumn();
        BasicQueryCriteria queryCriteria = new BasicQueryCriteria(criteriaColumn, CriteriaOperation.MIN);
        criterias.add(queryCriteria);
        boolean expResult = false;
        boolean result = CriteriaUtils.isCriteriaPresent(criterias, criteriaColumnBis);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
    @Test
    public void testIsCriteriaPresentEmpty() {
        List<BasicQueryCriteria> criterias = new ArrayList<BasicQueryCriteria>();
        BasicCriteriaColumn criteriaColumn = new BasicCriteriaColumn();
        boolean expResult = false;
        boolean result = CriteriaUtils.isCriteriaPresent(criterias, criteriaColumn);
        assertEquals(expResult, result);
    }

    /**
     * Test of isCriteriaPresent method, of class CriteriaUtils.
     */
    @Test
    public void testIsCriteriaPresentNull() {
        List<BasicQueryCriteria> criterias = null;
        BasicCriteriaColumn criteriaColumn = new BasicCriteriaColumn();
        boolean expResult = false;
        boolean result = CriteriaUtils.isCriteriaPresent(criterias, criteriaColumn);
        assertEquals(expResult, result);
    }
}
