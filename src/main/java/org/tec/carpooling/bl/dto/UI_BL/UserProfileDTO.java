package org.tec.carpooling.bl.dto;

import org.tec.carpooling.da.entities.GenderEntity;
import java.time.LocalDate;
import java.util.List;

// Este DTO transporta todos los datos necesarios para mostrar el perfil del usuario.
public class UserProfileDTO {

    private String fullName;
    private String idProfile;
    private String personalEmail;
    private String institutionalEmail;
    private LocalDate birthdate;
    private GenderEntity currentGender;
    private List<GenderEntity> allGenders;

    // Constructor, Getters y Setters

    public UserProfileDTO(String fullName, String idProfile, String personalEmail, String institutionalEmail, LocalDate birthdate, GenderEntity currentGender, List<GenderEntity> allGenders) {
        this.fullName = fullName;
        this.idProfile = idProfile;
        this.personalEmail = personalEmail;
        this.institutionalEmail = institutionalEmail;
        this.birthdate = birthdate;
        this.currentGender = currentGender;
        this.allGenders = allGenders;
    }

    // --- Getters ---
    public String getFullName() { return fullName; }
    public String getIdProfile() { return idProfile; }
    public String getPersonalEmail() { return personalEmail; }
    public String getInstitutionalEmail() { return institutionalEmail; }
    public LocalDate getBirthdate() { return birthdate; }
    public GenderEntity getCurrentGender() { return currentGender; }
    public List<GenderEntity> getAllGenders() { return allGenders; }
}