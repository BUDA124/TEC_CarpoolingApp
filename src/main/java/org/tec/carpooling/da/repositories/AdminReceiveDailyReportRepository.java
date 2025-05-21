package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.AdminReceiveDailyReportEntity;
import org.tec.carpooling.da.entities.AdministratorEntity;
import org.tec.carpooling.da.entities.DailyReportEntity;

import java.util.Optional;

@Repository
public interface AdminReceiveDailyReportRepository extends JpaRepository<AdminReceiveDailyReportEntity, Long> {
    Optional<AdminReceiveDailyReportEntity> findByAdministratorAndDailyReport(AdministratorEntity administratorEntity, DailyReportEntity dailyReportEntity);
}