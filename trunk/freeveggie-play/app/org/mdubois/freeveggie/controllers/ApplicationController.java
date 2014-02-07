package org.mdubois.freeveggie.controllers;

import org.mdubois.freeveggie.framework.play.Authenticated;

import play.cache.Cached;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.about;
import views.html.createaccount;
import views.html.home;
import views.html.requests;
import views.html.search;
import views.html.validateaccountcreation;

public class ApplicationController extends Controller {

    @Authenticated
    public static Result captcha() {
        return ok("" + (int ) Math.random() * 4);
    }

    @Authenticated
    public static Result search() {
        return ok(search.render());
    }

    @Authenticated
    public static Result requests() {
        return ok(requests.render());
    }

    @Authenticated
    public static Result home() {
        return ok(home.render());
    }

    @Authenticated
    public static Result about() {
        return ok(about.render());
    }

    public static Result blank() {
        return ok();
    }

    @Cached(key = "createAccountPage")
    public static Result createAccount() {
        return ok(createaccount.render());
    }

    public static Result validateAccountCreation() {
        return ok(validateaccountcreation.render());
    }
}
