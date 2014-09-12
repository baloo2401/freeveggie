package org.mdubois.freeveggie.controllers;



import play.mvc.Result;
import views.html.error;

public class ErrorController extends FreeveggieController {

	public static Result error() {
		String errorMessage = flash("errorMessage");
		session().clear();
		if (errorMessage == null) {
			return ok(error.render(""));
		} else {
			return ok(error.render(errorMessage));
		}
	}
}
