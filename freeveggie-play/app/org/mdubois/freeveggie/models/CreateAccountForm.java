package org.mdubois.freeveggie.models;

import java.util.ArrayList;
import java.util.List;

import org.mdubois.freeveggie.service.msg.AddressMsg;
import org.mdubois.freeveggie.service.msg.CreateAccountMsg;
import org.mdubois.freeveggie.service.msg.SubscriptionMsg;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;

public class CreateAccountForm {

	@Required
	public String firstname;
	@Required
	public String lastname;
	@Required
	public String username;
	@Required
	@Email
	public String email;
	@Required
	@Email
	public String reemail;
	@Required
	public String password;
	@Required
	public String repassword;
	@Required
	public String address;

	public String streetNumber;

	public String street;

	public String locality;

	public String administrativeAreaLevel2;

	public String administrativeAreaLevel1;

	public String country;

	public String postalCode;

	public Double latitude;

	public Double longitude;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if (!email.equals(reemail)) {
			ValidationError error = new ValidationError("reemail", "Email not identical");
			errors.add(error);
			return errors;
		} else if (!password.equals(repassword)) {
			ValidationError error = new ValidationError("repassword", "Password not identical");
			errors.add(error);
			return errors;
		}
		return null;
	}

	public CreateAccountMsg getCreateAccountMsg() {
		CreateAccountMsg createAccountMsg = new CreateAccountMsg();
		createAccountMsg.setUsername(username);
		createAccountMsg.setFirstname(firstname);
		createAccountMsg.setLastname(lastname);
		createAccountMsg.setEmail(email);
		createAccountMsg.setPassword(password);

		// TODO : Put this in the interface for the user to be able to choose
		SubscriptionMsg subscriptionMsg = new SubscriptionMsg();
		subscriptionMsg.setFreeveggieAgreement(true);
		subscriptionMsg.setFreeveggieNewsletter(true);
		subscriptionMsg.setShareGardenInformation(true);
		subscriptionMsg.setSharePersonalInformation(true);

		createAccountMsg.setSubscription(subscriptionMsg);

		AddressMsg addressMsg = new AddressMsg();
		addressMsg.setAdministrativeAreaLevel1(administrativeAreaLevel1);
		addressMsg.setAdministrativeAreaLevel2(administrativeAreaLevel2);
		addressMsg.setCountry(country);
		addressMsg.setLocality(locality);
		addressMsg.setLongitude(longitude);
		addressMsg.setLatitude(latitude);
		addressMsg.setName("Home address");
		addressMsg.setPostalCode(postalCode);
		addressMsg.setStreetName(street);
		addressMsg.setStreetNumber(streetNumber);

		createAccountMsg.setAddressMsg(addressMsg);

		return createAccountMsg;
	}
}
