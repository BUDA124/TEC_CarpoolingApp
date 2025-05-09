package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.EmailEntity;

public class EmailRepository extends BaseRepository<EmailEntity, Long> {

    public EmailRepository() {
        super(EmailEntity.class);
    }
}