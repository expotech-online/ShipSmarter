package org.ahmedukamel.shipsmarter.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.shipsmarter.annotation.ValidRegionId;
import org.ahmedukamel.shipsmarter.repository.RegionRepository;

@RequiredArgsConstructor
public class RegionIdValidator implements ConstraintValidator<ValidRegionId, Integer> {
    final RegionRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return repository.existsById(id);
    }
}