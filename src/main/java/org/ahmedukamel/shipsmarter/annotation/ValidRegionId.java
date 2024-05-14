package org.ahmedukamel.shipsmarter.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.shipsmarter.validator.RegionIdValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = RegionIdValidator.class)
public @interface ValidRegionId {
    String message() default "Region not found.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}