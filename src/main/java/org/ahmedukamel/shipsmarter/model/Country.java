package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String english;

    @Column(nullable = false)
    private String arabic;

    @Column(nullable = false)
    private String french;

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new HashSet<>();
}