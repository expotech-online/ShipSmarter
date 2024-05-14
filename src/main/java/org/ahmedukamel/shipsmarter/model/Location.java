package org.ahmedukamel.shipsmarter.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    private Double latitude;
    private Double longitude;
}