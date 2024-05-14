package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String english;

    @Column(nullable = false)
    private String arabic;

    @Column(nullable = false)
    private String french;

    @Embedded
    private Location location;

    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;
}