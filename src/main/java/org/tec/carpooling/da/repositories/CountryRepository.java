package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.CountryEntity;

public class CountryRepository extends BaseRepository<CountryEntity, Long> {

    public CountryRepository() {
        super(CountryEntity.class);
    }
}