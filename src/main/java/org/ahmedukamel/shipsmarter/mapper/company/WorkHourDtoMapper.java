package org.ahmedukamel.shipsmarter.mapper.company;

import org.ahmedukamel.shipsmarter.dto.company.WorkHourDto;
import org.ahmedukamel.shipsmarter.model.WorkHour;
import org.springframework.stereotype.Component;

@Component
public class WorkHourDtoMapper {
    public WorkHourDto map(WorkHour workHour) {
        return new WorkHourDto(
                workHour.getWeekDay(),
                workHour.getOpen(),
                workHour.getClose()
        );
    }
}