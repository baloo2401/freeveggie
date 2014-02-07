package org.mdubois.freeveggie.models;

import java.util.ArrayList;
import java.util.List;

import org.mdubois.freeveggie.CultureMode;
import org.mdubois.freeveggie.CultureType;
import org.mdubois.freeveggie.ExchangeType;
import org.mdubois.freeveggie.models.model.ProductRefId;
import org.mdubois.freeveggie.service.msg.GardenMsg;
import org.mdubois.freeveggie.service.msg.PartialUserMsg;
import org.mdubois.freeveggie.service.msg.ProductMsg;
import org.mdubois.freeveggie.service.msg.RefProductMsg;

import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;

public class EditProductForm {

	@Required
	public Long productId;
	/**
	 * The ref product id.
	 */
	@ProductRefId
	public Integer refProductId;

	/**
	 * The name of the product.
	 */
	@Required
	public String name;

	/**
	 * The culture mode
	 */
	@Required
	public String cultureMode;

	/**
	 * The culture type
	 */
	@Required
	public String cultureType;

	/**
	 * The echange type
	 */
	@Required
	public String exchangeType;

	/**
	 * The quantity
	 */
	@Required
	public Float quantity;

	/**
	 * The description
	 */
	@Required
	public String description;

	public List<ValidationError> validate() {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if (refProductId == -1) {
			ValidationError error = new ValidationError("refProductId", "Please select a known product");
			errors.add(error);
			return errors;
		}
		return null;
	}

	public ProductMsg getCorrespondingProductMsg() {
		ProductMsg productMsg = new ProductMsg();
		GardenMsg gardenMsg = new GardenMsg();
		productMsg.setId(productId);
		RefProductMsg refProductMsg = new RefProductMsg();
		refProductMsg.setId(Integer.valueOf(this.refProductId));
		gardenMsg.setOwner(new PartialUserMsg());
		productMsg.setCultureMode(CultureMode.fromInt(CultureMode.valueOf(this.cultureMode).getValue()));
		productMsg.setCultureType(CultureType.fromInt(CultureType.valueOf(this.cultureType).getValue()));
		productMsg.setDescription(this.description);
		productMsg.setExchangeType(ExchangeType.fromInt(ExchangeType.valueOf(this.exchangeType).getValue()));
		productMsg.setGarden(gardenMsg);
		productMsg.setName(this.name);
		productMsg.setQuantity(this.quantity.floatValue());
		productMsg.setReferenceProduct(refProductMsg);
		return productMsg;
	}
}
