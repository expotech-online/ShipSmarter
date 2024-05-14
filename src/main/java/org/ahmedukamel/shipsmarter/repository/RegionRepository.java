package org.ahmedukamel.shipsmarter.repository;

import org.ahmedukamel.shipsmarter.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}