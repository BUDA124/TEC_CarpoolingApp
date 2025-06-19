package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO;
import org.tec.carpooling.da.entities.PersonEntity; // Tu entidad Person

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByFirstNameAndFirstSurname(String carlos, String rodríguez);
    Optional<PersonEntity> findByFirstNameAndFirstSurnameAndSecondSurname(String maría, String fernández, String castro);
    @Query("SELECT new org.tec.carpooling.bl.dto.UI_BL.stats.ChartDataDTO(" +
            "CASE " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 0 AND 18 THEN '0-18 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 19 AND 30 THEN '19-30 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 31 AND 45 THEN '31-45 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 46 AND 60 THEN '46-60 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 61 AND 75 THEN '61-75 años' " +
            "  ELSE '>75 años' " +
            "END, COUNT(p.id)) " +
            "FROM PersonEntity p " +
            "WHERE (:genderName IS NULL OR p.idGender.genderName = :genderName) " +
            "GROUP BY CASE " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 0 AND 18 THEN '0-18 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 19 AND 30 THEN '19-30 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 31 AND 45 THEN '31-45 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 46 AND 60 THEN '46-60 años' " +
            "  WHEN (YEAR(CURRENT_DATE) - YEAR(p.birthdate)) BETWEEN 61 AND 75 THEN '61-75 años' " +
            "  ELSE '>75 años' " +
            "END")
    List<ChartDataDTO> countUsersByAgeGroup(@Param("genderName") String genderName);
}