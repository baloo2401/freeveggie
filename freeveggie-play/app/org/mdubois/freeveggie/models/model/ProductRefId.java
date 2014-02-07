package org.mdubois.freeveggie.models.model;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import javax.validation.*;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ProductRefIdValidator.class)
@play.data.Form.Display(name = "constraint.productrefid")
public @interface ProductRefId {
	String message() default ProductRefIdValidator.message;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}