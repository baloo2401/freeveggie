package org.mdubois.freeveggie.controllers;

import static play.data.Form.form;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.mdubois.freeveggie.framework.play.Authenticated;
import org.mdubois.freeveggie.models.CreateProductForm;
import org.mdubois.freeveggie.models.EditProductForm;
import org.mdubois.freeveggie.service.msg.ProductMsg;

import play.cache.Cached;
import play.data.Form;
import play.mvc.Result;

@Authenticated
public class ProductController extends FreeveggieController {

    /**
     * Name resource service.
     */
    private static final String WEB_SERVICE_PATH = "/product";

    /**
     * Service endpoint.
     */
    public static final String SERVICE_PATH = getFreeveggieEndPointURL() + WEB_SERVICE_PATH;

    @Cached(key = "ProductController.getProductById")
    public static Result getProductById(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
        return getAsyncFreeveggieRestService(feedUrl);
    }

    @Cached(key = "ProductController.getProduct")
    public static Result getProduct() throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + String.format("/%1$s", request().getQueryString("id"));
        return getAsyncFreeveggieRestService(feedUrl);
    }

    public static Result getProductByGarden(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + String.format("/garden/%1$s", id);
        return getAsyncFreeveggieRestService(feedUrl);
    }

    public static Result deleteProduct(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
        String feedUrl = SERVICE_PATH + String.format("/%1$s", id);
        return deleteAsyncFreeveggieRestService(feedUrl);
    }

    public static Result addProductToGarden(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
        Form<CreateProductForm> createProductFormPost = form(CreateProductForm.class).bindFromRequest();
        if (createProductFormPost.hasErrors()) {
            return badRequest(createProductFormPost.errorsAsJson());
        } else {
            CreateProductForm createProductForm = createProductFormPost.get();
            ProductMsg productMsg = createProductForm.getCorrespondingProductMsg();
            productMsg.getGarden().setId(id);
            return postAsyncFreeveggieRestService(SERVICE_PATH, productMsg);
        }
    }

    public static Result updateProductById(final Long id) throws JsonGenerationException, JsonMappingException, IOException {
        Form<EditProductForm> createProductFormPost = form(EditProductForm.class).bindFromRequest();
        if (createProductFormPost.hasErrors()) {
            return badRequest(createProductFormPost.errorsAsJson());
        } else {
            EditProductForm createProductForm = createProductFormPost.get();
            ProductMsg productMsg = createProductForm.getCorrespondingProductMsg();
            return putAsyncFreeveggieRestService(SERVICE_PATH, productMsg);
        }
    }
}
