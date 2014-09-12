package org.mdubois.freeveggie.models.model;

import javax.validation.*;

import play.libs.F.Tuple;

public class ProductRefIdValidator extends play.data.validation.Constraints.Validator<Object> implements
		ConstraintValidator<ProductRefId, Object> {

	/* Default error message */
	final static public String message = "Please select a valid reference product";

	/**
	 * Validator init Can be used to initialize the validation based on
	 * parameters passed to the annotation.
	 */
	@Override
	public void initialize(ProductRefId constraintAnnotation) {
	}

	/**
	 * The validation itself
	 */
	@Override
	public boolean isValid(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof Integer)) {
			return false;
		} else {
			Integer value = (Integer) object;
			if (value < 0) {
				return false;
			} else {
				return true;
			}
		}
	}

	/**
	 * Constructs a validator instance.
	 */
	public static play.data.validation.Constraints.Validator<Object> productrefid() {
		return new ProductRefIdValidator();
	}

	@Override
	public Tuple<String, Object[]> getErrorMessageKey() {
		Object[] o = new Object[1];
		o[1] = "constraint.productrefid";
		Tuple<String, Object[]> reponse = new Tuple<String, Object[]>("constraint.productrefid", o);
		return reponse;
	}
}