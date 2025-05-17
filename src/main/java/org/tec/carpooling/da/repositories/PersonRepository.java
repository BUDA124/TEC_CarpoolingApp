package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.PersonEntity; // Tu entidad Person
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> { // Asume que Person.id es Long
    // Métodos CRUD básicos (save, findById, findAll, delete, etc.) son heredados.

    // (Spring Data JPA lo implementa)
    Optional<PersonEntity> findByFirstNameAndFirstSurname(String firstName, String firstSurname);

    // Puedes añadir más métodos de consulta basados en convenciones de nombres
    // o usando @Query para JPQL o SQL nativo.
}