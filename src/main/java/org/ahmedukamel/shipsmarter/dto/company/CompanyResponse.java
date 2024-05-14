package org.ahmedukamel.shipsmarter.dto.company;

import java.time.ZonedDateTime;
import java.util.Collection;

public record CompanyResponse(
        String ownerName,

        String email,

        ZonedDateTime registration,

        String name,

        Double latitude,

        Double longitude,

        Integer zipCode,

        String taxNumber,

        String about,

        String description,

        String advantages,

        String disadvantages,

        Double distanceCost,

        Double weightCost,

        Double volumeCost,

        String logo,

        String tradeLicence,

        Collection<TrackResponse> tracks,

        Collection<WorkHourDto> workHours,

        Collection<String> pictures
) {
}