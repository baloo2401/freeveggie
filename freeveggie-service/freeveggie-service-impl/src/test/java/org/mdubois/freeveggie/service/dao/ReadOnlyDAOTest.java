package org.mdubois.freeveggie.service.dao;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.framework.bo.BusinessObject;
import org.mdubois.freeveggie.framework.dao.IReadOnlyDAO;
import org.mdubois.freeveggie.framework.service.TechnicalInformation;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaColumn;
import org.mdubois.freeveggie.framework.service.criteria.CriteriaOperation;
import org.mdubois.freeveggie.framework.service.criteria.QueryCriteria;
import org.mdubois.freeveggie.framework.service.order.OrderColumn;
import org.mdubois.freeveggie.framework.service.order.OrderWay;
import org.mdubois.freeveggie.framework.service.order.ResultOrder;
import org.mdubois.freeveggie.framework.test.AssertBusinnesObject;
import org.mdubois.freeveggie.framework.test.DaoUnitTest;
// </editor-fold>

/**
 *
 * @author Mickael Dubois
 * @param <T> - The entity to have a data access on
 * @param <PK> - The primary key of the entity
 */
public abstract class ReadOnlyDAOTest<T extends BusinessObject<PK>, PK extends Serializable> extends DaoUnitTest {

    @Test
    public void testGet() {

        T entity = getDao().get(getId());

        Assert.assertTrue("Dataset need more data to run test", entity != null);
    }

    @Test
    public void testGetAll() {
        List<T> entities = getDao().getAll(false, null);
        Assert.assertTrue("Dataset need more data to run test", entities.size() >= 2);
        int listSize = entities.size();

        entities = getDao().getAll(true, null);
        Assert.assertEquals(listSize, entities.size());
    }

