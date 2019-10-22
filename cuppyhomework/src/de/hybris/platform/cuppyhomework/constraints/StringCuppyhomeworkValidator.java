//https://wiki.hybris.com/display/R5T/Trail+~+Validation
//https://wiki.hybris.com/display/release5/Data+Validation+Framework
        
package de.hybris.platform.cuppyhomework.constraints;

import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringCuppyhomeworkValidator implements ConstraintValidator<StringCuppyhomework, String> {
    private static final int VALIDATION_MAXIMAL_LENGHT = 20;

    @Override
    public void initialize(final StringCuppyhomework constraintAnnotation) {
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return value != null &&
                !StringUtils.isEmpty(value.trim()) &&
                value.matches(".*[a-zA-Z].*") &&
                StringUtils.isNotEmpty(value) &&
                value.length() <= VALIDATION_MAXIMAL_LENGHT;
    }
}