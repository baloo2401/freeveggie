package org.mdubois.freeveggie.models;

import org.mdubois.freeveggie.service.msg.SubscriptionMsg;
import org.mdubois.freeveggie.service.msg.UserMsg;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

public class GeneralInformationForm {
	@Required
	public String firstname;
	@Required
	public String lastname;
	public String username;
	@Required
	@Email
	public String email;

	public UserMsg getCorrespondingUserMsg() {
		UserMsg userMsg = new UserMsg();
		userMsg.setFirstname(firstname);
		userMsg.setLastname(lastname);
		userMsg.setEmail(email);
		// TODO add this in the page for the user to be able to change values
		SubscriptionMsg subscription = new SubscriptionMsg();
		subscription.setFreeveggieAgreement(true);
		subscription.setFreeveggieNewsletter(true);
		subscription.setShareGardenInformation(true);
		subscription.setSharePersonalInformation(true);
		userMsg.setSubscription(subscription);
		return userMsg;
	}
}
