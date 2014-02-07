package org.mdubois.freeveggie.framework.service.order;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.mdubois.freeveggie.framework.bo.BusinessObject;

/**
 *
 * @author Mickael Dubois
 */
public class ResultOrderTest {

    /**
     * Test of getInstruction method with asc ordering.
     */
    @Test
    public void testGetInstructionASC() {
        SearchColumnImpl orderCulumn = new SearchColumnImpl();
        ResultOrder instance = new ResultSearchOrderImpl(orderCulumn, OrderWay.ASC);
        String expResult = "OrderredColumn ASC ";
        String result = instance.getInstruction();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInstruction method with desc ordering.
     */
    @Test
    public void testGetInstructionDESC() {
        SearchColumnImpl orderCulumn = new SearchColumnImpl();
        ResultOrder instance = new ResultSearchOrderImpl(orderCulumn, OrderWay.DESC);
        String expResult = "OrderredColumn DESC ";
        String result = instance.getInstruction();
        assertEquals(expResult, result);
    }

    public class ResultSearchOrderImpl extends ResultOrder<OrderColumn>{

        public ResultSearchOrderImpl(OrderColumn pOrderColumn, OrderWay pOrderWay) {
            super(pOrderColumn, pOrderWay);
        }

    }

    public class BusinessObjectImpl extends BusinessObject<Long> {

        @Override
        public Long getId() {
            return 1L;
        }

    }

   public class SearchColumnImpl implements OrderColumn {

        @Override
        public String getOrderedColumn() {
            return "OrderredColumn";
        }

    }
}
