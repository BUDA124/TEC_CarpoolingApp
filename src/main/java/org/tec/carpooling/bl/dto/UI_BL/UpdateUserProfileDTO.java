package org.tec.carpooling.bl.dto.UI_BL;

import org.tec.carpooling.da.entities.GenderEntity;
import java.time.LocalDate;

// Este DTO transporta los datos del formulario para actualizar el perfil.
public class UpdateUserProfileDTO {

    // El username del usuario a actualizar, para identificarlo en el servicio.
    private String username;

    private String personalEmail;
    private String institutionalEmail;
    private GenderEntity selectedGender;
    private LocalDate birthdate;
    private String currentPassword; // Para validación
    private String newPassword;     // Opcional, si se cambia la contraseña

    // Constructor, Getters y Setters
    public UpdateUserProfileDTO(String username, String personalEmail, String institutionalEmail, GenderEntity selectedGender, LocalDate birthdate, String currentPassword, String newPassword) {
        this.username = username;
        this.personalEmail = personalEmail;
        this.institutionalEmail = institutionalEmail;
        this.selectedGender = selectedGender;
        this.birthdate = birthdate;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    // --- Getters ---
    public String getUsername() { return username; }
    public String getPersonalEmail() { return personalEmail; }
    public String getInstitutionalEmail() { return institutionalEmail; }
    public GenderEntity getSelectedGender() { return selectedGender; }
    public LocalDate getBirthdate() { return birthdate; }
    public String getCurrentPassword() { return currentPassword; }
    public String getNewPassword() { return newPassword; }

    public boolean isPasswordChangeRequested() {
        return currentPassword != null && !currentPassword.isEmpty() &&
                newPassword != null && !newPassword.isEmpty();
    }
}