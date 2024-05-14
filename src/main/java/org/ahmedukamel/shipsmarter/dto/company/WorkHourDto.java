package org.ahmedukamel.shipsmarter.dto.company;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.shipsmarter.model.WeekDay;

import java.time.LocalTime;

public record WorkHourDto(
        @NotNull
        WeekDay weekDay,

        @NotNull
        LocalTime open,

        @NotNull
        LocalTime close
) {
}