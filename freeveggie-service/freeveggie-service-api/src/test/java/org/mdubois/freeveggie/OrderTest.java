package org.mdubois.freeveggie;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.order.AddressOrderColumn;
import org.mdubois.freeveggie.order.GardenCommentOrderColumn;
import org.mdubois.freeveggie.order.GardenLikeOrderColumn;
import org.mdubois.freeveggie.order.GardenOrderColumn;
import org.mdubois.freeveggie.order.ProductCommentOrderColumn;
import org.mdubois.freeveggie.order.ProductLikeOrderColumn;
import org.mdubois.freeveggie.order.ProductOrderColumn;
import org.mdubois.freeveggie.order.ProductRequestOrderColumn;
import org.mdubois.freeveggie.order.RefCityOrderColumn;
import org.mdubois.freeveggie.order.RefCountryOrderColumn;
import org.mdubois.freeveggie.order.RefProductOrderColumn;
import org.mdubois.freeveggie.order.RefRegionOrderColumn;
import org.mdubois.freeveggie.order.RefStateOrderColumn;
import org.mdubois.freeveggie.order.RelationShipOrderColumn;
import org.mdubois.freeveggie.order.UserOrderColumn;

/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class OrderTest {

    @Test
    public void testAddressOrderColumn() {
        Assert.assertEquals("city.name", AddressOrderColumn.CITY_NAME.getOrderedColumn());
        Assert.assertEquals("city.zipCode", AddressOrderColumn.CITY_ZIP_CODE.getOrderedColumn());
        Assert.assertEquals("city.region.name", AddressOrderColumn.REGION.getOrderedColumn());
        Assert.assertEquals("city.region.state.name", AddressOrderColumn.STATE.getOrderedColumn());
        Assert.assertEquals("city.region.state.country.name", AddressOrderColumn.COUNTRY.getOrderedColumn());

    }

    @Test
    public void testGardenCommentOrderColumn() {
        Assert.assertEquals("_note", GardenCommentOrderColumn.NOTE.getOrderedColumn());
        Assert.assertEquals("_status", GardenCommentOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", GardenCommentOrderColumn.CREATION_DATE.getOrderedColumn());
    }

    @Test
    public void testGardenOrderColumn() {
        Assert.assertEquals("name", GardenOrderColumn.NAME.getOrderedColumn());
        Assert.assertEquals("creationDate", GardenOrderColumn.CREATION_DATE.getOrderedColumn());
    }

    @Test
    public void testGardenLikeOrderColumn() {
        Assert.assertEquals("_status", GardenLikeOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", GardenLikeOrderColumn.CREATION_DATE.getOrderedColumn());
    }

    @Test
    public void testProductCommentOrderColumn() {
        Assert.assertEquals("_status", ProductCommentOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", ProductCommentOrderColumn.CREATION_DATE.getOrderedColumn());
        Assert.assertEquals("_note", ProductCommentOrderColumn.NOTE.getOrderedColumn());
    }

    @Test
    public void testProductOrderColumn() {

        Assert.assertEquals("name", ProductOrderColumn.NAME.getOrderedColumn());
        Assert.assertEquals("quantity", ProductOrderColumn.QUANTITY.getOrderedColumn());
        Assert.assertEquals("_cultureType", ProductOrderColumn.CULTURE_TYPE.getOrderedColumn());
        Assert.assertEquals("_cultureMode", ProductOrderColumn.CULTURE_MODE.getOrderedColumn());
        Assert.assertEquals("_exchangeType", ProductOrderColumn.EXCHANGE_TYPE.getOrderedColumn());
        Assert.assertEquals("_status", ProductOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("referenceProduct.name", ProductOrderColumn.REFERENCE_PRODUCT_NAME.getOrderedColumn());
    }

    @Test
    public void testProductLikeOrderColumn() {
        Assert.assertEquals("_status", ProductLikeOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", ProductLikeOrderColumn.CREATION_DATE.getOrderedColumn());
    }

    @Test
    public void testProductRequestOrderColumn() {

        Assert.assertEquals("requester.username", ProductRequestOrderColumn.REQUESTER.getOrderedColumn());
        Assert.assertEquals("product.referenceProduct.name", ProductRequestOrderColumn.PRODUCT_REF.getOrderedColumn());
        Assert.assertEquals("product.garden.owner.username", ProductRequestOrderColumn.PRODUCER.getOrderedColumn());
        Assert.assertEquals("_status", ProductRequestOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", ProductRequestOrderColumn.CREATION_DATE.getOrderedColumn());
        Assert.assertEquals("quantity", ProductRequestOrderColumn.QUANTITY.getOrderedColumn());
        Assert.assertEquals("answerDate", ProductRequestOrderColumn.ANSWER_DATE.getOrderedColumn());
        Assert.assertEquals("pickingDate", ProductRequestOrderColumn.PICKING_DATE.getOrderedColumn());
    }

    @Test
    public void testRefCityOrderColumn() {
        Assert.assertEquals("name", RefCityOrderColumn.NAME.getOrderedColumn());
        Assert.assertEquals("zipCode", RefCityOrderColumn.ZIP_CODE.getOrderedColumn());
    }

    @Test
    public void testRefCountryOrderColumn() {
        Assert.assertEquals("name", RefCountryOrderColumn.NAME.getOrderedColumn());
        Assert.assertEquals("codeIsoA2", RefCountryOrderColumn.CODE_ISO_A2.getOrderedColumn());
        Assert.assertEquals("codeIsoA3", RefCountryOrderColumn.CODE_ISO_A3.getOrderedColumn());
        Assert.assertEquals("codeIsoNumber", RefCountryOrderColumn.CODE_ISO_NUMBER.getOrderedColumn());
    }

    @Test
    public void testRefRegionOrderColumn() {
        Assert.assertEquals("name", RefRegionOrderColumn.NAME.getOrderedColumn());
    }

    @Test
    public void testRefStateOrderColumn() {
        Assert.assertEquals("name", RefStateOrderColumn.NAME.getOrderedColumn());
    }

    @Test
    public void testRefProductOrderColumn() {

        Assert.assertEquals("name", RefProductOrderColumn.NAME.getOrderedColumn());
        Assert.assertEquals("_type", RefProductOrderColumn.TYPE.getOrderedColumn());
        Assert.assertEquals("protein", RefProductOrderColumn.PROTEIN.getOrderedColumn());
        Assert.assertEquals("lipid", RefProductOrderColumn.LIPID.getOrderedColumn());
        Assert.assertEquals("carbohydrate", RefProductOrderColumn.CARBOHYDRATE.getOrderedColumn());
    }

    @Test
    public void testRelationShipOrderColumn() {

        Assert.assertEquals("_type", RelationShipOrderColumn.TYPE.getOrderedColumn());
        Assert.assertEquals("recipient", RelationShipOrderColumn.RECIPIENT.getOrderedColumn());
        Assert.assertEquals("_status", RelationShipOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", RelationShipOrderColumn.CREATION_DATE.getOrderedColumn());
    }

    @Test
    public void testRequestStatus() {

        Assert.assertEquals("lastname", UserOrderColumn.LASTNAME.getOrderedColumn());
        Assert.assertEquals("firstname", UserOrderColumn.FIRSTNAME.getOrderedColumn());
        Assert.assertEquals("username", UserOrderColumn.USERNAME.getOrderedColumn());
        Assert.assertEquals("lastConnexion", UserOrderColumn.LAST_CONNEXION.getOrderedColumn());
        Assert.assertEquals("_status", UserOrderColumn.STATUS.getOrderedColumn());
        Assert.assertEquals("creationDate", UserOrderColumn.CREATION_DATE.getOrderedColumn());
        Assert.assertEquals("_role", UserOrderColumn.ROLE.getOrderedColumn());
    }
}
