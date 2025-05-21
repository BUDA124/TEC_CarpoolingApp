package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PriceStatusEntity;

import java.util.Optional;


@Repository
public interface PriceStatusRepository extends JpaRepository<PriceStatusEntity, Long> {
    Optional<PriceStatusEntity> findByStatus(String statusName);
}