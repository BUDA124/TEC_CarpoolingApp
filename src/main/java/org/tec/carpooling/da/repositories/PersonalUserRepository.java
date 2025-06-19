package org.tec.carpooling.da.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopUserDTO;
import org.tec.carpooling.da.entities.PersonalUserEntity;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUserEntity, Long> {
    Optional<PersonalUserEntity> findByUsername(String username);
    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.generalQueries.TopUserDTO(p.firstName, p.firstSurname, COUNT(pjt.trip.id)) " +
            "FROM PassengerJoinTripEntity pjt JOIN pjt.personalUser u JOIN u.person p " +
            "GROUP BY u.id, p.firstName, p.firstSurname " +
            "ORDER BY COUNT(pjt.trip.id) DESC")
    Page<TopUserDTO> findTopActiveUsers(Pageable pageable);

    @Query("SELECT COUNT(u.id) FROM PersonalUserEntity u WHERE u.registrationDate >= :date")
    Long countNewUsersSince(@Param("date") LocalDate date);
}