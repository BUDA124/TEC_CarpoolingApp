package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PersonEntity;

public class PersonRepository extends BaseRepository<PersonEntity, Long> {

    public PersonRepository() {
        super(PersonEntity.class);
    }
}