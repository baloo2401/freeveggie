package org.mdubois.freeveggie;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.framework.security.UserRole;



/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class EnumTest{

    @Test
    public void testCultureMode() {

        Assert.assertEquals(CultureMode.GARDEN, CultureMode.fromInt(1));
        Assert.assertEquals(CultureMode.BALCONY, CultureMode.fromInt(2));
        Assert.assertEquals(CultureMode.TERRACE, CultureMode.fromInt(3));
        Assert.assertEquals(CultureMode.UNKNOWN, CultureMode.fromInt(99));
        Assert.assertEquals((int)99, (int)CultureMode.UNKNOWN.toInt());
    }

    @Test
    public void testCultureType() {

        Assert.assertEquals(CultureType.REGULAR, CultureType.fromInt(1));
        Assert.assertEquals(CultureType.BIO, CultureType.fromInt(2));
        Assert.assertEquals(CultureType.GMO, CultureType.fromInt(3));
        Assert.assertEquals(CultureType.UNKNOWN, CultureType.fromInt(99));
        Assert.assertEquals((int)99, (int)CultureType.UNKNOWN.toInt());
    }

    @Test
    public void testEvaluationNote() {

        Assert.assertEquals(EvaluationNote.VERYBAD, EvaluationNote.fromInt(1));
        Assert.assertEquals(EvaluationNote.BAD, EvaluationNote.fromInt(2));
        Assert.assertEquals(EvaluationNote.NORMAL, EvaluationNote.fromInt(3));
        Assert.assertEquals(EvaluationNote.GOOD, EvaluationNote.fromInt(4));
        Assert.assertEquals(EvaluationNote.VERYGOOD, EvaluationNote.fromInt(5));
        Assert.assertEquals(EvaluationNote.UNKNOWN, EvaluationNote.fromInt(99));
        Assert.assertEquals((int)99, (int)EvaluationNote.UNKNOWN.toInt());
    }

    @Test
    public void testEvaluationStatus() {

        Assert.assertEquals(EvaluationStatus.SETTED, EvaluationStatus.fromInt(1));
        Assert.assertEquals(EvaluationStatus.REMOVED, EvaluationStatus.fromInt(2));
        Assert.assertEquals(EvaluationStatus.ARCHIVED, EvaluationStatus.fromInt(3));
        Assert.assertEquals(EvaluationStatus.UNKNOWN, EvaluationStatus.fromInt(99));
        Assert.assertEquals((int)99, (int)EvaluationStatus.UNKNOWN.toInt());
    }

    @Test
    public void testExchangeType() {

        Assert.assertEquals(ExchangeType.SHARES, ExchangeType.fromInt(1));
        Assert.assertEquals(ExchangeType.GIVES, ExchangeType.fromInt(2));
        Assert.assertEquals(ExchangeType.SELLS, ExchangeType.fromInt(3));
        Assert.assertEquals(ExchangeType.UNKNOWN, ExchangeType.fromInt(99));
        Assert.assertEquals((int)99, (int)ExchangeType.UNKNOWN.toInt());
    }

    @Test
    public void testProductType() {

        Assert.assertEquals(ProductType.FRUIT, ProductType.fromInt(1));
        Assert.assertEquals(ProductType.VEGETABLE, ProductType.fromInt(2));
        Assert.assertEquals(ProductType.UNKNOWN, ProductType.fromInt(99));
        Assert.assertEquals((int)99, (int)ProductType.UNKNOWN.toInt());
    }

    @Test
    public void testRefMonth() {

        Assert.assertEquals(Month.JANUARY, Month.fromInt(1));
        Assert.assertEquals(Month.FEBRUARY, Month.fromInt(2));
        Assert.assertEquals(Month.MARCH, Month.fromInt(3));
        Assert.assertEquals(Month.APRIL, Month.fromInt(4));
        Assert.assertEquals(Month.MAY, Month.fromInt(5));
        Assert.assertEquals(Month.JUNE, Month.fromInt(6));
        Assert.assertEquals(Month.JULY, Month.fromInt(7));
        Assert.assertEquals(Month.AUGUST, Month.fromInt(8));
        Assert.assertEquals(Month.SEPTEMBER, Month.fromInt(9));
        Assert.assertEquals(Month.OCTOBER, Month.fromInt(10));
        Assert.assertEquals(Month.NOVEMBER, Month.fromInt(11));
        Assert.assertEquals(Month.DECEMBER, Month.fromInt(12));
        Assert.assertEquals(Month.UNKNOWN, Month.fromInt(99));
        Assert.assertEquals(Integer.valueOf(99), Month.UNKNOWN.toInt());
    }

    @Test
    public void testRelationshipStatus() {

        Assert.assertEquals(RelationshipStatus.PENDING, RelationshipStatus.fromInt(2));
        Assert.assertEquals(RelationshipStatus.VALIDED, RelationshipStatus.fromInt(3));
        Assert.assertEquals(RelationshipStatus.REFUSED, RelationshipStatus.fromInt(4));
        Assert.assertEquals(RelationshipStatus.UNKNOWN, RelationshipStatus.fromInt(99));
        Assert.assertEquals((int)99, (int)RelationshipStatus.UNKNOWN.toInt());
    }

    @Test
    public void testRelationshipType() {

        Assert.assertEquals(RelationshipType.FREEVEGGIE_FRIEND, RelationshipType.fromInt(1));
        Assert.assertEquals(RelationshipType.FRIEND, RelationshipType.fromInt(2));
        Assert.assertEquals(RelationshipType.NEIGHBOR, RelationshipType.fromInt(3));
        Assert.assertEquals(RelationshipType.UNKNOWN, RelationshipType.fromInt(99));
        Assert.assertEquals((int)99, (int)RelationshipType.UNKNOWN.toInt());
    }

    @Test
    public void testRequestStatus() {

        Assert.assertEquals(RequestStatus.PENDING, RequestStatus.fromInt(2));
        Assert.assertEquals(RequestStatus.ACCEPTED, RequestStatus.fromInt(3));
        Assert.assertEquals(RequestStatus.REFUSED, RequestStatus.fromInt(4));
        Assert.assertEquals(RequestStatus.UNKNOWN, RequestStatus.fromInt(99));
        Assert.assertEquals((int)99, (int)RequestStatus.UNKNOWN.toInt());
    }

    @Test
    public void testStatus() {

        Assert.assertEquals(Status.CREATED, Status.fromInt(1));
        Assert.assertEquals(Status.DELETED, Status.fromInt(2));
        Assert.assertEquals(Status.ARCHIVED, Status.fromInt(3));
        Assert.assertEquals(Status.BLACKLISTED, Status.fromInt(4));
        Assert.assertEquals(Status.UNKNOWN, Status.fromInt(99));
        Assert.assertEquals((int)99, (int)Status.UNKNOWN.toInt());
    }

    @Test
    public void testUserStatus() {

        Assert.assertEquals(UserStatus.NEW, UserStatus.fromInt(1));
        Assert.assertEquals(UserStatus.VALIDED, UserStatus.fromInt(2));
        Assert.assertEquals(UserStatus.BLACKLISTED, UserStatus.fromInt(3));
        Assert.assertEquals(UserStatus.DELETED, UserStatus.fromInt(4));
        Assert.assertEquals(UserStatus.ARCHIVED, UserStatus.fromInt(5));
        Assert.assertEquals(UserStatus.UNKNOWN, UserStatus.fromInt(99));
        Assert.assertEquals((int)99, (int)UserStatus.UNKNOWN.toInt());
    }

    @Test
    public void testUserRole() {

        Assert.assertEquals(UserRole.USER, UserRole.fromInt(1));
        Assert.assertEquals(UserRole.MANAGER, UserRole.fromInt(2));
        Assert.assertEquals(UserRole.ADMIN, UserRole.fromInt(3));
        Assert.assertEquals(UserRole.SUPERADMIN, UserRole.fromInt(4));
        Assert.assertEquals(UserRole.SYSTEM, UserRole.fromInt(5));
        Assert.assertEquals(UserRole.UNKNOWN, UserRole.fromInt(99));
        Assert.assertEquals((int)99, (int)UserRole.UNKNOWN.toInt());
    }
}
