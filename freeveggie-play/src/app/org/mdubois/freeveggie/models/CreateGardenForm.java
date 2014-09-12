package org.mdubois.freeveggie.models;

import org.mdubois.freeveggie.service.msg.GardenMsg;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;

public class CreateGardenForm {

	/**
	 * The name of the garden.
	 */
	@Required
	public String gardenName;

	/**
	 * Description
	 */
	@Required
	@MinLength(value = 10)
	@MaxLength(value = 512)
	public String description;

	public GardenMsg getCorrespondingGardenMsg() {
		GardenMsg gardenMsg = new GardenMsg();
		gardenMsg.setName(gardenName);
		gardenMsg.setDescription(description);
		return gardenMsg;
	}
}
