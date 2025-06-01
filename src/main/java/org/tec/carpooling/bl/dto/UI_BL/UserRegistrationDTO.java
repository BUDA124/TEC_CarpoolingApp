package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.*;
import org.tec.carpooling.bl.validators.constraints.MinimumAge;
import org.tec.carpooling.bl.validators.constraints.PasswordComplexity;
import org.tec.carpooling.bl.validators.constraints.ValidInstitutionalEmail;
import org.tec.carpooling.da.entities.CountryEntity;
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.InstitutionEntity;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;

import java.time.LocalDate;

/**
 * Data Transfer Object for carrying all necessary information from the UI to the BL
 * for registering a new user, including personal details, contact info, credentials,
 * institutional affiliation, and role preferences.
 */
public class UserRegistrationDTO {

    @NotBlank(message = "First name is required.")
    @Size(max = 50, message = "First name must not exceed 50 characters.")
    private String firstName;

    @Size(max = 50, message = "First name must not exceed 50 characters.")
    private String secondName;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name must not exceed 50 characters.")
    private String firstSurname;

    @Size(max = 50, message = "Last name must not exceed 50 characters.")
    private String secondSurname;

    @NotBlank(message = "Email is required.")
    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$")
    private String email;

    @NotBlank(message = "Institutional email is required.")
    @ValidInstitutionalEmail
    @Size(max = 254, message = "Email must not exceed 254 characters.") // Common max length
    private String institutionalEmail;

    @NotNull(message = "Birthdate is required.")
    @Past(message = "Birthdate must be in the past.")
    @MinimumAge(value = 16, message = "User must be at least 16 years old.")
    private LocalDate birthdate;

    @NotBlank(message = "Nationality is required.")
    @Size(max = 50, message = "Nationality must not exceed 50 characters.")
    private CountryEntity nationality;

    @NotBlank(message = "Credential number is required.")
    @Size(min = 3, max = 30, message = "Credential number must be between 6 and 30 characters.")
    private String credentialNumber;

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.")
    private String username;

    @NotBlank(message = "Password is required.")
    @PasswordComplexity
    private String password;

    @NotNull(message = "Institution ID is required.")
    @Positive(message = "Institution ID must be a positive number.")
    private InstitutionEntity idInstitution;

    @NotNull(message = "Gender ID is required.")
    @Positive(message = "Gender ID must be a positive number.")
    private GenderEntity idGender;

    @NotNull(message = "Credential ID is required.")
    @Positive(message = "Credential ID must be a positive number.")
    private TypeOfCredentialEntity idTypeOfCredential;

    public UserRegistrationDTO() {}

    public @NotBlank(message = "First name is required.") @Size(max = 50, message = "First name must not exceed 50 characters.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name is required.") @Size(max = 50, message = "First name must not exceed 50 characters.") String firstName) {
        this.firstName = firstName;
    }

    public @Size(max = 50, message = "First name must not exceed 50 characters.") String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Size(max = 50, message = "First name must not exceed 50 characters.") String secondName) {
        this.secondName = secondName;
    }

    public @NotBlank(message = "Last name is required.") @Size(max = 50, message = "Last name must not exceed 50 characters.") String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(@NotBlank(message = "Last name is required.") @Size(max = 50, message = "Last name must not exceed 50 characters.") String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public @Size(max = 50, message = "Last name must not exceed 50 characters.") String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(@Size(max = 50, message = "Last name must not exceed 50 characters.") String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public @NotBlank(message = "Email is required.") @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required.") @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Institutional email is required.") @Size(max = 254, message = "Email must not exceed 254 characters.") String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(@NotBlank(message = "Institutional email is required.") @Size(max = 254, message = "Email must not exceed 254 characters.") String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public @NotNull(message = "Birthdate is required.") @Past(message = "Birthdate must be in the past.") LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull(message = "Birthdate is required.") @Past(message = "Birthdate must be in the past.") LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public @NotBlank(message = "Nationality is required.") @Size(max = 50, message = "Nationality must not exceed 50 characters.") CountryEntity getNationality() {
        return nationality;
    }

    public void setNationality(@NotBlank(message = "Nationality is required.") @Size(max = 50, message = "Nationality must not exceed 50 characters.") CountryEntity nationality) {
        this.nationality = nationality;
    }

    public @NotBlank(message = "Credential number is required.") @Size(min = 3, max = 30, message = "Credential number must be between 6 and 30 characters.") String getCredentialNumber() {
        return credentialNumber;
    }

    public void setCredentialNumber(@NotBlank(message = "Credential number is required.") @Size(min = 3, max = 30, message = "Credential number must be between 6 and 30 characters.") String credentialNumber) {
        this.credentialNumber = credentialNumber;
    }

    public @NotBlank(message = "Username is required.") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.") @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username is required.") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.") @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "Username can only contain alphanumeric characters, underscores, dots, or hyphens.") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required.") String password) {
        this.password = password;
    }

    public @NotNull(message = "Institution ID is required.") @Positive(message = "Institution ID must be a positive number.") InstitutionEntity getIdInstitution() {
        return idInstitution;
    }

    public void setIdInstitution(@NotNull(message = "Institution ID is required.") @Positive(message = "Institution ID must be a positive number.") InstitutionEntity idInstitution) {
        this.idInstitution = idInstitution;
    }

    public @NotNull(message = "Gender ID is required.") @Positive(message = "Gender ID must be a positive number.") GenderEntity getIdGender() {
        return idGender;
    }

    public void setIdGender(@NotNull(message = "Gender ID is required.") @Positive(message = "Gender ID must be a positive number.") GenderEntity idGender) {
        this.idGender = idGender;
    }

    public @NotNull(message = "Credential ID is required.") @Positive(message = "Credential ID must be a positive number.") TypeOfCredentialEntity getIdTypeOfCredential() {
        return idTypeOfCredential;
    }

    public void setIdTypeOfCredential(@NotNull(message = "Credential ID is required.") @Positive(message = "Credential ID must be a positive number.") TypeOfCredentialEntity idTypeOfCredential) {
        this.idTypeOfCredential = idTypeOfCredential;
    }
}