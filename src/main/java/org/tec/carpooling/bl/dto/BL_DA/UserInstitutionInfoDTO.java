package org.tec.carpooling.bl.dto.BL_DA;

import org.tec.carpooling.da.entities.InstitutionEntity;

/**
 * Data Transfer Object for carrying basic user institution information (username, institution ID,
 * and institution name) between the Business Logic (BL) and Data Access (DA) layers,
 * or for internal BL use.
 */
public class UserInstitutionInfoDTO {
    private String username;
    private InstitutionEntity institutionId;
    private String institutionName;

    public UserInstitutionInfoDTO() {}

    public UserInstitutionInfoDTO(String username, InstitutionEntity institutionId, String institutionName) {
        this.username = username;
        this.institutionId = institutionId;
        this.institutionName = institutionName;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public InstitutionEntity getInstitutionId() { return institutionId; }
    public void setInstitutionId(InstitutionEntity institutionId) { this.institutionId = institutionId; }
    public String getInstitutionName() { return institutionName; }
    public void setInstitutionName(String institutionName) { this.institutionName = institutionName; }
}