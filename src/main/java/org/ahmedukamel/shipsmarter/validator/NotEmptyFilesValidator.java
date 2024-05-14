package org.ahmedukamel.shipsmarter.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.shipsmarter.annotation.NotEmptyFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class NotEmptyFilesValidator implements ConstraintValidator<NotEmptyFiles, MultipartFile[]> {
    @Override
    public boolean isValid(MultipartFile[] multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(multipartFiles) && !multipartFiles[0].isEmpty();
    }
}