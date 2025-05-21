package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PersonEntity; // Tu entidad Person
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findByFirstNameAndFirstSurname(String carlos, String rodríguez);

    Optional<PersonEntity> findByFirstNameAndFirstSurnameAndSecondSurname(String maría, String fernández, String castro);
}