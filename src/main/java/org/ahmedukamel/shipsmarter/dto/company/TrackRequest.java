package org.ahmedukamel.shipsmarter.dto.company;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.shipsmarter.annotation.ValidRegionId;

public record TrackRequest(
        @NotNull
        @ValidRegionId
        Integer from,

        @NotNull
        @ValidRegionId
        Integer to,

        @NotNull
        Double cost
) {
}