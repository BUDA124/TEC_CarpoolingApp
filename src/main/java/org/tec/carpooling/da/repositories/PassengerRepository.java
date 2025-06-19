package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;
import org.tec.carpooling.da.entities.PassengerEntity;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {
    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO(g.genderName, COUNT(p.id)) " +
            "FROM PassengerEntity pa JOIN pa.person p JOIN p.idGender g " +
            "WHERE p.idAuditLog.creationDate BETWEEN :startDate AND :endDate " +
            "GROUP BY g.genderName")
    List<ChartDataDTO> countPassengersByGenderInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}