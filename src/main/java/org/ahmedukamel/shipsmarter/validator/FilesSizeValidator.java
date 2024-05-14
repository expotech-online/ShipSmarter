package org.ahmedukamel.shipsmarter.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.shipsmarter.annotation.FilesSize;
import org.springframework.web.multipart.MultipartFile;

public class FilesSizeValidator implements ConstraintValidator<FilesSize, MultipartFile[]> {
    private int size;

    @Override
    public void initialize(FilesSize constraintAnnotation) {
        size = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(MultipartFile[] multipartFiles, ConstraintValidatorContext constraintValidatorContext) {
        return 1 <= multipartFiles.length && multipartFiles.length <= size;
    }
}