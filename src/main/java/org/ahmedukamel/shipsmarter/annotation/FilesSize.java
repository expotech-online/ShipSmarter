package org.ahmedukamel.shipsmarter.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.shipsmarter.validator.FilesSizeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Constraint(validatedBy = FilesSizeValidator.class)
public @interface FilesSize {
    int size();

    String message() default "Maximum size exceeded.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}