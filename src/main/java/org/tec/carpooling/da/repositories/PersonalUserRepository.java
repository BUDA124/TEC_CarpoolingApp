package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PersonalUserEntity;


@Repository
public interface PersonalUserRepository extends JpaRepository<PersonalUserEntity, Long> {
    boolean existsByUsername(String username);
    PersonalUserEntity findByUsername(String username);
}