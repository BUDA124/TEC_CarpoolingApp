package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CantonEntity;
import org.tec.carpooling.da.entities.DistrictEntity;

import java.util.Optional;


@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findByNameAndCanton(String districtCarmenSanJose, CantonEntity sanJoseCanton);
}