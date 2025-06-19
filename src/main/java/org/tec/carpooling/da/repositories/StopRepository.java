package org.tec.carpooling.da.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopStopDTO;
import org.tec.carpooling.da.entities.DistrictEntity;
import org.tec.carpooling.da.entities.StopEntity;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface StopRepository extends JpaRepository<StopEntity, Long> {
    Optional<StopEntity> findByAddressAndDistrict(String address, DistrictEntity district);

    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopStopDTO(s.address, COUNT(ths.stop.id)) " +
            "FROM TripHasStopEntity ths JOIN ths.stop s JOIN ths.trip t " +
            "WHERE t.departureDateTime BETWEEN :startDate AND :endDate " +
            "GROUP BY s.id, s.address " +
            "ORDER BY COUNT(ths.stop.id) DESC")
    Page<TopStopDTO> findTopConcurrentStops(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);
}