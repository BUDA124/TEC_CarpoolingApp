package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.ContactPhoneNumberEntity;

public class ContactPhoneNumberRepository extends BaseRepository<ContactPhoneNumberEntity, Long> {

    public ContactPhoneNumberRepository() {
        super(ContactPhoneNumberEntity.class);
    }
}