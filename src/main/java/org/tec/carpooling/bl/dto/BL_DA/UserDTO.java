package org.tec.carpooling.bl.dto.BL_DA;

import org.tec.carpooling.da.entities.InstitutionEntity;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private InstitutionEntity idInstitution;

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public InstitutionEntity getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(InstitutionEntity idInstitution) {
        this.idInstitution = idInstitution;
    }
}