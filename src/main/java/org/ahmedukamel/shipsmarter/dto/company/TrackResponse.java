package org.ahmedukamel.shipsmarter.dto.company;

public record TrackResponse(
        String fromCountry,
        String fromCity,
        String fromRegion,
        Integer fromId,
        String toCountry,
        String toCity,
        String toRegion,
        Integer toId,
        Double cost
) {
}