package org.mdubois.freeveggie.framework.dao;

import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import javax.persistence.EntityManager;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ReadWriteDAOTest {

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    /**
     * Test of save method, of class IReadWriteDAO.
     */
    @Test
    public void testSave() {
        final BusinessObjectImpl transientInstance = new BusinessObjectImpl();
        final IReadWriteDAO instance = new ReadWriteDAOImpl();
        final Object expResult = 100L;

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.persist(transientInstance);
            }
        };
        Object result = instance.save(transientInstance);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class IReadWriteDAO.
     */
    @Test
    public void testUpdate() {
        final BusinessObjectImpl transientInstance = new BusinessObjectImpl();
        final IReadWriteDAO instance = new ReadWriteDAOImpl();
        final Object expResult = 100L;

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

//                mockEntityManager.merge(transientInstance);
//                mockEntityManager.persist(transientInstance);
            }
        };
        instance.update(transientInstance);
    }

    /**
     * Test of delete method, of class IReadWriteDAO.
     */
    @Test
    public void testDelete() {
        final BusinessObjectImpl transientInstance = new BusinessObjectImpl();
        final IReadWriteDAO instance = new ReadWriteDAOImpl();
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.remove(transientInstance);
            }
        };
        instance.delete(transientInstance);
    }

    /**
     * Test of flush method, of class IReadWriteDAO.
     */
    @Test
    public void testFlush() {
        final IReadWriteDAO instance = new ReadWriteDAOImpl();

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.flush();
            }
        };
        instance.flush();
    }

    public class ReadWriteDAOImpl extends ReadWriteDAO<BusinessObjectImpl, Long> implements IReadWriteDAO<BusinessObjectImpl, Long> {

        @Override
        public ResultOrder getDefaultOrder() {
            return new ResultOrder(new OrderColumn() {

                @Override
                public String getOrderedColumn() {
                    return "orderedcolumn";
                }
            }, OrderWay.ASC);
        }

        @Override
        protected Class<BusinessObjectImpl> getType() {
            return BusinessObjectImpl.class;
        }
    }

    public class BusinessObjectImpl extends BusinessObject<Long> {

        @Override
        public Long getId() {
            return 100L;
        }
    }
}
