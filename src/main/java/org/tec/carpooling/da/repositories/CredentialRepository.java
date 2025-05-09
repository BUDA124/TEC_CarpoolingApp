package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.CredentialEntity;

public class CredentialRepository extends BaseRepository<CredentialEntity, Long> {

    public CredentialRepository() {
        super(CredentialEntity.class);
    }
}