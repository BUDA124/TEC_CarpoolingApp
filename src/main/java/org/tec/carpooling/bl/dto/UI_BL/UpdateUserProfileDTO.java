package org.tec.carpooling.bl.dto.UI_BL;

import jakarta.validation.constraints.*;
import java.util.List;

/**
 * Data Transfer Object for carrying user profile fields that can be updated.
 * Sent from the UI to the BL. Fields that are null are typically ignored during the update.
 */
public class UpdateUserProfileDTO {

    // Fields that can be updated. Most are optional.
    // Username is implicit for the logged-in user.
    // Institutional email, institution, birthdate, gender are typically not updatable or require special process.

    @Size(max = 50, message = "First name must not exceed 50 characters.")
    private String firstName; // Optional: if null, not updated

    @Size(max = 50, message = "Second name must not exceed 50 characters.")
    private String secondName; // Optional

    @Size(max = 50, message = "Last name must not exceed 50 characters.")
    private String firstSurname; // Optional

    @Size(max = 50, message = "Second surname must not exceed 50 characters.")
    private String secondSurname; // Optional

    @Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,63}$", message = "Invalid personal email format.")
    private String personalEmail; // Optional

    @Size(max = 100, message = "Contact information must not exceed 100 characters.")
    private String contactInfo; // Optional

    // Assuming photos are URLs.
    @Size(max = 5, message = "A maximum of 5 photo URLs can be provided.")
    private List<@NotBlank @org.hibernate.validator.constraints.URL String> photoUrls; // Optional

    private Boolean isDriver; // Optional
    private Boolean isPassenger; // Optional

    public UpdateUserProfileDTO() {}

    // Getters and Setters for all fields (omitted for brevity)
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    // ... and so on for all fields
    public String getSecondName() { return secondName; }
    public void setSecondName(String secondName) { this.secondName = secondName; }
    public String getFirstSurname() { return firstSurname; }
    public void setFirstSurname(String firstSurname) { this.firstSurname = firstSurname; }
    public String getSecondSurname() { return secondSurname; }
    public void setSecondSurname(String secondSurname) { this.secondSurname = secondSurname; }
    public String getPersonalEmail() { return personalEmail; }
    public void setPersonalEmail(String personalEmail) { this.personalEmail = personalEmail; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public List<String> getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; }
    public Boolean getIsDriver() { return isDriver; }
    public void setIsDriver(Boolean isDriver) { this.isDriver = isDriver; }
    public Boolean getIsPassenger() { return isPassenger; }
    public void setIsPassenger(Boolean isPassenger) { this.isPassenger = isPassenger; }
}