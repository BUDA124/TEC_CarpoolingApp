package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CantonEntity;
import org.tec.carpooling.da.entities.DistrictEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    Optional<DistrictEntity> findByNameAndCanton(String districtCarmenSanJose, CantonEntity sanJoseCanton);
    List<DistrictEntity> findByCanton_Province_Name(String provinceName);
    ArrayList<DistrictEntity> findByCantonIn(List<CantonEntity> allCantons);
}