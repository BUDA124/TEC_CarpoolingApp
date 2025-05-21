package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.DailyReportEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;

import java.util.Optional;


@Repository
public interface DailyReportRepository extends JpaRepository<DailyReportEntity, Long> {
    Optional<DailyReportEntity> findByInstitution(InstitutionEntity institution);
}