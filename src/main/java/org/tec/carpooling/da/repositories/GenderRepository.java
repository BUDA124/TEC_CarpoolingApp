package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.GenderEntity;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    Optional<GenderEntity> findByGenderName(String genderName);
}