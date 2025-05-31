package org.tec.carpooling.bl.dto.UI_BL;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for carrying comprehensive user profile information from the BL
 * to the UI for display. This includes personal details, contact info, calculated age,
 * role preferences, and registration status.
 */
public class UserProfileDTO {

    private String username;
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private String personalEmail;
    private String institutionalEmail;
    private String contactInfo;
    private String identificationType;
    private String idNumber;
    private LocalDate birthdate;
    private Integer age; // Calculated
    private String gender; // Gender name, not ID
    private String institutionName;
    private List<String> photoUrls;
    private LocalDate registrationDate;
    private boolean termsAccepted;
    private Boolean isDriver;
    private Boolean isPassenger;


    public UserProfileDTO() {}

    // Getters and Setters for all fields (omitted for brevity)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
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
    public String getInstitutionalEmail() { return institutionalEmail; }
    public void setInstitutionalEmail(String institutionalEmail) { this.institutionalEmail = institutionalEmail; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public String getIdentificationType() { return identificationType; }
    public void setIdentificationType(String identificationType) { this.identificationType = identificationType; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }
    public List<String> getPhotoUrls() { return photoUrls; }
    public void setPhotoUrls(List<String> photoUrls) { this.photoUrls = photoUrls; }
    public Boolean getIsDriver() { return isDriver; }
    public void setIsDriver(Boolean isDriver) { this.isDriver = isDriver; }
    public Boolean getIsPassenger() { return isPassenger; }
    public void setIsPassenger(Boolean isPassenger) { this.isPassenger = isPassenger; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public boolean isTermsAccepted() { return termsAccepted; }
    public void setTermsAccepted(boolean termsAccepted) { this.termsAccepted = termsAccepted; }
}