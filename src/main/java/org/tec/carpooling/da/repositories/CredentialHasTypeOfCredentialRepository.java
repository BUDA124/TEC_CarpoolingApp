package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.CredentialHasTypeOfCredentialEntity;
import org.tec.carpooling.da.entities.embeddable.CredentialHasTypeOfCredentialId; // Assuming this PK class exists

public class CredentialHasTypeOfCredentialRepository extends BaseRepository<CredentialHasTypeOfCredentialEntity, CredentialHasTypeOfCredentialId> {

    public CredentialHasTypeOfCredentialRepository() {
        super(CredentialHasTypeOfCredentialEntity.class);
    }
}