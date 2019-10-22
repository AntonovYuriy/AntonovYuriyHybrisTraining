package de.hybris.platform.cuppyhomework.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = StringCuppyhomeworkValidator.class)
@Documented
public @interface StringCuppyhomework {
    String message() default "{de.hybris.platform.cuppyhomework.constraints.StringCuppyhomework.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
