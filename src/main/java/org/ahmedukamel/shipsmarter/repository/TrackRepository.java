package org.ahmedukamel.shipsmarter.repository;

import org.ahmedukamel.shipsmarter.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Track.TrackId> {
}