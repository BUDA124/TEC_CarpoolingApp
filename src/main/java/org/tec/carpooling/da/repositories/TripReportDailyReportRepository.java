package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.DailyReportEntity;
import org.tec.carpooling.da.entities.TripEntity;
import org.tec.carpooling.da.entities.TripReportDailyReportEntity;

import java.util.Optional;


@Repository
public interface TripReportDailyReportRepository extends JpaRepository<TripReportDailyReportEntity, Long> {
    Optional<TripReportDailyReportEntity> findByTripAndDailyReport(TripEntity tripEntity, DailyReportEntity dailyReportEntity);
}