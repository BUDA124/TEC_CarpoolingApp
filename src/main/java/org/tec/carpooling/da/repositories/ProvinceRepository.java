package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CountryEntity;
import org.tec.carpooling.da.entities.ProvinceEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;


@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceEntity, Long> {
    Optional<ProvinceEntity> findByNameAndCountry(String provinceSanJose, CountryEntity costaRica);

    ArrayList<ProvinceEntity> findByCountry(CountryEntity costaRica);
}