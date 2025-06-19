package org.tec.carpooling.da.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopDriverDTO;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;
import org.tec.carpooling.da.entities.DriverEntity;
import org.tec.carpooling.da.entities.TripEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {
    Optional<TripEntity> findByDriverAndDepartureDateTime(DriverEntity driverEntity, LocalDateTime of);

    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO(CAST(FUNCTION('HOUR', t.departureDateTime) AS string), COUNT(t.id)) " +
            "FROM TripEntity t " +
            "GROUP BY FUNCTION('HOUR', t.departureDateTime) " +
            "ORDER BY FUNCTION('HOUR', t.departureDateTime) ASC")
    List<ChartDataDTO> countTripsByHour();


    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopDriverDTO(p.firstName, p.firstSurname, COUNT(t.id)) " +
            "FROM TripEntity t JOIN t.driver d JOIN d.person p " +
            "GROUP BY p.id, p.firstName, p.firstSurname " +
            "ORDER BY COUNT(t.id) DESC")
    Page<TopDriverDTO> findTopDrivers(Pageable pageable);

}