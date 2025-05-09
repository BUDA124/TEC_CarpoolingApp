package org.tec.carpooling.da.repositories;

import org.tec.carpooling.da.entities.UserStatusEntity;

public class UserStatusRepository extends BaseRepository<UserStatusEntity, Long> {

    public UserStatusRepository() {
        super(UserStatusEntity.class);
    }
}