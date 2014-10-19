package org.mdubois.freeveggie.service.exp.rest.application.config;


import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.mdubois.freeveggie.service.exp.rest.AddressREST;
import org.mdubois.freeveggie.service.exp.rest.GardenCommentREST;
import org.mdubois.freeveggie.service.exp.rest.GardenLikeREST;
import org.mdubois.freeveggie.service.exp.rest.GardenREST;
import org.mdubois.freeveggie.service.exp.rest.PictureREST;
import org.mdubois.freeveggie.service.exp.rest.ProductCommentREST;
import org.mdubois.freeveggie.service.exp.rest.ProductLikeREST;
import org.mdubois.freeveggie.service.exp.rest.ProductREST;
import org.mdubois.freeveggie.service.exp.rest.ProductRequestREST;
import org.mdubois.freeveggie.service.exp.rest.ProfilREST;
import org.mdubois.freeveggie.service.exp.rest.ReferenceREST;
import org.mdubois.freeveggie.service.exp.rest.RelationshipRequestREST;
import org.mdubois.freeveggie.service.exp.rest.SecurityREST;
import org.mdubois.freeveggie.service.exp.rest.UserREST;
import org.mdubois.freeveggie.service.exp.rest.application.config.ApplicationConfig;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mickael
 */
public class ApplicationConfigTest {

    private final ApplicationConfig applicationContext = new ApplicationConfig();

    @Test
    public void testGetClasses() {
        Set<Class<?>> classes = applicationContext.getClasses();
        Assert.assertTrue(classes.contains(AddressREST.class));
        Assert.assertTrue(classes.contains(GardenCommentREST.class));
        Assert.assertTrue(classes.contains(GardenLikeREST.class));
        Assert.assertTrue(classes.contains(GardenREST.class));
        Assert.assertTrue(classes.contains(PictureREST.class));
        Assert.assertTrue(classes.contains(ProductCommentREST.class));
        Assert.assertTrue(classes.contains(ProductLikeREST.class));
        Assert.assertTrue(classes.contains(ProductREST.class));
        Assert.assertTrue(classes.contains(ProductRequestREST.class));
        Assert.assertTrue(classes.contains(ProfilREST.class));
        Assert.assertTrue(classes.contains(ReferenceREST.class));
        Assert.assertTrue(classes.contains(RelationshipRequestREST.class));
        Assert.assertTrue(classes.contains(SecurityREST.class));
        Assert.assertTrue(classes.contains(UserREST.class));

    }
}
