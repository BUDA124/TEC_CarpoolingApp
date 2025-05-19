package org.tec.carpooling.da.repositories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tec.carpooling.da.entities.InstitutionalEmailEntity;


@Repository
public interface InstitutionalEmailRepository extends JpaRepository<InstitutionalEmailEntity, Long> {}