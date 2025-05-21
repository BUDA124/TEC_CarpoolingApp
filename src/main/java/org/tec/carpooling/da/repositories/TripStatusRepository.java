package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.TripStatusEntity;

import java.util.Optional;


@Repository
public interface TripStatusRepository extends JpaRepository<TripStatusEntity, Long> {
    Optional<TripStatusEntity> findByStatus(String statusName);
}