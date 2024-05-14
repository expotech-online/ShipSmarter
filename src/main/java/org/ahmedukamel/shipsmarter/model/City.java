package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String english;

    @Column(nullable = false)
    private String arabic;

    @Column(nullable = false)
    private String french;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Region> regions = new HashSet<>();
}