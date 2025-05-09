package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.TypeOfCredentialEntity;

public class TypeOfCredentialRepository extends BaseRepository<TypeOfCredentialEntity, Long> {

    public TypeOfCredentialRepository() {
        super(TypeOfCredentialEntity.class);
    }
}