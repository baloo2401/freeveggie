package org.mdubois.freeveggie.framework.dao;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Mickael Dubois
 */
public class HQLSimpleParserTest {

    public HQLSimpleParserTest() {
    }

    /**
     * Test of addOrder method, of class HQLSimpleParser.
     */
    @Test
    public void testAddOrder() {
        String hql = "SELECT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct";
        HQLSimpleParser result = HQLSimpleParser.parseSimpleHql(hql);
        result.addOrder("name");
        String hqlExpected = "SELECT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct"
                + " ORDER BY e.name";

        Assert.assertEquals(result.getHQLQuery().replaceFirst("  ", " ").replaceFirst("  ", " ").trim(), hqlExpected.replaceFirst("  ", " ").trim());

        result.addOrder("type");
        hqlExpected = "SELECT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct"
                + " ORDER BY e.name, e.type";
        Assert.assertEquals(result.getHQLQuery().replaceFirst("  ", " ").replaceFirst("  ", " ").trim(), hqlExpected.replaceFirst("  ", " ").trim());

    }

    /**
     * Test of addWhereClause method, of class HQLSimpleParser.
     */
    @Test
    public void testAddWhereClause() {
        String hql = "SELECT DISTINCT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct"
                + " ORDER BY e.name";
        HQLSimpleParser result = HQLSimpleParser.parseSimpleHql(hql);
        result.addWhereClause("name like :name");
        String hqlExpected = "SELECT DISTINCT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct"
                + " AND e.name like :name"
                + " ORDER BY e.name";

        Assert.assertEquals(result.getHQLQuery().replaceFirst("  ", " ").replaceFirst("  ", " ").trim(), hqlExpected.replaceFirst("  ", " ").trim());
    }

    /**
     * Test of addWhereClause method, of class HQLSimpleParser.
     */

    @Test
    public void testAddWhereClauseNew() {
        String hql = "SELECT DISTINCT e"
                + " FROM GardenBO e join e.products as product"
                + " ORDER BY e.name";
        HQLSimpleParser result = HQLSimpleParser.parseSimpleHql(hql);
        result.addWhereClause(null);

        result.addWhereClause("name like :name");
        String hqlExpected = "SELECT DISTINCT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE e.name like :name"
                + " ORDER BY e.name";

        Assert.assertEquals(result.getHQLQuery().replaceFirst("  ", " ").replaceFirst("  ", " ").trim(), hqlExpected.replaceFirst("  ", " ").trim());
    }

    /**
     * Test of parseSimpleHql method, of class HQLSimpleParser.
     */
    @Test
    public void testParseSimpleHql() {
        String hql = "SELECT DISTINCT e"
                + " FROM GardenBO e join e.products as product"
                + " WHERE product.referenceProduct= :refProduct"
                + " ORDER BY e.name";
        HQLSimpleParser result = HQLSimpleParser.parseSimpleHql(hql);

        Assert.assertEquals(result.getHQLQuery().replaceFirst("  ", " ").replaceFirst("  ", " ").trim(), hql.replaceFirst("  ", " ").trim());
    }
}
