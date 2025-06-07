package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.AdminManageInstitutionId;
import java.util.Objects;

@Entity
@Table(name = "ADMINMANAGEINSTITUTION")
public class AdminManageInstitutionEntity implements Identifiable<AdminManageInstitutionId> {

    @EmbeddedId
    private AdminManageInstitutionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPerson")
    @JoinColumn(name = "IDPERSON", referencedColumnName = "IDPERSON", insertable = false, updatable = false)
    private AdministratorEntity administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idInstitution")
    @JoinColumn(name = "IDINSTITUTION", referencedColumnName = "ID", insertable = false, updatable = false)
    private InstitutionEntity institution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public AdminManageInstitutionEntity() {}

    public AdminManageInstitutionEntity(AdministratorEntity administrator, InstitutionEntity institution, AuditLogEntity auditLogEntity) {
        this.id = new AdminManageInstitutionId();
        this.administrator = administrator;
        this.institution = institution;
        this.auditLog = auditLogEntity;
    }

    // Getters and Setters
    public AdminManageInstitutionId getId() { return id; }
    public void setId(AdminManageInstitutionId id) { this.id = id; }
    public AdministratorEntity getAdministrator() { return administrator; }
    public void setAdministrator(AdministratorEntity administrator) { this.administrator = administrator; }
    public InstitutionEntity getInstitution() { return institution; }
    public void setInstitution(InstitutionEntity institution) { this.institution = institution; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminManageInstitutionEntity that = (AdminManageInstitutionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "AdminManageInstitutionEntity{" +
                "id=" + id +
                ", administratorId=" + (administrator != null ? administrator.getId() : null) +
                ", institutionId=" + (institution != null ? institution.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}