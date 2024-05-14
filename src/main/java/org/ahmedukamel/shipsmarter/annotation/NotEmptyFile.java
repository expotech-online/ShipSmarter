package org.ahmedukamel.shipsmarter.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.shipsmarter.validator.NotEmptyFileValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Constraint(validatedBy = NotEmptyFileValidator.class)
public @interface NotEmptyFile {
    String message() default "File is empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}