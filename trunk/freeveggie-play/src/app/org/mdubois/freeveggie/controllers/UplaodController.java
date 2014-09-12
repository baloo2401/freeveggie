package org.mdubois.freeveggie.controllers;

import java.io.File;
import java.util.UUID;

import play.Play;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

public class UplaodController extends FreeveggieController {

	private static final String UPLOAD_FILE_LOCATION = Play.application().configuration()
			.getString("upload.file.location");

	public static Result uploadGardenPicture(final Long pGardenId) {
		session("connected", "123");
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("fileToUpload");
		if (picture != null) {
			String fileName = picture.getFilename();
			// String contentType = picture.getContentType();
			File file = new File(picture.getFile(), "/public/images/user/" + contextId() + "/garden/" + pGardenId + "/"
					+ UUID.randomUUID() + fileName.substring(fileName.lastIndexOf(".")).toLowerCase());
			System.out.println(file.getAbsolutePath());
			file.setExecutable(false);
			return ok();
		} else {
			return badRequest();
		}
	}

	public static Result uploadProductPicture(final Long pProductId) {
		File file = request().body().asRaw().asFile();
		return ok();
	}
}
