package org.ahmedukamel.shipsmarter.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.shipsmarter.annotation.NotEmptyFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class NotEmptyFileValidator implements ConstraintValidator<NotEmptyFile, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(file) && !file.isEmpty();
    }
}