package org.ahmedukamel.shipsmarter.mapper.company;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.shipsmarter.dto.company.CompanyResponse;
import org.ahmedukamel.shipsmarter.model.Company;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyResponseMapper {
    final TrackResponseMapper trackMapper;
    final WorkHourDtoMapper workHourMapper;

    public CompanyResponse map(Company company) {
        return new CompanyResponse(
                company.getOwner().getName().toString(),
                company.getEmail(),
                company.getOwner().getRegistration(),
                company.getName(),
                company.getLocation().getLatitude(),
                company.getLocation().getLongitude(),
                company.getZipCode(),
                company.getTaxNumber(),
                company.getAbout(),
                company.getDescription(),
                company.getAdvantages(),
                company.getDisadvantages(),
                company.getDistanceCost(),
                company.getWeightCost(),
                company.getVolumeCost(),
                company.getLogo(),
                company.getTradeLicence(),
                company.getTracks().stream().map(trackMapper::map).toList(),
                company.getWorkHours().stream().map(workHourMapper::map).toList(),
                company.getPictures()
        );
    }
}