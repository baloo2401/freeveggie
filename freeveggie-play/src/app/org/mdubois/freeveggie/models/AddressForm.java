package org.mdubois.freeveggie.models;

import org.mdubois.freeveggie.service.msg.AddressMsg;

import play.data.validation.Constraints.Required;

public class AddressForm {
	public Long addressId;
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

	public AddressMsg getCorrespondingAddressMsg() {
		AddressMsg addressMsg = new AddressMsg();
		addressMsg.setId(addressId);
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

		return addressMsg;
	}
}
