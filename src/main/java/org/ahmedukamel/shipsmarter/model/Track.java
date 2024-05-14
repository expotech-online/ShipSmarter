package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity
@Table(name = "tracks")
@IdClass(value = Track.TrackId.class)
public class Track {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Company company;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Region from;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Region to;

    @Column(nullable = false)
    private Double cost;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrackId implements Serializable {
        private Company company;
        private Region from;
        private Region to;
    }
}