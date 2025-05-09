package org.tec.carpooling.da.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripReportDailyReportId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "IDTRIP")
    private Long idTrip;

    @Column(name = "IDDAILYREPORT")
    private Long idDailyReport;

    public TripReportDailyReportId() {}

    public TripReportDailyReportId(Long idTrip, Long idDailyReport) {
        this.idTrip = idTrip;
        this.idDailyReport = idDailyReport;
    }

    // Getters and Setters
    public Long getIdTrip() { return idTrip; }
    public void setIdTrip(Long idTrip) { this.idTrip = idTrip; }
    public Long getIdDailyReport() { return idDailyReport; }
    public void setIdDailyReport(Long idDailyReport) { this.idDailyReport = idDailyReport; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripReportDailyReportId that = (TripReportDailyReportId) o;
        return Objects.equals(idTrip, that.idTrip) &&
                Objects.equals(idDailyReport, that.idDailyReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrip, idDailyReport);
    }
}