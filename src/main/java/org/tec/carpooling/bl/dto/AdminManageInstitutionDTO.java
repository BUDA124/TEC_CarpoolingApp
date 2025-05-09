package org.tec.carpooling.bl.dto;

public class AdminManageInstitutionDTO {
    private AdministratorDTO administrator; // Represents idPerson from ADMINISTRATOR table
    private InstitutionDTO institution;
    private AuditLogDTO auditLog;

    public AdminManageInstitutionDTO() {
    }

    // Getters and Setters
    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public InstitutionDTO getInstitution() {
        return institution;
    }

    public void setInstitution(InstitutionDTO institution) {
        this.institution = institution;
    }

    public AuditLogDTO getAuditLog() {
        return auditLog;
    }

    public void setAuditLog(AuditLogDTO auditLog) {
        this.auditLog = auditLog;
    }
}