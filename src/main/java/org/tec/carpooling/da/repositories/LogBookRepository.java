package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.LogBookEntity;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface LogBookRepository extends JpaRepository<LogBookEntity, Long> {
    Optional<LogBookEntity> findByLogTimeAndDescription(LocalDateTime logTime, String description);
}