package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.ContactEmailEntity;

public class ContactEmailRepository extends BaseRepository<ContactEmailEntity, Long> {

    public ContactEmailRepository() {
        super(ContactEmailEntity.class);
    }
}