    @Test
    public void testCriteria() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (getCriteriaColumn() != null) {
            List<T> entities = getDao().getAll(false, null);
            Assert.assertTrue("Dataset need more data to run test", entities.size() >= 2);
            List<QueryCriteria<CriteriaColumn>> criterias = getCriterias(getCriteriaColumn());
            List<QueryCriteria<? extends CriteriaColumn>> criteriaTest = new ArrayList<>(1);
            QueryCriteria<CriteriaColumn> newCriteria;
            for (QueryCriteria<CriteriaColumn> criteria : criterias) {
                Object expectedValue = PropertyUtils.getNestedProperty(entities.get(0), criteria.getCriteria().getCriteriaColumn().replace("_", ""));
                criteriaTest.clear();
                newCriteria = new QueryCriteria<>(criteria.getCriteria(), CriteriaOperation.EQUAL, expectedValue);
                criteriaTest.add(newCriteria);
                List<T> entitiesTests = getDao().getAll(criteriaTest, null, null);
                Assert.assertTrue(entities != null && entities.size() > 0);
                AssertBusinnesObject.assertNoDuplicate(entitiesTests);
                for (T entity : entitiesTests) {
                    Object actualValue = PropertyUtils.getNestedProperty(entity, criteria.getCriteria().getCriteriaColumn().replace("_", ""));
                    Assert.assertEquals("Criteria is not respected", expectedValue, actualValue);
                }
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testOrder() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (getOrderColumn() != null) {
            List<T> entities;
            List<ResultOrder<OrderColumn>> orders = getOrders(getOrderColumn());
            for (ResultOrder<OrderColumn> order : orders) {
                entities = getDao().getAll(null, order, null);

                Assert.assertTrue("Dataset need more data to run test", entities.size() >= 2);
                AssertBusinnesObject.assertNoDuplicate(entities);
                for (int i = 0; i < entities.size() - 1; i++) {
                    T firstBO = entities.get(i);
                    T secondBO = entities.get(i + 1);
                    Object firstObject = PropertyUtils.getNestedProperty(firstBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                    Object secondObject = PropertyUtils.getNestedProperty(secondBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                    Assert.assertTrue(order.getClass() + " object badly define", firstObject != null);
                    Assert.assertTrue(order.getClass() + " object badly define", secondObject != null);
                    if (order.getWay().equals(OrderWay.ASC)) {
                        Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) firstObject).compareTo((Comparable) secondObject) <= 0);
                    } else {
                        Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) secondObject).compareTo((Comparable) firstObject) <= 0);
                    }
                }
            }
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testOrderAndCriteria() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (getOrderColumn() != null && getCriteriaColumn() != null) {
            List<T> entities = getDao().getAll(false, null);
            List<ResultOrder<OrderColumn>> orders = getOrders(getOrderColumn());
            List<QueryCriteria<CriteriaColumn>> criterias = getCriterias(getCriteriaColumn());
            List<QueryCriteria<? extends CriteriaColumn>> criteriaTest = new ArrayList<QueryCriteria<? extends CriteriaColumn>>(1);
            QueryCriteria<CriteriaColumn> newCriteria;
            for (QueryCriteria<CriteriaColumn> criteria : criterias) {
                for (ResultOrder<OrderColumn> order : orders) {
                    Object expectedValue = PropertyUtils.getNestedProperty(entities.get(0), criteria.getCriteria().getCriteriaColumn().replace("_", ""));

                    criteriaTest.clear();
                    newCriteria = new QueryCriteria<CriteriaColumn>(criteria.getCriteria(), CriteriaOperation.EQUAL, expectedValue);
                    criteriaTest.add(newCriteria);

                    List<T> entitiesTests = getDao().getAll(criteriaTest, order, null);
                    Assert.assertTrue("Dataset need more data to run test", entities != null && entities.size() >= 2);
                    AssertBusinnesObject.assertNoDuplicate(entitiesTests);
                    for (T entity : entitiesTests) {
                        Object actualValue = PropertyUtils.getNestedProperty(entity, criteria.getCriteria().getCriteriaColumn().replace("_", ""));
                        Assert.assertEquals("Criteria is not respected", expectedValue, actualValue);
                    }


                    for (int i = 0; i < entitiesTests.size() - 1; i++) {
                        T firstBO = entitiesTests.get(i);
                        T secondBO = entitiesTests.get(i + 1);
                        Object firstObject = PropertyUtils.getNestedProperty(firstBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                        Object secondObject = PropertyUtils.getNestedProperty(secondBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                        Assert.assertTrue(order.getClass() + " object badly define", firstObject != null);
                        Assert.assertTrue(order.getClass() + " object badly define", secondObject != null);
                        if (order.getWay().equals(OrderWay.ASC)) {
                            Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) firstObject).compareTo((Comparable) secondObject) <= 0);
                        } else {
                            Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) secondObject).compareTo((Comparable) firstObject) <= 0);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void testOrderAndCriteria(final String pMethodName, final Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Class<?>[] clazzs = new Class<?>[args.length + 1];
        for (int i = 0; i < args.length; i++) {
            clazzs[i] = args[i].getClass();
        }
        clazzs[args.length] = TechnicalInformation.class;
        Method method = getDao().getClass().getMethod(pMethodName, clazzs);

        if (getOrderColumn() != null && getCriteriaColumn() != null) {
            Object[] objects = new Object[args.length + 1];
            System.arraycopy(args, 0, objects, 0, args.length);
            objects[args.length] = null;
            List<T> entities = (List<T>) method.invoke(getDao(), objects);
            Assert.assertTrue("Dao should not return null", entities != null);
            Assert.assertTrue("Dataset need more data to run test", entities.size() > 0);
            List<ResultOrder<OrderColumn>> orders = getOrders(getOrderColumn());
            List<QueryCriteria<CriteriaColumn>> criterias = getCriterias(getCriteriaColumn());
            List<QueryCriteria<CriteriaColumn>> criteriaTest = new ArrayList<QueryCriteria<CriteriaColumn>>(1);
            QueryCriteria<CriteriaColumn> newCriteria;
            for (QueryCriteria<CriteriaColumn> criteria : criterias) {
                for (ResultOrder<OrderColumn> order : orders) {
                    Object expectedValue = PropertyUtils.getNestedProperty(entities.get(0), criteria.getCriteria().getCriteriaColumn().replace("_", ""));

                    criteriaTest.clear();
                    newCriteria = new QueryCriteria<CriteriaColumn>(criteria.getCriteria(), CriteriaOperation.EQUAL, expectedValue);
                    criteriaTest.add(newCriteria);
                    TechnicalInformation<CriteriaColumn, OrderColumn> technicalInformation = new TechnicalInformation<CriteriaColumn, OrderColumn>();
                    technicalInformation.setCriterias(criteriaTest);
                    technicalInformation.setOrder(order);
                    objects[args.length] = technicalInformation;
                    List<T> entitiesTests = (List<T>) method.invoke(getDao(), objects);
                    Assert.assertTrue("Dao should not return null", entities != null);
                    Assert.assertTrue("Dataset need more data to run test using " + ArrayUtils.toString(objects), entities.size() >= 2);
                    AssertBusinnesObject.assertNoDuplicate(entitiesTests);
                    for (T entity : entitiesTests) {
                        Object actualValue = PropertyUtils.getNestedProperty(entity, criteria.getCriteria().getCriteriaColumn().replace("_", ""));
                        Assert.assertEquals("Criteria is not respected", expectedValue, actualValue);
                    }


                    for (int i = 0; i < entitiesTests.size() - 1; i++) {
                        T firstBO = entitiesTests.get(i);
                        T secondBO = entitiesTests.get(i + 1);
                        Object firstObject = PropertyUtils.getNestedProperty(firstBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                        Object secondObject = PropertyUtils.getNestedProperty(secondBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                        Assert.assertTrue(order.getClass() + " object badly define", firstObject != null);
                        Assert.assertTrue(order.getClass() + " object badly define", secondObject != null);
                        if (order.getWay().equals(OrderWay.ASC)) {
                            Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) firstObject).compareTo((Comparable) secondObject) <= 0);
                        } else {
                            Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) secondObject).compareTo((Comparable) firstObject) <= 0);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void testCriteria(final String pMethodName, final Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Class<?>[] clazzs = new Class<?>[args.length + 1];
        for (int i = 0; i < args.length; i++) {
            clazzs[i] = args[i].getClass();
        }
        clazzs[args.length] = TechnicalInformation.class;
        Method method = getDao().getClass().getMethod(pMethodName, clazzs);

        if (getCriteriaColumn() != null) {
            Object[] objects = new Object[args.length + 1];
            System.arraycopy(args, 0, objects, 0, args.length);
            objects[args.length] = null;
            List<T> entities = (List<T>) method.invoke(getDao(), objects);
            Assert.assertTrue("Dao should not return null", entities != null);
            Assert.assertTrue("Dataset need more data to run test", entities.size() > 0);
            AssertBusinnesObject.assertNoDuplicate(entities);
            List<QueryCriteria<CriteriaColumn>> criterias = getCriterias(getCriteriaColumn());
            List<QueryCriteria<CriteriaColumn>> criteriaTest = new ArrayList<QueryCriteria<CriteriaColumn>>(1);
            QueryCriteria<CriteriaColumn> newCriteria;
            for (QueryCriteria<CriteriaColumn> criteria : criterias) {
                Object expectedValue = PropertyUtils.getNestedProperty(entities.get(0), criteria.getCriteria().getCriteriaColumn().replace("_", ""));

                criteriaTest.clear();
                newCriteria = new QueryCriteria<CriteriaColumn>(criteria.getCriteria(), CriteriaOperation.EQUAL,expectedValue);
                criteriaTest.add(newCriteria);
                TechnicalInformation<CriteriaColumn, OrderColumn> technicalInformation = new TechnicalInformation<CriteriaColumn, OrderColumn>();
                technicalInformation.setCriterias(criteriaTest);
                objects[args.length] = technicalInformation;
                List<T> entitiesTests = (List<T>) method.invoke(getDao(), objects);
                Assert.assertTrue("Dao should not return null", entities != null);
                Assert.assertTrue("Criteria is not respected", entitiesTests.size() > 0);
                AssertBusinnesObject.assertNoDuplicate(entitiesTests);
                for (T entity : entitiesTests) {
                    Object actualValue = PropertyUtils.getNestedProperty(entity, criteria.getCriteria().getCriteriaColumn().replace("_", ""));
                    Assert.assertEquals("Criteria is not respected", expectedValue, actualValue);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void testOrder(final String pMethodName, final Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        Class<?>[] clazzs = new Class<?>[args.length + 1];
        for (int i = 0; i < args.length; i++) {
            clazzs[i] = args[i].getClass();
        }
        clazzs[args.length] = TechnicalInformation.class;
        Method method = getDao().getClass().getMethod(pMethodName, clazzs);
        if (getOrderColumn() != null) {
            List<T> entities;
            List<ResultOrder<OrderColumn>> orders = getOrders(getOrderColumn());
            for (ResultOrder<OrderColumn> order : orders) {
                Object[] objects = new Object[args.length + 1];
                System.arraycopy(args, 0, objects, 0, args.length);
                TechnicalInformation<CriteriaColumn, OrderColumn> technicalInformation = new TechnicalInformation<CriteriaColumn, OrderColumn>();
                technicalInformation.setOrder(order);
                objects[args.length] = technicalInformation;
                entities = (List<T>) method.invoke(getDao(), objects);
                Assert.assertTrue("Dao should not return null", entities != null);
                Assert.assertTrue("Dataset need more data to run test to using " + ArrayUtils.toString(objects), entities.size() >= 2);
                AssertBusinnesObject.assertNoDuplicate(entities);
                for (int i = 0; i < entities.size() - 1; i++) {
                    T firstBO = entities.get(i);
                    T secondBO = entities.get(i + 1);
                    Object firstObject = PropertyUtils.getNestedProperty(firstBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                    Object secondObject = PropertyUtils.getNestedProperty(secondBO, order.getOrderColumn().getOrderedColumn().replace("_", ""));
                    Assert.assertTrue(order.getClass() + " object badly define", firstObject != null);
                    Assert.assertTrue(order.getClass() + " object badly define", secondObject != null);
                    if (order.getWay().equals(OrderWay.ASC)) {
                        Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) firstObject).compareTo((Comparable) secondObject) <= 0);
                    } else {
                        Assert.assertTrue("The order is not respected", firstObject.equals(secondObject) || ((Comparable) secondObject).compareTo((Comparable) firstObject) <= 0);
                    }
                }
            }
        }
    }

    public abstract IReadOnlyDAO<T, PK> getDao();

    public abstract Class< ? extends OrderColumn> getOrderColumn();

    public abstract Class< ? extends CriteriaColumn> getCriteriaColumn();

    public Class< ? extends ResultOrder<OrderColumn>> getCriteria() {
        return null;
    }

    public abstract PK getId();

    public List<ResultOrder<OrderColumn>> getOrders(Class< ? extends OrderColumn> aClass) {
        OrderColumn[] enumConstants = aClass.getEnumConstants();
        List<ResultOrder<OrderColumn>> orders = new ArrayList<ResultOrder<OrderColumn>>();
        ResultOrder<OrderColumn> order;
        for (int i = 0; i < enumConstants.length; i++) {
            OrderColumn orderColumn = enumConstants[i];
            order = new ResultOrder<OrderColumn>(orderColumn, OrderWay.ASC);
            orders.add(order);
            order = new ResultOrder<OrderColumn>(orderColumn, OrderWay.DESC);
            orders.add(order);
        }
        return orders;
    }

    public List<QueryCriteria<CriteriaColumn>> getCriterias(Class< ? extends CriteriaColumn> aClass) {
        CriteriaColumn[] enumConstants = aClass.getEnumConstants();
        List<QueryCriteria<CriteriaColumn>> orders = new ArrayList<QueryCriteria<CriteriaColumn>>();
        QueryCriteria<CriteriaColumn> order;
        for (int i = 0; i < enumConstants.length; i++) {
            CriteriaColumn orderColumn = enumConstants[i];
            order = new QueryCriteria<CriteriaColumn>(orderColumn, CriteriaOperation.EQUAL);
            orders.add(order);
        }
        return orders;
    }
}
