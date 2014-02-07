package org.mdubois.freeveggie;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.order.*;

/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class OrderTest {

    @Test
    public void testAddressOrderColumn() {
        Assert.assertEquals(AddressOrderColumn.CITY_NAME.getOrderedColumn(), "city.name");
        Assert.assertEquals(AddressOrderColumn.CITY_ZIP_CODE.getOrderedColumn(), "city.zipCode");
        Assert.assertEquals(AddressOrderColumn.REGION.getOrderedColumn(), "city.region.name");
        Assert.assertEquals(AddressOrderColumn.STATE.getOrderedColumn(), "city.region.state.name");
        Assert.assertEquals(AddressOrderColumn.COUNTRY.getOrderedColumn(), "city.region.state.country.name");

    }

    @Test
    public void testGardenCommentOrderColumn() {
        Assert.assertEquals(GardenCommentOrderColumn.NOTE.getOrderedColumn(), "_note");
        Assert.assertEquals(GardenCommentOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(GardenCommentOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
    }

    @Test
    public void testGardenOrderColumn() {
        Assert.assertEquals(GardenOrderColumn.NAME.getOrderedColumn(), "name");
        Assert.assertEquals(GardenOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
    }

    @Test
    public void testGardenLikeOrderColumn() {
        Assert.assertEquals(GardenLikeOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(GardenLikeOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
    }

    @Test
    public void testProductCommentOrderColumn() {
        Assert.assertEquals(ProductCommentOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(ProductCommentOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
        Assert.assertEquals(ProductCommentOrderColumn.NOTE.getOrderedColumn(), "_note");
    }

    @Test
    public void testProductOrderColumn() {

        Assert.assertEquals(ProductOrderColumn.NAME.getOrderedColumn(), "name");
        Assert.assertEquals(ProductOrderColumn.QUANTITY.getOrderedColumn(), "quantity");
        Assert.assertEquals(ProductOrderColumn.CULTURE_TYPE.getOrderedColumn(), "_cultureType");
        Assert.assertEquals(ProductOrderColumn.CULTURE_MODE.getOrderedColumn(), "_cultureMode");
        Assert.assertEquals(ProductOrderColumn.EXCHANGE_TYPE.getOrderedColumn(), "_exchangeType");
        Assert.assertEquals(ProductOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(ProductOrderColumn.REFERENCE_PRODUCT_NAME.getOrderedColumn(), "referenceProduct.name");
    }

    @Test
    public void testProductLikeOrderColumn() {
        Assert.assertEquals(ProductLikeOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(ProductLikeOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
    }

    @Test
    public void testProductRequestOrderColumn() {

        Assert.assertEquals(ProductRequestOrderColumn.REQUESTER.getOrderedColumn(), "requester.username");
        Assert.assertEquals(ProductRequestOrderColumn.PRODUCT_REF.getOrderedColumn(), "product.referenceProduct.name");
        Assert.assertEquals(ProductRequestOrderColumn.PRODUCER.getOrderedColumn(), "product.garden.owner.username");
        Assert.assertEquals(ProductRequestOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(ProductRequestOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
        Assert.assertEquals(ProductRequestOrderColumn.QUANTITY.getOrderedColumn(), "quantity");
        Assert.assertEquals(ProductRequestOrderColumn.ANSWER_DATE.getOrderedColumn(), "answerDate");
        Assert.assertEquals(ProductRequestOrderColumn.PICKING_DATE.getOrderedColumn(), "pickingDate");
    }

    @Test
    public void testRefCityOrderColumn() {
        Assert.assertEquals(RefCityOrderColumn.NAME.getOrderedColumn(), "name");
        Assert.assertEquals(RefCityOrderColumn.ZIP_CODE.getOrderedColumn(), "zipCode");
    }

    @Test
    public void testRefCountryOrderColumn() {
        Assert.assertEquals(RefCountryOrderColumn.NAME.getOrderedColumn(), "name");
        Assert.assertEquals(RefCountryOrderColumn.CODE_ISO_A2.getOrderedColumn(), "codeIsoA2");
        Assert.assertEquals(RefCountryOrderColumn.CODE_ISO_A3.getOrderedColumn(), "codeIsoA3");
        Assert.assertEquals(RefCountryOrderColumn.CODE_ISO_NUMBER.getOrderedColumn(), "codeIsoNumber");
    }

    @Test
    public void testRefRegionOrderColumn() {
        Assert.assertEquals(RefRegionOrderColumn.NAME.getOrderedColumn(), "name");
    }

    @Test
    public void testRefStateOrderColumn() {
        Assert.assertEquals(RefStateOrderColumn.NAME.getOrderedColumn(), "name");
    }

    @Test
    public void testRefProductOrderColumn() {

        Assert.assertEquals(RefProductOrderColumn.NAME.getOrderedColumn(), "name");
        Assert.assertEquals(RefProductOrderColumn.TYPE.getOrderedColumn(), "_type");
        Assert.assertEquals(RefProductOrderColumn.PROTEIN.getOrderedColumn(), "protein");
        Assert.assertEquals(RefProductOrderColumn.LIPID.getOrderedColumn(), "lipid");
        Assert.assertEquals(RefProductOrderColumn.CARBOHYDRATE.getOrderedColumn(), "carbohydrate");
    }

    @Test
    public void testRelationShipOrderColumn() {

        Assert.assertEquals(RelationShipOrderColumn.TYPE.getOrderedColumn(), "_type");
        Assert.assertEquals(RelationShipOrderColumn.RECIPIENT.getOrderedColumn(), "recipient");
        Assert.assertEquals(RelationShipOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(RelationShipOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
    }

    @Test
    public void testRequestStatus() {

        Assert.assertEquals(UserOrderColumn.LASTNAME.getOrderedColumn(), "lastname");
        Assert.assertEquals(UserOrderColumn.FIRSTNAME.getOrderedColumn(), "firstname");
        Assert.assertEquals(UserOrderColumn.USERNAME.getOrderedColumn(), "username");
        Assert.assertEquals(UserOrderColumn.LAST_CONNEXION.getOrderedColumn(), "lastConnexion");
        Assert.assertEquals(UserOrderColumn.STATUS.getOrderedColumn(), "_status");
        Assert.assertEquals(UserOrderColumn.CREATION_DATE.getOrderedColumn(), "creationDate");
        Assert.assertEquals(UserOrderColumn.ROLE.getOrderedColumn(), "_role");
    }
}
