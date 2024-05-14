package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Embedded
    private PhoneNumber number;

    @Embedded
    @Column(nullable = false)
    private Location location;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private String taxNumber;

    private String logo;

    private String tradeLicence;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String advantages;

    @Column(nullable = false)
    private String disadvantages;

    @Column(nullable = false)
    private Double distanceCost;

    @Column(nullable = false)
    private Double weightCost;

    @Column(nullable = false)
    private Double volumeCost;

    @ElementCollection
    private Set<String> pictures = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Track> tracks = new HashSet<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<WorkHour> workHours = new HashSet<>();
}