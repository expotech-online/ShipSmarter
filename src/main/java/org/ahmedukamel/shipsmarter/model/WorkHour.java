package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "work_hours")
@IdClass(value = WorkHour.WorkHourId.class)
public class WorkHour {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Company company;

    @Id
    @Column(nullable = false, updatable = false)
    @Enumerated(value = EnumType.STRING)
    private WeekDay weekDay;

    @Column(nullable = false)
    private LocalTime open;

    @Column(nullable = false)
    private LocalTime close;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WorkHourId implements Serializable {
        private Company company;
        private WeekDay weekDay;
    }
}