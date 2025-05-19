package org.tec.carpooling.da.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.CredentialEntity;

import java.util.List;


@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {}