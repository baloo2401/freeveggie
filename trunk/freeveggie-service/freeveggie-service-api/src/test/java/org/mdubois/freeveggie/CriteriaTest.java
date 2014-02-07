package org.mdubois.freeveggie;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.criteria.*;

/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class CriteriaTest {

    @Test
    public void testGardenCommentCriteriaColumn() {
        Assert.assertEquals(GardenCommentCriteriaColumn.NOTE.getCriteriaColumn(), "_note");
        Assert.assertEquals(GardenCommentCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(GardenCommentCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
    }

    @Test
    public void testGardenCriteriaColumn() {
        Assert.assertEquals(GardenCriteriaColumn.OWNER.getCriteriaColumn(), "owner");
        Assert.assertEquals(GardenCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
    }

    @Test
    public void testGardenLikeCriteriaColumn() {
        Assert.assertEquals(GardenLikeCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(GardenLikeCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
    }

    @Test
    public void testProductCommentCriteriaColumn() {
        Assert.assertEquals(ProductCommentCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(ProductCommentCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
        Assert.assertEquals(ProductCommentCriteriaColumn.NOTE.getCriteriaColumn(), "_note");
    }

    @Test
    public void testProductCriteriaColumn() {

        Assert.assertEquals(ProductCriteriaColumn.NAME.getCriteriaColumn(), "name");
        Assert.assertEquals(ProductCriteriaColumn.QUANTITY.getCriteriaColumn(), "quantity");
        Assert.assertEquals(ProductCriteriaColumn.CULTURE_TYPE.getCriteriaColumn(), "_cultureType");
        Assert.assertEquals(ProductCriteriaColumn.CULTURE_MODE.getCriteriaColumn(), "_cultureMode");
        Assert.assertEquals(ProductCriteriaColumn.EXCHANGE_TYPE.getCriteriaColumn(), "_exchangeType");
        Assert.assertEquals(ProductCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(ProductCriteriaColumn.REFERENCE_PRODUCT_NAME.getCriteriaColumn(), "referenceProduct.name");
    }

    @Test
    public void testProductLikeCriteriaColumn() {
        Assert.assertEquals(ProductLikeCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(ProductLikeCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
    }

    @Test
    public void testProductRequestCriteriaColumn() {

        Assert.assertEquals(ProductRequestCriteriaColumn.REQUESTER.getCriteriaColumn(), "requester.username");
        Assert.assertEquals(ProductRequestCriteriaColumn.PRODUCT_REF.getCriteriaColumn(), "product.referenceProduct.name");
        Assert.assertEquals(ProductRequestCriteriaColumn.PRODUCER.getCriteriaColumn(), "product.garden.owner.username");
        Assert.assertEquals(ProductRequestCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(ProductRequestCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
        Assert.assertEquals(ProductRequestCriteriaColumn.QUANTITY.getCriteriaColumn(), "quantity");
        Assert.assertEquals(ProductRequestCriteriaColumn.ANSWER_DATE.getCriteriaColumn(), "answerDate");
        Assert.assertEquals(ProductRequestCriteriaColumn.PICKING_DATE.getCriteriaColumn(), "pickingDate");
    }

    @Test
    public void testRefProductCriteriaColumn() {

        Assert.assertEquals(RefProductCriteriaColumn.NAME.getCriteriaColumn(), "name");
        Assert.assertEquals(RefProductCriteriaColumn.TYPE.getCriteriaColumn(), "_type");
        Assert.assertEquals(RefProductCriteriaColumn.PROTEIN.getCriteriaColumn(), "protein");
        Assert.assertEquals(RefProductCriteriaColumn.LIPID.getCriteriaColumn(), "lipid");
        Assert.assertEquals(RefProductCriteriaColumn.CARBOHYDRATE.getCriteriaColumn(), "carbohydrate");
    }

    @Test
    public void testRelationShipCriteriaColumn() {

        Assert.assertEquals(RelationShipCriteriaColumn.TYPE.getCriteriaColumn(), "_type");
        Assert.assertEquals(RelationShipCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(RelationShipCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
    }

    @Test
    public void testRequestStatus() {

        Assert.assertEquals(UserCriteriaColumn.LASTNAME.getCriteriaColumn(), "lastname");
        Assert.assertEquals(UserCriteriaColumn.FIRSTNAME.getCriteriaColumn(), "firstname");
        Assert.assertEquals(UserCriteriaColumn.USERNAME.getCriteriaColumn(), "username");
        Assert.assertEquals(UserCriteriaColumn.LAST_CONNEXION.getCriteriaColumn(), "lastConnexion");
        Assert.assertEquals(UserCriteriaColumn.STATUS.getCriteriaColumn(), "_status");
        Assert.assertEquals(UserCriteriaColumn.CREATION_DATE.getCriteriaColumn(), "creationDate");
        Assert.assertEquals(UserCriteriaColumn.ROLE.getCriteriaColumn(), "_role");
    }

}
