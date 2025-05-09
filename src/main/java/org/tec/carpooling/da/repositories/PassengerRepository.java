package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.PassengerEntity;

public class PassengerRepository extends BaseRepository<PassengerEntity, Long> {

    public PassengerRepository() {
        super(PassengerEntity.class);
    }
}