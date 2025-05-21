package org.tec.carpooling.da.entities;

import jakarta.persistence.*;
import org.tec.carpooling.da.entities.embeddable.AdminReceiveDailyReportId;
import java.util.Objects;

@Entity
@Table(name = "ADMINRECEIVEDAILYREPORT")
public class AdminReceiveDailyReportEntity implements Identifiable<AdminReceiveDailyReportId> {

    @EmbeddedId
    private AdminReceiveDailyReportId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAdministrator") // refers to AdminReceiveDailyReportId.idAdministrator
    @JoinColumn(name = "IDADMINISTRATOR", referencedColumnName = "IDPERSON", insertable = false, updatable = false)
    private AdministratorEntity administrator;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDailyReport")
    @JoinColumn(name = "IDDAILYREPORT", referencedColumnName = "ID", insertable = false, updatable = false)
    private DailyReportEntity dailyReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDAUDITLOG", nullable = false)
    private AuditLogEntity auditLog;

    public AdminReceiveDailyReportEntity() {}

    public AdminReceiveDailyReportEntity(AdministratorEntity administratorEntity, DailyReportEntity dailyReportEntity, AuditLogEntity auditLogEntity) {
    }

    // Getters and Setters
    public AdminReceiveDailyReportId getId() { return id; }
    public void setId(AdminReceiveDailyReportId id) { this.id = id; }
    public AdministratorEntity getAdministrator() { return administrator; }
    public void setAdministrator(AdministratorEntity administrator) { this.administrator = administrator; }
    public DailyReportEntity getDailyReport() { return dailyReport; }
    public void setDailyReport(DailyReportEntity dailyReport) { this.dailyReport = dailyReport; }
    public AuditLogEntity getAuditLog() { return auditLog; }
    public void setAuditLog(AuditLogEntity auditLog) { this.auditLog = auditLog; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminReceiveDailyReportEntity that = (AdminReceiveDailyReportEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AdminReceiveDailyReportEntity{" +
                "id=" + id +
                ", administratorId=" + (administrator != null ? administrator.getId() : null) +
                ", dailyReportId=" + (dailyReport != null ? dailyReport.getId() : null) +
                ", auditLogId=" + (auditLog != null ? auditLog.getId() : null) +
                '}';
    }
}