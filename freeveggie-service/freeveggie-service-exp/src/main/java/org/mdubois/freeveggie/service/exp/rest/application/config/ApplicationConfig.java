package org.mdubois.freeveggie.service.exp.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Mickael
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return getRestResourceClasses();
    }

    /**
     * Do not modify this method. It is automatically generated by NetBeans REST support.
     */
    private Set<Class<?>> getRestResourceClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        resources.add(org.mdubois.freeveggie.service.exp.rest.SecurityREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ProductRequestREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.GardenREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.AddressREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ProductREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.RelationshipRequestREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.UserREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ProfilREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.GardenCommentREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ProductCommentREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.GardenLikeREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ProductLikeREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.ReferenceREST.class);
        resources.add(org.mdubois.freeveggie.service.exp.rest.PictureREST.class);
        return resources;
    }
    
}
