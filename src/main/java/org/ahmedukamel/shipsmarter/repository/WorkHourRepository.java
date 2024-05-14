package org.ahmedukamel.shipsmarter.repository;

import org.ahmedukamel.shipsmarter.model.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour, WorkHour.WorkHourId> {
}