package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PersonalUserEntity;

public class PersonalUserRepository extends BaseRepository<PersonalUserEntity, Long> {

    public PersonalUserRepository() {
        super(PersonalUserEntity.class);
    }
}