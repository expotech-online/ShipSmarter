package org.ahmedukamel.shipsmarter.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.shipsmarter.validator.NotEmptyFilesValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.PARAMETER)
@Constraint(validatedBy = NotEmptyFilesValidator.class)
public @interface NotEmptyFiles {
    String message() default "Files is empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}