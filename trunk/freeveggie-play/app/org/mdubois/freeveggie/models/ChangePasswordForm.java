package org.mdubois.freeveggie.models;

import java.util.ArrayList;
import java.util.List;

import org.mdubois.freeveggie.framework.security.PasswordValidator;
import org.mdubois.freeveggie.service.msg.ChangePasswordMsg;

import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;

public class ChangePasswordForm {

	/**
	 * The old password.
	 */
	@Required
	public String oldPassword;

	/**
	 * The new password.
	 */
	@Required
	public String newPassword;

	/**
	 * The retype new password.
	 */
	@Required
	public String retypeNewPassword;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if (!newPassword.equals(retypeNewPassword)) {
			ValidationError error = new ValidationError("newPassword", "Password not identical");
			errors.add(error);
			return errors;
		} else if (!PasswordValidator.isValid(newPassword)) {
			// Unvalid password : A password need to have at least 8 charactere
			// and have no space
			ValidationError error = new ValidationError("newPassword",
					"Unvalid password : A password need to have at least 8 charactere and have no space");
			errors.add(error);
			return errors;
		}
		return null;
	}

	public ChangePasswordMsg getCorrespondingChangePasswordMsg() {
		ChangePasswordMsg changePasswordMsg = new ChangePasswordMsg();
		changePasswordMsg.setNewPassword(newPassword);
		changePasswordMsg.setOldPassword(oldPassword);
		return changePasswordMsg;
	}
}
