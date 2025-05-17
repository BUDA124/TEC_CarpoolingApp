package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PriceStatusEntity;


@Repository
public interface PriceStatusRepository extends JpaRepository<PriceStatusEntity, Long> {}