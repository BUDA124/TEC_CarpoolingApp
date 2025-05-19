package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PersonalUserEntity;

import java.util.Optional;


@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUserEntity, Long> {
    Optional<PersonalUserEntity> findByUsername(String username);
}