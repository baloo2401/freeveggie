package org.mdubois.freeveggie.framework.dao;

import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.msg.MessageEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import junit.framework.Assert;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Mockit;
import mockit.integration.junit4.JMockit;
import org.apache.commons.collections.map.LinkedMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.service.Pagination;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;

/**
 *
 * @author Mickael Dubois
 */
@RunWith(JMockit.class)
public class ReadOnlyDAOTest {

    @After
    public void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(new ReadOnlyDAOImpl().getType(), BusinessObjectImpl.class);
    }

    /**
     * Test of get method, of class ReadOnlyDAO.
     */
    @Test
    public void testGet() {
        final Long pId = 10L;
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        final BusinessObjectImpl resulExpected = new BusinessObjectImpl();
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.find(BusinessObjectImpl.class, pId);
                repeats(1);
                returns(resulExpected);
            }
        };
        BusinessObjectImpl result = instance.get(pId);
        assertEquals(resulExpected, result);

    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Null_Null_Null() {
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.getResultList();
                returns(null);
            }
        };
        List result = instance.getAll(null, null, null);
        Assert.assertNull(result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Null_Null_Null_Return() {
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        final List<BusinessObjectImpl> expectedResult = new ArrayList<BusinessObjectImpl>();
        expectedResult.add(new BusinessObjectImpl());
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.getResultList();

                returns(expectedResult);

            }
        };
        List result = instance.getAll(null, null, null);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Criteria_Null_Null() {
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        List<QueryCriteria<? extends CriteriaColumn>> criterias = new ArrayList<QueryCriteria<? extends CriteriaColumn>>();


        QueryCriteria<CriteriaColumn> criteria = new QueryCriteria<CriteriaColumn>(new CriteriaColumnImpl(), CriteriaOperation.MIN);
        criteria.setValue("criteriavalue");

        criterias.add(criteria);

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e WHERE 1=1 AND e.criteriaColumn >  :param0", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.setParameter("param0", "criteriavalue");

                mockTypedQuery.getResultList();
                returns(null);
            }
        };
        List result = instance.getAll(criterias, null, null);
        Assert.assertNull(result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Criteria_Null_Null_Return() {
        final List<BusinessObjectImpl> expectedResult = new ArrayList<BusinessObjectImpl>();
        expectedResult.add(new BusinessObjectImpl());
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        List<QueryCriteria<? extends CriteriaColumn>> criterias = new ArrayList<QueryCriteria<? extends CriteriaColumn>>();


        QueryCriteria<CriteriaColumn> criteria = new QueryCriteria<CriteriaColumn>(new CriteriaColumnImpl(), CriteriaOperation.MIN, "criteriavalue");
        criterias.add(criteria);

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e WHERE 1=1 AND e.criteriaColumn >  :param0", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.setParameter("param0", "criteriavalue");

                mockTypedQuery.getResultList();
                returns(expectedResult);
            }
        };
        List result = instance.getAll(criterias, null, null);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Criteria_BusinessObjectEnum_Null_Null_Return() {
        final List<BusinessObjectImpl> expectedResult = new ArrayList<BusinessObjectImpl>();
        expectedResult.add(new BusinessObjectImpl());
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        List<QueryCriteria<? extends CriteriaColumn>> criterias = new ArrayList<QueryCriteria<? extends CriteriaColumn>>();

        QueryCriteria<CriteriaColumn> criteria = new QueryCriteria<CriteriaColumn>(new CriteriaColumnImpl(), CriteriaOperation.MIN, CultureMode.DEFAULT);
        criterias.add(criteria);

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e WHERE 1=1 AND e.criteriaColumn >  :param0", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.setParameter("param0", 102);

                mockTypedQuery.getResultList();
                returns(expectedResult);
            }
        };
        List result = instance.getAll(criterias, null, null);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Null_Order_Null() {
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        OrderColumn searchColumn = new OrderColumn() {

            @Override
            public String getOrderedColumn() {
                return "orderColumn";
            }
        };

        final ResultOrder<OrderColumn> resultSearchOrder = new ResultOrder<OrderColumn>(searchColumn, OrderWay.ASC);
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e  ORDER BY e.orderColumn ASC ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.getResultList();
                returns(null);
            }
        };
        List result = instance.getAll(null, resultSearchOrder, null);
        Assert.assertNull(result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_3args_Null_Null_Pagination() {
        final ReadOnlyDAO<BusinessObjectImpl, Long> instance = new ReadOnlyDAOImpl();
        Pagination pagination = new Pagination(100, 12);

        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.setMaxResults(100);
                returns(mockTypedQuery);
                mockTypedQuery.setFirstResult(1100);
                returns(mockTypedQuery);
                mockTypedQuery.getResultList();
                returns(null);
            }
        };

        List result = instance.getAll(null, null, pagination);
        Assert.assertNull(result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_boolean_Pagination_false_null() {
        boolean withDefaultOrder = false;
        Pagination pPagination = null;
        final IReadOnlyDAO instance = new ReadOnlyDAOImpl();
        List expResult = null;
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.getResultList();
                returns(null);
            }
        };

        List result = instance.getAll(withDefaultOrder, pPagination);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_boolean_Pagination_true_null() {
        boolean withDefaultOrder = true;
        Pagination pPagination = null;
        final IReadOnlyDAO instance = new ReadOnlyDAOImpl();
        List expResult = null;
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e  ORDER BY e.orderedcolumn ASC ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.getResultList();
                returns(null);
            }
        };

        List result = instance.getAll(withDefaultOrder, pPagination);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAll method, of class ReadOnlyDAO.
     */
    @Test
    public void testGetAll_boolean_Pagination() {
        boolean withDefaultOrder = true;
        Pagination pPagination = new Pagination(100, 12);
        final IReadOnlyDAO instance = new ReadOnlyDAOImpl();
        List expResult = null;
        new Expectations() {

            @Mocked
            private EntityManager mockEntityManager;
            @Mocked
            private TypedQuery mockTypedQuery;

            {
                Deencapsulation.setField(instance, "entityManager", mockEntityManager);

                mockEntityManager.createQuery("SELECT e FROM BusinessObjectImpl e  ORDER BY e.orderedcolumn ASC ", BusinessObjectImpl.class);
                returns(mockTypedQuery);

                mockTypedQuery.setMaxResults(100);
                returns(mockTypedQuery);
                mockTypedQuery.setFirstResult(1100);
                returns(mockTypedQuery);
                mockTypedQuery.getResultList();
                returns(null);
            }
        };

        List result = instance.getAll(withDefaultOrder, pPagination);
        assertEquals(expResult, result);
    }
    @Mocked
    TypedQuery typedQuery;

    @Test
    public void testAddPagination() {
        ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        TechnicalInformation pTechnicalInformation = new TechnicalInformation();
        pTechnicalInformation.setPagination(new Pagination(10, 10));
        new Expectations() {

            {

                typedQuery.setMaxResults(10);
                returns(typedQuery);

                typedQuery.setFirstResult(90);
                returns(typedQuery);
            }
        };
        readOnlyDAO.addPagination(typedQuery, pTechnicalInformation);
    }
    @Mocked
    @SuppressWarnings("unused")
    private HQLSimpleParser mockHQLSimpleParser;

    @Test
    public void testFindPaginationQuery() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);

        Pagination pagination = new Pagination(10, 11);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("key", parameter);

                mockQuery.setMaxResults(10);
                returns(mockQuery);

                mockQuery.setFirstResult(100);
                returns(mockQuery);

                mockQuery.getResultList();

            }
        };

        readOnlyDAO.findPaginationQuery(queryName, parameters, pagination);
    }

    @Test
    public void testFindPaginationWithTechnicalQuery() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);

        Pagination pagination = new Pagination(10, 11);

        TechnicalInformation technicalInformation = new TechnicalInformation();
        technicalInformation.setPagination(pagination);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("key", parameter);

                mockQuery.setMaxResults(10);
                returns(mockQuery);

                mockQuery.setFirstResult(100);
                returns(mockQuery);

                mockQuery.getResultList();

            }
        };

        readOnlyDAO.findPaginationQuery(queryName, parameters, technicalInformation);
    }

    @Test
    public void testFindPaginationWithTechnical2Query() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);

        final ResultOrder order = new ResultOrder(new OrderColumn() {

            @Override
            public String getOrderedColumn() {
                return "ordercolumn";
            }
        }, OrderWay.ASC);

        Pagination pagination = new Pagination(10, 11);
        TechnicalInformation technicalInformation = new TechnicalInformation();
        technicalInformation.setPagination(pagination);
        technicalInformation.setOrder(order);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.addOrder(order.getInstruction());

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("key", parameter);

                mockQuery.setMaxResults(10);
                returns(mockQuery);

                mockQuery.setFirstResult(100);
                returns(mockQuery);

                mockQuery.getResultList();

            }
        };

        readOnlyDAO.findPaginationQuery(queryName, parameters, technicalInformation);
    }

    @Test
    public void testFindPaginationWithTechnical3Query() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);
        final Object parameterBis = new Object();
        final QueryCriteria criteria = new QueryCriteria(new CriteriaColumn() {

            @Override
            public String getCriteriaColumn() {
                return "criteriaColumn";
            }
        }, CriteriaOperation.MIN);

        criteria.setValue(parameterBis);
        final ResultOrder order = new ResultOrder(new OrderColumn() {

            @Override
            public String getOrderedColumn() {
                return "ordercolumn";
            }
        }, OrderWay.ASC);

        Pagination pagination = new Pagination(10, 11);
        TechnicalInformation technicalInformation = new TechnicalInformation();
        technicalInformation.setPagination(pagination);
        technicalInformation.setOrder(order);
        technicalInformation.addCriteria(criteria);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.addWhereClause("criteriaColumn >  :param0");

                mockHQLSimpleParser.addOrder(order.getInstruction());

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("param0", parameterBis);
                mockQuery.setParameter("key", parameter);

                mockQuery.setMaxResults(10);
                returns(mockQuery);

                mockQuery.setFirstResult(100);
                returns(mockQuery);

                mockQuery.getResultList();

            }
        };

        readOnlyDAO.findPaginationQuery(queryName, parameters, technicalInformation);
    }

    @Test
    public void testFindPaginationWithTechnical4Query() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);
        final Object parameterBis = new Object();
        CriteriaColumn criteriaColumn = new CriteriaColumn() {

            @Override
            public String getCriteriaColumn() {
                return "criteriaColumn";
            }
        };

        final QueryCriteria criteria = new QueryCriteria(criteriaColumn, CriteriaOperation.EQUAL);
        criteria.setValue(parameterBis);

        final QueryCriteria criteriaBis = new QueryCriteria(criteriaColumn, CriteriaOperation.EQUAL);
        criteriaBis.setValue(parameterBis);

        final ResultOrder order = new ResultOrder(new OrderColumn() {

            @Override
            public String getOrderedColumn() {
                return "ordercolumn";
            }
        }, OrderWay.ASC);

        Pagination pagination = new Pagination(10, 11);
        TechnicalInformation technicalInformation = new TechnicalInformation();
        technicalInformation.setPagination(pagination);
        technicalInformation.setOrder(order);
        technicalInformation.addCriteria(criteria);
        technicalInformation.addCriteria(criteriaBis);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.addWhereClause("criteriaColumn =  :param0");
                mockHQLSimpleParser.addWhereClause("criteriaColumn =  :param1");

                mockHQLSimpleParser.addOrder(order.getInstruction());

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("param0", parameterBis);
                mockQuery.setParameter("param1", parameterBis);
                mockQuery.setParameter("key", parameter);

                mockQuery.setMaxResults(10);
                returns(mockQuery);

                mockQuery.setFirstResult(100);
                returns(mockQuery);

                mockQuery.getResultList();

            }
        };

        readOnlyDAO.findPaginationQuery(queryName, parameters, technicalInformation);
    }

    @Test
    public void testFindQuery() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("key", parameter);

                mockQuery.getSingleResult();

            }
        };

        readOnlyDAO.findQuery(queryName, parameters);
    }

    @Test
    public void testFindQueryNoResult() {
        final ReadOnlyDAO readOnlyDAO = new ReadOnlyDAOImpl();
        String queryName = "BusinessObjectImpl.get";
        Map<String, Object> parameters = new LinkedMap();
        final Object parameter = new Object();
        parameters.put("key", parameter);

        new Expectations() {

            @Mocked
            EntityManager mockEntityManager;
            @Mocked
            Query mockQuery;
            @Mocked
            org.hibernate.Query mockQueryHibernate;
            @Mocked
            HQLSimpleParser mockLocalHQLSimpleParser;

            {
                Deencapsulation.setField(readOnlyDAO, "entityManager", mockEntityManager);
                mockEntityManager.createNamedQuery("BusinessObjectImpl.get");
                returns(mockQuery);

                mockQuery.unwrap(org.hibernate.Query.class);
                returns(mockQueryHibernate);

                mockQueryHibernate.getQueryString();
                returns("anyString");

                HQLSimpleParser.parseSimpleHql(anyString);
                returns(mockHQLSimpleParser);

                mockHQLSimpleParser.getHQLQuery();
                returns("Query");

                mockEntityManager.createQuery("Query");
                returns(mockQuery);

                mockQuery.setParameter("key", parameter);

                mockQuery.getSingleResult();
                throwsException(new NoResultException() );
            }
        };

        BusinessObject result = readOnlyDAO.findQuery(queryName, parameters);
        Assert.assertNull(result);
    }

    public class ReadOnlyDAOImpl extends ReadOnlyDAO<BusinessObjectImpl, Long> implements IReadOnlyDAO<BusinessObjectImpl, Long> {

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

    public class CriteriaColumnImpl implements CriteriaColumn {

            @Override
            public String getCriteriaColumn() {
                    return "criteriaColumn";
            }
    }

    public class BusinessObjectImpl extends BusinessObject<Long> {

        @Override
        public Long getId() {
            return 1L;
        }

    }


    public enum CultureMode implements MessageEnum {

        DEFAULT;

        @Override
        public Integer toInt() {
            return 102;
        }

    }
}
