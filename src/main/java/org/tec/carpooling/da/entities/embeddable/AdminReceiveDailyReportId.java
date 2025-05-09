package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdminReceiveDailyReportId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDADMINISTRATOR")
    private Long idAdministrator;

    @Column(name = "IDDAILYREPORT")
    private Long idDailyReport;

    public AdminReceiveDailyReportId() {}

    public AdminReceiveDailyReportId(Long idAdministrator, Long idDailyReport) {
        this.idAdministrator = idAdministrator;
        this.idDailyReport = idDailyReport;
    }

    // Getters and Setters
    public Long getIdAdministrator() { return idAdministrator; }
    public void setIdAdministrator(Long idAdministrator) { this.idAdministrator = idAdministrator; }
    public Long getIdDailyReport() { return idDailyReport; }
    public void setIdDailyReport(Long idDailyReport) { this.idDailyReport = idDailyReport; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminReceiveDailyReportId that = (AdminReceiveDailyReportId) o;
        return Objects.equals(idAdministrator, that.idAdministrator) &&
               Objects.equals(idDailyReport, that.idDailyReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdministrator, idDailyReport);
    }
}