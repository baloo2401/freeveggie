package org.mdubois.freeveggie;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mdubois.freeveggie.criteria.GardenCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenCriteriaColumn;
import org.mdubois.freeveggie.criteria.GardenLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCommentCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductLikeCriteriaColumn;
import org.mdubois.freeveggie.criteria.ProductRequestCriteriaColumn;
import org.mdubois.freeveggie.criteria.RefProductCriteriaColumn;
import org.mdubois.freeveggie.criteria.RelationShipCriteriaColumn;
import org.mdubois.freeveggie.criteria.UserCriteriaColumn;

/**
 *
 * @author mdubois
 */
@RunWith(JMockit.class)
public class CriteriaTest {

    @Test
    public void testGardenCommentCriteriaColumn() {
        Assert.assertEquals("_note", GardenCommentCriteriaColumn.NOTE.getCriteriaColumn());
        Assert.assertEquals("_status", GardenCommentCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", GardenCommentCriteriaColumn.CREATION_DATE.getCriteriaColumn());
    }

    @Test
    public void testGardenCriteriaColumn() {
        Assert.assertEquals("owner", GardenCriteriaColumn.OWNER.getCriteriaColumn());
        Assert.assertEquals("_status", GardenCriteriaColumn.STATUS.getCriteriaColumn());
    }

    @Test
    public void testGardenLikeCriteriaColumn() {
        Assert.assertEquals("_status", GardenLikeCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", GardenLikeCriteriaColumn.CREATION_DATE.getCriteriaColumn());
    }

    @Test
    public void testProductCommentCriteriaColumn() {
        Assert.assertEquals("_status", ProductCommentCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", ProductCommentCriteriaColumn.CREATION_DATE.getCriteriaColumn());
        Assert.assertEquals("_note", ProductCommentCriteriaColumn.NOTE.getCriteriaColumn());
    }

    @Test
    public void testProductCriteriaColumn() {

        Assert.assertEquals("name", ProductCriteriaColumn.NAME.getCriteriaColumn());
        Assert.assertEquals("quantity", ProductCriteriaColumn.QUANTITY.getCriteriaColumn());
        Assert.assertEquals("_cultureType", ProductCriteriaColumn.CULTURE_TYPE.getCriteriaColumn());
        Assert.assertEquals("_cultureMode", ProductCriteriaColumn.CULTURE_MODE.getCriteriaColumn());
        Assert.assertEquals("_exchangeType", ProductCriteriaColumn.EXCHANGE_TYPE.getCriteriaColumn());
        Assert.assertEquals("_status", ProductCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("referenceProduct.name", ProductCriteriaColumn.REFERENCE_PRODUCT_NAME.getCriteriaColumn());
    }

    @Test
    public void testProductLikeCriteriaColumn() {
        Assert.assertEquals("_status", ProductLikeCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", ProductLikeCriteriaColumn.CREATION_DATE.getCriteriaColumn());
    }

    @Test
    public void testProductRequestCriteriaColumn() {

        Assert.assertEquals("requester.username", ProductRequestCriteriaColumn.REQUESTER.getCriteriaColumn());
        Assert.assertEquals("product.referenceProduct.name", ProductRequestCriteriaColumn.PRODUCT_REF.getCriteriaColumn());
        Assert.assertEquals("product.garden.owner.username", ProductRequestCriteriaColumn.PRODUCER.getCriteriaColumn());
        Assert.assertEquals("_status", ProductRequestCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", ProductRequestCriteriaColumn.CREATION_DATE.getCriteriaColumn());
        Assert.assertEquals("quantity", ProductRequestCriteriaColumn.QUANTITY.getCriteriaColumn());
        Assert.assertEquals("answerDate", ProductRequestCriteriaColumn.ANSWER_DATE.getCriteriaColumn());
        Assert.assertEquals("pickingDate", ProductRequestCriteriaColumn.PICKING_DATE.getCriteriaColumn());
    }

    @Test
    public void testRefProductCriteriaColumn() {

        Assert.assertEquals("name", RefProductCriteriaColumn.NAME.getCriteriaColumn());
        Assert.assertEquals("_type", RefProductCriteriaColumn.TYPE.getCriteriaColumn());
        Assert.assertEquals("protein", RefProductCriteriaColumn.PROTEIN.getCriteriaColumn());
        Assert.assertEquals("lipid", RefProductCriteriaColumn.LIPID.getCriteriaColumn());
        Assert.assertEquals("carbohydrate", RefProductCriteriaColumn.CARBOHYDRATE.getCriteriaColumn());
    }

    @Test
    public void testRelationShipCriteriaColumn() {

        Assert.assertEquals("_type", RelationShipCriteriaColumn.TYPE.getCriteriaColumn());
        Assert.assertEquals("_status", RelationShipCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", RelationShipCriteriaColumn.CREATION_DATE.getCriteriaColumn());
    }

    @Test
    public void testRequestStatus() {

        Assert.assertEquals("lastname", UserCriteriaColumn.LASTNAME.getCriteriaColumn());
        Assert.assertEquals("firstname", UserCriteriaColumn.FIRSTNAME.getCriteriaColumn());
        Assert.assertEquals("username", UserCriteriaColumn.USERNAME.getCriteriaColumn());
        Assert.assertEquals("lastConnexion", UserCriteriaColumn.LAST_CONNEXION.getCriteriaColumn());
        Assert.assertEquals("_status", UserCriteriaColumn.STATUS.getCriteriaColumn());
        Assert.assertEquals("creationDate", UserCriteriaColumn.CREATION_DATE.getCriteriaColumn());
        Assert.assertEquals("_role", UserCriteriaColumn.ROLE.getCriteriaColumn());
    }

}
