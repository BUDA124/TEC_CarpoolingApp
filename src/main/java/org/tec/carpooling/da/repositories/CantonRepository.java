package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CantonEntity;
import org.tec.carpooling.da.entities.ProvinceEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface CantonRepository extends JpaRepository<CantonEntity, Long> {
    Optional<CantonEntity> findByNameAndProvince(String cantonSanJoseCentral, ProvinceEntity sanJoseProvince);

    ArrayList<CantonEntity> findByProvinceIn(List<ProvinceEntity> allProvinces);
}