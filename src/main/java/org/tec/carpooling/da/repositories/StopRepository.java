package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.StopEntity;


@Repository
public interface StopRepository extends JpaRepository<StopEntity, Long> {}